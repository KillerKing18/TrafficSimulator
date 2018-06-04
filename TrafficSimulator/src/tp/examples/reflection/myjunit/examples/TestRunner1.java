package tp.examples.reflection.myjunit.examples;

import java.util.Collection;


import tp.examples.reflection.myjunit.lib.TPUnitCore;

public class TestRunner1 {

	public static void main(String[] args) {
		Collection<String> result = TPUnitCore.runClasses(AllTests.class);
		for (String err : result ) {
			System.out.println(err);
		}

	}

}
