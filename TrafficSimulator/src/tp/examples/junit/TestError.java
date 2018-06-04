package tp.examples.junit;

@SuppressWarnings("serial")
public class TestError extends RuntimeException {

	public TestError(String msg) {
		super(msg);
	}

}
