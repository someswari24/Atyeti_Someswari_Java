package reentrantProcessingSystem;

public class OrdeProcessingSystem {
    public static void main(String[] args) throws InterruptedException {
        OrderQueue orderQueue=new OrderQueue(5);

        Thread consumer1=new Thread(new Consumer(orderQueue),"consumer1");
        Thread consumer2=new Thread(new Consumer(orderQueue),"consumer2");

        consumer1.start();
        consumer2.start();

        Thread producer1=new Thread(new Producer(orderQueue,5),"producer1");
        Thread producer2=new Thread(new Producer(orderQueue,5),"producer2");
        Thread producer3=new Thread(new Producer(orderQueue,5),"producer3");

        producer1.start();
        producer2.start();
        producer3.start();

        producer1.join();
        producer2.join();
        producer3.join();

        consumer1.interrupt();
        consumer2.interrupt();
    }
}
