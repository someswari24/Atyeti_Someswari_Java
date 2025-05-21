package trafficLightSimulation;

public class TrafficLightSimulator {

    public  void simulateLight(TrafficLight light){
        switch (light){
            case RED :
                System.out.println("RED Light - > stop");
                waitTime(3000);
                break;
            case YELLOW:
                System.out.println("YELLOW Light - > ready to go");
                waitTime(1000);
                break;
            case GREEN:
                System.out.println("GREEN Light - > GO");
                waitTime(4000);
                break;
            default:
                System.out.println("Invalid Light");
        }
    }

    public void waitTime(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception:"+e.getMessage());
        }
    }
}
