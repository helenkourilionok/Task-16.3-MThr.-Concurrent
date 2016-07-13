package by.training.concurrent.condition;

public class ConditionProducer implements Runnable {

	private String[] sourceArray;
	//"������� ��������" �� �������� ������������� ���� 
	//������ � �������� � ����������� ������
	private final ConditionSharedFiFoQueue queue;
	//����������� ������ 
	//�������, � ������� ������������ �������� 
	//��-�� �� sourceArray
	//� ����������� ���� �� ������� ��������

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
				//������������� ��������� ������� ������ ����������(��������)
				System.out.println("[Producer] Read the object : "+inputWord);
		}
		//���������� �������� �������� ���������� ��� ������������ 
		//���������� �������
		queue.add(null);
	}

}
