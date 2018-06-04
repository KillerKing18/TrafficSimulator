package tp.examples.generics.ex1;

import java.util.Arrays;

public class MyList<T extends Object> {

	private final static int _INIT_SIZE = 2;
	public T[] elem;
	private int last;

	public MyList() {
		last = -1;
		elem = (T[]) new Object[_INIT_SIZE];
	}

	public void addElem(T e) {
		if (last == elem.length - 1) {
			elem = Arrays.copyOf(elem, elem.length * 2);
		}
		elem[++last] = e;
	}

	public T getElem(int index) {
		if (index > last) {
			return null;
		} else {
			return elem[index];
		}
	}

	public int size() {
		return last+1;
	}

	public static void main(String[] args) {
		MyList<String> x = new MyList<String>();
		x.addElem("aaa");
		
		String y = x.getElem(0);
		System.out.println(y);
	}
}
