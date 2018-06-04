package tp.examples.swing.mvc.primes;

public interface Observable<T> {
	public void addObserver(T o);
	public void removeObserver(T o);
}
