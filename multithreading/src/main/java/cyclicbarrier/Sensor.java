package cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Sensor implements Runnable{
    private String sensorType;
    private CyclicBarrier cyclicBarrier;

    private Random random=new Random();

    private static final int CYCLES = 3;

    static int temperature = 0;
    static int windSpeed = 0;
    static int rainfall = 0;

    public Sensor(String sensorType, CyclicBarrier cyclicBarrier) {
        this.sensorType = sensorType;
        this.cyclicBarrier = cyclicBarrier;
    }

    public Sensor() {

    }

    @Override
    public void run() {
        try {
            for (int i=1;i<=CYCLES;i++){
                Thread.sleep(1000);
                switch (sensorType){
                    case "Temperature":
                        temperature=25+random.nextInt(20);
                        System.out.println("Cycle " + i + ": " + sensorType + " : " + temperature + "°C");
                        break;

                    case "Wind":
                        windSpeed=5+random.nextInt(10);
                        System.out.println("Cycle " + i + ": " + sensorType + " : " + windSpeed + "km/h");
                        break;

                    case "Rain":
                        rainfall=random.nextInt(10);
                        System.out.println("Cycle " + i + ": " + sensorType + " : " + rainfall + "°C");
                        break;

                }
                cyclicBarrier.await();
            }
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
