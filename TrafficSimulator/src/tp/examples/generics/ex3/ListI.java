package tp.examples.generics.ex3;

public interface ListI<T> {
	public void addElem(T e);
	public T getElem(int index);
	public int size();
	
}
