package com.stefanmuenchow.collections.set;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

import com.stefanmuenchow.collections.AbstractSmartCollection;
import com.stefanmuenchow.collections.ISmartCollection;
import com.stefanmuenchow.collections.function.IUnaryFunction;

public class SmartSortedSet<E extends Comparable<E>> extends AbstractSmartCollection<E> implements ISmartSortedSet<E> {

	protected SmartSortedSet(Collection<E> collection) {
		super(collection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Comparator<? super E> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> subSet(E fromElement, E toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> headSet(E toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> tailSet(E fromElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E last() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSubsetOf(Set<E> anotherSet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProperSubsetOf(Set<E> anotherSet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSupersetOf(Set<E> anotherSet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProperSupersetOf(Set<E> anotherSet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ISmartSet<E> union(Set<E> anotherSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISmartSet<E> intersection(Set<E> anotherSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISmartSet<E> difference(Set<E> anotherSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> ISmartCollection<R> map(IUnaryFunction<R, E> function) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISmartCollection<E> flatten() {
		// TODO Auto-generated method stub
		return null;
	}

}
