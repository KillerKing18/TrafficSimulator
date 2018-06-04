package tp.examples.junit;

public class Misc {

	public void f(Object x) {
		throw new TestError("bla");
		// x.hashCode();
	}

	public void g() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
	}

}
