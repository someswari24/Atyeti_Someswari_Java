package largeScaleLogAnalysisSystem;

import java.util.HashMap;
import java.util.Map;

public class LogFileResult {
    private long totalErrors = 0;
    private long responseTimeSum = 0;
    private long totalResponses = 0;
    private Map<String,Long> errorCodeFrequency=new HashMap<>();

    public void merge(LogFile logFile){
        this.totalErrors+= logFile.getErrorCount();
        this.responseTimeSum+= logFile.getResponseTime();
        this.totalResponses+= logFile.getResponseCount();
        for (Map.Entry<String, Long> entry: logFile.getErrorCodeFrequency().entrySet()){
            this.errorCodeFrequency.merge(entry.getKey(),entry.getValue(),Long::sum);
        }
    }

    public long getTotalErrors() {
        return totalErrors;
    }

    public double getAvgResponseCount() {
        return totalResponses == 0 ? 0.0 : (double) responseTimeSum / totalResponses;
    }
}
