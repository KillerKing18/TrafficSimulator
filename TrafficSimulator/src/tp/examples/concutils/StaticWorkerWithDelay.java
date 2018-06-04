package tp.examples.concutils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class StaticWorkerWithDelay {

	private static BlockingQueue<DelayedJob> tasks;

	public static void startWorker() {
		tasks = new DelayQueue<DelayedJob>();

		new Thread() {
			public void run() {
				processTasks();
			}
		}.start();
	}

	public static void addTask(final Runnable t) {
		addTask(t, 0);

	}

	public static void addTask(final Runnable t, int ms) {
		try {
			tasks.put(new DelayedJob(ms, t));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addAndWaitTask(final Runnable t, int ms) {
		Runnable t_aux = new Runnable() {
			public void run() {
				t.run();
				synchronized (this) {
					this.notify();
				}
			}
		};
		synchronized (t_aux) {
			addTask(t_aux, ms);
			try {
				t_aux.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	private static void processTasks() {
		while (!Thread.interrupted()) {
			Runnable task;
			try {
				task = tasks.take().getJob();
				task.run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
