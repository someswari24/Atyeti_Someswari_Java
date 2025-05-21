package smartVehicleManagementSystem;

public class DieselTruck extends Vehicle{
    public DieselTruck(String make, String model, int year, VehicleState vehicleState) {
        super(make, model, year, vehicleState);
    }
    @Override
    void start() {
        setVehicleState(VehicleState.RUNNING);
        System.out.println("Diesel Truck started ");
    }

    @Override
    void stop() {
        setVehicleState(VehicleState.IDLE);
        System.out.println("Diesel Truck stopped");
    }

    @Override
    void displayInfo() {
        System.out.println("Electric Car details");
        System.out.println("make - "+getMake());
        System.out.println("model - "+getModel());
        System.out.println("year - "+getYear());
        System.out.println("state - "+getVehicleState());
    }

}
