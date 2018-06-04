package tp.examples.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ATest {
	@Test
	public void incrTest() {
		A x = new A();
		int i = x.incr(5);
		assertEquals("incr does not return the right value", 6, i);
	}

	@Test
	public void decTest() {
		A x = new A();
		int i = x.decr(5);
		assertEquals("decr does not have the right value", 4, i);
	}
	
	void foo() {
		
	}

}
