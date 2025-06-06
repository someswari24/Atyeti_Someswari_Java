package arraylist.threadSafe;

import java.util.*;

public class SynchronizedList{
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i); // Safe: synchronized access
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Synchronized list size: " + list.size());

        // Safe iteration (must synchronize manually)
        synchronized (list) {
            for (int num : list) {
                System.out.println(num);
            }
        }
    }
}

