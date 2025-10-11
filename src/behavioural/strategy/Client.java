package behavioural.strategy;

import behavioural.strategy.payment.impl.BitcoinPayment;
import behavioural.strategy.payment.impl.CreditCartPayment;
import behavioural.strategy.payment.impl.PayPalPayment;
import behavioural.strategy.service.ShoppingCart;

public class Client {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Kredi kartı ile ödeme
        System.out.println("=== Kredi Kartı ile Ödeme ===");
        cart.setPaymentStrategy(new CreditCartPayment("****-****-****-****", "Ahmet Yılmaz"));
        cart.checkout(250.50);

        System.out.println("\n=== PayPal ile Ödeme ===");
        cart.setPaymentStrategy(new PayPalPayment("ahmet@example.com"));
        cart.checkout(150.75);

        System.out.println("\n=== Bitcoin ile Ödeme ===");
        cart.setPaymentStrategy(new BitcoinPayment("1A2B3C4D5E6F"));
        cart.checkout(500.00);

        // Runtime'da strateji değiştirebiliyoruz
        System.out.println("\n=== Strateji Değişikliği ===");
        cart.setPaymentStrategy(new CreditCartPayment("****-****-****-****", "Ayşe Demir"));
        cart.checkout(99.99);
    }
}
