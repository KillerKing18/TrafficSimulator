package tp.examples.nestedclasses.set;

public interface Set<T> extends Iterable<T> {
	public void add(T x);
	public boolean has(T x);
	public void remove(T x);
}
