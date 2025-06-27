package cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class WeatherReport {
    public static void main(String[] args) {
        Sensor sensor=new Sensor();
        CyclicBarrier cyclicBarrier=new CyclicBarrier(3,()-> {
            System.out.println("Summary: Temp=" + sensor.temperature + " °C, Wind=" + sensor.windSpeed +
                    " km/h, Rain=" + sensor.rainfall + " mm");
            System.out.println("-----------------------------------");
        });

        Thread temperatureSensor=new Thread(new Sensor("Temperature",cyclicBarrier));
        Thread windSensor=new Thread(new Sensor("Wind",cyclicBarrier));
        Thread rainfallSensor=new Thread(new Sensor("Rain",cyclicBarrier));

        temperatureSensor.start();
        windSensor.start();
        rainfallSensor.start();
    }
}
