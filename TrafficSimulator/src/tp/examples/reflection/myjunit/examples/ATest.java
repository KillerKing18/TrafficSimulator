package tp.examples.reflection.myjunit.examples;

import static tp.examples.reflection.myjunit.lib.Assert.assertEquals;
import tp.examples.reflection.myjunit.lib.TPTest;


public class ATest {
	
	public void incrTest() {
		A x = new A();
		int i = x.incr(5);
		assertEquals("incr does not return the right value", 6, i);
	}

	@TPTest
	public void decTest() {
		A x = new A();
		int i = x.decr(5);
		assertEquals("decr does not have the right value", 4, i);
	}

}
