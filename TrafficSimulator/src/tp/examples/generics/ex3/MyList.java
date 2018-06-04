package tp.examples.generics.ex3;

import java.util.Arrays;

public class MyList<T> implements ListI<T> {

	private final static int _INIT_SIZE = 2;
	public T[] elem;
	private int last;

	public MyList() {
		last = -1;
		elem = (T[]) new Object[_INIT_SIZE];
	}

	@Override
	public void addElem(T e) {
		if (last == elem.length - 1) {
			elem = Arrays.copyOf(elem, elem.length * 2);
		}
		elem[++last] = e;
	}

	@Override
	public T getElem(int index) {
		if (index > last) {
			return null;
		} else {
			return elem[index];
		}
	}

	@Override
	public int size() {
		return last+1;
	}


	public static void test1() {
		MyList<Integer> x = new MyList<Integer>();
	//	MyList<Number> y = x; // TYPE ERROR	
	}

	public static void test2() {
		MyList<Integer> x = new MyList<Integer>();
		Utils.print(x);
	}

	public static void main(String[] args) {
		MyList<Integer> x = new MyList<Integer>();
		x.addElem(1);
		x.addElem(2);
		Utils.print(x);
		MyList<String> y = new MyList<String>();
		y.addElem("Bla!");
		y.addElem("Hola!");
		Utils.print(y);
	}
}
