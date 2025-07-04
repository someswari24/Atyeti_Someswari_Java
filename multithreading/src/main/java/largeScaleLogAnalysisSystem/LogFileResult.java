package largeScaleLogAnalysisSystem;

import java.util.HashMap;
import java.util.Map;

public class LogFileResult {
    private  long errorCount=0;
    private  long responseTime=0;
    private  long responseCount=0;
    private Map<String,Long> errorCodeFrequency=new HashMap<>();

    public void merge(LogFile logFile){
        this.errorCount+= logFile.getErrorCount();
        this.responseTime+= logFile.getResponseTime();
        this.responseCount+= logFile.getResponseCount();
        for (Map.Entry<String, Long> entry: logFile.getErrorCodeFrequency().entrySet()){
            this.errorCodeFrequency.merge(entry.getKey(),entry.getValue(),Long::sum);
        }
    }

    public long getErrorCount() {
        return errorCount;
    }

    public double getAvgResponseCount() {
        return responseCount == 0 ? 0.0 : (double) responseTime / responseCount;
    }
}
