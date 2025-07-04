package largeScaleLogAnalysisSystem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class LogAnalysis {
    private final List<String> logFilePaths;
    private final ExecutorService downloadReadExecutor;
    private final ForkJoinPool processPool;

    public LogAnalysis(List<String> logFilePaths) {
        this.logFilePaths = logFilePaths;
        this.downloadReadExecutor = Executors.newFixedThreadPool(10);
        this.processPool = new ForkJoinPool();
    }


}
