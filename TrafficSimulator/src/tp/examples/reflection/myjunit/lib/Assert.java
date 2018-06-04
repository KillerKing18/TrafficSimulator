package tp.examples.reflection.myjunit.lib;

public class Assert {
	public static void assertEquals(String msg, int expected, int actual) {
		if ( expected != actual ) {
			throw new TPTestError(msg);
		}
	}

}
