package by.training.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierCar implements Runnable {
	 private int carNumber;
	 //����� ��������
	 private CyclicBarrier passage;
	 //������, ������� �������
	 //���� ��� ������(������) �������� � �������
	 
     public CyclicBarrierCar(int carNumber,CyclicBarrier passage) {
         this.carNumber = carNumber;
         this.passage = passage;
     }

     @Override
     public void run() {
         try {
             System.out.printf("Car �%d drive to passage\n", carNumber);
             //��� �������� ������ � ��� ��� �� ������ �������, ����� ������� ����� await()
             //����� ����� ������ ����� ����������� � ���� ���� ��������� ������� ��������� �������
             passage.await();
             System.out.printf("Car �%d continues driving\n", carNumber);
         } catch (InterruptedException e) {
        	 e.printStackTrace();
         }catch(BrokenBarrierException e){
        	 e.printStackTrace();
         }
     }
}