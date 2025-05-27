package reports;

import processor.WasteDataProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WasteReportGenerator {
    public static void writeFile() throws IOException {

        String filePath = "smartCityWasteManagementSystem/src/main/resources/waste_report.txt";
                     //File reportFile = new File("waste_report.txt");

        try(BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(filePath,true))){
            bufferedWriter.write("----------Waste Management Report----------\n\n");
            bufferedWriter.write("Total Waste per Zone:\n");

            Map<String, Double> totalWaste = WasteDataProcessor.totalWastePerZone();
            for (Map.Entry<String, Double> entry:totalWaste.entrySet()){
                bufferedWriter.write(entry.getKey()+" : "+entry.getValue()+ " kg\n");
            }

            Map<String, Double> avgWaste = WasteDataProcessor.avgWastePerType();
            bufferedWriter.write("\nAverage Waste Per Waste Type \n");
            for(var entry:avgWaste.entrySet()){
                bufferedWriter.write(entry.getKey()+" : "+entry.getValue()+"\n");
            }

            String zone = WasteDataProcessor.zoneWithHighestPlasticWaste();
            bufferedWriter.write("\nZone with highest plastic waste : "+zone);

        }
    }
}
