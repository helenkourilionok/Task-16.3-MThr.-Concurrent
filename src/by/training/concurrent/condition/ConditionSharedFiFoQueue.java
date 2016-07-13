package by.training.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionSharedFiFoQueue {
	private String[] elems;
	//������ �����, ������� � �������� ����������� ��������
	//� ������� �������������-�����������
	//��� ��������� ����������� �������� ��������� �� �������
	//��� ��������� ������������� � ������� - ��-�� ����������� � ������
	private int current;
	//������ �������� �������� ������� �����
	private int placeIndex;
	//������, �� �������� ����-�� ���������� ��-�� � ������
	private int removeIndex;
	//������, �� �������� ����-�� �������� ��-�� �� �������
	
	private final Lock lock;
	//������ ���������� ��� ������� �����(������������ �������)
	private final Condition isEmpty;
	//������� ���������� ��� �������� ���������� ��������� � elems 
	private final Condition isFull;
	//������� ���������� ��� �������� 
	//��������� �� �������� ������ elems 
	
	public ConditionSharedFiFoQueue(int capacity) {
		this.lock = new ReentrantLock();
		this.isEmpty = this.lock.newCondition();
		this.isFull = this.lock.newCondition();
		this.elems = new String[capacity];
		//������ ������ ����� �������� �����������
	}
	
	//���������� �������� � ������ �����
	public void add(String elem) {
		try{
			lock.lock();//��������� ������� ���������� �� ����� ������
			while(current >= elems.length)
				isFull.await();
			//����� ������������� ���������
			//� ��������� ��������,���� ������ ��������
		
			elems[placeIndex] = elem;
			//���������� � ������ ������
			
			placeIndex = (placeIndex + 1) % elems.length;
			//�������� ������, �� �������� ����� ����������� 
			//���������� ��-�� ��� ��������� ����������
			
			++current;
			//����������� �������� ������� �������� ��-�� �� 1
			//������ ����� ������������ ����� ���-�� ��������� � ������� �� ������ ������
			
			//���������� �����������, ������� ������� 
			//������� � ������������ ������� 
			isEmpty.signal();
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
			//������ ���������� � �������
		}
	}

	public String remove() {
		String elem = null;
		try{	
			lock.lock();//��������� ��������� �� ����� ������
			while(current <= 0)
				isEmpty.await();
			//���� � ������� ��� ���������, 
			//�� ����� ����������� ��������� � ��������� ��������
			
			elem = elems[removeIndex];
			//���� � ������� ���� ��-��,
			//�� ����� ����������� ����� �� �����
			//�.�. ������� �� �������
			
			removeIndex = (removeIndex + 1) % elems.length;
			//��������� ������� ���������� ��������,
			//������� ����� �������
			
			--current;
			//��������� ������ �������� �������� �� �������
			
			//���������� ������������� �.�. ������ ��
			//����� �������� ������� � ������
			isFull.signal();
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
			//������� ���������� � ������������ �������
		}
		return elem;
	}
}
