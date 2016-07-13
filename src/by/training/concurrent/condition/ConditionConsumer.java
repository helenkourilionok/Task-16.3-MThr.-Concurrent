package by.training.concurrent.condition;

import java.util.HashSet;
import java.util.Set;

public class ConditionConsumer implements Runnable{
	
	private final Set<String> seenObjects = new HashSet<String>();
	//��������� ���������, ������� �����������
	//����� ����� �� ������ �������
	private int total = 0;
	private final ConditionSharedFiFoQueue queue;
	//�������, �� ������� ����������� ����� ����� �������� 
	
	public ConditionConsumer(ConditionSharedFiFoQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		do {
			String obj = queue.remove();
			if(obj == null)
				break;
			//���� ����� ���������� ����������
			//���� �� ������� �� ����� ������� null
			//���������
			
			if(!seenObjects.contains(obj)) {
				//���� ���������� �� ������� ������� ��� �� ��� 
				//�������� � ������ ��������� �����������
				++total;
				//����������� ������� ���-�� ��������� �����������
				seenObjects.add(obj);
				//��������� ������� � ������ ��������� �����������
			}
			
			System.out.println("[Consumer] Read the element: " + obj);
			
		} while(true);
		System.out.println("\n[Consumer] " + total + " distinct words have been read...");
	}

}
