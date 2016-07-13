package by.training.concurrent.reentrantlock;

public class ReentrantLockCountSync implements Runnable {

	private ReentrantLockCounter commonCounter = new ReentrantLockCounter();
	//разделяемый потоками ресурс
	
	public ReentrantLockCountSync(ReentrantLockCounter commonCounter){
		this.commonCounter = commonCounter;
	}
	
	@Override
	public void run() {
		// получаем значение разделяемого ресурса
		// когда оно станет равным 6 поток завершает выполнение
		while (commonCounter.getCount() < 6) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

}
