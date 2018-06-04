package tp.examples.generics.ex1;

import java.util.Arrays;

public class ObjList {

	private final static int _INIT_SIZE = 2;
	private Object[] elem;
	private int last;

	public ObjList() {
		last = -1;
		elem = new Object[_INIT_SIZE];
	}

	public void addElem(Object e) {
		if (last == elem.length - 1) {
			elem = Arrays.copyOf(elem, elem.length * 2);
		}
		elem[++last] = e;
	}

	public Object getElem(int index) {
		if (index > last) {
			return null;
		} else {
			return elem[index];
		}
	}

	public int size() {
		return last+1;
	}

	public static void test1() {
		ObjList x = new ObjList();
		x.addElem(1);

		// Integer y = x.getElem(0) + 10;

		// we can cast
		Integer y = (Integer) x.getElem(0) + 10;
		System.out.println(y);
	}

	public static void test2() {
		ObjList x = new ObjList();
		x.addElem("Bla");
		x.addElem(1);

		// compiles, but there is runtime error
		int y = (Integer) x.getElem(0) + 10;
		System.out.println(y);
	}

	public static void main(String[] args) {
		// test1();
		test2();

	}
}
