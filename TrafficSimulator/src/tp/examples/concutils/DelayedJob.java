package tp.examples.concutils;

import java.util.concurrent.Delayed;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class DelayedJob implements Delayed {

	private long d;
	private Runnable job;
	SynchronousQueue<Integer> x;

	DelayedJob(int ms, Runnable job) {
		this.d = java.lang.System.currentTimeMillis() + ms;
		this.job = job;
	}

	public Runnable getJob() {
		return job;
	}

	@Override
	public int compareTo(Delayed o) {
		long d1 = this.getDelay(TimeUnit.MILLISECONDS);
		long d2 = o.getDelay(TimeUnit.MILLISECONDS);
		if (d1 < d2)
			return -1;
		else if (d1 == d2)
			return 0;
		else
			return 1;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// we assume unit is TimeUnit.MILLISECONDS
		return d - java.lang.System.currentTimeMillis();
	}

}
