package tp.examples.concutils;

import java.util.concurrent.Exchanger;

public class ExchangerRunnable implements Runnable {

	Exchanger<Object> exchanger = null;
	Object object = null;

	public ExchangerRunnable(Exchanger<Object> exchanger) {
		this.exchanger = exchanger;
		this.object = new Object();
	}

	public void run() {
		try {
			Object previous = this.object;

			this.object = this.exchanger.exchange(this.object);

			System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
