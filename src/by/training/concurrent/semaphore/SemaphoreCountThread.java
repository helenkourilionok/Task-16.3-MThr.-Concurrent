package by.training.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreCountThread implements Runnable{

	private SemaphoreCommonResource commonResource;//����� ������
	private Semaphore semaphore;
	//������� - ������ �������������, ������� ����� �����������
	 
	public SemaphoreCountThread(SemaphoreCommonResource commonResource, Semaphore semaphore){
	        this.commonResource = commonResource;
	        this.semaphore = semaphore;
	 }
	
	@Override
	public void run() {

		try{
            System.out.println(Thread.currentThread().getName() + " ������� ����������");
            semaphore.acquire();
            //����� �������� ���������� �� ������ � ������������ �������
            //� ������ ���� ���������� ���������� �� ������ � ������� ����� 0
            //����� �����������
            System.out.println(Thread.currentThread().getName() + " ������� ����������");
            for (int i = 1; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + ": commonResource "
                													+ commonResource.getId());
                commonResource.incrementId();
                //� ����� ��������� �������� ��� ����������� ��������
                //� ������ ������ ����������� �������� ����. ������� �� 1
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
        	e.printStackTrace();
        }finally{
            System.out.println(Thread.currentThread().getName() + " ����������� ����������");
            semaphore.release();
            //����������� ���������� ����������, ����� ������ 
            //������ ����� ����������� �������� ������ � ������������ �������
        }
	}

}
