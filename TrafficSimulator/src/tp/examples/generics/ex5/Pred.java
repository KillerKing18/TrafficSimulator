package tp.examples.generics.ex5;

// java already has this interface, java.util.functions.Predicate
public interface Pred<T> {
	public boolean test(T x);
}
