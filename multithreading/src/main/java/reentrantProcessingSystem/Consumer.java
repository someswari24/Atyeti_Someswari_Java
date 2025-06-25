package reentrantProcessingSystem;

public class Consumer implements Runnable{
    private OrderQueue orderQueue;
    private int count=0;

    public Consumer(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        try {
            while (true){
                String order=orderQueue.takeOrder();
                count++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" interrupted "+count);
        }
    }
}
