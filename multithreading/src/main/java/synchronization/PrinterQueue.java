package synchronization;

public class PrinterQueue {
    public static void main(String[] args) {
        Printer printer=new Printer();

        Thread thread1=new Employee("Document-1", printer);
        Thread thread2=new Employee("Document-2", printer);
        Thread thread3=new Employee("Document-3", printer);
        Thread thread4=new Employee("Document-4", printer);
        Thread thread5=new Employee("Document-5", printer);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        
    }
}
