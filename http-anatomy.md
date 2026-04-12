# HTTP İsteğinin Anatomisi

## HTTP Nedir?

HTTP (Hyper Text Transfer Protocol), istemci (client) ile sunucu (server) arasında veri alışverişini sağlamak amacıyla kullanılan bir iletişim protokolüdür.

İstemci, sunucuya bir istek (request) gönderir. Sunucu bu isteği değerlendirir ve sonucunda istemciye bir cevap (response) iletir.


## HTTP Anatomi

HTTP yapısını daha iyi anlamak için aşağıdaki örnek incelenebilir:
![alt text](image.png)

GET https://www.server.com/api/orders.json?created_at=2022-01-18T19:08:48+00:00&limit=10


## 1. Headers

Headers (başlıklar), HTTP isteği veya yanıtı hakkında ek bilgiler taşır. Bu bilgiler genellikle key-value (anahtar-değer) çiftleri şeklindedir.

Örnek:
        / HTTP/1.1
        Host: www.server.com
        authorization: Basic Yjw2WU57IogGNiZzgx0DhjZwBk0WIwZjdjZTY=   

## 2. Method

HTTP metodları, istemcinin sunucuya hangi türde bir işlem yapmak istediğini belirtir.

- GET → Sunucudan veri çekmek için kullanılır
- POST → Sunucuya yeni veri göndermek için kullanılır 
- PUT → Var olan bir veriyi güncellemek için kullanılır  
- DELETE → Veriyi silmek için kullanılır


## 2. Scheme

URL’in başındaki kısmıdır.

- http:// → güvenli değil  
- https:// → güvenli (şifrelenmiş)

“S” harfi **secure** anlamına gelir. Yani http ile başlayan web sitelerine güven konusunu daha hassas tutmalıyız.

## 3. Server (Host)

Sunucu istemcinin gönderdiği talepleri işleyen ve istemcilere yanıtları gönderen bilgisayar veya sistemdir.

Örnek: (www.server.com)


## 4. Path

Sunucu üzerindeki kaynağın konumunu belirtir. Örneğin “/index.html” . Web sitenin adresinin belirten yol diyebiliriz.
Örnek: (api/orders.json)


## 5. URL

Bir kaynağın internet üzerindeki adresidir.

## 6. Query String

URL sonunda `?` ile başlayan ve ek verileri taşıyan ve yine key-value çiftelerini içeren bir dizedir. Örnek bir sorgu http nedir diye google üzerinde arattığımızda aslında aşağıdaki gibi tanımlandığını browser alanında görebilirsiniz. “?q=” aramanız bu şekilde görüntülenir.


## Status Code

Server’dan dönen yanıtın durumunu belirtir.

100- Bilgilendirme amaçlı kodlar

200- Başarılı işlemler

300- Yönlendirme durumları

400- istemci hatalarından kaynaklanır.

500- sunucu kaynaklı hatalardır.


## Kaynakça
https://medium.com/@ozgeakinci/http-nedir-nasıl-çalışır-anatomisi-nasıldır-a288a1768996