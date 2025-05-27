package pojo;

import java.time.LocalDate;

public class WasteRecord {
    private String zoneID;
    private LocalDate date;
    private String wasteType;
    private double weight;

    public WasteRecord(String zoneID, LocalDate date, String wasteType, double weight) {
        this.zoneID = zoneID;
        this.date = date;
        this.wasteType = wasteType;
        this.weight = weight;
    }

    public String getZoneID() {
        return zoneID;
    }

    public void setZoneID(String zoneID) {
        this.zoneID = zoneID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "WasteRecord{" +
                "zoneID='" + zoneID + '\'' +
                ", date=" + date +
                ", wasteType='" + wasteType + '\'' +
                ", weight=" + weight +
                '}';
    }
}
