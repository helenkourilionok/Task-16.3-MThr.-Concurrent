package by.training.concurrent.main;

import by.training.concurrent.util.Util;

public class ConcurrentMain {

	public static void main(String[] args) {
		Util.performSemaphore();
		Util.performCountDownLatch();
		Util.performCyclicBarrier();
		Util.performExchanger();
		Util.performPhaser();
		Util.performCondition();
		Util.performReentrantLock();
		Util.performReentrantReadWriteLock();
	}
}
