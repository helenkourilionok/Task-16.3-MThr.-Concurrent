package by.training.concurrent.reentrantlock;

public class ReentrantLockCountSync implements Runnable {

	private ReentrantLockCounter commonCounter = new ReentrantLockCounter();
	//����������� �������� ������
	
	public ReentrantLockCountSync(ReentrantLockCounter commonCounter){
		this.commonCounter = commonCounter;
	}
	
	@Override
	public void run() {
		// �������� �������� ������������ �������
		// ����� ��� ������ ������ 6 ����� ��������� ����������
		while (commonCounter.getCount() < 6) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

}
