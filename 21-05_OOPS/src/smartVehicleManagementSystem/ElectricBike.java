package smartVehicleManagementSystem;

public class ElectricBike extends Vehicle implements EcoFriendly{
    public ElectricBike(String make, String model, int year, VehicleState vehicleState) {
        super(make, model, year, vehicleState);
    }

    @Override
    public void chargeBattery() {
        System.out.println(getMake()+" , "+getModel()+" is charging its battery");
    }

    @Override
    void start() {
        setVehicleState(VehicleState.RUNNING);
        System.out.println("Electric Bike started ");
    }

    @Override
    void stop() {
        setVehicleState(VehicleState.IDLE);
        System.out.println("Elecrtric Bike stopped");
    }

    @Override
    void displayInfo() {
        System.out.println("Electric Bike details");
        System.out.println("make - "+getMake());
        System.out.println("model - "+getModel());
        System.out.println("year - "+getYear());
        System.out.println("state - "+getVehicleState());
    }
}
