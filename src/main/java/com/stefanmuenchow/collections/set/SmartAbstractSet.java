package com.stefanmuenchow.collections.set;

import java.util.Set;

import com.stefanmuenchow.collections.SmartAbstractCollection;

public class SmartAbstractSet<E> extends SmartAbstractCollection<E> implements ISmartSet<E> {

	protected SmartAbstractSet(Set<E> set) {
		super(set);
	}
	
	protected Set<E> getInternalSet() {
		return (Set<E>)internalColl;
	}
}
