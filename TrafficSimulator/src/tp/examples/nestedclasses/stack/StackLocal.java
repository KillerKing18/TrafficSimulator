package tp.examples.nestedclasses.stack;

import java.util.Iterator;

public class StackLocal<T> implements Iterable<T> {
	private static final int initSize = 10;
	Object[] elem;
	private int last;

	StackLocal() {
		elem = new Object[initSize];
		last = -1;
	}

	@SuppressWarnings("unchecked")
	T pop() throws StackError {
		if (last >= 0)
			return (T) elem[last];
		else
			throw new StackError();
	}

	void push(T x) {
		if (last > elem.length - 1)
			resize();
		last++;
		elem[last] = x;
	}

	private void resize() {
		Object[] aux = new Object[elem.length * 2];
		for (int i = 0; i < elem.length; i++)
			aux[i] = elem[i];
		elem = aux;
	}

	@Override
	public Iterator<T> iterator() {
		class StatckIterator implements Iterator<T> {

			int curr = 0;

			@Override
			public boolean hasNext() {
				return curr <= last;
			}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				if (hasNext()) {
					curr++;
					return (T) elem[curr - 1];
				} else
					return null;
			}

			@Override
			public void remove() {
			}

		}

		return new StatckIterator();
	}

}
