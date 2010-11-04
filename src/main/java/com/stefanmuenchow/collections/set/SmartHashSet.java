package com.stefanmuenchow.collections.set;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class SmartHashSet<E> extends SmartAbstractSet<E> {

	public SmartHashSet() {
		super(new HashSet<E>());
	}
	
	public SmartHashSet(E... elems) {
		super(new HashSet<E>(Arrays.asList(elems)));
	}
	
	public SmartHashSet(Collection<E> coll) {
		super(new HashSet<E>(coll));
	}
}
