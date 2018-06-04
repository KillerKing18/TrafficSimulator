package tp.examples.generics.ex5;

public class Person implements Comparable<Person> {
	private String name;
	private int id;
	
	Person(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Person o) {
		if ( id < o.id ) {
			return -1;
		} else if ( id > o.id ) {
			return 1;
		} else {			
			return name.compareTo(o.name);
		}
	}
}
