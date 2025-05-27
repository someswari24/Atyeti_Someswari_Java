package main;

import processor.WasteDataProcessor;
import reports.WasteReportGenerator;

import java.io.IOException;

public class WasteManagementSystem {
    public static void main(String[] args) throws IOException {
        try {
            WasteDataProcessor.readFile();
            WasteReportGenerator.writeFile();
            System.out.println("Report generated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
