package by.training.concurrent.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerSendable implements Runnable {

	private Exchanger<String> exchanger;
	// ������ ������������� ��� ������ ����������� �����
	// �������, ������� �������� ����������
	// � �������, ������� ��������� ����������
	private String strExchange;
	//������ , ������� ����� �������� ������� ������

	public ExchangerSendable(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		strExchange = "Test Exchanger";
		try {
			System.out.println("Wait for send message: "+strExchange);
			strExchange = exchanger.exchange(strExchange);
			System.out.println("Message is sent");
			//�������� ������ �������� ������ ��� ������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
