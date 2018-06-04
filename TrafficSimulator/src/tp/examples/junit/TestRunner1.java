package tp.examples.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner1 {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(ATest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		result = JUnitCore.runClasses(BTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
	}

}
