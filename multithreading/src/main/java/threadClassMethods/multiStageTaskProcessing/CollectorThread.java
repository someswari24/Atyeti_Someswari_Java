package threadClassMethods.multiStageTaskProcessing;

public class CollectorThread implements Runnable{
    @Override
    public void run() {
        for (int i=1;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+" iteration "+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println(Thread.currentThread().getName()+" completed");
    }
}
