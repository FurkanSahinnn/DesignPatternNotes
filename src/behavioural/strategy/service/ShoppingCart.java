package behavioural.strategy.service;

import behavioural.strategy.payment.IPaymentStrategy;

public class ShoppingCart {
    private IPaymentStrategy paymentStrategy;

    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
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
