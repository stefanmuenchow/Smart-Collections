package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.List;

public interface ISmartList<E> extends List<E>, ISmartCollection<E> {	
	ISmartList<E> addElem(int index, E elem);	
	ISmartList<E> addAllElems(int index, Collection<E> coll);
	ISmartList<E> removeElem(int index);	
	ISmartList<E> setElem(int index, E elem);
	ISmartList<E> subSmartList(int from, int to);
}
