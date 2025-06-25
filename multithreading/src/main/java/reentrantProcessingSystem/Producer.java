package reentrantProcessingSystem;

public class Producer implements Runnable{
    private OrderQueue orderQueue;
    private int orderCount;

    public Producer(OrderQueue orderQueue, int orderCount) {
        this.orderQueue = orderQueue;
        this.orderCount = orderCount;
    }

    @Override
    public void run() {
        for (int i=1;i<=orderCount;i++){
            try {
                String order=Thread.currentThread().getName()+" - order - "+i;
                orderQueue.placeOrder(order);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+" interrupted "+orderCount);
            }
        }
    }
}
