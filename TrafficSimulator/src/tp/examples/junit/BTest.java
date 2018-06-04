package tp.examples.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BTest {
	@Test
	public void fTest() {
		B x = new B();
		int i = x.f(5);
		assertEquals("f does not return the right value", 6, i);
	}

	@Test
	public void gTest() {
		B x = new B();
		int i = x.g(5);
		assertEquals("g does not have the right value", 4, i);
	}

}
