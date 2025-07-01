package forkJoinFramework;

public class SequentialArraySum {
    public static Double computeSum(int[] array){
        double sum=0;
        for (int value:array){
            sum += Math.sqrt(value) * Math.log(value);
        }
        return sum;
    }
}
