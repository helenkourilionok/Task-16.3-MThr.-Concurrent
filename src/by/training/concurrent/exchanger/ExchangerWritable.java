package by.training.concurrent.exchanger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Exchanger;

public class ExchangerWritable implements Runnable {

	private Exchanger<String> exchanger;
	// объект синхронизации для обмена
	// информацией между потоками
	private String strExchange;
	// строка для обмена информацией между потоками
	//строка приёмник

	public ExchangerWritable(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		FileWriter fileWriter = null;
		BufferedWriter out = null;
		try {
			fileWriter = new FileWriter("D://test.txt");
			//открываем файл для записи
			out = new BufferedWriter(fileWriter);
			System.out.println("Wait for taking message");
			strExchange = exchanger.exchange(new String());
			out.write(strExchange);
			//запись в файл полученной строки
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
