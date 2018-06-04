package tp.examples.concutils;

import java.util.concurrent.CountDownLatch;

public class Waiter implements Runnable {

	MyCounter latch = null;

	public Waiter(MyCounter latch) {
		this.latch = latch;
	}

	public void run() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Waiter Released");
	}
}
