package tp.examples.generics.ex2;

public class Utils {
	public static <A,B> boolean compare(Pair<A,B> p1, Pair<A,B> p2) {
		return p1.getFirst().equals(p2.getFirst()) &&
				p2.getSecond().equals(p2.getSecond());
	}
}
