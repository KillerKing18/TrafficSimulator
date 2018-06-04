package tp.examples.concutils;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

	public static void main(String[] args) throws InterruptedException {
		Exchanger<Object> exchanger = new Exchanger<Object>();

		ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger);

		ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger);

		new Thread(exchangerRunnable1).start();
		new Thread(exchangerRunnable2).start();

	}
}
