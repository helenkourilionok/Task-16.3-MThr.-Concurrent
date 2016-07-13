package by.training.concurrent.countdownlatch;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchHorse implements Runnable{

    private CountDownLatch startRace;
    //стартовый счётчик,когда он станет равным нулю
    //скачки начнутся
    private CountDownLatch finishRace;
    //счётик конца гонок, когда он станет равным нулю
    //лошадиные гонки закончатся
    private String nameHorse;//имя лошади
    private int distance;//расстояние, которое она должна пробежать
    private List<String> places;//места занятые лошадьми
	
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
             startRace.await();//лошадь ожидает начала скачек
             //поток ждёт обнуления счётчика startRace
             int traveled = 0;//сколько лошадь пробежала на данный момент
             //цикл будет продолжаться до тех пор 
             //пока лошадь не пробежит дистанцию 
             while (traveled < distance)
             {
                 // через 0-2 секунды....
                 Thread.sleep(random.nextInt(3) * 1000);
                 // ... лошадь проходит дистанцию 0-14 пунктов
                 traveled += random.nextInt(15);
                 //прибавляем к уже пройденному расстоянию текущее расстояние
                 System.out.println(nameHorse + " advanced to " + traveled + "!");
             }
             //уменьшаем значение счётчика отвечающего за конец гонок
             //чтобы гонки завершились нужно чтобы все лошади пробежали дистанцию
             //чтобы все потоки завершили цикл
             System.out.println(nameHorse + " crossed the finish!");
             places.add(nameHorse);
             //в список мы добавляем лошадей в порядке достижения
             //ими финишной дистанции
         }
         catch (InterruptedException e)
         {
             e.printStackTrace();
         }finally{
        	 finishRace.countDown();
        	 //при достижении лошадью конца дистанции
        	 //конечный счётчик уменьшает своё значение на 1
        	 //таким образом когда все лошади достигнут
        	 //конца дистанции - гонка завершится
         }
     }
}
