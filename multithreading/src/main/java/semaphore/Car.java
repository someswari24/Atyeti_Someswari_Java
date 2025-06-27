package semaphore;

import java.util.concurrent.Semaphore;

class Car implements Runnable{
    private int carNumber;
    private static final Semaphore parkingSpots=new Semaphore(5,true);

    public Car(int carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public void run() {
        try {
            System.out.println("Car :"+carNumber+" trying to park");
            parkingSpots.acquire();

            System.out.println("Car :"+carNumber+" parked. Spots left:"+parkingSpots.availablePermits());

            Thread.sleep(1000);

            System.out.println("Car :"+carNumber+" leaving");
            parkingSpots.release();
            System.out.println("Car :"+carNumber+" left. Spots left:"+parkingSpots.availablePermits());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
