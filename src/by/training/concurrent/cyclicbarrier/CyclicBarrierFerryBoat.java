package by.training.concurrent.cyclicbarrier;

public class CyclicBarrierFerryBoat implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println("Cars are ferried across!");
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
    }
}
