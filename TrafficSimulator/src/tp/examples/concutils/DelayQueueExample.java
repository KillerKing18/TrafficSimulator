package tp.examples.concutils;

import java.util.concurrent.DelayQueue;

public class DelayQueueExample {

	public static void main(String[] args) throws InterruptedException {
		DelayQueue<DelayedElem> queue = new DelayQueue<DelayedElem>();

		DelayedElem element1 = new DelayedElem(10000, "1");
		DelayedElem element2 = new DelayedElem(5000, "2");

		queue.put(element1);
		queue.put(element2);

		System.out.println(queue.take());
		System.out.println(queue.take());
	}
}
