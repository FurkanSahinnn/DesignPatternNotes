## Observer Pattern:
Bir object'in durumundaki değişikliklerin, onu gözlemleyen diğer object'lere otomatik olarak bildirilmesine denir.

### Temel Kavramlar:
1. **Subject:** Durumu değişince, durumunun değiştiğini observer'lara bildiren object.
2. **Observer:** Subject'in durumunu izleyen ve değişikliklerinden haberdar olmak isteyen object.

### Kullanım Sebebi:
* Object'ler arasında **loose coupling** sağlar.
* Bir object'in state'i değiştiğinde, observer'lara haber verilir.
* Yeni bir observer eklemek kolaydır. (OCR prensibi)

### Hava Durumu Örnek Senaryo:
Hava durumundaki değişiklikleri dinleyen 3 tane display class'ı olsun.

Bu class'lara concrete observer class'ı denir:
1. **CurrentConditionsDisplay:** Anlık değerleri gösterir.
2. **StatisticsDisplay:** İstatistik tutar.
3. **ForecastDisplay:** Tahmin yapar.

Bu class'ların amacı subject'in durumundaki değişiklikleri dinlemektir.

iki tane interface'e ihtiyaç vardır:
1. **Observer:** Bu interface'in amacı concrete observer class'larına <code>update()</code> method'unu sağlamaktır.
2. **Subject:** Bu interface'in amacı ise concrete subject class'lara,
   1. **registerObserver():** Observer'ın subject'e abone olmasını sağlar. 
   2. **removeObserver():** Subject'e abone olan observer'ı siler.
   3. **notifyObserver():** Kendisine abone olan observer'lara, state'inin değiştiğine dair bildirim gönderir.
   yukarıdaki method'ları sağlamaktır.
