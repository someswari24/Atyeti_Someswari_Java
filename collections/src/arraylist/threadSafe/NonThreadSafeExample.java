package arraylist.threadSafe;

import java.util.ArrayList;
import java.util.List;

public class NonThreadSafeExample {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i); // Unsafe: multiple threads writing to the list
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Non-thread-safe list size: " + list.size());
    }
}

