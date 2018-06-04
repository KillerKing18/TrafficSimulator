package tp.examples.generics.ex3;

public class Utils {
	
	public static <T> void print(MyList<T> x) {
		for(int i=0; i<x.size(); i++) {
			System.out.println(x.getElem(i));
		}
	}

	public static <T extends Number> void printNumbers(MyList<T> x) {
		for(int i=0; i<x.size(); i++) {
			System.out.println(x);
		}
	}

}
