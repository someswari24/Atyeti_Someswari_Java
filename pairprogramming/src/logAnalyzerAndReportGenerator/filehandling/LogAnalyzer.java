package logAnalyzerAndReportGenerator.filehandling;

import java.io.*;
import java.util.logging.Logger;

public class LogAnalyzer {
    private static final Logger logger=Logger.getLogger(LogAnalyzer.class.getName());

    public static int infoCount=0;
    public static int errorCount=0;
    public static int warningCount=0;

    static String outputFile="C://Users//SomeswariKaranam//OneDrive - Atyeti Inc//Desktop//core java//pairprogramming//src//logAnalyzerAndReportGenerator//duplicateLogFiles//logWriter.log";

    public static void readLogFiles(String directoryPath) throws FileNotFoundException {
        File file = new File(directoryPath);
        if (!file.exists() || !file.isDirectory()) {
            logger.warning("Directory does not exist: "+directoryPath);
            return;
        }

        File[] files=file.listFiles((d, name) -> name.endsWith(".log")); // filter only .log files
        if (files==null || files.length==0) {
            logger.warning("No log files found in directory: "+directoryPath);
            return;
        }

        for (File file1 : files) {
            logger.info("Reading file: " + file.getName());
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
                    BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(outputFile))) {

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                    String[] split = line.split(" - ");
                    if (split.length < 3 || split[0].isEmpty() || split[1].isEmpty() || split[2].isEmpty()) {
                        logger.warning("skipping lines due to missing fields"+line);
                        continue;
                    }
                    String logger = split[1].trim();
                    switch (logger.toUpperCase()) {
                        case "INFO":
                            infoCount++;
                            break;
                        case "ERROR":
                            errorCount++;
                            break;
                        case "WARNING":
                            warningCount++;
                            break;
                    }
                }
                logger.info("file copied successfully");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
