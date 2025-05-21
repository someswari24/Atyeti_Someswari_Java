package smartVehicleManagementSystem;

import java.util.Date;

public abstract class Vehicle {
    private String make;
    private String model;
    private int year;
    private VehicleState vehicleState;

    abstract void start();
    abstract void stop();
    abstract void displayInfo();

    public Vehicle(String make, String model, int year, VehicleState vehicleState) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vehicleState = vehicleState;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public VehicleState getVehicleState() {
        return vehicleState;
    }

    public void setVehicleState(VehicleState vehicleState) {
        this.vehicleState = vehicleState;
    }
}
