package largeScaleLogAnalysisSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class LogAnalysis {
    private final List<String> logFilePaths;
    private final ExecutorService downloadReadExecutor;
    private final ForkJoinPool processPool;

    public LogAnalysis(List<String> logFilePaths) {
        this.logFilePaths = logFilePaths;
        this.downloadReadExecutor = Executors.newFixedThreadPool(10);
        this.processPool = new ForkJoinPool();
    }

    public void executeAnalysis() throws ExecutionException, InterruptedException {
        List<Future<LogFile>> futures = new ArrayList<>();

        long start=System.currentTimeMillis();

        for (String filePath:logFilePaths){
            LogFileFetcher fileFetcher=new LogFileFetcher(filePath,processPool);
            futures.add(downloadReadExecutor.submit(fileFetcher));
        }

        LogFileResult fileResult=new LogFileResult();
        for (Future<LogFile> future : futures) {
            LogFile file=future.get();
            fileResult.merge(file);
        }

        long end=System.currentTimeMillis();

        printReport(fileResult,end-start);

        shutdown();
    }

    private void printReport(LogFileResult fileResult, long timeTaken) {
        System.out.println("===== FINAL REPORT =====");
        System.out.println("Total Errors: " + fileResult.getTotalErrors());
        System.out.println("Avg Response Time: " + fileResult.getAvgResponseCount());
        System.out.println("Total Processing Time: " + timeTaken + " ms");
    }

    private void shutdown() {
        downloadReadExecutor.shutdown();
        processPool.shutdown();
    }

}
