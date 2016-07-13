package by.training.concurrent.reentrantreadwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
	private static final ReadWriteLock lock = new ReentrantReadWriteLock(true);
	// объект позвол€ющий получить <i> на чтение и запись
	// информации из раздел€емого ресурса

	private static String message = "a";
	// строка - раздел€емый ресурс

	public static void executeReadWriteExample() throws InterruptedException{
		Thread t1 = new Thread(new Read());
		Thread t2 = new Thread(new WriteA());
		Thread t3 = new Thread(new WriteB());
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
	}
	
	static class Read implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 10; i++) {
				// if(lock.isWriteLocked()) {//
				// System.out.println("I'll take the lock from Write");
				// }
				lock.readLock().lock();
				// получение объекта блокировки на чтение ресурса и блокировка
				// ресурса
				System.out.println("ReadThread " + Thread.currentThread().getName() + " ---> Message is " + message);
				lock.readLock().unlock();// снимаем блокировку на чтение
			}
		}
	}

	static class WriteA implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 10; i++) {
				try {
					lock.writeLock().lock();
					// получаем объект блокировки на чтение ресурса и
					// блокируем ресурс дл€ чтени€ информации
					message = message.concat("a");// добавл€ем к строке букву a
					System.out.println("Thread " + Thread.currentThread().getName() + "write a");
				} finally {
					lock.writeLock().unlock();
					// сн€тие блокировки на запись ресурса
				}
			}
		}
	}

	static class WriteB implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 10; i++) {
				try {
					lock.writeLock().lock();
					// получаем объект блокировки на чтение ресурса и
					// блокируем ресурс дл€ чтени€ информации
					message = message.concat("b");
					// добавление буквы b
					System.out.println("Thread " + Thread.currentThread().getName() + "write b");
				} finally {
					lock.writeLock().unlock();
					// освобождаем блокировку на запись
				}
			}
		}
	}
}
