package tp.examples.generics.ex5;

// java already has an interface java.util.functions.Function
public interface Fun<A,B> {
	public B apply(A x);
}
