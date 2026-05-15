## Ödev: Add JWT role-based authorization behavior 

### Uygulama Görevleri

1. **JWT ve UserContext yapısına rol mantığı eklenecek**
   - Her request optionally belirli roller gerektirebilir
   - Eğer requiredRoles listesi dolu ise, bu roller her request için kontrol edilmeli

2. **Special Auth Exceptions oluşturulacak**
   - `AuthenticatedException` - kimlik doğrulama (authentication) başarısızsa
   - `AuthorizationException` - yetkilendirme (authorization) başarısızsa
   - Bu exception'lar generic `RuntimeException`'dan ayrı olmalı

3. **Custom Exception Handling**
   - Auth exception'lar için custom exception handler yazılmalı
   - HTTP status kodları şu şekilde dönmeli:
     - `401 Unauthorized` -> authentication failed
     - `403 Forbidden` -> authorization failed

---

##  Notlar

### JWT Token Yapısı
JWT token 3 part'tan oluşur: `HEADER.PAYLOAD.SIGNATURE`

#### Header
- Token type ve algorithm bilgisi taşır
- Örnek: `{ "alg": "HS256", "typ": "JWT" }`

#### Payload
- User claims ve metadata içerir
- Örnek fields: `sub`, `email`, `roles`, `exp`

#### Signature
- Token'ın integrity'sini sağlar
- Header + Payload + secret ile oluşturulur

---

## Architectural Components

### JwtService
- Token üretir (`generateToken`) ve parse eder (`parseToken`)
- Claim extraction yapar
- Token validation logic burada yer alabilir

### JwtAuthFilter
- Every request'te `Authorization` header'ını okur
- Bearer token varsa JWT'yi parse ve validate eder
- Valid token ise UserContext'i doldurur

### AuthorizationBehavior
- Bu component handler/doman logic çalışmadan önce devreye girer
- Kullanıcının authenticated olduğunu kontrol eder
- Eğer request requiredRoles belirtiyorsa, roller kontrol edilir

### UserContext
- Current user information için merkezi bir konteyner
- JwtAuthFilter token'dan userId/email/roles alır ve UserContext'e yazar
- Business logic/handler'lar UserContext üzerinden current user'ı okuyabilir

---

## Temel Kavramlar

### Claims Çıkarma
- `claims.getSubject()` - claims nesnesinden subject alanını alır
- Token'da depolanan kullanıcıya özgü bilgileri almak için kullanılır

### Genel Tip İşleme (`<T>`)
- Farklı tipler döndürebilen metotlarda kullanılır
- Örnek: `userId` `String` döner, `expiration` `Date` döner

### Token Ayrıştırma
- JWT token'ı okunabilir hale getirme işlemi
- Claims çıkarma ve doğrulamaya izin verir

### Authorization Başlığı
- Frontend, JWT token'ı HTTP başlıkları aracılığıyla gönderir
- Standart format: `Authorization: Bearer {jwt_token}`
- Sunucu tarafında alınması: `request.getHeader("Authorization")`

---

## Korumalı Kaynaklar Örneği

### GetAllCategoriesQuery - Korumalı Endpoint
```java
public record GetAllCategoriesQuery(int pageNumber, int pageSize)
    implements Query<Page<GetAllCategoriesResponse>>, AuthorizableRequest {
}

public interface AuthorizableRequest {
    // Varsayılan olarak belirli rol gerektirmez
    // Sadece giriş yapılmış olmak yeterlidir
    default List<String> requiredRoles() {
        return List.of();
    }
}
```
- Bu sorgu, kimlik doğrulama gerektiren bir kaynağı işaretlemek için `AuthorizableRequest`'i uygular
- Gerekirse belirli roller gerektirmek için genişletilebilir
- Varsayılan davranış: Sadece geçerli JWT gereklidir (giriş yapılması gereklidir)
