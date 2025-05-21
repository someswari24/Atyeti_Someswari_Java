package paymentGatewayIntegration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        PaymentMethod paymentMethod=null;

        while (true){
            System.out.println("----payment options----");
            System.out.println("1.pay with credit card");
            System.out.println("2.Pay with Paypal");
            System.out.println("3.Pay with UPI");
            System.out.println("4.exit");

            System.out.print("select the payment option:");
            int option=scanner.nextInt();

            if(option==4){
                System.out.println("Exiting payment option");
                break;
            }

            System.out.print("Enter payment amount:");
            double amount=scanner.nextDouble();

            switch (option){
                case 1:
                    paymentMethod=new CreditCard();
                    break;
                case 2:
                    paymentMethod=new PayPal();
                    break;
                case 3:
                    paymentMethod=new UPI();
                    break;
                default:
                    System.out.println("Invalid option");
                    continue;
            }

            paymentMethod.processPayment(amount);
        }
    }
}
