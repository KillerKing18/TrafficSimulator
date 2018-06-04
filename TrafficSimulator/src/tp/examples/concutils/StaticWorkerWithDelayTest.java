package tp.examples.concutils;

public class StaticWorkerWithDelayTest {
	public static void main(String[] args) throws InterruptedException {
		StaticWorkerWithDelay.startWorker();
		StaticWorkerWithDelay.addTask(new Runnable() {

			@Override
			public void run() {
				System.out.println("Job 1");
			}
		}, 10000);
		StaticWorkerWithDelay.addTask(new Runnable() {

			@Override
			public void run() {
				System.out.println("Job 2");
			}
		}, 5000);
		System.out.println("Main is done!");
	}
}
