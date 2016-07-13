package by.training.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierCar implements Runnable {
	 private int carNumber;
	 //номер автобуса
	 private CyclicBarrier passage;
	 //барьер, который ожидает
	 //пока все машины(потоки) подойдут к барьеру
	 
     public CyclicBarrierCar(int carNumber,CyclicBarrier passage) {
         this.carNumber = carNumber;
         this.passage = passage;
     }

     @Override
     public void run() {
         try {
             System.out.printf("Car є%d drive to passage\n", carNumber);
             //ƒл€ указани€ потоку о том что он достиг барьера, нужно вызвать метод await()
             //ѕосле этого данный поток блокируетс€ и ждет пока остальные стороны достигнут барьера
             passage.await();
             System.out.printf("Car є%d continues driving\n", carNumber);
         } catch (InterruptedException e) {
        	 e.printStackTrace();
         }catch(BrokenBarrierException e){
        	 e.printStackTrace();
         }
     }
}