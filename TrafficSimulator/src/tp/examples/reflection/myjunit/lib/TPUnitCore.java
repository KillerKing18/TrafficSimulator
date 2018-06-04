package tp.examples.reflection.myjunit.lib;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;

public class TPUnitCore {

	public static Collection<String> runClasses(Class<?> c) {
		LinkedList<String> errs = new LinkedList<String>();
		runClasses(c, errs);

		return errs;
	}

	static void runClasses(Class<?> c, Collection<String> errs) {

		runSuites(c, errs);

		try {
			Object o = c.newInstance();
			Method[] methods = c.getMethods();
			for (Method m : methods) {
				if (isTestAnnotated(m)) {
					try {
						m.invoke(o);
					} catch (InvocationTargetException e) {
						errs.add("Class "+c.getName() + ", method " + m.getName() + ": "
								+ e.getTargetException().getMessage());
					}
				}
			}
		} catch (Exception e) {
			errs.add(e.getMessage());
		}

	}

	private static void runSuites(Class<?> c, Collection<String> errs) {
		Annotation a = c.getAnnotation(TPTestSuite.class);
		if (a instanceof TPTestSuite) {
			for (Class<?> elem : ((TPTestSuite) a).value()) {
				runClasses(elem, errs);
			}
		}
	}

	static boolean isTestAnnotated(Method m) {
		Annotation a = m.getAnnotation(TPTest.class);
		System.out.println(a);
		if (a instanceof TPTest) {
			return true;
		}
		return false;
	}
}
