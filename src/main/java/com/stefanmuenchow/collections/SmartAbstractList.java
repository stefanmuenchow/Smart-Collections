package com.stefanmuenchow.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public abstract class SmartAbstractList<E> extends SmartAbstractCollection<E>
        implements SmartList<E> {

    protected SmartAbstractList(final Collection<E> innerList) {
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
    public <R> SmartList<R> map(final UnaryFunction<R, E> function) {
        SmartCollection<R> result = super.map(function);
        return (SmartList<R>) result;
    }

    @Override
    public SmartList<Object> flatten() {
        SmartCollection<Object> result = super.flatten();
        return (SmartList<Object>) result;
    }

    @Override
    public E head() {
        return getInternalList().get(0);
    }

    @Override
    public SmartList<E> tail() {
        return drop(1);
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
    public SmartList<E> take(final int n) {
        SmartList<E> result = createNewInstance(subList(0, n));
        return result;
    }

    @Override
    public SmartList<E> drop(final int n) {
        SmartList<E> result = createNewInstance(subList(n, size()));
        return result;
    }

    @Override
    public SmartList<E> takeWhile(final Predicate<E> pred) {
        SmartList<E> resultList = createNewInstance();
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
    public SmartList<E> dropWhile(final Predicate<E> pred) {
        SmartList<E> resultList = createNewInstance(getInternalList());
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
    public SmartList<E> removeDuplicates() {
        SmartSet<E> resultSet = new SmartHashSet<E>(this);
        internalColl = new ArrayList<E>(resultSet);
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
