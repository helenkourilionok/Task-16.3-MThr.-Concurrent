package by.training.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreCountThread implements Runnable{

	private SemaphoreCommonResource commonResource;//общий ресурс
	private Semaphore semaphore;
	//семафор - объект синхронизации, который будет использован
	 
	public SemaphoreCountThread(SemaphoreCommonResource commonResource, Semaphore semaphore){
	        this.commonResource = commonResource;
	        this.semaphore = semaphore;
	 }
	
	@Override
	public void run() {

		try{
            System.out.println(Thread.currentThread().getName() + " ожидает разрешение");
            semaphore.acquire();
            //поток получает разрешение на доступ к разделяемому ресурсу
            //в случае если количество разрешений на доступ к ресурсу равно 0
            //поток блокируется
            System.out.println(Thread.currentThread().getName() + " получил разрешение");
            for (int i = 1; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + ": commonResource "
                													+ commonResource.getId());
                commonResource.incrementId();
                //в цикле выполняем операцию над разделяемым ресурсом
                //в данном случае увеличиваем значение разд. ресурса на 1
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
        	e.printStackTrace();
        }finally{
            System.out.println(Thread.currentThread().getName() + " освобождает разрешение");
            semaphore.release();
            //увеличиваем количество разрешений, чтобы другие 
            //потоки имели возможность получить доступ к разделяемому ресурсу
        }
	}

}
