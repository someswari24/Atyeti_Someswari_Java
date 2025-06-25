package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class WithCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Loaded configuration");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Connected to Database");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1500);
                System.out.println("Started Web Server");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();

        latch.await();
        System.out.println("All services started.Startup complete");
    }
}

