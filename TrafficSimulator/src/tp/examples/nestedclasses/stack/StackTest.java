package tp.examples.nestedclasses.stack;

import java.util.Iterator;

public class StackTest {

	static public void printstack1(Stack<Integer> s) {
		for (Integer e : s) {
			System.out.println(e);
		}
	}

	static public void printstack2(Stack<Integer> s) {
		Iterator<Integer> it = s.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}

	static public void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(4);
		s.push(5);
		s.push(6);
		s.push(7);
		printstack1(s);
		System.out.println("-----");
		printstack2(s);
	}
}
