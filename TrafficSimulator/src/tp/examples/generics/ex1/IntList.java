package tp.examples.generics.ex1;

import java.util.Arrays;

public class IntList {

	private final static int _INIT_SIZE = 2;
	private Integer[] elem;
	private int last;

	public IntList() {
		last = -1;
		elem = new Integer[_INIT_SIZE];
	}

	public void addElem(Integer e) {
		if (last == elem.length - 1) {
			elem = Arrays.copyOf(elem, elem.length * 2);
		}
		elem[++last] = e;
	}

	public Integer getElem(int index) {
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
		IntList x = new IntList();
		x.addElem(12);
		x.addElem(244);
		x.addElem(31);
		System.out.println(x.getElem(0));
		System.out.println(x.getElem(1));
		System.out.println(x.getElem(2));
	}
}
