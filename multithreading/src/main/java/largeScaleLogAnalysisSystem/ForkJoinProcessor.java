package largeScaleLogAnalysisSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class ForkJoinProcessor extends RecursiveTask<LogFile> {
    private static final int size=1000;
    private List<String> lines;
    private int start;
    private int end;

    public ForkJoinProcessor(List<String> lines, int start, int end) {
        this.lines = lines;
        this.start = start;
        this.end = end;
    }

    @Override
    protected LogFile compute() {
        if (end-start<=size){
            return processLines(lines.subList(start,end));
        }
        else {
            int mid=(start+end)/2;
            ForkJoinProcessor leftTask=new ForkJoinProcessor(lines,start,mid);
            ForkJoinProcessor rightTask=new ForkJoinProcessor(lines,mid,end);
            leftTask.fork();
            LogFile rightResult=rightTask.compute();
            LogFile leftResult=leftTask.join();
            return leftResult.merge(rightResult);
        }
    }

    private LogFile processLines(List<String> stringList) {
        long errorCount=0;
        long responseTime=0;
        long responseCount=0;
        Map<String,Long> errorCodeFrequency=new HashMap<>();

        for (String line:stringList){
            if (line.contains("ERROR")){
                errorCount++;
                String[] parts = line.split(" ");
                if (parts.length>=2){
                    String code=parts[1];
                    errorCodeFrequency.merge(code,1L,Long::sum);
                }
            }
            String[] parts=line.split(" ");
            if (parts.length>=3){
                String timePart = parts[2].replace("ms", "");
                try {
                    responseTime += Long.parseLong(timePart);
                    responseCount++;
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }

        }
        return new LogFile(errorCount,responseTime,responseCount,errorCodeFrequency);
    }
}
