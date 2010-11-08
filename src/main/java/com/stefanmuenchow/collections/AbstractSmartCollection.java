package com.stefanmuenchow.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.stefanmuenchow.collections.function.IBinaryFunction;
import com.stefanmuenchow.collections.function.IPredicate;

public abstract class AbstractSmartCollection<E> implements ISmartCollection<E> {
	protected Collection<E> internalColl;
	
	protected AbstractSmartCollection(Collection<E> collection) {
		internalColl = collection;
	}
	
	/** Collection methods */
	
	@Override
	public int size() {
		return internalColl.size();
	}

	@Override
	public boolean isEmpty() {
		return internalColl.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return internalColl.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return internalColl.iterator();
	}

	@Override
	public Object[] toArray() {
		return internalColl.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return internalColl.toArray(a);
	}

	@Override
	public boolean add(E e) {
		return internalColl.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return internalColl.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return internalColl.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return internalColl.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return internalColl.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return internalColl.retainAll(c);
	}

	@Override
	public void clear() {
		internalColl.clear();
	}
	
	/** ISmartCollection methods */

	@Override
	public E find(IPredicate<E> pred) {
		for (E elem : internalColl) {
			if (pred.check(elem)) {
				return elem;
			}
		}
		
		return null;
	}

	@Override
	public ISmartCollection<E> filter(IPredicate<E> predicate) {
		List<E> toRemove = new ArrayList<E>();
		
		for (E elem : toRemove) {
			if (!predicate.check(elem)) {
				toRemove.add(elem);
			}
		}
		
		internalColl.removeAll(toRemove);
		return this;
	}

	@Override
	public ISmartCollection<E> remove(IPredicate<E> predicate) {
		List<E> toRemove = new ArrayList<E>();
		
		for (E elem : toRemove) {
			if (predicate.check(elem)) {
				toRemove.add(elem);
			}
		}
		
		internalColl.removeAll(toRemove);
		return this;
	}

	@Override
	public ISmartCollection<E> replace(E seek, E replacement) {
		final E seekVar = seek;
		
		return this.replace(new IPredicate<E>() {
			@Override
			public boolean check(E input) {
				return input.equals(seekVar);
			}
		}, replacement);
	}

	@Override
	public ISmartCollection<E> replace(IPredicate<E> predicate, E replacement) {
		List<E> tempList = new ArrayList<E>();
		
		for (E elem : internalColl) {
			if (predicate.check(elem)) {
				tempList.add(replacement);
			} else {
				tempList.add(elem);
			}
		}
		
		internalColl = tempList;
		return this;
	}

	@Override
	public <R> R reduce(R initial, IBinaryFunction<R, R, E> funct) {
		R result = initial;
		
		for (E elem : internalColl) {
			result = funct.execute(result, elem);
		}

		return result;
	}
	
	@Override
	public E reduce(IBinaryFunction<E, E, E> funct) {
		Iterator<E> it = internalColl.iterator();
		E result = null;
		
		if (it.hasNext()) {
			result = it.next();
		}
		
		while (it.hasNext()) {
			result = funct.execute(result, it.next());
		}

		return result;
	}

	@Override
	public String join(String delimiter) {
		StringBuffer result = new StringBuffer();
		for (E elem : internalColl) {
			result.append(elem.toString());
			result.append(delimiter);
		}
		
		result.setLength(result.length() - delimiter.length());
		return result.toString();
	}

	@Override
	public int count(IPredicate<E> predicate) {
		int counter = 0;
		for (E elem : internalColl) {
			if (predicate.check(elem)) {
				counter++;
			}
		}
		
		return counter;
	}

	@Override
	public boolean exists(IPredicate<E> pred) {
		for (E elem : internalColl) {
			if (pred.check(elem)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean forall(IPredicate<E> pred) {
		for (E elem : internalColl) {
			if (!pred.check(elem)) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return internalColl.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return internalColl.hashCode();
	}
}
