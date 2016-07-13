package by.training.concurrent.exchanger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Exchanger;

public class ExchangerWritable implements Runnable {

	private Exchanger<String> exchanger;
	// ������ ������������� ��� ������
	// ����������� ����� ��������
	private String strExchange;
	// ������ ��� ������ ����������� ����� ��������
	//������ �������

	public ExchangerWritable(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		FileWriter fileWriter = null;
		BufferedWriter out = null;
		try {
			fileWriter = new FileWriter("D://test.txt");
			//��������� ���� ��� ������
			out = new BufferedWriter(fileWriter);
			System.out.println("Wait for taking message");
			strExchange = exchanger.exchange(new String());
			out.write(strExchange);
			//������ � ���� ���������� ������
			System.out.println("Write message("+strExchange+") in file");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
