package by.training.concurrent.condition;

public class ConditionProducer implements Runnable {

	private String[] sourceArray;
	//"внешний источник" из которого производитель берёт 
	//данные и помещает в разделяемый ресурс
	private final ConditionSharedFiFoQueue queue;
	//разделяемый ресурс 
	//очередь, в которую прозводитель помещает 
	//эл-ты из sourceArray
	//а потребитель берёт из очереди значения

	public ConditionProducer(ConditionSharedFiFoQueue queue) {
		this.queue = queue;
		sourceArray =new String[] {"first","second","third",
		 		   "forth","fifth","sixth","seventh",
		 		   "eighth","ninth","tenth"};
	}

	@Override
	public void run() {
		for (String inputWord : sourceArray){
				queue.add(inputWord);
				//производитель заполняет очередь своими продуктами(строками)
				System.out.println("[Producer] Read the object : "+inputWord);
		}
		//добавление нулевого элемента необходимо для приостановки 
		//выполнения потоков
		queue.add(null);
	}

}
