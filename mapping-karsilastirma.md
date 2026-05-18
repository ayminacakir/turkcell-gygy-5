# Java Object Mapping Yöntemleri Karşılaştırması

Aşağıdaki örneklerde hep aynı senaryo kullanılıyor:  
`Category` → `CreatedCategoryResponse` dönüşümü.

```java
// Kaynak sınıf
public class Category {
    private UUID id;
    private String name;
}

// Hedef sınıf
public class CreatedCategoryResponse {
    private UUID id;
    private String name;
}
```

---

## 1. Manuel Mapping

Elle her alanı tek tek set ediyorsun.

```java
@Component
public class CategoryMapper {

    public CreatedCategoryResponse toResponse(Category category) {
        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }
}
```

**Kullanım:**
```java
CreatedCategoryResponse response = categoryMapper.toResponse(category);
```

✅ Tam kontrol — her alanı kendin yönetirsin  
✅ Hata derleme anında yakalanır  
✅ Bağımlılık gerekmez  
❌ Alan sayısı arttıkça kod şişer (30 alan = 30 satır setter)  
❌ Yeni alan eklenince mapper güncellemeyi unutabilirsin  

---

## 2. MapStruct

Interface yazıyorsun, MapStruct derleme anında implementasyonu üretiyor.

**Dependency (pom.xml):**
```xml
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.5.5.Final</version>
</dependency>
```

```java
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // Alan adları aynıysa bu kadar yeterli!
    CreatedCategoryResponse toResponse(Category category);
}
```

Alan adları farklıysa:
```java
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "fullName", target = "name")
    CreatedCategoryResponse toResponse(Category category);
}
```

**Kullanım:**
```java
@Autowired
CategoryMapper categoryMapper;

CreatedCategoryResponse response = categoryMapper.toResponse(category);
```

✅ En hızlı yöntem (compile-time kod üretimi, reflection yok)  
✅ Az kod — tek satır yeterli  
✅ Hata derleme anında yakalanır  
✅ Alan adı farklıysa `@Mapping` ile kolayca çözülür  
❌ Ek bağımlılık gerekir  
❌ Öğrenme eğrisi biraz dik  

---

## 3. ModelMapper

Reflection kullanarak runtime'da alanları otomatik eşleştirir.

**Dependency (pom.xml):**
```xml
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.1.1</version>
</dependency>
```

```java
@Component
public class CategoryMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public CreatedCategoryResponse toResponse(Category category) {
        return modelMapper.map(category, CreatedCategoryResponse.class);
    }
}
```

Alan adları farklıysa:
```java
modelMapper.typeMap(Category.class, CreatedCategoryResponse.class)
           .addMappings(mapper -> mapper.map(Category::getFullName,
                                             CreatedCategoryResponse::setName));
```

**Kullanım:**
```java
CreatedCategoryResponse response = categoryMapper.toResponse(category);
```

✅ Çok az kod  
✅ Konfigürasyon az  
❌ Runtime'da çalıştığı için MapStruct'tan yavaş  
❌ Hatalar runtime'da ortaya çıkar  
❌ Ek bağımlılık gerekir  

---

## 4. ObjectMapper (Jackson)

Asıl amacı JSON dönüşümü ama `convertValue` ile object mapping de yapılabilir.  
Spring Boot'ta **zaten mevcut**, ekstra bağımlılık gerekmez.

```java
@Component
public class CategoryMapper {

    private final ObjectMapper objectMapper;

    public CategoryMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CreatedCategoryResponse toResponse(Category category) {
        return objectMapper.convertValue(category, CreatedCategoryResponse.class);
    }
}
```

**Kullanım:**
```java
CreatedCategoryResponse response = categoryMapper.toResponse(category);
```

✅ Spring Boot'ta zaten var, ekstra bağımlılık gerekmez  
✅ Alan adları aynıysa tek satır yeter  
⚠️ Mapping onun asıl amacı değil — yan kullanım  
❌ Reflection tabanlı, runtime'da çalışır  
❌ Alan adı farklıysa `@JsonProperty` ile uğraşmak gerekir  

---

## Genel Karşılaştırma Tablosu

| Özellik | Manuel | MapStruct | ModelMapper | ObjectMapper |
|---|---|---|---|---|
| **Hız** | ✅ Hızlı | ✅ En hızlı | ⚠️ Yavaş | ⚠️ Orta |
| **Kod miktarı** | ❌ Çok fazla | ✅ Az | ✅ Az | ✅ Az |
| **Hata yakalama** | Compile-time | ✅ Compile-time | Runtime | Runtime |
| **Ek bağımlılık** | ❌ Yok | Gerekir | Gerekir | ❌ Yok (Spring'de var) |
| **Asıl amacı** | — | ✅ Object mapping | ✅ Object mapping | JSON dönüşümü |
| **Alan adı farklıysa** | Elle yazarsın | `@Mapping` ile | TypeMap ile | `@JsonProperty` ile |

---

## Ne Zaman Hangisi?

| Durum | Öneri |
|---|---|
| Az alan, basit proje | **Manuel** veya **ObjectMapper** |
| Performans kritik, büyük proje | **MapStruct** |
| Hızlı prototip, alan adları aynı | **ModelMapper** veya **ObjectMapper** |
| Spring Boot projesi, ek bağımlılık istemiyorum | **ObjectMapper** |
| Sektör standardı, production | **MapStruct** |

> 💡 **Sektörde en yaygın tercih MapStruct'tır.** Alan adları birebir aynıysa tek satır interface metodu yeterli — 30 alanlı class için manuel mapping'in önüne geçer.
