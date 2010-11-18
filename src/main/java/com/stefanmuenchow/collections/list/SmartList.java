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

public class SmartList<E> extends AbstractSmartCollection<E> implements
        ISmartList<E> {

    public SmartList() {
        super(new ArrayList<E>());
    }

    public SmartList(final Collection<? extends E> innerList) {
        super(new ArrayList<E>(innerList));
    }

    /** Helper methods */

    private List<E> getInternalList() {
        return (List<E>) internalColl;
    }

    /** List methods */

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

    /** ISmartCollection methods */

    @Override
    public <R> ISmartCollection<R> map(final IUnaryFunction<R, E> function) {
        ISmartList<R> resultList = new SmartList<R>();
        for (E elem : getInternalList()) {
            resultList.add(function.execute(elem));
        }

        return resultList;
    }

    @Override
    public ISmartCollection<E> flatten() {
        ISmartList<E> resultList = new SmartList<E>();

        if (!isEmpty() && first() instanceof Collection) {
            for (E elem : getInternalList()) {
                @SuppressWarnings("unchecked")
                Collection<? extends E> innerList = (Collection<? extends E>) elem;
                resultList.addAll(new SmartList<E>(innerList).flatten());
            }
        }

        return resultList;
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
    public E get(final int index, final E defaultVal) {
        E result = null;
        try {
            result = get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            result = defaultVal;
        }

        return result;
    }

    @Override
    public ISmartList<E> take(final int n) {
        ISmartList<E> result = new SmartList<E>(subList(0, n));
        return result;
    }

    @Override
    public ISmartList<E> drop(final int n) {
        ISmartList<E> result = new SmartList<E>(subList(n, size()));
        return result;
    }

    @Override
    public ISmartList<E> takeWhile(final IPredicate<E> pred) {
        ISmartList<E> resultList = new SmartList<E>();
        for (E elem : this) {
            if (pred.check(elem)) {
                resultList.add(elem);
            } else {
                break;
            }
        }

        return resultList;
    }

    @Override
    public ISmartList<E> dropWhile(final IPredicate<E> pred) {
        ISmartList<E> resultList = new SmartList<E>(this);
        for (E elem : this) {
            if (pred.check(elem)) {
                resultList.remove(0);
            } else {
                break;
            }
        }

        return resultList;
    }

    @Override
    public ISmartList<E> removeDuplicates() {
        ISmartSet<E> resultSet = new SmartSet<E>(this);
        internalColl = new ArrayList<E>(resultSet);
        return this;
    }

    @Override
    public ISmartList<E> intersperse(final E elem) {
        for (int i = 1; i < size(); i += 2) {
            add(i, elem);
        }

        return this;
    }

    @Override
    public <T> ISmartMap<E, T> zipWith(final List<T> anotherList) {
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
