package com.stefanmuenchow.collections.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.stefanmuenchow.collections.AbstractSmartCollection;
import com.stefanmuenchow.collections.ISmartCollection;
import com.stefanmuenchow.collections.function.IPredicate;
import com.stefanmuenchow.collections.function.IUnaryFunction;
import com.stefanmuenchow.collections.map.ISmartMap;
import com.stefanmuenchow.collections.map.SmartMap;
import com.stefanmuenchow.collections.set.ISmartSet;
import com.stefanmuenchow.collections.set.SmartSet;

public class SmartList<E> extends AbstractSmartCollection<E> implements ISmartList<E> {

	public SmartList() {
		super(new ArrayList<E>());
	}
	
	public SmartList(Collection<? extends E> innerList) {
		super(new ArrayList<E>(innerList));
	}
	
	/** Helper methods */
	
	private List<E> getInternalList() {
		return (List<E>)internalColl;
	}
	
	/** List methods */
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return getInternalList().addAll(index, c);
	}

	@Override
	public E get(int index) {
		return getInternalList().get(index);
	}

	@Override
	public E set(int index, E element) {
		return getInternalList().set(index, element);
	}

	@Override
	public void add(int index, E element) {
		getInternalList().add(index, element);
	}

	@Override
	public E remove(int index) {
		return getInternalList().remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return getInternalList().indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return getInternalList().indexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return getInternalList().listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return getInternalList().listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return getInternalList().subList(fromIndex, toIndex);
	}
	
	/** ISmartCollection methods */

	@Override
	public <R> ISmartCollection<R> map(IUnaryFunction<R, E> function) {
		ISmartList<R> resultList = new SmartList<R>();
		for (E elem : getInternalList()) {
			resultList.add(function.execute(elem));
		}
		
		return resultList;
	}
	
	@Override
	public ISmartCollection<E> flatten() {
		List<E> resultList = new ArrayList<E>();
		
		if (!isEmpty() && first() instanceof Collection) {
			for (E elem : getInternalList()) {
				@SuppressWarnings("unchecked")
				Collection<? extends E> innerList = (Collection<? extends E>)elem;
				resultList.addAll(new SmartList<E>(innerList).flatten());
			}
			
			internalColl = resultList;
		}

		return this;
	}

	/** ISmartList methods */

	@Override
	public E first() {
		return getInternalList().get(0);
	}

	@Override
	public E last() {
		return getInternalList().get(getInternalList().size() - 1);
	}

	@Override
	public E get(int index, E defaultVal) {
		E result = null;		
		try {
			result = get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			result = defaultVal;
		}
		
		return result;
	}

	@Override
	public ISmartList<E> take(int n) {
		internalColl = subList(0, n);
		return this;
	}

	@Override
	public ISmartList<E> drop(int n) {
		internalColl = subList(n, size());
		return this;
	}

	@Override
	public ISmartList<E> takeWhile(IPredicate<E> pred) {
		List<E> resultList = new ArrayList<E>();
		for (E elem : this) {
			if (pred.check(elem)) {
				resultList.add(elem);
			} else {
				break;
			}
		}

		internalColl = resultList;
		return this;
	}

	@Override
	public ISmartList<E> dropWhile(IPredicate<E> pred) {
		List<E> resultList = new ArrayList<E>(this);
		for (E elem : this) {
			if (pred.check(elem)) {
				resultList.remove(0);
			} else {
				break;
			}
		}

		internalColl = resultList;
		return this;
	}

	@Override
	public ISmartList<E> removeDuplicates() {
		ISmartSet<E> resultSet = new SmartSet<E>(this);
		internalColl = new ArrayList<E>(resultSet);
		return this;
	}

	@Override
	public ISmartList<E> intersperse(E elem) {
		for(int i = 1; i < size(); i += 2) {
			add(i, elem);
		}
		
		return this;
	}

	@Override
	public <T> ISmartMap<E, T> zipWith(List<T> anotherList) {
		ISmartMap<E, T> resultMap = new SmartMap<E, T>();
		Iterator<E> keys = iterator();
		Iterator<T> vals = anotherList.iterator();
		
		while (keys.hasNext() && vals.hasNext()) {
			resultMap.put(keys.next(), vals.next());
		}
		
		return resultMap;
	}

	@Override
	public ISmartList<Integer> getIndicesList() {
		ISmartList<Integer> result = new SmartList<Integer>();
		for (int i = 0; i < size(); i++) {
			result.add(i);
		}
		
		return result;
	}

	@Override
	public ISmartMap<E, Integer> getOccurenceCountMap() {
		ISmartMap<E, Integer> result = new SmartMap<E, Integer>();
		for (E elem : this) {
			if (result.containsKey(elem)) {
				result.put(elem, result.get(elem) + 1);
			} else {
				result.put(elem, 1);
			}
		}
		
		return result;
	}

	@Override
	public ISmartList<E> reverse() {
		Collections.reverse(getInternalList());
		return this;
	}

	@Override
	public int sizeWithoutNulls() {
		List<E> temp = new ArrayList<E>(getInternalList());
		temp.removeAll(null);
		return temp.size();
	}
}
