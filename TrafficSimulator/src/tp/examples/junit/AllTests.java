package tp.examples.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ATest.class, BTest.class })
public class AllTests {
}
