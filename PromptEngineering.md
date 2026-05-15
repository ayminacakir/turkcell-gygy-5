# Prompt Engineering & AI Native Developer — Ders Notları

---

## 1. Software Developer → AI Native Developer

Geleneksel geliştirici iyi kod yazan kişiydi.  
**AI Native Developer** ise buna ek olarak:

- AI araçlarını etkili kullanır
- Prompt Engineering mantığını bilir
- Hangi iş için hangi modeli seçeceğini bilir
- AI çıktısını kontrol eder ve onaylar
- Kod + analiz + ürün düşüncesi + DevOps + AI governance'ı birlikte düşünür

> Araçlar insana göre **10x hızlı** — ama sorumlu kullanan insan olmadan bu hız anlamsız.

---

## 2. AI Native Developer'ın 4 Temel Yetkinliği

```
Consistency + Security + Speed + Cost = Efficiency
```

### Consistency (Tutarlılık)
6 farklı PC'den aynı ya da yakın prompt girildiğinde çıktının birbirinden aşırı **farklılaşmaması** gerekir.  
İyi prompt = tek seferlik değil, **tekrar edilebilir** doğru cevap.

### Security (Güvenlik)
LLM milyonlarca satır veriyle eğitildi — bu veride **SQL injection'a açık kodlar da var.**  
AI çıktısı doğrudan güvenli kabul edilemez. Dikkat edilmesi gerekenler:

- SQL Injection
- Hardcoded password / secret
- Eksik input validation
- Yetkilendirme hataları
- Güvensiz API kullanımı
- Eski / önerilmeyen kütüphaneler

> **Yapay zekâ sürekli kontrol edilebilmeli. Son sorumluluk insandadır.**

### Speed (Hız)
Hız = sadece prompt yazıp cevap almak değil.

```
Doğru context + doğru model = hızlı ve kaliteli çıktı
```

Sorulması gereken sorular:
- Bu iş için hızlı mı, derin düşünen mi model lazım?
- Anlık cevap mı, arka planda analiz mi?
- Modele ne kadar bağlam verilmeli?

### Cost (Maliyet)
Her iş için en güçlü modeli kullanmak yanlış.

| İş | Model Tercihi |
|---|---|
| Basit dönüşüm / kısa metin | Hızlı, uygun maliyetli model |
| Uzun analiz / detaylı özet | Güçlü model (Opus vb.) |
| Doğrulama gereken bilgi | RAG + web search |

Örnek: `docx → pdf` dönüşümü için Opus 4 değil, daha hafif model yeterli.

---

## 3. AI Geldi → Sorumluluklar Arttı

```
Geleneksel efor: 40 saat
  - 10 saat StackOverflow research
  - 10 saat kodlama
  - ...

AI ile: 30 saat → -28 saat → 2 saate düşebilir
```

**Ama dikkat:** AI hızlandırdı, sorumluluğu azaltmadı.

Eskiden: `Teknik Analiz → Kod`

Şimdi:
```
Analizi yapan + kodu yazan + product thinker + devops bilen + AI governance düşünen
```

---

## 4. Human Generated vs AI Generated

```
Human Generated → Human Approved
AI Generated    → Human Approved
```

AI çıktı üretir. İnsan kontrol eder. İnsan onaylar.  
**Sorumluluk her zaman insandadır.**

---

## 5. LLM Nasıl Çalışır?

```
LLM → Verdiğin kelimelerden anlam çıkarır
    → O kelimelere karşılık en yakın anlama göre bilgi hazinesinden eşleşme bulur
```

- **Genel / belirsiz prompt** → Genel geçer, detaysız, kalitesiz çıktı
- **Net / bağlamlı prompt** → Doğru, kaliteli, kontrollü çıktı

---

## 6. CRAFT Framework

İyi prompt yazmak için kullanılan yapı:

```
C - Context     → Bağlam
R - Role        → Rol
A - Action      → Aksiyon
F - Format      → Çıktı Formatı
T - Tone        → Ton / Hedef Kitle
```

---

### C — Context (Bağlam)
Modele durumu, seviyeni ve sınırları anlat.

```
Bir Spring Boot geliştirme kursundayım.
Temel Java ve Spring Boot bilgisine sahibim.
Ancak Auth ve JWT konusu tam oturmadı.
Bu konuyu anlamak için kaynak oluşturmaya çalışıyorum.
Bu bağlamda action'da istenilenin dışına çıkmak yasak.
```

---

### R — Role (Rol)
Modelin hangi uzman kimliğiyle cevap vereceğini belirt.

```
Sen 10 yıllık bir Java Spring geliştiricisi ve danışmanısın.
Aktif olarak bu konuda eğitim veriyorsun.
Gerçek hayattan örnekler vermeyi seven bir eğitmensin.
Önce teoriyi sonra pratiği anlatıyorsun.
```

---

### A — Action (Aksiyon)
Ne yapmasını istediğini net söyle.

```
Var olan bir Spring projesi üzerinde sıfırdan açıklamalar yaparak
JWT ile Auth sistemi kur.
Kurarken her aksiyonda kullanıcıya nedenini açıkla.
```

---

### F — Format (Çıktı Formatı)
Cevabın nasıl görünmesini istediğini belirt.

```
- Her alt konuyu önce 2-3 paragraf kavramsal açıkla
- Her implementasyonda 1. adım, 2. adım diye sırayla git
- En altta bu konuda sık yapılan 5 hata ve çözümünü ver
- Emoji kullanma, resmi ol
```

---

### T — Tone / Target Audience (Ton & Hedef Kitle)
Üslubu ve kitleyi tanımla.

```
Hedef Kitle: Junior Spring Geliştiriciler
Ton: Samimi ama teknik, resmiyetten ödün vermeyen bir ton kullan.
Örnekleri net, somut ve gerçek hayat örnekleri olarak ver.
```

---

## 7. Kötü Prompt vs İyi Prompt

| Kötü Prompt | İyi Prompt |
|---|---|
| `Bana Spring'de JWT anlat.` | CRAFT ile yazılmış tam prompt |
| Genel, belirsiz | Net, bağlamlı |
| Tek seferlik | Tekrar edilebilir |
| Kontrol yok | Format ve ton belirli |

---

## 8. Token Mantığı

```
Input token  → Modele gönderdiğin prompt + context
Output token → Modelin ürettiği cevap
Inference    → Modelin cevap üretme süreci
```

> Gereksiz uzun prompt → maliyet artar  
> Eksik prompt → kalite düşer  
> **İyi prompt = yeterli ama gereksiz uzun olmayan prompt**

---

## 9. RAG Mantığı

Model sadece kendi eğitim bilgisinden değil, **dış kaynaklardan** da bilgi alarak cevap üretir.

```
Model cevap üretir → RAG dış kaynak getirir → Web Search doğrular → İnsan kontrol eder
```

RAG kullanım amaçları:
- Halüsinasyon riskini azaltmak
- Güncel bilgiye erişmek
- Çıktıyı doğrulanabilir yapmak

---

## 10. Akılda Kalacak Cümleler

```
Software Developer → AI Native Developer
```
```
AI Generated → Human Approved
```
```
Consistency + Security + Speed + Cost = Efficiency
```
```
AI hızlı üretir, insan kontrol eder.
```
```
Prompt ne kadar netse, çıktı o kadar kaliteli olur.
```
```
Context eksikse model tahmin eder. Context netse model yönlendirilir.
```
```
Doğru model + doğru context + insan kontrolü = kaliteli AI kullanımı
```
```
Gereksiz uzun prompt maliyeti artırır, eksik prompt kaliteyi düşürür.
```
