package paymentGatewayIntegration;

public interface PaymentMethod {
     abstract void processPayment(double amount);
}
