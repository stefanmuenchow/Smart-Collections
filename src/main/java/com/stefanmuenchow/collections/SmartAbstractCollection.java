package com.stefanmuenchow.collections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public abstract class SmartAbstractCollection<E> implements SmartCollection<E> {
    protected Collection<E> internalColl;

    protected SmartAbstractCollection(final Collection<E> collection) {
        internalColl = collection;
    }

    /** Helper methods */

    protected abstract SmartCollection<E> createNewInstance();

    protected abstract <T> SmartCollection<T> createNewInstance(
            Collection<T> aColl);

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
    public boolean contains(final Object o) {
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
    public <T> T[] toArray(final T[] a) {
        return internalColl.toArray(a);
    }

    @Override
    public boolean add(final E e) {
        return internalColl.add(e);
    }

    @Override
    public boolean remove(final Object o) {
        return internalColl.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return internalColl.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        return internalColl.addAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return internalColl.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return internalColl.retainAll(c);
    }

    @Override
    public void clear() {
        internalColl.clear();
    }

    /** ISmartCollection methods */

    @Override
    public E find(final Predicate<E> pred) {
        for (E elem : internalColl) {
            if (pred.check(elem)) {
                return elem;
            }
        }

        return null;
    }

    @Override
    public SmartCollection<E> filter(final Predicate<E> predicate) {
        List<E> toRemove = new ArrayList<E>();

        for (E elem : internalColl) {
            if (!predicate.check(elem)) {
                toRemove.add(elem);
            }
        }

        internalColl.removeAll(toRemove);
        return this;
    }

    @Override
    public SmartCollection<E> remove(final Predicate<E> predicate) {
        List<E> toRemove = new ArrayList<E>();

        for (E elem : internalColl) {
            if (predicate.check(elem)) {
                toRemove.add(elem);
            }
        }

        internalColl.removeAll(toRemove);
        return this;
    }

    @Override
    public SmartCollection<E> replace(final E seek, final E replacement) {
        final E seekVar = seek;

        return this.replace(new Predicate<E>() {
            @Override
            public boolean check(final E input) {
                return input.equals(seekVar);
            }
        }, replacement);
    }

    @Override
    public SmartCollection<E> replace(final Predicate<E> predicate,
            final E replacement) {
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
    public <R> R reduce(final R initial, final BinaryFunction<R, R, E> funct) {
        R result = initial;

        for (E elem : internalColl) {
            result = funct.execute(result, elem);
        }

        return result;
    }

    @Override
    public E reduce(final BinaryFunction<E, E, E> funct) {
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
    public String join(final String delimiter) {
        StringBuffer result = new StringBuffer();
        for (E elem : internalColl) {
            result.append(elem.toString());
            result.append(delimiter);
        }

        result.setLength(result.length() - delimiter.length());
        return result.toString();
    }

    @Override
    public int count(final Predicate<E> predicate) {
        int counter = 0;
        for (E elem : internalColl) {
            if (predicate.check(elem)) {
                counter++;
            }
        }

        return counter;
    }

    @Override
    public boolean exists(final Predicate<E> pred) {
        for (E elem : internalColl) {
            if (pred.check(elem)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean forall(final Predicate<E> pred) {
        for (E elem : internalColl) {
            if (!pred.check(elem)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <R> SmartCollection<R> map(final UnaryFunction<R, E> function) {
        SmartCollection<R> resultList = createNewInstance(new ArrayList<R>());
        for (E elem : internalColl) {
            resultList.add(function.execute(elem));
        }

        return resultList;
    }

    @Override
    public SmartCollection<Object> flatten() {
        SmartCollection<Object> resultList = createNewInstance(new ArrayList<Object>());

        Iterator<E> iterator = this.iterator();
        E first = null;

        if (iterator.hasNext()) {
            first = iterator.next();
        }

        if (first != null && first instanceof Collection) {
            for (E elem : internalColl) {
                @SuppressWarnings("unchecked")
                Collection<Object> innerList = (Collection<Object>) elem;
                resultList.addAll(createNewInstance(innerList).flatten());
            }
        } else {
            resultList.addAll(internalColl);
        }

        return resultList;
    }

    @Override
	public <T> SmartCollection<T> castAllElements(final Class<T> clazz) {
    	SmartCollection<T> result = createNewInstance(new ArrayList<T>());

    	for (E elem : internalColl) {
    		result.add(clazz.cast(elem));
    	}

    	return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] ToArray(final Class<T> clazz) {
        return toArray((T[]) Array.newInstance(clazz, size()));
    }

    @Override
    public boolean equals(final Object obj) {
        return internalColl.equals(obj);
    }

    @Override
    public int hashCode() {
        return internalColl.hashCode();
    }

    @Override
    public String toString() {
        return internalColl.toString();
    }
}
