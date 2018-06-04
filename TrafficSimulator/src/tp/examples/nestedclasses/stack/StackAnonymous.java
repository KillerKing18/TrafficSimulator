package tp.examples.nestedclasses.stack;

import java.util.Iterator;

public class StackAnonymous<T> implements Iterable<T> {
	private static final int initSize = 10;
	T[] elem;
	private int last;

	@SuppressWarnings("unchecked")
	StackAnonymous() {
		elem = (T[]) new Object[initSize];
		last = -1;
	}

	T pop() throws StackError {
		if (last >= 0)
			return elem[last];
		else
			throw new StackError();
	}

	void push(T x) {
		if (last == elem.length - 1)
			resize();
		elem[++last] = x;
	}

	private void resize() {
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Object[elem.length * 2];
		for (int i = 0; i < elem.length; i++)
			aux[i] = elem[i];
		elem = aux;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			int curr = 0;

			@Override
			public boolean hasNext() {
				return curr <= last;
			}

			@Override
			public T next() {
				if (hasNext()) {
					return elem[curr++];
				} else
					return null;
			}

			@Override
			public void remove() {
			}

		};
	}

}
