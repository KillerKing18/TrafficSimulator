package tp.examples.junit;

import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MiscTest {

	@Test
	public void test7() {
		try {
			Misc x = new Misc();
			x.f(null);
		} catch (Exception e) {
			fail("An exception has been thrown by f");
		}
	}

	@Test(expected = NullPointerException.class)
	public void test2() {
		Misc x = new Misc();
		x.f(null);
	}

	@Test
	public void test3() {
		try {
			Misc x = new Misc();
			x.f(null);
			fail("f  must throw an exception");
		} catch (Exception e) {
		}
	}

	@Test(timeout = 100)
	public void test4() {
		Misc x = new Misc();
		x.g();
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void test5() {
		exception.expect(NullPointerException.class);
		// exception.expectMessage("An Expected Messgae");
		Misc x = new Misc();
		x.f(null);
	}

}
