package misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class SortedArrayList<E> extends ArrayList<E>{
	
	Comparator<E> _comparator;
	
	public SortedArrayList(Comparator<E> comparator){
		_comparator = comparator;
	}
	
	public SortedArrayList(){
		
	}
	/*
	public boolean add(E e) {
		add(e);
		sort(_comparator);
		return true;
	}
	
	public boolean addAll(Collection<? extends E> collection) {
		addAll(collection);
		sort(_comparator);
		return true;
	}
	*/
}