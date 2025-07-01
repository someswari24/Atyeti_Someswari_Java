package synchronization;

public class Printer {
    public synchronized void printDocument(String document){
        System.out.println(Thread.currentThread().getName()+" printing "+document);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" has finished printing "+document);
    }
}
