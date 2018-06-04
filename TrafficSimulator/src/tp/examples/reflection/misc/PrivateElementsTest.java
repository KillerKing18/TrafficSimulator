package tp.examples.reflection.misc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrivateElementsTest {
	static public void main(String[] args) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		PrivateObject o = new PrivateObject("The Private Value");

		Field f = PrivateObject.class.getDeclaredField("f");
		f.setAccessible(true);
		String fieldValue = (String) f.get(o);
		System.out.println("fieldValue = " + fieldValue);

		Method m = PrivateObject.class.getDeclaredMethod("getF", null);
		m.setAccessible(true);
		String returnValue = (String) m.invoke(o, null);
		System.out.println("returnValue = " + returnValue);
	}
}
