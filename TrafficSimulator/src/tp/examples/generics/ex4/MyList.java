package tp.examples.generics.ex4;

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
		return last + 1;
	}

	@Override
	public String toString() {
		String s = "";

		for (int i = 0; i <= last; i++)
			s = s + elem[i] + " ";
		return s;
	}

	public static void main(String[] args) {
		MyList<Integer> x = new MyList<Integer>();
		x.addElem(1);
		Integer y = x.getElem(0);
		System.out.println(y);
	}
}
