package by.training.concurrent.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter {
	private Lock lock = new ReentrantLock();
	// объект синхронизации для блокировки
	// ресурса
	private int count;
	// разделяемый ресурс

	public int getCount() {
		lock.lock();
		System.out.println("Lock object");
		// блокируем разделяемый ресурс
		try {
			System.out.println(Thread.currentThread().getName() + " gets count: " + count);
			return count++;
		} finally {
			lock.unlock();
			// снимаем блокировку ресурса
			System.out.println("Unlock object");
		}
	}
}
