package largeScaleLogAnalysisSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;

public class LogFileFetcher implements Callable <LogFile>{
    private static final int MAX_ATTEMPTS=3;

    private final String filePath;
    private final ForkJoinPool processorPool;

    public LogFileFetcher(String filePath, ForkJoinPool processorPool) {
        this.filePath = filePath;
        this.processorPool = processorPool;
    }

    @Override
    public LogFile call() throws Exception {
        int attempts=0;
        while (attempts<MAX_ATTEMPTS){
            try{
                List<String>lines= Files.readAllLines(Path.of(filePath));
                ForkJoinProcessor task=new ForkJoinProcessor(lines,0,lines.size());
                return processorPool.invoke(task);
            } catch (IOException e) {
                attempts++;
                System.err.println("unable to read file"+filePath);
                if(attempts>=MAX_ATTEMPTS){
                    System.out.println("Maximum attempts exceeded"+attempts);
                }
            }
        }
        return null;
    }
}
