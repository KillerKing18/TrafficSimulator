package tp.examples.nestedclasses.set;

import java.util.Iterator;

public class LinkedListSet<T> implements Set<T> {
	
	Cell<T> list;
	
	private static class Cell<T> {
		T data;
		Cell<T> next;
		
		Cell(T data, Cell<T> next) {
			this.data = data;
			this.next = next;
		}	
	}		
	
	public void add(T x) {
		if ( !has(x) )
			list = new Cell<T>(x,list);
	}

	public void remove(T x) {
		Cell<T> last = list;
		Cell<T> curr = list;
		while ( curr != null && curr.data != x) {
			last = curr;
			curr = curr.next;
		}
		
		if ( curr != null ) {
			if ( last == null ) list = curr.next;
			else last.next = curr.next;
		}
	}

	public boolean has(T x) {
		Cell<T> aux = list;
		while ( aux != null && aux.data != x)
			aux = aux.next;
		
		return aux != null;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			Cell<T>p = list;
			
			@Override
			public boolean hasNext() {
				return p != null;
			}

			@Override
			public T next() {
				Cell<T> aux = p;
				if ( p != null ) {
					p = p.next;
				}
				return aux.data;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
			}
		};
	}
	
	
}
