package processor;

import exceptionHandling.InvalidWasteDataException;
import pojo.WasteRecord;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WasteDataProcessor {
    private static final Logger logger=Logger.getLogger(WasteDataProcessor.class.getName());
    static List<WasteRecord> records=new ArrayList<>();
    public static List<WasteRecord> readFile() throws FileNotFoundException {
        InputStream inputStream = WasteDataProcessor.class.getClassLoader().getResourceAsStream("waste_data.csv");
        //String filePath = "src/main/resources/waste_data.csv";

        try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            bufferedReader.readLine();
            while ((line=bufferedReader.readLine())!=null){
                String[] split = line.split(",");
                if(split.length!=4 || split[0].isEmpty() ||split[1].isEmpty() ||split[2].isEmpty() ||split[3].isEmpty()){
                    throw new InvalidWasteDataException("missing data"+line);
                }

                String zoneId=split[0];
                LocalDate date=LocalDate.parse(split[1]);
                String wasteType=split[2];
                double weight= Double.parseDouble(split[3]);

                records.add(new WasteRecord(zoneId,date,wasteType,weight));

            }

        } catch (IOException | InvalidWasteDataException e) {
            e.printStackTrace();
        }
        return records;
    }

    //Total waste per zone
    public static Map<String,Double> totalWastePerZone(){
        return records.stream().collect(Collectors.groupingBy(WasteRecord::getZoneID,Collectors.summingDouble(WasteRecord::getWeight)));
    }

    //Zone with highest plastic waste
    public static String zoneWithHighestPlasticWaste(){
        return records.stream().filter(r->r.getWasteType().equalsIgnoreCase("plastic"))
                .collect(Collectors.groupingBy(WasteRecord::getWasteType,Collectors.summingDouble(WasteRecord::getWeight)))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("0");
    }

    //Average waste per type
    public static Map<String,Double> avgWastePerType(){
        return records.stream().collect(Collectors.groupingBy(WasteRecord::getZoneID,Collectors.averagingDouble(WasteRecord::getWeight)));
    }
}
