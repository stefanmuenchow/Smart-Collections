/**
 * Copyright (c) Stefan Muenchow. All rights reserved.
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 **/

package com.stefanmuenchow.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public abstract class AbstractSmartList<E> extends AbstractSmartCollection<E> implements SmartList<E> {

    protected AbstractSmartList(final Collection<E> innerList) {
        super(innerList);
    }

    /** Helper methods */

    private List<E> getInternalList() {
        return (List<E>) internalColl;
    }

    @Override
    protected abstract SmartList<E> createNewInstance();

    @Override
    protected abstract <T> SmartList<T> createNewInstance(Collection<T> aColl);

    /** List methods */
    
	@Override
	public SmartList<E> addReturn(int index, E element) {
		add(index, element);
		return this;
	}

	@Override
	public SmartList<E> addAllReturn(int index, Collection<E> c) {
		addAll(index, c);
		return this;
	}

	@Override
	public SmartList<E> removeIndexReturn(int index) {
		remove(index);
		return this;
	}

	@Override
	public SmartList<E> setReturn(int index, E elem) {
		set(index, elem);
		return this;
	}

    @Override
    public boolean addAll(final int index, final Collection<? extends E> c) {
        return getInternalList().addAll(index, c);
    }

    @Override
    public E get(final int index) {
        return getInternalList().get(index);
    }

    @Override
    public E set(final int index, final E element) {
        return getInternalList().set(index, element);
    }

    @Override
    public void add(final int index, final E element) {
        getInternalList().add(index, element);
    }

    @Override
    public E remove(final int index) {
        return getInternalList().remove(index);
    }

    @Override
    public int indexOf(final Object o) {
        return getInternalList().indexOf(o);
    }

    @Override
    public int lastIndexOf(final Object o) {
        return getInternalList().indexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return getInternalList().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        return getInternalList().listIterator(index);
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex) {
        return getInternalList().subList(fromIndex, toIndex);
    }

    /** ISmartList methods */

    @Override
    public <R> SmartList<R> map(final UnaryFunction<R, ? super E> function) {
        SmartCollection<R> result = super.map(function);
        return (SmartList<R>) result;
    }

    @Override
    public <T> SmartList<T> castEach(final Class<T> clazz) {
        SmartCollection<T> result = super.castEach(clazz);
        return (SmartList<T>) result;
    }

    @Override
    public E head() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty, no head element available");
        }
        return getInternalList().get(0);
    }

    @Override
    public SmartList<E> tail() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("List is empty, no tail list available");
        }

        return drop(1);
    }

    @Override
    public E last() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty, no last element available");
        }

        return getInternalList().get(getInternalList().size() - 1);
    }

    @Override
    public E get(final int index, final E defaultVal) {
        E result = null;
        try {
            result = get(index);
        } catch (IndexOutOfBoundsException e) {
            result = defaultVal;
        }

        return result;
    }

    @Override
    public SmartList<E> take(final int n) {
        int high = n;
        if (size() < n) {
            high = size();
        }

        SmartList<E> result = createNewInstance(subList(0, high));
        return result;
    }

    @Override
    public SmartList<E> drop(final int n) {
        int lower = n;
        if (n > size()) {
            lower = size();
        }

        SmartList<E> result = createNewInstance(subList(lower, size()));
        return result;
    }

    @Override
    public SmartList<E> takeWhile(final Predicate<? super E> pred) {
        SmartList<E> resultList = createNewInstance();
        for (E elem : this) {
            if (pred.test(elem)) {
                resultList.add(elem);
            } else {
                break;
            }
        }

        return resultList;
    }

    @Override
    public SmartList<E> dropWhile(final Predicate<? super E> pred) {
        SmartList<E> resultList = createNewInstance(getInternalList());
        for (E elem : this) {
            if (pred.test(elem)) {
                resultList.remove(0);
            } else {
                break;
            }
        }

        return resultList;
    }

    @Override
    public SmartList<E> removeDuplicates() {
        SmartSet<E> resultSet = new SmartHashSet<E>(internalColl);
        clear();
        addAll(resultSet);
        return this;
    }

    @Override
    public SmartList<E> intersperse(final E elem) {
        for (int i = 1; i < size(); i += 2) {
            add(i, elem);
        }
        
        return this;
    }

    @Override
    public <T> SmartMap<E, T> zipWith(final List<T> anotherList) {
        SmartMap<E, T> resultMap = new SmartHashMap<E, T>();
        Iterator<E> keys = iterator();
        Iterator<T> vals = anotherList.iterator();

        while (keys.hasNext() && vals.hasNext()) {
            resultMap.put(keys.next(), vals.next());
        }

        return resultMap;
    }

    @Override
    public SmartList<Integer> getIndicesList() {
        SmartList<Integer> result = new SmartArrayList<Integer>(
                new ArrayList<Integer>());
        for (int i = 0; i < size(); i++) {
            result.add(i);
        }

        return result;
    }

    @Override
    public SmartMap<E, Integer> getOccurenceCountMap() {
        SmartMap<E, Integer> result = new SmartHashMap<E, Integer>();
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
	public SmartList<E> reverse() {
        Collections.reverse(this);
        return this;
    }

    @Override
    public int sizeWithoutNulls() {
    	int i = 0;
        for (E elem : getInternalList()) {
            if (elem != null) {
               i++;
            }
        }

        return i;
    }
    
    @Override
    public List<E> toStandardCollection() {
    	return (List<E>) super.toStandardCollection();
    }
}
