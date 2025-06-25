package reentrantProcessingSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OrderQueue {
    private final Queue<String>queue=new LinkedList<>();
    private final int capacity;
    private ReentrantLock lock =new ReentrantLock(true);
    Condition notFull=lock.newCondition();
    Condition notEmpty=lock.newCondition();

    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

    public void placeOrder(String order) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.offer(order);
            System.out.println(Thread.currentThread().getName() + " placed order " + order);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
        public String takeOrder() {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    notEmpty.await();
                }
                String order = queue.poll();
                System.out.println(Thread.currentThread().getName() + " placing order " + order);
                notFull.signal();
                return order;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return null;
        }
    }

