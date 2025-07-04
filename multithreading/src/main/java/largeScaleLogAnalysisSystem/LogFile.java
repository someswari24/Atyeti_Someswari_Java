package largeScaleLogAnalysisSystem;

import java.util.Map;

public class LogFile {
    private  long errorCount;
    private  long responseTime;
    private  long responseCount;
    private  Map<String,Long> errorCodeFrequency;

    public LogFile(long errorCount, long responseTime, long responseCount, Map<String, Long> errorCodeFrequency) {
        this.errorCount = errorCount;
        this.responseTime = responseTime;
        this.responseCount = responseCount;
        this.errorCodeFrequency = errorCodeFrequency;
    }

    public LogFile merge(LogFile logFile){
        this.errorCount+=logFile.errorCount;
        this.responseTime+=logFile.responseTime;
        this.responseCount+=logFile.responseCount;
        for (Map.Entry<String, Long> entry:logFile.errorCodeFrequency.entrySet()){
            this.errorCodeFrequency.merge(entry.getKey(),entry.getValue(),Long::sum);
        }
        return this;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public long getResponseCount() {
        return responseCount;
    }

    public Map<String, Long> getErrorCodeFrequency() {
        return errorCodeFrequency;
    }
}
