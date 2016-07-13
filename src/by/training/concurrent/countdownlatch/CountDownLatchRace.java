package by.training.concurrent.countdownlatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchRace{

    private int distance;
    //���������, ������� ������ ��������� ������
    private CountDownLatch startRace;
    //�������,������� ���������� ������ �����
    private CountDownLatch finishRace;
    //�������, ������� ���������� ����� �����
    private List<String> horses;
    //������ ��� �������
    
    public CountDownLatchRace(CountDownLatch startRace,
    						  CountDownLatch finishRace,int distance,String... names)
    {
    	this.distance = distance;
    	this.startRace = startRace;
    	this.finishRace = finishRace;
    	this.horses = new ArrayList<String>();
        this.horses.addAll(Arrays.asList(names));
    }
    
    public void makeRace()
    {
        System.out.println("And the horses are stepping up to the gate...");
        List<String> places = Collections.synchronizedList(new ArrayList<String>());
  
        System.out.println("And... they're off!");
        startRace.countDown();
        //�������� ������� ������ �����
        //������ ��������� ����� ����� ���� ������
        for(String nameHorse:horses){
        	//������ �������(������)
        	new Thread(new CountDownLatchHorse(startRace, finishRace, nameHorse, distance, places)).start();
        }
        try {
			finishRace.await();//������� ���� ��� ������(������) ��������� �������� ������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("And we have our winners!");
        System.out.println(places.get(0) + " took the gold...");
        System.out.println(places.get(1) + " got the silver...");
        System.out.println("and " + places.get(2) + " took home the bronze.");
    }
}
