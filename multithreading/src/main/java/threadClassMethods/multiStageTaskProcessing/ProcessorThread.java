package threadClassMethods.multiStageTaskProcessing;

public class ProcessorThread implements Runnable{
    @Override
    public void run() {
        for (int i=1;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+" iteration "+i);
            if (i == 3) {
                System.out.println(Thread.currentThread().getName() + " yielding CPU on iteration " +i);
                Thread.yield();
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"completed");
    }
}
