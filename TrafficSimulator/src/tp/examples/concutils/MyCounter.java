package tp.examples.concutils;

public class MyCounter {
	int c;

	public MyCounter(int c) {
		this.c = c;
	}

	synchronized public void countDown() {
		c--;
		if (c == 0)
			this.notifyAll();
	}

	synchronized public void await() throws InterruptedException {
		if (c == 0)
			return;

		this.wait();
	}

}
