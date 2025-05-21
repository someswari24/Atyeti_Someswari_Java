package smartVehicleManagementSystem;

import java.util.ArrayList;

public class VehicleManager {
    public static void main(String[] args) {
        ArrayList<Vehicle>vehicles=new ArrayList<>();
        vehicles.add(new ElectricCar("Tesla","Model-3",2017,VehicleState.OFF));
        vehicles.add(new ElectricBike("Revolt","RV400",2025,VehicleState.OFF));
        vehicles.add(new DieselTruck("Ford","super duty",2025,VehicleState.OFF));

        for(Vehicle vehicle:vehicles){
            vehicle.start();
            vehicle.displayInfo();

            if(vehicle instanceof EcoFriendly){
                ((EcoFriendly) vehicle).chargeBattery();
            }

            vehicle.stop();
            vehicle.displayInfo();
            System.out.println("-------------------------------");
        }
    }
}
