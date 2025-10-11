package behavioural.strategy.payment.impl;

import behavioural.strategy.payment.IPaymentStrategy;

public class CreditCartPayment implements IPaymentStrategy {
    private String cardNumber;
    private String name;

    public CreditCartPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " TL, "+ cardNumber + " numaralı kredi kartı ile ödendi.");
        System.out.println("Kart sahibi: " + name);
    }
}
