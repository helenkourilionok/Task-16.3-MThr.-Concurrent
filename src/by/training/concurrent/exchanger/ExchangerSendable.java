package by.training.concurrent.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerSendable implements Runnable {

	private Exchanger<String> exchanger;
	// объект синхронизации для обмена информацией между
	// потоком, который посылает информацию
	// и потоком, который принимает информацию
	private String strExchange;
	//строка , которая будет передана другому потоку

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
			//передача потоку приёмнику строки для обмена
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
