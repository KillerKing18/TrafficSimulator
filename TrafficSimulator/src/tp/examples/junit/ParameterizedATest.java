package tp.examples.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedATest {

	private int num;

	public ParameterizedATest(int testParameter) {
		this.num = testParameter;
	}

	// creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 1 }, { 5 }, { 121 } };
		return Arrays.asList(data);
	}

	@Test
	public void inctTest() {
		A tester = new A();
		assertEquals("incr does not return the right value", num, tester.incr(num));
	}

	@Test
	public void decTest() {
		A tester = new A();
		assertEquals("decr does not return the right value", num, tester.decr(num));
	}
}
