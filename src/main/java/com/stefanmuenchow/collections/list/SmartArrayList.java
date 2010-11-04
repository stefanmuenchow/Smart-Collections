package com.stefanmuenchow.collections.list;

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
	protected ArrayList<E> getInternalList() {
		return (ArrayList<E>)internalColl;
	}

	@Override
	public ISmartList<E> subSmartList(int from, int to) {
		SmartArrayList<E> result = new SmartArrayList<E>(getInternalList().subList(from, to));
		return result;
	}
	
	/** ArrayList specific methods */
	
	public void ensureCapacity(int minCapacity) {
		getInternalList().ensureCapacity(minCapacity);
	}
	
	public void trimToSize() {
		getInternalList().trimToSize();
	}
}
