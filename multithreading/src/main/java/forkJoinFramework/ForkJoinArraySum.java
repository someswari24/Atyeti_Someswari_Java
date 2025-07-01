package forkJoinFramework;

import java.util.concurrent.RecursiveTask;

public class ForkJoinArraySum extends RecursiveTask<Double> {
    private static final int THRESHOLD=10000000;
    private int [] array;
    private int start;
    private int end;

    public ForkJoinArraySum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        int length=end-start;
        if(length<=THRESHOLD){
            double sum= 0L;
            for (int i=start;i<end;i++){
                sum += Math.sqrt(array[i]) * Math.log(array[i]);
            }
            return sum;
        }
       else {
           int mid=(start+end)/2;

           ForkJoinArraySum leftTask=new ForkJoinArraySum(array,start,mid);
           ForkJoinArraySum rightTask=new ForkJoinArraySum(array,mid,end);

           leftTask.fork();
           double rightResult=rightTask.compute();
           double leftResult= leftTask.join();

           return leftResult+rightResult;
        }
    }
}
