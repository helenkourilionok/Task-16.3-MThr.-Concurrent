package by.training.concurrent.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

import by.training.concurrent.condition.ConditionConsumer;
import by.training.concurrent.condition.ConditionProducer;
import by.training.concurrent.condition.ConditionSharedFiFoQueue;
import by.training.concurrent.countdownlatch.CountDownLatchRace;
import by.training.concurrent.cyclicbarrier.CyclicBarrierCar;
import by.training.concurrent.cyclicbarrier.CyclicBarrierFerryBoat;
import by.training.concurrent.exchanger.ExchangerSendable;
import by.training.concurrent.exchanger.ExchangerWritable;
import by.training.concurrent.phaser.PhaserThread;
import by.training.concurrent.reentrantlock.ReentrantLockCountSync;
import by.training.concurrent.reentrantlock.ReentrantLockCounter;
import by.training.concurrent.reentrantreadwritelock.ReadWriteLockExample;
import by.training.concurrent.semaphore.SemaphoreCommonResource;
import by.training.concurrent.semaphore.SemaphoreCountThread;

public final class Util {
	public static void performSemaphore() {
		List<Thread> listThread = new ArrayList<Thread>();
		Semaphore semaphore = new Semaphore(1);
		// ������ ������� � ����� ����������� �� ������ � �������
		SemaphoreCommonResource commonResource = new SemaphoreCommonResource();
		// ������ ����� ������
		for (int i = 0; i < 4; i++) {
			listThread.add(new Thread(new SemaphoreCountThread(commonResource, semaphore)));
			// �������� 4 ������, ������� ������� ����� ������
		}
		startJoinThread(listThread);
	}

	public static void performCountDownLatch() {
		String[] horseName = { "Beverly Takes a Bath", "RockerHorse", "Phineas", "Ferb", "Tin Cup",
				"I'm Faster Than a Monkey", "Glue Factory Reject" };
		int distance = 150;
		CountDownLatch startRace = new CountDownLatch(1);
		// ������ ������� �� ��������� 1
		// ������ ����� ����� ��������� ��������
		// �.�. ���� �� ��������� ����� ������ countDown
		// �������� �� ����� ������
		CountDownLatch finishRace = new CountDownLatch(horseName.length);
		// �������� �������� ����� ���������� �������
		// �������� �� ��������� �����
		CountDownLatchRace race = new CountDownLatchRace(startRace, finishRace, distance, horseName);
		System.out.println("It's a race of " + distance + " lengths");
		System.out.println("Press Enter to run the race....");
		race.makeRace();
	}

	public static void performCyclicBarrier() {
		List<Thread> listThread = new ArrayList<Thread>();
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicBarrierFerryBoat());
		// 3 ������ ������ ������� � ������� ��� ���� ���������� ��������
		// ����������� �� ������ ��������� ������������
		for (int i = 0; i < 9; i++) {
			listThread.add(new Thread(new CyclicBarrierCar(i, cyclicBarrier)));
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		startJoinThread(listThread);
	}

	public static void performExchanger() {
		List<Thread> listThread = new ArrayList<Thread>();
		Exchanger<String> exch = new Exchanger<String>();
		listThread.add(new Thread(new ExchangerSendable(exch)));
		listThread.add(new Thread(new ExchangerWritable(exch)));
		startJoinThread(listThread);
	}

	public static void performPhaser() {
		Phaser p = new Phaser(1);
		new Thread(new PhaserThread("A", p)).start();
		int num = p.getPhase();
		p.arriveAndAwaitAdvance();
		System.out.println("End of phase �" + num);
		num = p.getPhase();
		p.arriveAndAwaitAdvance();
		System.out.println("End of phase �" + num);
		num = p.getPhase();
		p.arriveAndAwaitAdvance();
		System.out.println("End of phase �" + num);
		p.arriveAndDeregister();
	}

	public static void performCondition() {
		int capacity = 5;
		List<Thread> listThread = new ArrayList<Thread>();
		ConditionSharedFiFoQueue sharedQueue = new ConditionSharedFiFoQueue(capacity);
		// Create a producer and a consumer.
		listThread.add(new Thread(new ConditionProducer(sharedQueue)));
		listThread.add(new Thread(new ConditionConsumer(sharedQueue)));
		startJoinThread(listThread);
	}

	public static void performReentrantLock(){
		ReentrantLockCounter commonCounter = new ReentrantLockCounter();
		//����������� �������� ������
		List<Thread> listThread = new ArrayList<Thread>();
		for (int i = 0; i < 2; i++) {
			listThread.add(new Thread(new ReentrantLockCountSync(commonCounter)));
		}
		startJoinThread(listThread);
	}

	public static void performReentrantReadWriteLock(){
		try {
			ReadWriteLockExample.executeReadWriteExample();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void startJoinThread(List<Thread> listThread){
		for (Thread thread : listThread) {
			thread.start();
		}
		for (Thread thread : listThread) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
