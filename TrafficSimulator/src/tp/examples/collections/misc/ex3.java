package tp.examples.collections.misc;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class ex3 {
	public static void test1(Set<String> l) {
		System.out.println("======");
		l.add("John");
		l.add("Mike");
		l.add("Andres");
		l.add("Mike"); // not added
		System.out.println(l);

		System.out.println("----");
		for (String s : l) {
			System.out.println(s);
		}
		System.out.println("----");

		l.remove("Mike");
		System.out.println(l);

	}

	public static void test2(Set<Person> l) {
		System.out.println("======");
		l.add(new Person("John", 10));
		l.add(new Person("Mike", 4));
		l.add(new Person("Andres", 15));
		l.add(new Person("Michael", 4)); // not added if we change person to ignore name
		System.out.println(l);

		System.out.println("----");
		for (Person s : l) {
			System.out.println(s);
		}
		System.out.println("----");

		l.remove(new Person("Mike", 4));
		System.out.println(l);

	}

	public static void main(String[] args) {

		//( new HashSet<>());
		// test1( new LinkedHashSet<>());
		// test1( new TreeSet<>());
		// test2( new HashSet<>());
		// test2( new LinkedHashSet<>());

		// in the following example we need a comparator since Person does not implement
		// Comparable
		/*
		  test2(new TreeSet<>(new Comparator<Person>() {
		 
			@Override
			public int compare(Person o1, Person o2) {
				return o1.getId() > o2.getId() ? 1 : o1.getId() < o2.getId() ? -1 : 0;
				//return o1.getName().compareTo(o2.getName());
			}
		}));
		*/

	}
}
