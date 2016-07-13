package by.training.concurrent.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter {
	private Lock lock = new ReentrantLock();
	// ������ ������������� ��� ����������
	// �������
	private int count;
	// ����������� ������

	public int getCount() {
		lock.lock();
		System.out.println("Lock object");
		// ��������� ����������� ������
		try {
			System.out.println(Thread.currentThread().getName() + " gets count: " + count);
			return count++;
		} finally {
			lock.unlock();
			// ������� ���������� �������
			System.out.println("Unlock object");
		}
	}
}
