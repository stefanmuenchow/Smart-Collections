package com.stefanmuenchow.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SmartTreeSet<E> extends SmartAbstractSet<E> implements ISmartSortedSet<E> {

	public SmartTreeSet() {
		super(new TreeSet<E>());
	}
	
	public SmartTreeSet(E... elems) {
		super(new TreeSet<E>(Arrays.asList(elems)));
	}
	
	public SmartTreeSet(Collection<E> coll) {
		super(new TreeSet<E>(coll));
	}
	
	@Override
	protected SortedSet<E> getInternalSet() {
		return (SortedSet<E>)internalColl;
	}

	@Override
	public Comparator<? super E> comparator() {
		return getInternalSet().comparator();
	}

	@Override
	public SortedSet<E> subSet(E fromElement, E toElement) {
		return getInternalSet().subSet(fromElement, toElement);
	}

	@Override
	public SortedSet<E> headSet(E toElement) {
		return getInternalSet().headSet(toElement);
	}

	@Override
	public SortedSet<E> tailSet(E fromElement) {
		return getInternalSet().tailSet(fromElement);
	}

	@Override
	public E first() {
		return getInternalSet().first();
	}

	@Override
	public E last() {
		return getInternalSet().last();
	}
}
