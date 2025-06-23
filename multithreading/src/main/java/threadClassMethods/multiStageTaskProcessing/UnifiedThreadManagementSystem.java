package threadClassMethods.multiStageTaskProcessing;

public class UnifiedThreadManagementSystem {
    public static void main(String[] args) {
        Thread collectorThread=new Thread(new CollectorThread(),"Collector Thread");

        Thread processorThread=new Thread(new ProcessorThread(),"ProcessorThread");

        Thread reporterThread=new Thread(new ReporterThread(),"ReporterThread");

        collectorThread.setPriority(Thread.MIN_PRIORITY);
        processorThread.setPriority(Thread.NORM_PRIORITY);
        reporterThread.setPriority(Thread.MAX_PRIORITY);

        System.out.println(collectorThread.getName() + " Priority: " + collectorThread.getPriority());
        System.out.println(processorThread.getName() + " Priority: " + processorThread.getPriority());
        System.out.println(reporterThread.getName() + " Priority: " + reporterThread.getPriority());

        collectorThread.start();

        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("collector thread interruption");
                collectorThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            //System.out.println("collector thread alive: "+collectorThread.isAlive());
            collectorThread.join();
            System.out.println("collector thread alive: "+collectorThread.isAlive());
            processorThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            processorThread.join();
            System.out.println("processor thread alive: "+processorThread.isAlive());
            reporterThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            reporterThread.join();
            System.out.println("reporter thread alive: "+reporterThread.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
