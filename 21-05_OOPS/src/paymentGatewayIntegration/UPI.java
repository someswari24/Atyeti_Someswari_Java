package paymentGatewayIntegration;

import java.util.UUID;

public class UPI implements PaymentMethod{
    @Override
    public void processPayment(double amount) {
        String transactionId= String.valueOf(UUID.randomUUID());

        System.out.println("----credit card payment receipt----");
        System.out.println("Transaction Id :"+transactionId);
        System.out.println("Origina1 Amount : "+amount);
        System.out.println("UPI Payment is Successfull");
    }
}
