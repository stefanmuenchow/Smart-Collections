package com.stefanmuenchow.collections.set;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.stefanmuenchow.collections.AbstractSmartCollection;
import com.stefanmuenchow.collections.ISmartCollection;
import com.stefanmuenchow.collections.function.IUnaryFunction;
import com.stefanmuenchow.collections.list.SmartList;

public class SmartSet<E> extends AbstractSmartCollection<E> implements ISmartSet<E> {

	public SmartSet() {
		super(new HashSet<E>());
	}
	
	public SmartSet(Collection<E> collection) {
		super(new HashSet<E>(collection));
	}
	

	/** Helper methods */
	
	private Set<E> getInternalSet() {
		return (Set<E>)internalColl;
	}

	/** ISmartCollection methods */

	@Override
	public <R> ISmartCollection<R> map(IUnaryFunction<R, E> function) {
		ISmartSet<R> resultSet = new SmartSet<R>();
		for (E elem : getInternalSet()) {
			resultSet.add(function.execute(elem));
		}
		
		return resultSet;
	}
	
	@Override
	public ISmartCollection<E> flatten() {
		ISmartSet<E> resultSet = new SmartSet<E>();
		Iterator<E> it = getInternalSet().iterator();
		E first = null;
		
		if (it.hasNext()) {
			first = it.next();
		}
		
		if (first != null && first instanceof Collection) {
			for (E elem : getInternalSet()) {
				@SuppressWarnings("unchecked")
				Collection<? extends E> innerList = (Collection<? extends E>)elem;
				resultSet.addAll(new SmartList<E>(innerList).flatten());
			}
		}

		return resultSet;
	}
	
	/** ISmartSet methods */

	@Override
	public boolean isSubsetOf(Set<E> anotherSet) {
		ISmartSet<E> temp = new SmartSet<E>(this);
		temp.difference(anotherSet);
		return temp.isEmpty();
	}

	@Override
	public boolean isProperSubsetOf(Set<E> anotherSet) {
		return isSubsetOf(anotherSet) && !equals(anotherSet);
	}

	@Override
	public boolean isSupersetOf(Set<E> anotherSet) {
		ISmartSet<E> temp = new SmartSet<E>(anotherSet);
		return temp.isSubsetOf(this);
	}

	@Override
	public boolean isProperSupersetOf(Set<E> anotherSet) {
		return isSupersetOf(anotherSet) && !equals(anotherSet);
	}

	@Override
	public ISmartSet<E> union(Set<E> anotherSet) {
		getInternalSet().addAll(anotherSet);
		return this;
	}

	@Override
	public ISmartSet<E> intersection(Set<E> anotherSet) {
		getInternalSet().retainAll(anotherSet);
		return this;
	}

	@Override
	public ISmartSet<E> difference(Set<E> anotherSet) {
		getInternalSet().removeAll(anotherSet);
		return this;
	}
}
