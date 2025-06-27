package exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                String dataToExchange = "Data from Thread 1";
                System.out.println("Thread 1 ready to exchange: " + dataToExchange);
                String received = exchanger.exchange(dataToExchange);
                System.out.println("Thread 1 received: " + received);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        new Thread(() -> {
            try {
                String dataToExchange = "Data from Thread 2";
                System.out.println("Thread 2 ready to exchange: " + dataToExchange);
                String received = exchanger.exchange(dataToExchange);
                System.out.println("Thread 2 received: " + received);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
