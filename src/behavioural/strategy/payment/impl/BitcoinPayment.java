package behavioural.strategy.payment.impl;

import behavioural.strategy.payment.IPaymentStrategy;

public class BitcoinPayment implements IPaymentStrategy {
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
