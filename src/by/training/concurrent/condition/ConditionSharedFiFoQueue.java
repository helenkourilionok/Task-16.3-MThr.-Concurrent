package by.training.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionSharedFiFoQueue {
	private String[] elems;
	//массив строк, который и является разделяемым ресурсом
	//в системе производитель-потребитель
	//при обращении потребителя элементы удаляются из массива
	//при обращении производителя к массиву - эл-ты добавляются в массив
	private int current;
	//индекс текущего элемента массива строк
	private int placeIndex;
	//индекс, по которому осущ-ся добавление эл-та в массив
	private int removeIndex;
	//индекс, по которому осущ-ся удаление эл-та из массива
	
	private final Lock lock;
	//объект блокировки для массива строк(разделяемого ресурса)
	private final Condition isEmpty;
	//условие блокировки для проверки отсутствия элементов в elems 
	private final Condition isFull;
	//условие блокировки для проверки 
	//полностью ли заполнен массив elems 
	
	public ConditionSharedFiFoQueue(int capacity) {
		this.lock = new ReentrantLock();
		this.isEmpty = this.lock.newCondition();
		this.isFull = this.lock.newCondition();
		this.elems = new String[capacity];
		//создаём массив строк заданной размерности
	}
	
	//добавление элемента в массив строк
	public void add(String elem) {
		try{
			lock.lock();//получение потоком блокировки на общий ресурс
			while(current >= elems.length)
				isFull.await();
			//поток производитель переходит
			//в состояние ожидания,если массив заполнен
		
			elems[placeIndex] = elem;
			//добавление в массив строки
			
			placeIndex = (placeIndex + 1) % elems.length;
			//получаем индекс, по которому будет осущетвлено 
			//добавление эл-та при следующем добавлении
			
			++current;
			//увеличиваем значение индекса текущего эл-та на 1
			//данное число представляет собой кол-во элементов в массиве на данный момент
			
			//пробуждаем потребителя, который ожидает 
			//доступа к разделяемому ресурсу 
			isEmpty.signal();
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
			//снятие блокировки с ресурса
		}
	}

	public String remove() {
		String elem = null;
		try{	
			lock.lock();//получение блокирвки на общий ресурс
			while(current <= 0)
				isEmpty.await();
			//если в массиве нет элементов, 
			//то поток потребитель переходит в состояние ожидания
			
			elem = elems[removeIndex];
			//если в массиве есть эл-ты,
			//то поток потребитель может их взять
			//т.е. удалить из массива
			
			removeIndex = (removeIndex + 1) % elems.length;
			//получение индекса следующего элемента,
			//который можно удалить
			
			--current;
			//уменьшаем индекс текущего элемента на единицу
			
			//пробуждаем производителя т.к. теперь он
			//может добавить элемент в массив
			isFull.signal();
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
			//снимаем блокировку с разделяемого ресурса
		}
		return elem;
	}
}
