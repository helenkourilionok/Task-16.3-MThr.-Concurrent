package by.training.concurrent.phaser;

import java.util.concurrent.Phaser;

public class PhaserThread implements Runnable {
	private String source;
	private Phaser phaser;

	public PhaserThread(String source, Phaser phaser) {
		this.source = source;
		this.phaser = phaser;
		this.phaser.register();
		//������������ ������� ����� ��� �������� �������������
	}

	@Override
	public void run() {
		String temp;
		System.out.println("Create variable");
		phaser.arriveAndAwaitAdvance();
		//���� ��� ������ �� ��������� ����� ����
		//������ ��������� ����� ���� �������
		temp = source;
		System.out.println("Give value to variable");
		phaser.arriveAndAwaitAdvance();
		System.out.println("temp = " + temp);
		phaser.arriveAndDeregister();
		//������� �� ����� ���������� �������������
	}
}
