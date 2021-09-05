package es.ucm.fdi.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class SortedArrayList <E> extends ArrayList<E>{

	private static final long serialVersionUID = 1L;
	private Comparator<E> _cmp;
	
	// Si no le pasan el comparador lo deriva al compareTo.
	// Nosotros usaremos este.
	// En la práctica nuestra vamos a comparar eventos por el tiempo y
	// vehículos por la localización, en ambos casos son enteros y el orden
	// natural
	// por eso usamos el compareTo.
	public SortedArrayList(){
		_cmp = new Comparator<E>() {

			@SuppressWarnings("unchecked")
			@Override
			public int compare(E o1, E o2) {
				return ((Comparable<E>) o1).compareTo(o2);
			}
		};
	}
	
	// este constructor es para cuando le pasan el comparador
	public SortedArrayList(Comparator<E> cmp) {
		super();
		_cmp = cmp;
	}
	
	public boolean add(E e){
		int i = 0;

		// get to the first element equals to e ...
		while (i < size() && _cmp.compare(e, get(i)) != -1) {
			i++;
		}

		// then skip till the first element greater than e
		while (i < size() && _cmp.compare(e, get(i)) == 0) {
			i++;
		}

		super.add(i, e);

		return true;
	}

	@Override
	 public boolean addAll(Collection<? extends E> c) {
		 for (E e : c) {
			 add(e);
		 }
		 return true;
	 }
	
	 @Override
	 public void add(int index, E element) {
		 throw new UnsupportedOperationException("Cannot insert to a sorted list");
	 }
	
	 @Override
	 public boolean addAll(int index, Collection<? extends E> c) {
		 throw new UnsupportedOperationException("Cannot insert to a sorted list");
	 }
	
	 @Override
	 public E set(int index, E element) {
		 throw new UnsupportedOperationException("Cannot set an element in a sorted list");
	 }
}

