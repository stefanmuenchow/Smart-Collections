package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public abstract class SmartAbstractList<E> extends SmartAbstractCollection<E> implements ISmartList<E> {
	
	protected SmartAbstractList(List<E> list) {
		super(list);
	}
	
	protected List<E> getInternalList() {
		return (List<E>)internalColl;
	}
	
	/** Delegate all standard List interface operations */
	
	@Override
	public void add(int index, E element) {
		getInternalList().add(index, element);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return getInternalList().addAll(index, c);
	}

	@Override
	public E get(int index) {
		return getInternalList().get(index);
	}

	@Override
	public int indexOf(Object o) {
		return getInternalList().indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return getInternalList().lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return getInternalList().listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return getInternalList().listIterator(index);
	}

	@Override
	public E remove(int index) {
		return getInternalList().remove(index);
	}

	@Override
	public E set(int index, E element) {
		return getInternalList().set(index, element);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return getInternalList().subList(fromIndex, toIndex);
	}
	
	/** Add some new smarter operations */

	@Override
	public ISmartList<E> addElem(int index, E elem) {
		getInternalList().add(index, elem);
		return this;
	}

	@Override
	public ISmartList<E> addAllElems(int index, Collection<E> coll) {
		getInternalList().addAll(index, coll);
		return this;
	}

	@Override
	public ISmartList<E> removeElem(int index) {
		getInternalList().remove(index);
		return this;
	}

	@Override
	public ISmartList<E> setElem(int index, E elem) {
		getInternalList().set(index, elem);
		return this;
	}
}
