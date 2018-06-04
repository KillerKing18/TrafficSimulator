package tp.examples.concutils;

import java.util.concurrent.CountDownLatch;

public class Decrementer implements Runnable {

	MyCounter latch = null;

	public Decrementer(MyCounter latch) {
		this.latch = latch;
	}

	public void run() {

		try {
			Thread.sleep(1000);
			this.latch.countDown();

			Thread.sleep(1000);
			this.latch.countDown();

			Thread.sleep(1000);
			this.latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
