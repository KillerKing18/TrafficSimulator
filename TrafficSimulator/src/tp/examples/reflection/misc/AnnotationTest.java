package tp.examples.reflection.misc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {
	static void test1() {
		Class<SomeClass> aClass = SomeClass.class;
		Annotation[] annotations = aClass.getAnnotations();

		for (Annotation annotation : annotations) {
			if (annotation instanceof MyAnnotation) {
				MyAnnotation myAnnotation = (MyAnnotation) annotation;
				System.out.println("name: " + myAnnotation.name());
				System.out.println("value: " + myAnnotation.value());
			}
		}
	}

	static void test2() {
		Class<SomeClass> aClass = SomeClass.class;
		Annotation annotation = aClass.getAnnotation(MyAnnotation.class);

		if (annotation instanceof MyAnnotation) {
			MyAnnotation myAnnotation = (MyAnnotation) annotation;
			System.out.println("name: " + myAnnotation.name());
			System.out.println("value: " + myAnnotation.value());
		}
	}

	static void test3() throws NoSuchMethodException, SecurityException {
		Class<SomeClass> aClass = SomeClass.class;

		Method method = aClass.getMethod("doSomething", null);
		Annotation[] annotations = method.getDeclaredAnnotations();

		for (Annotation annotation : annotations) {
			if (annotation instanceof MyAnnotation) {
				MyAnnotation myAnnotation = (MyAnnotation) annotation;
				System.out.println("name: " + myAnnotation.name());
				System.out.println("value: " + myAnnotation.value());
			}
		}

	}

	static void test4() throws NoSuchMethodException, SecurityException {
		Class<SomeClass> aClass = SomeClass.class;

		Method method = aClass.getMethod("doSomething", null);
		Annotation annotation = method.getAnnotation(MyAnnotation.class);

		if (annotation instanceof MyAnnotation) {
			MyAnnotation myAnnotation = (MyAnnotation) annotation;
			System.out.println("name: " + myAnnotation.name());
			System.out.println("value: " + myAnnotation.value());
		}

	}

	static public void main(String[] args) throws NoSuchMethodException,
			SecurityException {
		test1();
		test2();
		test3();
		test4();
	}

}
