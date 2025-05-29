package robustStudentDataProcessor.errorlogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLogger {
    public static final String logFile="error_log.txt";
    public static void writeErrorLog(int lineNumber,Exception e) throws IOException {

        try(BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(logFile,true))){

            String timestamp= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
            bufferedWriter.write(String.format("[%s] error at line %d: %s - %s%n",timestamp,lineNumber,e.getClass().getSimpleName(),e.getMessage()));

        }

    }
}
