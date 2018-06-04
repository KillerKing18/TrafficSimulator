package tp.examples.generics.ex1;

import java.util.Arrays;

public class StringList {

	private final static int _INIT_SIZE = 2;
	private String[] elem;
	private int last;

	public StringList() {
		last = -1;
		elem = new String[_INIT_SIZE];
	}

	public void addElem(String e) {
		if (last == elem.length - 1) {
			elem = Arrays.copyOf(elem, elem.length * 2);
		}
		elem[++last] = e;
	}

	public String getElem(int index) {
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
		StringList x = new StringList();
		x.addElem("Hola!");
		x.addElem("Hey!");
		x.addElem("Bla!");
		System.out.println(x.getElem(0));
		System.out.println(x.getElem(1));
		System.out.println(x.getElem(2));
	}
}
