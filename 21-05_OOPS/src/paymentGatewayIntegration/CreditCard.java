package paymentGatewayIntegration;

import java.util.UUID;

public class CreditCard implements PaymentMethod{
    @Override
    public void processPayment(double amount) {
        double extrafee=15.0;
        double totalPayment=amount+(amount-(amount*(extrafee*0.01)));
        String transactionId= String.valueOf(UUID.randomUUID());

        System.out.println("----credit card payment receipt----");
        System.out.println("Transaction Id :"+transactionId);
        System.out.println("Originam Amount : "+amount);
        System.out.println("Processing Fee : "+extrafee);
        System.out.println("Total payment: "+totalPayment);
        System.out.println("credit card Payment is Successfull");

    }
}
