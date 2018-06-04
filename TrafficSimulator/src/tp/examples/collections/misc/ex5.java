package tp.examples.collections.misc;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ex5 {

	public static void test1(Map<String, Integer> m) {
		System.out.println("------");
		m.put("Mike", 23);
		m.put("John", 16);
		m.put("Andres", 5);

		System.out.println(m);

		m.put("Mike", 15); // previous value is deleted
		System.out.println(m);

		System.out.println(m.keySet());
		System.out.println(m.values());
	}

	public static void test2(Map<Person, String> m) {
		System.out.println("------");
		m.put(new Person("Mike", 23), "Prof.");
		m.put(new Person("John", 23), "Student");
		m.put(new Person("Andres", 5), "Admin");

		System.out.println(m);

		System.out.println(m.keySet());
		System.out.println(m.values());
	}

	public static void test3(Map<PersonBadHashCode, String> m) {
		System.out.println("------");
		m.put(new PersonBadHashCode("Mike", 23), "Prof.");
		m.put(new PersonBadHashCode("Mike", 23), "Student");

		System.out.println(m);

		System.out.println(m.keySet());
		System.out.println(m.values());
	}

	public static void main(String[] args) {
		
		//test1(new HashMap<String, Integer>());
		//test1(new TreeMap<String, Integer>());

		// test2(new HashMap<Person, String>());
		/*test2(new TreeMap<Person, String>(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.getId() > o2.getId() ? 1 : o1.getId() < o2.getId() ? -1 : 0;
			}
		}));
		*/
		test3(new HashMap<PersonBadHashCode, String>());
	}
	
	
}
