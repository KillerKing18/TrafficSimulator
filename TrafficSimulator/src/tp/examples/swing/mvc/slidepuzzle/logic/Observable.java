package tp.examples.swing.mvc.slidepuzzle.logic;

/**
 * 
 * Generic interface for the Observable pattern.
 *
 * @param <T>
 */
public interface Observable<T> {
	/**
	 * Adds an observer
	 * 
	 * @param o
	 *            An observer
	 */
	public void addObserver(T o);

	/**
	 * Removes an observer
	 * 
	 * @param o
	 *            An observer
	 */
	public void removeObserver(T o);
}
