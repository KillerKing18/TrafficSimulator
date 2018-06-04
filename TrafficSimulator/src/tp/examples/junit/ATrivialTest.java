package tp.examples.junit;

public class ATrivialTest {

	static public void incrTest1() {
		A x = new A();
		int i = x.incr(5);
		if (i != 5)
			throw new TestError("incr does not return the right value");
	}

	static public void decrTest1() {
		A x = new A();
		int i = x.incr(5);
		if (i != 5)
			throw new TestError("decr does not return the right value");
	}

	static public void main(String[] args) {
		incrTest1();
		decrTest1();
	}

}
