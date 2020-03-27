package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {
	
	
	//	referencia al primer nodo de la lista
	private DoubleNode<T> front;
	
	//	referencia al Último nodo de la lista
	private DoubleNode<T> last;
	
	
	private class DoubleNode<T> {
		
		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}
		
		T elem;
		
		DoubleNode<T> next;
	    DoubleNode<T> prev;
	}

///// ITERADOR normal //////////

	@SuppressWarnings("hiding")
	private class DobleLinkedListIterator<T> implements Iterator<T> {
		  // añadir atributos
	 
		
       	
		public DobleLinkedListIterator(DoubleNode<T> nodo) {
			// todo
			
				}
		
		@Override
		public boolean hasNext() {
			
			// todo
			return false;
		}

		@Override
		public T next() {
			// todo

			return null;
			
				}

		
	}
	
	////// FIN ITERATOR
	
	
	
	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS 3 ITERADORES
	
	
    /////
	
	public DoubleLinkedListImpl() {
		this.front = null;
		this.last = null;
	}
	
	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		for (T elem:v) {
			this.insertLast(elem);
		}
	}


	@Override
	public boolean isEmpty() {
		return front==null;
	}


	@Override
	public void clear() {
		this.front = null;
		this.last = null;
		
	}


	@Override
	public void insertFirst(T elem) {
		if(elem == null)
			throw new NullPointerException();
		
		if(this.front == null) {
			
			this.front = new DoubleNode<T>(elem);
			this.last = this.front;
			
		}else {
			DoubleNode<T> aux = new DoubleNode<T>(elem);
			this.front.prev = aux;
			aux.next = this.front;
			aux.prev = null;
			this.front = aux;
		}
	}


	@Override
	public void insertLast(T elem) {
		if(elem == null)
			throw new NullPointerException();
			
		if(this.last == null) {
			
			this.last = new DoubleNode<T>(elem);
			this.front = this.last;
			
		}else {
			DoubleNode<T> aux = new DoubleNode<T>(elem);
			this.last.next = aux;
			aux.prev = this.last;
			aux.next = null;
			this.last = aux;
		}
		
	}


	@Override
	public T removeFirst() throws EmptyCollectionException{
			
		if(this.front == null)
			throw new EmptyCollectionException("Empty list");
		
		T elem = this.front.elem;
			
		this.front = this.front.next;
		this.front.prev=null;
			
		return elem;
	}


	@Override
	public T removeLast()  throws EmptyCollectionException{
		
		if(this.front == null)
			throw new EmptyCollectionException("Empty list");
		
		T elem = this.last.elem;
			
		this.last = this.last.prev;
		this.last.next=null;
			
		return elem;
	}


	@Override
	public void insertPos(T elem, int position) {
		if(elem == null)
			throw new NullPointerException();
		if(position <=0)
			throw new IllegalArgumentException();
	
		
		if(position > this.size()) {
			this.insertLast(elem);
		}else if(position == 1) {
			this.insertFirst(elem);
		}else {
			
			DoubleNode<T> nodo = front;
			DoubleNode<T> nodoAdd = new DoubleNode<T>(elem);
			
			for(int i = 0 ; i<position;i++) {
				nodo = nodo.next;
			}
			
			nodoAdd.next = nodo;
			nodoAdd.prev = nodo.prev;
			nodoAdd.prev.next = nodoAdd;
			nodo.prev = nodoAdd;
			
		}
		
	}


	@Override
	public void insertBefore(T elem, T target) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public T getElemPos(int position) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getPosFirst(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getPosLast(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public T removePos(int pos) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int removeAll(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean contains(T elem) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int size() {
		
		int size = 0;
		DoubleNode<T> aux = this.front;
		
		while(aux != null) {
			size++;
			aux = aux.next;
		}
			
		return size;
	}


	@Override
	public String toStringReverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleList<T> reverse() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int maxRepeated() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isEquals(DoubleList<T> other) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsAll(DoubleList<T> other) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isSubList(DoubleList<T> other) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String toStringFromUntil(int from, int until) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
     return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> reverseIterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterator<T> evenPositionsIterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterator<T> progressIterator() {
		// TODO Auto-generated method stub
		return null;
	}


}
