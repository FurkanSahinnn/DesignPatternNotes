## Stategy Pattern:
En kaba tanımıyla, bir algoritma ailesindeki her bir algoritmanın birbiri yerine kullanılmasını sağlayan bir pattern'dır.
Mesela bir e-ticaret sitesinde ödeme yaparken müşterilerin kredi kartı, banka kartı, kapıda ödeme gibi seçeneklerden birin
seçerek ödeme yapması gibi düşünülebilir. Tüm ödeme yöntemleri birbirinden farklı ödeme algoritmaları kullanır ama 
hepsi "ödemeyi gerçekleştirme" davranışını gerçekleştirir. 

### Yapısal Tanım:
Bu pattern'nın 3 temel parçası vardır:
1. Ortak davranışları concrete class'lara verecek strategy interface. (Yukarıdaki örnek için mesela,  <code>pay(double amount)</code>)
2. Strategy interface'inin implement edecek concrete strategy class'lar. (mesela, <code>CreditCardPayment</code>.)
3. Concrete strategy class'ları içerisinde kullanan tek bir context class'tır. Context class'ın tek amacı concrete strategy class'ları kullanmaktır.

### Örnek:
```java
// 1. Strategy Interface - Ortak davranışı tanımlar
interface PaymentStrategy {
    void pay(double amount);
}

// 2. Concrete Strategies - Farklı ödeme yöntemleri
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;

    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " TL kredi kartı ile ödendi.");
        System.out.println("Kart sahibi: " + name);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " TL PayPal ile ödendi.");
        System.out.println("Email: " + email);
    }
}

class BitcoinPayment implements PaymentStrategy {
    private String walletAddress;

    public BitcoinPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " TL Bitcoin ile ödendi.");
        System.out.println("Wallet: " + walletAddress);
    }
}

// 3. Context - Stratejiyi kullanan sınıf
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    // Ödeme yöntemini değiştirebiliriz
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Lütfen bir ödeme yöntemi seçin!");
            return;
        }
        paymentStrategy.pay(amount);
    }
}
// 4. Kullanım
public class StrategyPatternDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Kredi kartı ile ödeme
        System.out.println("=== Kredi Kartı ile Ödeme ===");
        cart.setPaymentStrategy(new CreditCardPayment("****-****-****-****", "Ahmet Yılmaz"));
        cart.checkout(250.50);

        System.out.println("\n=== PayPal ile Ödeme ===");
        cart.setPaymentStrategy(new PayPalPayment("ahmet@example.com"));
        cart.checkout(150.75);

        System.out.println("\n=== Bitcoin ile Ödeme ===");
        cart.setPaymentStrategy(new BitcoinPayment("1A2B3C4D5E6F"));
        cart.checkout(500.00);

        // Runtime'da strateji değiştirebiliyoruz
        System.out.println("\n=== Strateji Değişikliği ===");
        cart.setPaymentStrategy(new CreditCardPayment("****-****-****-****", "Ayşe Demir"));
        cart.checkout(99.99);
    }
}
```

### Avantajları:
* OCP ve SRP prensiplerine uygundur. Yeni bir strategy class'ı eklenmek istendiğinde, var olan kodlar değiştirilmez. Sadece yeni bir class oluşturulur.
* Strategy class'lar context'e bağımlı değildir. Context, strategy class'lara bağımlıdır. Böylece, strategy class'lar projenin başka yerlerinde de kullanılabilirler.
* If-else kalabalığından kurtarır.
* Her bir strategy class, birbirinden bağımsız olarak test edilebilir.

### Dezavantajları:
* Her yeni strategy class gereksinimi durumunda sürekli strategy class oluşturmak gerekir.
* Strategy class'lar strategy ailesini oluşturan class'lar oldukları için uygun olmayan yerlerde kullanmak doğru değildir.
* Client'in hangi strategy class'ı ve onunla ilişkili context class'ı nasıl kullanması gerektiğini bilmesi gerekir.

### Kullanım yerleri:
* <code>Collections.sort()</code> sorting algoritmasında farklı collection tiplerinin sort edilmesinde.
* Aynı tür log'ların loglanmasında
* Ödeme veya kampanya sistemlerinde