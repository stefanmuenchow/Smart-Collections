package com.stefanmuenchow.collections;

import java.util.Set;

public class SmartAbstractSet<E> extends SmartAbstractCollection<E> implements ISmartSet<E> {

	protected SmartAbstractSet(Set<E> set) {
		super(set);
	}
	
	protected Set<E> getInternalSet() {
		return (Set<E>)internalColl;
	}
}
