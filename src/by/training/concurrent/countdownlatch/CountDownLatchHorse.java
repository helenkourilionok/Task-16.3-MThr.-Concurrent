package by.training.concurrent.countdownlatch;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchHorse implements Runnable{

    private CountDownLatch startRace;
    //��������� �������,����� �� ������ ������ ����
    //������ ��������
    private CountDownLatch finishRace;
    //������ ����� �����, ����� �� ������ ������ ����
    //��������� ����� ����������
    private String nameHorse;//��� ������
    private int distance;//����������, ������� ��� ������ ���������
    private List<String> places;//����� ������� ��������
	
	public CountDownLatchHorse(CountDownLatch startRace, CountDownLatch finishRace,
								String nameHorse,int distance,List<String> places) {
		super();
		this.startRace = startRace;
		this.finishRace = finishRace;
		this.nameHorse = nameHorse;
		this.distance = distance;
		this.places = places;
	}

	@Override
	public void run() {
		 try
         {
			 Random random = new Random();
             System.out.println(nameHorse + " stepping up to the gate...");
             startRace.await();//������ ������� ������ ������
             //����� ��� ��������� �������� startRace
             int traveled = 0;//������� ������ ��������� �� ������ ������
             //���� ����� ������������ �� ��� ��� 
             //���� ������ �� �������� ��������� 
             while (traveled < distance)
             {
                 // ����� 0-2 �������....
                 Thread.sleep(random.nextInt(3) * 1000);
                 // ... ������ �������� ��������� 0-14 �������
                 traveled += random.nextInt(15);
                 //���������� � ��� ����������� ���������� ������� ����������
                 System.out.println(nameHorse + " advanced to " + traveled + "!");
             }
             //��������� �������� �������� ����������� �� ����� �����
             //����� ����� ����������� ����� ����� ��� ������ ��������� ���������
             //����� ��� ������ ��������� ����
             System.out.println(nameHorse + " crossed the finish!");
             places.add(nameHorse);
             //� ������ �� ��������� ������� � ������� ����������
             //��� �������� ���������
         }
         catch (InterruptedException e)
         {
             e.printStackTrace();
         }finally{
        	 finishRace.countDown();
        	 //��� ���������� ������� ����� ���������
        	 //�������� ������� ��������� ��� �������� �� 1
        	 //����� ������� ����� ��� ������ ���������
        	 //����� ��������� - ����� ����������
         }
     }
}
