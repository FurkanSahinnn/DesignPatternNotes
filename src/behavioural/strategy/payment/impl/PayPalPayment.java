package behavioural.strategy.payment.impl;

import behavioural.strategy.payment.IPaymentStrategy;

public class PayPalPayment implements IPaymentStrategy {
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
