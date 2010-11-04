package com.stefanmuenchow.collections.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class SmartLinkedList<E> extends SmartAbstractList<E> {
	
	public SmartLinkedList() {
		super(new LinkedList<E>());
	}
	
	public SmartLinkedList(E... elems) {
		super(new LinkedList<E>(Arrays.asList(elems)));
	}
	
	public SmartLinkedList(Collection<E> coll) {
		super(new LinkedList<E>(coll));
	}
	
	@Override
	protected LinkedList<E> getInternalList() {
		return (LinkedList<E>)internalColl;
	}
	
	@Override
	public ISmartList<E> subSmartList(int from, int to) {
		SmartLinkedList<E> result = new SmartLinkedList<E>(getInternalList().subList(from, to));
		return result;
	}
	
	/** LinkedList specific methods */
	
	public E getFirst() {
        return getInternalList().getFirst();
    }

    public E getLast()  {
        return getInternalList().getLast();
    }
    
    public E removeFirst() {
        return getInternalList().removeFirst();
    }

    public E removeLast() {
        return getInternalList().removeLast();
    }
    
    public void addFirst(E e) {
        getInternalList().addFirst(e);
    }

    public void addLast(E e) {
        getInternalList().addLast(e);
    }
    
    /** Smarter LinkedList specific methods */
    
    public SmartLinkedList<E> addFirstElem(E e) {
        getInternalList().addFirst(e);
        return this;
    }

    public SmartLinkedList<E> addLastElem(E e) {
        getInternalList().addLast(e);
        return this;
    }
}
