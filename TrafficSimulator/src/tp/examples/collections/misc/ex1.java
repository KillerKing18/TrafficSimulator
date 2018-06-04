package tp.examples.collections.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ex1 {

	static public void test1() {
		List<Integer> l = new ArrayList<>();
		l.add(10);
		l.add(20);
		l.add(15);
		l.add(7);
		
		
		print_1(l);
		print_2(l);
	}

	static public void test2() {
		List<String> l = new Vector<>();
		l.add("Hola!");
		l.add("Hello!");
		l.add("Ciao!");

		print_1(l);
		print_2(l);
	}
	
	static public void print_1(Collection<?> l) {
		System.out.println("----");
		Iterator<?> it = l.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("----");

	}

	static public void print_2(Collection<?> l) {
		System.out.println("----");
		for (Object o : l) {
			System.out.println(o);
		}
		System.out.println("----");
	}

	static public void main(String[] args) { 
		test1();
		test2();
	}
}
