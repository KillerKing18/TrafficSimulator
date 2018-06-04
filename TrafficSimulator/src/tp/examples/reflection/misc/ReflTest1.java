package tp.examples.reflection.misc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.Class;

public class ReflTest1 {

	static public void printMethods(Class<?> c) {
		System.out.println("Methods of "+c.getName());
		System.out.println("Supuer class "+c.getSuperclass().getName());
		
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			System.out.println("method:  " + method.getName());
		}

		for ( Field f : c.getDeclaredFields() ) {
			System.out.println("field:  " + f.getName());
		}
		

	}

	static public void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		printMethods(java.util.ArrayList.class);
		printMethods(Class.forName("tp.examples.junit.A"));
		Class<?> c = PrivateObject.class;
		Field f = c.getField("g");
		Method m = c.getMethod("h");
		PrivateObject x = new PrivateObject("bla");
		f.set(x,"bla bla");
		System.out.println(f.get(x));
		m.invoke(x);
		
		
	}

}
