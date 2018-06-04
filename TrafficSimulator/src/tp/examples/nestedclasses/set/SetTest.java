package tp.examples.nestedclasses.set;

import java.util.Iterator;

public class SetTest {

	static public void print_1(Set<Integer> s) {
		for (Integer e : s) {
			System.out.println(e+" ");
		}
		System.out.println();
	}

	static public void print_2(Set<Integer> s) {
		Iterator<Integer> it = s.iterator();
		while (it.hasNext())
			System.out.print(it.next()+" ");
		System.out.println();
	}

	static public void main(String[] args) {
		Set<Integer> s = new LinkedListSet<Integer>();
		s.add(4);
		s.add(5);
		s.add(6);
		s.add(7);
		print_2(s);
		s.remove(5);
		print_2(s);
		s.add(5);
		print_2(s);
	}
}
