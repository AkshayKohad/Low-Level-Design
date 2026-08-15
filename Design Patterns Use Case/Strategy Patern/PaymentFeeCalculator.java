interface PaymentFeeStrategy {
    double calculateFinalAmount(double amount);
}

class CreditCardPaymentStrategy implements PaymentFeeStrategy {
    private static final double PROCESSING_FEE_RATE = 0.02;

    @Override
    public double calculateFinalAmount(double amount) {
        return amount + (amount * PROCESSING_FEE_RATE);
    }
}

class UpiPaymentStrategy implements PaymentFeeStrategy {
    private static final double PROCESSING_FEE = 5.0;

    @Override
    public double calculateFinalAmount(double amount) {
        return amount + PROCESSING_FEE;
    }
}

class WalletPaymentStrategy implements PaymentFeeStrategy {
    private static final double PROCESSING_FEE_RATE = 0.01;
    private static final double MAX_PROCESSING_FEE = 30.0;

    @Override
    public double calculateFinalAmount(double amount) {
        double fee = Math.min(amount * PROCESSING_FEE_RATE, MAX_PROCESSING_FEE);
        return amount + fee;
    }
}

class PaymentGateway {
    private PaymentFeeStrategy paymentFeeStrategy;

    public void setPaymentFeeStrategy(PaymentFeeStrategy paymentFeeStrategy) {
        if (paymentFeeStrategy == null) {
            throw new IllegalArgumentException("Payment fee strategy cannot be null");
        }
        this.paymentFeeStrategy = paymentFeeStrategy;
    }

    public double pay(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (paymentFeeStrategy == null) {
            throw new IllegalStateException("Select a payment method before paying");
        }
        return paymentFeeStrategy.calculateFinalAmount(amount);
    }
}

public class PaymentFeeCalculator {
    public static void main(String[] args) {
        PaymentGateway gateway = new PaymentGateway();

        gateway.setPaymentFeeStrategy(new CreditCardPaymentStrategy());
        System.out.println("Credit card: " + gateway.pay(1000)); // 1020.0

        gateway.setPaymentFeeStrategy(new UpiPaymentStrategy());
        System.out.println("UPI: " + gateway.pay(1000)); // 1005.0

        gateway.setPaymentFeeStrategy(new WalletPaymentStrategy());
        System.out.println("Wallet: " + gateway.pay(5000)); // 5030.0
        System.out.println("Wallet: " + gateway.pay(1000)); // 1010.0
    }
}
