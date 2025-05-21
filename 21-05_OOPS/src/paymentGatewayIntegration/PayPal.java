package paymentGatewayIntegration;

import java.util.UUID;

public class PayPal implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        double discount=10.0;
        double totalPayment=amount-(amount-(amount*(discount*0.01)));
        String transactionId= String.valueOf(UUID.randomUUID());

        System.out.println("----credit card payment receipt----");
        System.out.println("Transaction Id :"+transactionId);
        System.out.println("Originam Amount : "+amount);
        System.out.println("Processing Fee : "+discount);
        System.out.println("Total payment: "+totalPayment);
        System.out.println("PayPal Payment is Successfull");
    }
}
