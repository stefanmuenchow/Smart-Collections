package com.stefanmuenchow.collections;

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
	public ISmartList<E> subSmartList(int from, int to) {
		ISmartList<E> result = new SmartLinkedList<E>(getInternalList().subList(from, to));
		return result;
	}
}
