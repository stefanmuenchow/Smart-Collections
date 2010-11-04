package com.stefanmuenchow.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SmartArrayList<E> extends SmartAbstractList<E> {
	
	public SmartArrayList() {
		super(new ArrayList<E>());
	}
	
	public SmartArrayList(E... elems) {
		super(new ArrayList<E>(Arrays.asList(elems)));
	}
	
	public SmartArrayList(Collection<E> coll) {
		super(new ArrayList<E>(coll));
	}

	@Override
	public ISmartList<E> subSmartList(int from, int to) {
		ISmartList<E> result = new SmartArrayList<E>(getInternalList().subList(from, to));
		return result;
	}
}
