package com.stefanmuenchow.collections;

import java.util.Collection;

public interface ISmartCollection<E> extends Collection<E> {
	ISmartCollection<E> addElem(E elem);
	ISmartCollection<E> addAllElems(Collection<E> coll);
	ISmartCollection<E> addAllElems(E... elems);
	ISmartCollection<E> removeElem(E elem);
	ISmartCollection<E> removeAllElems(Collection<E> coll);
	ISmartCollection<E> removeAllElems(E... elems);
	ISmartCollection<E> retainAllElems(Collection<E> coll);
	ISmartCollection<E> retainAllElems(E... elems);
	ISmartCollection<E> clearAll();

}
