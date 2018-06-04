package tp.examples.generics.ex4;

public interface ListI<T> {
    public abstract void addElem(T e);
	public abstract T getElem(int index);
	public abstract int size();
}
