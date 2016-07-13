package by.training.concurrent.condition;

import java.util.HashSet;
import java.util.Set;

public class ConditionConsumer implements Runnable{
	
	private final Set<String> seenObjects = new HashSet<String>();
	//коллекция продуктов, которые потребитель
	//будет брать из общего ресурса
	private int total = 0;
	private final ConditionSharedFiFoQueue queue;
	//очередь, из которой потребитель будет брать продукты 
	
	public ConditionConsumer(ConditionSharedFiFoQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		do {
			String obj = queue.remove();
			if(obj == null)
				break;
			//цикл будет выполнятся бесконечно
			//пока из очереди не будет получен null
			//элементов
			
			if(!seenObjects.contains(obj)) {
				//если полученный из очереди продукт ещё не был 
				//добавлен в список продуктов потребителя
				++total;
				//увеличиваем счётчик кол-ва продуктов потребителя
				seenObjects.add(obj);
				//добавляем продукт в список продуктов потребителя
			}
			
			System.out.println("[Consumer] Read the element: " + obj);
			
		} while(true);
		System.out.println("\n[Consumer] " + total + " distinct words have been read...");
	}

}
