package tp.examples.collections.misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ex2 {

	public static void test1() {
		List<String> l = new ArrayList<String>();
		
		l.add("John");
		l.add("Mike");
		l.add("Mike");
		l.add("Andres");
		System.out.println(l);

		l.add("Michael");
		System.out.println(l);

		System.out.println("has Andres?: " + l.contains("Andres"));

		l.remove("Andres");
		System.out.println(l);
		
		System.out.println("has Andres?: " + l.contains("Andres"));
		
		System.out.println("----");
		for(String s : l) {
			System.out.println(s);
		}
		System.out.println("----");

		l.remove("Mike");
		System.out.println(l);

		List<String> d = new ArrayList<String>();
		d.add("Mike");
		d.add("John");
		l.removeAll(d);
		System.out.println(l);
	}
	
	public static void test2() {
		List<String> l = new LinkedList<String>();
		
		l.add("John");
		l.add("Mike");
		l.add("Andres");
		System.out.println(l);

		l.add("Michael");
		System.out.println(l);

		System.out.println("has Andres?: " + l.contains("Andres"));

		l.remove("Andres");
		System.out.println(l);
		
		System.out.println("has Andres?: " + l.contains("Andres"));
		
		System.out.println("----");
		for(String s : l) {
			System.out.println(s);
		}
		System.out.println("----");
		
		List<String> d = new ArrayList<String>();
		d.add("Mike");
		d.add("John");
		l.removeAll(d);
		System.out.println(l);
	}
	
	public static void test3() {
		List<Person> l = new ArrayList<Person>();

		l.add( new Person("Mike",1));
		l.add( new Person("Mike",1));
		l.add( new Person("John",23));
		l.add( new Person("Andres",2));
		System.out.println(l);

		l.remove( new Person("Mikeee", 1)); // change equal of person and run again
		System.out.println(l);
		
	}
	
	public static void main(String[] args) {
	//	test1();
	//	test2();
		test3();
	}
}
