package tp.examples.collections.iter;

import java.util.Arrays;
import java.util.Iterator;

public class MyList<T> implements Iterable<T> {

	private final static int _INIT_SIZE = 2;
	public T[] elem;
	private int last;

	@SuppressWarnings("unchecked") // the new is type safe
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
		return last + 1;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int curr = 0;

			@Override
			public boolean hasNext() {
				return curr <= last;
			}

			@Override
			public T next() {
				curr++;
				return elem[curr - 1];
			}
		};
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
		x.addElem(15);
		x.addElem(5);
		
		for( Integer i : x ) {
			System.out.println(i);
		}
	}

}
