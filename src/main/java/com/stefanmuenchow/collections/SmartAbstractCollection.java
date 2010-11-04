package com.stefanmuenchow.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public abstract class SmartAbstractCollection<E> implements Collection<E>, ISmartCollection<E> {
	protected final Collection<E> internalColl;
	
	protected SmartAbstractCollection(Collection<E> coll) {
		internalColl = coll;
	}
	
	/** Delegate all standard Collection interface operations */
	
	public boolean add(E e) {
		return internalColl.add(e);
	}

	public boolean addAll(Collection<? extends E> c) {
		return internalColl.addAll(c);
	}

	public void clear() {
		internalColl.clear();
	}

	public boolean contains(Object o) {
		return internalColl.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return internalColl.containsAll(c);
	}

	public boolean equals(Object o) {
		return internalColl.equals(o);
	}

	public int hashCode() {
		return internalColl.hashCode();
	}

	public boolean isEmpty() {
		return internalColl.isEmpty();
	}

	public Iterator<E> iterator() {
		return internalColl.iterator();
	}

	public boolean remove(Object o) {
		return internalColl.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return internalColl.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return internalColl.retainAll(c);
	}

	public int size() {
		return internalColl.size();
	}

	public Object[] toArray() {
		return internalColl.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return internalColl.toArray(a);
	}
	
	/** Add some new smarter operations */

	@Override
	public ISmartCollection<E> addElem(E elem) {
		internalColl.add(elem);
		return this;
	}

	@Override
	public ISmartCollection<E> addAllElems(Collection<E> coll) {
		internalColl.addAll(coll);
		return this;
	}
	
	@Override
	public ISmartCollection<E> addAllElems(E... elems) {
		addAllElems(Arrays.asList(elems));
		return this;
	}

	@Override
	public ISmartCollection<E> removeElem(E elem) {
		internalColl.remove(elem);
		return this;
	}

	@Override
	public ISmartCollection<E> removeAllElems(Collection<E> coll) {
		internalColl.removeAll(coll);
		return this;
	}
	
	@Override
	public ISmartCollection<E> removeAllElems(E... elems) {
		removeAllElems(Arrays.asList(elems));
		return this;
	}

	@Override
	public ISmartCollection<E> retainAllElems(Collection<E> coll) {
		internalColl.retainAll(coll);
		return this;
	}
	
	@Override
	public ISmartCollection<E> retainAllElems(E... elems) {
		retainAllElems(Arrays.asList(elems));
		return this;
	}

	@Override
	public ISmartCollection<E> clearAll() {
		internalColl.clear();
		return this;
	}
}
