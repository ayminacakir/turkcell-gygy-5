# Java Primitive vs Wrapper Tipler

Java'da her primitive tipin bir **Wrapper (sarmalayıcı)** sınıfı vardır.

---

## Tüm Primitive — Wrapper Eşleşmeleri

| Primitive | Wrapper |
|---|---|
| `boolean` | `Boolean` |
| `int` | `Integer` |
| `double` | `Double` |
| `float` | `Float` |
| `long` | `Long` |
| `short` | `Short` |
| `byte` | `Byte` |
| `char` | `Character` |

---

## Temel Farklar

| Özellik | Primitive | Wrapper |
|---|---|---|
| Bellek | Stack'te, hafif | Heap'te, ağır |
| Null olabilir mi? | ❌ Hayır | ✅ Evet |
| Default değer | `false` / `0` | `null` |
| Koleksiyonlarda kullanım | ❌ Kullanılamaz | ✅ Kullanılabilir |
| Performans | ✅ Daha hızlı | ⚠️ Daha yavaş |

---

## Ne Zaman Hangisi?

### Primitive Kullan
Değer **kesinlikle mevcutsa** ve null ihtimali yoksa:

```java
// Motorun intercom'u ya var ya yok — null anlamsız
private boolean hasIntercom;

// Kullanıcının yaşı her zaman bir sayı
private int age;
```

### Wrapper Kullan
Değer **bilinmiyor olabiliyorsa** veya null gelebiliyorsa:

```java
// Veritabanında NULL gelebilir
private Boolean hasIntercom;

// Kullanıcı yaşını girmemiş olabilir
private Integer age;
```

---

## Dikkat: NullPointerException Tuzağı

```java
// ❌ Tehlikeli — DB'den null gelirse NullPointerException!
private boolean hasIntercom;  // null atanamaz ama DB'den null gelebilir

// ✅ Güvenli
private Boolean hasIntercom;  // null, true veya false olabilir

if (bike.getHasIntercom() != null && bike.getHasIntercom()) {
    // güvenli kullanım
}
```

---

## Koleksiyonlarda Wrapper Zorunlu

```java
// ❌ Derleme hatası — primitive koleksiyonda kullanılamaz
List<int> numbers;

// ✅ Doğru
List<Integer> numbers;
Map<String, Boolean> flags;
```

---

## Autoboxing & Unboxing

Java, primitive ve Wrapper arasında **otomatik dönüşüm** yapar:

```java
// Autoboxing — primitive → Wrapper (otomatik)
Integer sayi = 42;       // aslında Integer.valueOf(42)

// Unboxing — Wrapper → primitive (otomatik)
int x = sayi;            // aslında sayi.intValue()
```

⚠️ Ama dikkat:
```java
Integer sayi = null;
int x = sayi;  // NullPointerException! — null unbox edilemez
```

---

## Genel Kural

| Durum | Tercih |
|---|---|
| Normal değişken, null ihtimali yok | **Primitive** |
| Veritabanı alanı (NULL olabilir) | **Wrapper** |
| API response / request alanı | **Wrapper** |
| Koleksiyon içinde kullanım | **Wrapper** (zorunlu) |
| Performans kritik döngüler | **Primitive** |

> 💡 **Özet:** Null ihtimali yoksa primitive, null gelebiliyorsa (DB, API, opsiyonel alan) Wrapper kullan.
