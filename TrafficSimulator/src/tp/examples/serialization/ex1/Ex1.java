package tp.examples.serialization.ex1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ex1 {

	public static void saveObject(String fname, Object o) throws IOException {
		FileOutputStream os = new FileOutputStream(fname);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(o);
		oos.close();
	}

	public static Object readObject(String fname) throws IOException, ClassNotFoundException {
		FileInputStream is = new FileInputStream(fname);
		ObjectInputStream ois = new ObjectInputStream(is);
		Object o = ois.readObject();
		ois.close();
		return o;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Person p = new Person("John",2);
		
		//saveObject("/tmp/bla", p);
		
		Person q = (Person) readObject("/tmp/bla");
		
		System.out.println(p+" : "+" "+q+" : "+(p==q));
	}
}
