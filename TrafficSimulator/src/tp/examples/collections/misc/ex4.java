package tp.examples.collections.misc;

import java.util.NavigableSet;
import java.util.TreeSet;

public class ex4 {
	public static void test() {
		NavigableSet<String> l = new TreeSet<String>();
		System.out.println("======");
		l.add("John");
		l.add("Mike");
		l.add("Andres");
		l.add("Mike"); // not added
		System.out.println(l);
		System.out.println();

		System.out.println();
		System.out.println(l.ceiling("Mike"));
		System.out.println(l.higher("Mike"));
		System.out.println(l.floor("Mike"));
		System.out.println(l.lower("Mike"));

		System.out.println();
		System.out.println(l.ceiling("John"));
		System.out.println(l.higher("John"));
		System.out.println(l.floor("John"));
		System.out.println(l.lower("John"));

		System.out.println();
		System.out.println(l.ceiling("Boris"));
		System.out.println(l.higher("Boris"));
		System.out.println(l.floor("Boris"));
		System.out.println(l.lower("Boris"));

	}

	public static void main(String[] args) {
		test();
	}
}
