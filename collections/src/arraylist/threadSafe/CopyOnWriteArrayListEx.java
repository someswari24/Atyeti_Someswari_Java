package arraylist.threadSafe;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListEx{
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i); // Safe: internally uses a new copy for each modification
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("CopyOnWriteArrayList size: " + list.size());

    }
}
