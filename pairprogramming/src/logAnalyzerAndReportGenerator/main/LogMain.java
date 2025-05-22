package logAnalyzerAndReportGenerator.main;

import logAnalyzerAndReportGenerator.databaseConnection.DatabaseConnection;
import logAnalyzerAndReportGenerator.filehandling.LogAnalyzer;

import java.io.FileNotFoundException;

public class LogMain {
    public static void main(String[] args) throws FileNotFoundException {
        String directoryPath="C://Users//SomeswariKaranam//OneDrive - Atyeti Inc//Desktop//core java//pairprogramming//src//logAnalyzerAndReportGenerator//logfiles";
        LogAnalyzer.readLogFiles(directoryPath);
        DatabaseConnection.database();
    }
}
