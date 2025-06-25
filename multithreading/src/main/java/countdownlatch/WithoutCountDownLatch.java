package countdownlatch;

public class WithoutCountDownLatch {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Loaded configuration");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Connected to Database");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1500);
                System.out.println("Started Web Server");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("All services started. Startup complete");
    }
}
