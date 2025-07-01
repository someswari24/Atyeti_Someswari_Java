package forkJoinFramework;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int array[]=new int[100000000];

        for (int i=0;i<array.length;i++){
            array[i]=5;
        }

        long startSequentialTime=System.currentTimeMillis();
        double sequentialSum=SequentialArraySum.computeSum(array);
        long endSequentialTime=System.currentTimeMillis();
        System.out.println("Sequential Sum = "+sequentialSum+" Time : "+(endSequentialTime-startSequentialTime)+" ms");

        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinArraySum arraySum=new ForkJoinArraySum(array,0,array.length);

        long startTime=System.currentTimeMillis();
        double sum= forkJoinPool.invoke(arraySum);
        long endTime=System.currentTimeMillis();
        System.out.println("ForkJoin array sum= "+sum+" Time: "+(endTime-startTime)+" ms");
    }
}
