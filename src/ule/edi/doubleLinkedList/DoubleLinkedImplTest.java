package ule.edi.doubleLinkedList;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;
import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedImplTest {
	DoubleLinkedListImpl<String> lv;
	DoubleLinkedListImpl<String> listaConElems;
	
@Before
public void antesDe() {
	lv=new DoubleLinkedListImpl<String>();
	listaConElems=new DoubleLinkedListImpl<String>();
	listaConElems.insertFirst("D");
	listaConElems.insertFirst("B");
	listaConElems.insertFirst("A");
	listaConElems.insertFirst("C");
	listaConElems.insertFirst("B");
	listaConElems.insertFirst("A");
	
}


	
	@Test
	public void isEmptyTest() {
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		Assert.assertFalse(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==6);
		
	}
	
	@Test
	public void clearTest() {
		lv.clear();
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		
		listaConElems.clear();
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
	}
	
	@Test
	public void containsTest() {
		Assert.assertFalse(lv.contains("A"));
		Assert.assertTrue(listaConElems.contains("A"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("Z"));
		
	}
	
	@Test
	public void removeAllTest() throws EmptyCollectionException {
        Assert.assertEquals(2, listaConElems.removeAll("A"));
    	Assert.assertEquals(listaConElems.toString(),"(B C B D )");
    	
        listaConElems.removeAll("B");
		Assert.assertFalse(listaConElems.contains("A"));
		Assert.assertFalse(listaConElems.contains("B"));
		Assert.assertEquals(listaConElems.toString(),"(C D )");
		listaConElems.removeAll("C");
		
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("C"));
        listaConElems.removeAll("D");
		
		Assert.assertFalse(listaConElems.contains("D"));
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
	}
	
	@Test
	public void isSubListTest() throws EmptyCollectionException {
		 Assert.assertTrue(listaConElems.isSubList(lv));
	    	Assert.assertTrue(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "B", "C")));
	      	Assert.assertEquals(listaConElems.toString(),"(A B C A B D )");
	      	Assert.assertEquals(new DoubleLinkedListImpl<String>("A", "C").toString(),"(A C )");   
	     	Assert.assertFalse(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "C")));
	     	Assert.assertEquals(listaConElems.maxRepeated(),2);
	     	listaConElems.insertBefore("A", "D");
	    	Assert.assertEquals(listaConElems.toString(),"(A B C A B A D )");
	    	Assert.assertTrue(listaConElems.maxRepeated()==3);
	        	  
	}
	
	@Test
	public void removeLastTest() throws EmptyCollectionException{
		
		
		Assert.assertEquals(listaConElems.toString(),"(A B C A B D )");
		Assert.assertEquals(listaConElems.removeLast(),"D");
		Assert.assertEquals(listaConElems.toString(),"(A B C A B )");
		DoubleLinkedListImpl<String> vacia=new DoubleLinkedListImpl<String>();
		vacia.insertFirst("A");
		Assert.assertEquals(vacia.toString(),"(A )");
		Assert.assertEquals(vacia.removeLast(),"A");

	}
	
	@Test
	public void insertPosTest() {
		
		Assert.assertEquals(listaConElems.toString(),"(A B C A B D )");
		listaConElems.insertPos("X", 200);
		Assert.assertEquals(listaConElems.toString(),"(A B C A B D X )");
		listaConElems.insertPos("Z", 1);
		Assert.assertEquals(listaConElems.toString(),"(Z A B C A B D X )");
	}
	
	@Test
	public void insertBeforeTest() {
		Assert.assertEquals(listaConElems.toString(),"(A B C A B D )");
		listaConElems.insertBefore("X", "A");
		Assert.assertEquals(listaConElems.toString(),"(X A B C A B D )");
		listaConElems.insertBefore("Z", "D");
		Assert.assertEquals(listaConElems.toString(),"(X A B C A B Z D )");
	}
	
	@Test
	public void getPosLastTest() {
		Assert.assertEquals(listaConElems.getPosLast("B"),5);
		Assert.assertEquals(listaConElems.getPosLast("A"),4);
	}
	
	@Test
	public void removePosTest() {
		Assert.assertEquals(listaConElems.removePos(1), "A");
		Assert.assertEquals(listaConElems.toString(),"(B C A B D )");
		Assert.assertEquals(listaConElems.removePos(5), "D");
		Assert.assertEquals(listaConElems.toString(),"(B C A B )");

	}
	
	@Test
	public void reverseListTest() {
		Assert.assertEquals(listaConElems.reverse().toString(),"(D B A C B A )");
	}
	
	@Test
	public void maxRepeatedTest() {
		
		DoubleLinkedListImpl<String> listaDeAes =new DoubleLinkedListImpl<String>();
		listaDeAes.insertFirst("A");
		listaDeAes.insertFirst("A");
		listaDeAes.insertFirst("A");
		listaDeAes.insertFirst("A");
		listaDeAes.insertFirst("A");
		Assert.assertEquals(listaConElems.maxRepeated(), 2);
		Assert.assertEquals(listaDeAes.maxRepeated(), 5);
	}
	
	@Test 
	public void testEquals() {
		
		DoubleLinkedListImpl<String> listaDeAes =new DoubleLinkedListImpl<String>();
		listaDeAes.insertFirst("A");
		listaDeAes.insertFirst("A");
		listaDeAes.insertFirst("A");
		listaDeAes.insertFirst("A");
		listaDeAes.insertFirst("A");
		
		Assert.assertTrue(listaConElems.isEquals(listaConElems));
		Assert.assertFalse(listaConElems.isEquals(listaDeAes));
		
		listaDeAes.insertFirst("A");
		
		Assert.assertFalse(listaConElems.isEquals(listaDeAes));
	}
	
	@Test
	public void testContainsAll() {
		DoubleLinkedListImpl<String> l2 = new DoubleLinkedListImpl<String>();
		DoubleLinkedListImpl<String> l3 = new DoubleLinkedListImpl<String>();
		l2.insertFirst("D");
		l2.insertFirst("B");
		l2.insertFirst("C");
		
		l3.insertFirst("X");
		l3.insertFirst("D");
		l3.insertFirst("B");
		
		Assert.assertTrue(listaConElems.containsAll(l2));
		Assert.assertFalse(listaConElems.containsAll(l3));
	}
	
	@Test
	public void isSubListTest2() {
		
		DoubleLinkedListImpl<String> l2 = new DoubleLinkedListImpl<String>();
		l2.insertFirst("A");
		l2.insertFirst("C");
		l2.insertFirst("B");
		Assert.assertTrue(listaConElems.isSubList(l2));
		
		DoubleLinkedListImpl<String> l3 = new DoubleLinkedListImpl<String>();
		Assert.assertTrue(listaConElems.isSubList(l3));
		
		l3.insertFirst("A");
		l3.insertFirst("C");
		l3.insertFirst("B");
		l3.insertFirst("X");
		Assert.assertFalse(listaConElems.isSubList(l3));
		
	}
	
	@Test
	public void toStringReverseTest () {
		
		DoubleLinkedListImpl<String> vacia=new DoubleLinkedListImpl<String>();
		Assert.assertEquals(vacia.toStringReverse(),"()");
		Assert.assertEquals(listaConElems.toStringReverse(),"(D B A C B A )");
		
	}
	
	@Test
	public void toStringFromUntilTest() {

		Assert.assertEquals(listaConElems.toStringFromUntil(2,20),"(B C A B D )");
		Assert.assertEquals(listaConElems.toStringFromUntil(300,301),"()");
		Assert.assertEquals(listaConElems.toStringFromUntil(5,5),"(B )");
	}
	
	//Test excepciones
	
	@Test(expected = NullPointerException.class)
	public void insertFirstTestExcp() {
		listaConElems.insertFirst(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void insertLastTestExcp() {
		listaConElems.insertLast(null);
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void removeFirstTestExcp() throws EmptyCollectionException{
		lv.removeFirst();
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void removeLastTestExcp() throws EmptyCollectionException{
		lv.removeLast();
	}
	
	@Test(expected = NullPointerException.class)
	public void insertPosTestExcpNull() {
		listaConElems.insertPos(null, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void insertPosTestExcpIllgArg() {
		listaConElems.insertPos("A", -1);
	}
	
	@Test(expected = NullPointerException.class)
	public void insertBeforeTestExcp() {
		listaConElems.insertBefore(null, "A");
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getElemPosTestExcp1() {
		listaConElems.getElemPos(-3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getElemPosTestExcp2() {
		listaConElems.getElemPos(200);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void getPosFirstTestExcp(){
		listaConElems.getPosFirst("X");
	}
	
	@Test(expected = NoSuchElementException.class)
	public void getPosLastTestExcp(){
		listaConElems.getPosLast("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removePosTestExcp1() {
		listaConElems.removePos(-3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removePosTestExcp2() {
		listaConElems.removePos(200);
	}
	
	@Test(expected = NullPointerException.class)
	public void removeAllTestExcpNull() {
		listaConElems.removeAll(null);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void removeAllTestExcpNoElm() {
		listaConElems.removeAll("X");
	}
	
	@Test(expected = NullPointerException.class)
	public void containsTestExcp() {
		listaConElems.contains(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void isEqualsTestExcp() {
		listaConElems.isEquals(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void containsAllTestExcp() {
		listaConElems.containsAll(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void isSubListTestExcp() {
		listaConElems.isSubList(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void toStringFromUntilTestExcp1() {
		listaConElems.toStringFromUntil(-1,2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void toStringFromUntilTestExcp2() {
		listaConElems.toStringFromUntil(1,-2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void toStringFromUntilTestExcp3() {
		listaConElems.toStringFromUntil(7,2);
	}
	
	//Test iteradores
	
	@Test
	public void normalIteratorTest() {
		
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.insertFirst("A");
		lista.insertFirst("A");
		lista.insertFirst("A");
		lista.insertFirst("A");
		lista.insertFirst("A");
		
		Iterator<String> iterador = lista.iterator();
		
		int i = 0;
		while(iterador.hasNext()) {
			
			Assert.assertEquals(iterador.next(),"A");
			i++;
		}
		
		Assert.assertEquals(i,5);
		Assert.assertFalse(iterador.hasNext());
		
		DoubleLinkedListImpl<String> lista2 = new DoubleLinkedListImpl<String>();
		Iterator<String> iterador2 = lista2.iterator();
		Assert.assertFalse(iterador.hasNext());
	}
	
	@Test
	public void reverseIteratorTest() {
		
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.insertFirst("A");
		lista.insertFirst("A");
		lista.insertFirst("A");
		lista.insertFirst("A");
		lista.insertFirst("A");
		
		Iterator<String> iterador = lista.reverseIterator();
		
		int i = 0;
		while(iterador.hasNext()) {
			
			Assert.assertEquals(iterador.next(),"A");
			i++;
		}
		
		Assert.assertEquals(i,5);
		Assert.assertFalse(iterador.hasNext());
		
		DoubleLinkedListImpl<String> lista2 = new DoubleLinkedListImpl<String>();
		Iterator<String> iterador2 = lista2.reverseIterator();
		Assert.assertFalse(iterador.hasNext());
	}
	
	@Test
	public void evenIteratorTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		
		lista.insertFirst("E");
		lista.insertFirst("D");
		lista.insertFirst("C");
		lista.insertFirst("B");
		lista.insertFirst("A");
		
		
		Iterator<String> iterador = lista.evenPositionsIterator();
		Assert.assertEquals(iterador.next(),"B");
		Assert.assertEquals(iterador.next(),"D");
		Assert.assertFalse(iterador.hasNext());
		
		lista.insertFirst("F");
		Iterator<String> iterador2 = lista.evenPositionsIterator();
		Assert.assertEquals(iterador2.next(),"A");
		Assert.assertEquals(iterador2.next(),"C");
		Assert.assertEquals(iterador2.next(),"E");
		Assert.assertFalse(iterador2.hasNext());
	}
	
	@Test
	public void progressIteratorTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19");
		Iterator<String> iterador = lista.progressIterator();
		
		Assert.assertEquals(iterador.next(),"1");
		Assert.assertEquals(iterador.next(),"2");
		Assert.assertEquals(iterador.next(),"4");
		Assert.assertEquals(iterador.next(),"7");
		Assert.assertEquals(iterador.next(),"11");
		Assert.assertEquals(iterador.next(),"16");
		Assert.assertFalse(iterador.hasNext());
		
	}
	
	//Excepciones iteradores
	
	@Test(expected=NoSuchElementException.class)
	public void normalIteratorTestExcp() {
		
		DoubleLinkedListImpl<String> listaVacia = new DoubleLinkedListImpl<String>();
		Iterator<String> iterador = listaVacia.iterator();
		iterador.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void reverseIteratorTestExcp() {
		
		DoubleLinkedListImpl<String> listaVacia = new DoubleLinkedListImpl<String>();
		Iterator<String> iterador = listaVacia.reverseIterator();
		iterador.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void evenIteratorTestExcp() {
		
		DoubleLinkedListImpl<String> listaVacia = new DoubleLinkedListImpl<String>();
		Iterator<String> iterador = listaVacia.evenPositionsIterator();
		iterador.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void progressIteratorTestExcp() {
		
		DoubleLinkedListImpl<String> listaVacia = new DoubleLinkedListImpl<String>();
		Iterator<String> iterador = listaVacia.progressIterator();
		iterador.next();
	}
}
