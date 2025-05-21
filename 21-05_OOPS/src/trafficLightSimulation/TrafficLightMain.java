package trafficLightSimulation;

public class TrafficLightMain {
    public static void main(String[] args) {
        TrafficLightSimulator trafficLightSimulator=new TrafficLightSimulator();
        while (true) {
            trafficLightSimulator.simulateLight(TrafficLight.RED);
            trafficLightSimulator.simulateLight(TrafficLight.YELLOW);
            trafficLightSimulator.simulateLight(TrafficLight.GREEN);
        }
    }
}
