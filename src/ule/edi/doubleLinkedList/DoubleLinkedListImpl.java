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
		
		private DoubleNode<T> actual;
       	
		public DobleLinkedListIterator(DoubleNode<T> nodo) {
			this.actual = nodo;
		}
		
		@Override
		public boolean hasNext() {
			
			return actual!=null;
		}

		@Override
		public T next() {
			
			if(!this.hasNext()) 
				throw new NoSuchElementException();
			
			DoubleNode<T> aux = this.actual;
			this.actual = this.actual.next;
			return aux.elem;
			
		}
	}
	
	////// FIN ITERATOR NORMAL //////////
	
	///// REVERSE ITERATOR //////////
	
	@SuppressWarnings("hiding")
	private class DobleLinkedListReverseIterator<T> implements Iterator<T> {
		
		private DoubleNode<T> actual;
       	
		public DobleLinkedListReverseIterator(DoubleNode<T> nodo) {
			this.actual = nodo;
		}
		
		@Override
		public boolean hasNext() {
			
			return actual!=null;
		}

		@Override
		public T next() {
			
			if(!this.hasNext()) 
				throw new NoSuchElementException();
			
			DoubleNode<T> aux = this.actual;
			this.actual = this.actual.prev;
			return aux.elem;
			
		}

		
	}
	
	//////FIN REVERSE ITERATOR //////////
	
	///// EVEN POSITIONS ITERATOR //////////
	
	@SuppressWarnings("hiding")
	private class DobleLinkedListEvenPosIterator<T> implements Iterator<T> {
	 
		private DoubleNode<T> actual;
		
		public DobleLinkedListEvenPosIterator(DoubleNode<T> nodo) {
			
			if(nodo == null)
				this.actual = null;
			else
				this.actual = nodo.next;
		}
		
		@Override
		public boolean hasNext() {
			
			return actual!=null;
		}

		@Override
		public T next() {
			
			if(!this.hasNext()) 
				throw new NoSuchElementException();
			
			DoubleNode<T> aux = this.actual;
			if(actual.next == null)
				this.actual = null;
			else
				this.actual = this.actual.next.next;
			
			return aux.elem;
		}

		
	}
	
	//////FIN EVEN POSITIONS ITERATOR //////////
	
	///// PROGRESS ITERATOR //////////
	
	@SuppressWarnings("hiding")
	private class DobleLinkedListProgressIterator<T> implements Iterator<T> {
	 
		private DoubleNode<T> actual;
		private int saltos;
		
		public DobleLinkedListProgressIterator(DoubleNode<T> nodo) {
			
			this.actual = nodo;
			this.saltos = 0;
		}
		
		@Override
		public boolean hasNext() {
			
			return actual!=null;
		}

		@Override
		public T next() {
			
			if(!this.hasNext()) 
				throw new NoSuchElementException();
			
			DoubleNode<T> aux = this.actual;
			saltos++;
			int i = 0;
			while(actual != null && i<saltos) {
				actual = actual.next;
				i++;
			}
			
			return aux.elem;
			
		}
		
	}
	
	//////FIN PROGRESS ITERATOR //////////
	
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
		
		if(this.size()==1) {
			this.front = null;
			this.last = null;
			return elem;
		}
			
		this.front = this.front.next;
		this.front.prev=null;
			
		return elem;
	}


	@Override
	public T removeLast()  throws EmptyCollectionException{
		
		if(this.front == null)
			throw new EmptyCollectionException("Empty list");
		
		T elem = this.last.elem;
				
		if(this.size()==1) {
			this.front = null;
			this.last = null;
			return elem;
		}

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
		if(elem == null)
			throw new NullPointerException();
		
			int pos = this.getPosFirst(target)-1;
			
			if(pos == 0)
				this.insertFirst(elem);	
			else 
				this.insertPos(elem,pos);
			
	}


	@Override
	public T getElemPos(int position) {
		if(position < 1 || position > size())
			throw new IllegalArgumentException();
		
		DoubleNode<T> aux = this.front;
		for(int i = 0 ; i < position ; i++) {
			aux = aux.next;
		}
		return aux.elem;
	}


	@Override
	public int getPosFirst(T elem) {
		
		DoubleNode<T> aux = this.front;
		int pos = 1;
		boolean found = false;
		
		while(aux != null && !found) {
			if(aux.elem == elem)
				found = true;
			else {
				aux = aux.next;
				pos++;
			}
		}
		
		if(!found)
			throw new NoSuchElementException();
		
		return pos;
	}


	@Override
	public int getPosLast(T elem) {
		
		DoubleNode<T> aux = this.last;
		int pos = size();
		boolean found = false;
		
		while(aux != null && !found) {
			if(aux.elem == elem)
				found = true;
			else {
				aux = aux.prev;
				pos--;
			}
		}
		
		if(!found)
			throw new NoSuchElementException();
		
		return pos;
	}


	@Override
	public T removePos(int pos){
		
		if(pos < 1 || pos > size())
			throw new IllegalArgumentException();
		if(pos == 1)
			try{return this.removeFirst();}catch(Exception e) {/*No entra nunca*/}
		if(pos == this.size())
			try{return this.removeLast();}catch(Exception e) {/*No entra nunca*/}
		
		
		DoubleNode<T> aux = this.front;
		int i = 0;
		while(i < pos-1) {
			aux = aux.next;
			i++;
		}
	
		T elem = aux.elem;
		aux.next.prev = aux.prev;
		aux.prev.next = aux.next;
		return elem;
		
	}


	@Override
	public int removeAll(T elem) {
		
		if(elem == null)
			throw new NullPointerException();
		
		int nApar = 0;
		
		while(this.contains(elem)) {

			this.removePos(this.getPosFirst(elem));
			nApar++;
		}
		
		if(nApar == 0)
			throw new NoSuchElementException();
		
		return nApar;
	}


	@Override
	public boolean contains(T elem) {
		
		if(elem == null)
			throw new NullPointerException();
		
		DoubleNode<T> aux = this.front;
		boolean exists = false;
		
		while(aux != null && !exists) {
			if(aux.elem == elem)
				exists = true;
			aux = aux.next;
		}
		return exists;
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
		
		StringBuilder output = new StringBuilder();
		output.append("(");
		
		DoubleNode<T> aux = this.last;
		while(aux!=null) {
			output.append(aux.elem.toString()+" ");
			aux = aux.prev;
		}
		output.append(")");
		return output.toString();
	}

	@Override
	public DoubleList<T> reverse() {
		DoubleLinkedListImpl<T> reverse = new DoubleLinkedListImpl<T>();
		
		DoubleNode<T> aux = this.front;
		while(aux!=null) {
			reverse.insertFirst(aux.elem);
			aux = aux.next;
		}
		
		return reverse;
	}


	@Override
	public int maxRepeated() {
		
		int maxRepeticiones = 1, tempCount;
		T elemMasRepetido;
		DoubleNode<T> aux = this.front;
		
		while(aux!=null) {
			
			elemMasRepetido = aux.elem;
			tempCount = 0;
			DoubleNode<T> aux2 = aux;
			
			while(aux2!=null) {
				
				if(elemMasRepetido.equals(aux2.elem)) 
					tempCount++;
				
				aux2 = aux2.next;
			}
			
			if(tempCount>maxRepeticiones) 
				maxRepeticiones = tempCount;
			
			aux = aux.next;
		}
		
		return maxRepeticiones;
	}


	@Override
	public boolean isEquals(DoubleList<T> other) {
		
		if(other == null)
			throw new NullPointerException();
		
		boolean equal = true;
		DoubleNode<T> aux = this.front;
		DoubleLinkedListImpl<T> otherL = (DoubleLinkedListImpl<T>) other;
		DoubleNode<T> auxOth = otherL.front;
		
		if(this.size() != otherL.size())
			return false;
		
		while(equal && aux!=null) {
			
			if( !(aux.elem.equals(auxOth.elem)) ) {
				equal = false;
			}
			
			aux = aux.next;
			auxOth = auxOth.next;
		}
		
		return equal;
	}


	@Override
	public boolean containsAll(DoubleList<T> other) {
		
		if(other == null)
			throw new NullPointerException();
		
		boolean containsAll = true;
		DoubleLinkedListImpl<T> otherL = (DoubleLinkedListImpl<T>) other;
		DoubleNode<T> auxOth = otherL.front;
		
		while(auxOth!=null && containsAll) {
			
			boolean containThis = false;
			DoubleNode<T> aux = this.front;
			while(aux!=null && !containThis) {
				
				if(aux.elem.equals(auxOth.elem))
					containThis = true;
				
				aux = aux.next;
			}
			
			if(!containThis)
				containsAll = false;
			
			auxOth = auxOth.next;
		}
		

		return containsAll;
	}


	@Override
	public boolean isSubList(DoubleList<T> other) {
		
		if(other == null)
			throw new NullPointerException();
		
		boolean isSubList = false;
		DoubleLinkedListImpl<T> otherL = (DoubleLinkedListImpl<T>) other;
		DoubleNode<T> aux = this.front;
		
		if(otherL.isEmpty())
			return true;
		if(!this.containsAll(otherL))
			return false;
		
		while(aux!= null && !isSubList) {
			
			isSubList = true;
			DoubleNode<T> aux2 = aux;
			DoubleNode<T> auxOth = otherL.front;
			while(auxOth!=null && isSubList) {
				
				if(!auxOth.elem.equals(aux2.elem))
					isSubList=false;
				
				auxOth = auxOth.next;
				aux2 = aux2.next;
			}
			
			aux = aux.next;
		}
		
		return isSubList;
	}


	@Override
	public String toStringFromUntil(int from, int until) {
		
		if(from <=0 || until <=0 || until<from)
			throw new IllegalArgumentException();
		
		if(until>size())
			until = size();
		if(from>size())
			return "()";
			
		StringBuilder output = new StringBuilder();
		output.append("(");
		
		for(int i = from-1; i<until; i++) {
			output.append(this.getElemPos(i).toString()+" ");	
		}
		output.append(")");
		return output.toString();
		
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("(");
		
		DoubleNode<T> aux = this.front;
		while(aux!=null) {
			output.append(aux.elem.toString()+" ");
			aux = aux.next;
		}
		output.append(")");
		return output.toString();

	}

	@Override
	public Iterator<T> iterator() {
		return new DobleLinkedListIterator<T>(this.front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DobleLinkedListReverseIterator<T>(this.last);
	}


	@Override
	public Iterator<T> evenPositionsIterator() {
		return new DobleLinkedListEvenPosIterator<T>(this.front);

	}


	@Override
	public Iterator<T> progressIterator() {
		return new DobleLinkedListProgressIterator<T>(this.front);
	}


}
