package largeScaleLogAnalysisSystem;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LogAnalysisApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String>logFiles=List.of(
                "multithreading/src/main/java/largeScaleLogAnalysisSystem/logs/server1.log",
                "multithreading/src/main/java/largeScaleLogAnalysisSystem/logs/server2.log",
                "multithreading/src/main/java/largeScaleLogAnalysisSystem/logs/server3.log"
        );
        LogAnalysis analysis=new LogAnalysis(logFiles);
        analysis.executeAnalysis();
    }
}
