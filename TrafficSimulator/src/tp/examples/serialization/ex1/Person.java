package tp.examples.serialization.ex1;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer id;
	public int x;
	
	// if you uncomment the following line you get an error when serializing, to
	// solve it add the 'transient' keyword
	//
	// public SomeNonSerClass x = new SomeNonSerClass();
    
	public Person(String name, Integer id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		return "(" + name + "," + id + ")";
	}
}
