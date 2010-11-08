package com.stefanmuenchow.collections.set;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.stefanmuenchow.collections.AbstractSmartCollection;
import com.stefanmuenchow.collections.ISmartCollection;
import com.stefanmuenchow.collections.function.IUnaryFunction;

public class SmartSet<E> extends AbstractSmartCollection<E> implements ISmartSet<E> {

	public SmartSet() {
		super(new HashSet<E>());
	}
	
	public SmartSet(Collection<E> collection) {
		super(collection);
		// TODO Auto-generated constructor stub
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

}
