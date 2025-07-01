package synchronization;

public class Employee extends Thread{
    private Printer printer;
    private String document;

    public Employee(String document, Printer printer) {
        this.document = document;
        this.printer = printer;
    }
    @Override
    public void run(){
        printer.printDocument(document);
    }
}
