package com.stefanmuenchow.collections.set;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

public class SmartLinkedHashSet<E> extends SmartAbstractSet<E> {

	public SmartLinkedHashSet() {
		super(new LinkedHashSet<E>());
	}
	
	public SmartLinkedHashSet(E... elems) {
		super(new LinkedHashSet<E>(Arrays.asList(elems)));
	}
	
	public SmartLinkedHashSet(Collection<E> coll) {
		super(new LinkedHashSet<E>(coll));
	}
}
