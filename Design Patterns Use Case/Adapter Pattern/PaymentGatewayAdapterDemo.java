interface PaymentProcessor{
    void processPayment(double amount);
}

class CardPaymentProcessor implements PaymentProcessor{
    @Override
    public void processPayment(double amount){
        System.out.println("Payment is processed by Card Payment of Amount " + amount);
    }
}

class LegacyBankGatewayAdapter implements PaymentProcessor{
    private final LegacyBankGateway legacy;

    public LegacyBankGatewayAdapter(LegacyBankGateway legacy) {
        this.legacy = legacy;
    }

    @Override
    public void processPayment(double amount){
        int amountInPaise = (int) Math.round(amount * 100);
        legacy.makeTransaction(amountInPaise);
    }
}

class LegacyBankGateway{
    public void makeTransaction(int amount){
        System.out.println("Payment is processed by Legancy Bank of Amount " + amount);
    }
}

class CheckoutService{
    private PaymentProcessor processor;

    public void setPaymentProcessor(PaymentProcessor processor){
        if(processor == null){
            throw new IllegalArgumentException("processor cannot be null");
        }
        this.processor = processor;
    }

    public void checkout(double amount){
        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if(processor == null){
            throw new IllegalStateException("Processor is not selected, first select procesor to proceed");
        }
        processor.processPayment(amount);
    }

}

public class PaymentGatewayAdapterDemo{
    public static void main(String[] args){
        CheckoutService checkout = new CheckoutService();
        checkout.setPaymentProcessor(new CardPaymentProcessor());
        checkout.checkout(499.50);
        checkout.setPaymentProcessor(
            new LegacyBankGatewayAdapter(new LegacyBankGateway())
        );
        checkout.checkout(499.50);
    }
}
