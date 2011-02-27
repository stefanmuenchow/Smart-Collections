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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public abstract class AbstractSmartCollection<E> implements SmartCollection<E> {
    protected Collection<E> internalColl;

    protected AbstractSmartCollection(final Collection<E> collection) {
        internalColl = collection;
    }

    /** Helper methods */

    protected abstract SmartCollection<E> createNewInstance();

    protected abstract <T> SmartCollection<T> createNewInstance(
            Collection<T> aColl);

    /** Collection methods */
    
	@Override
	public SmartCollection<E> addReturn(E elem) {
		add(elem);
		return this;
	}

	@Override
	public SmartCollection<E> addAllReturn(Collection<E> coll) {
		addAll(coll);
		return this;
	}

	@Override
	public SmartCollection<E> removeReturn(E elem) {
		remove(elem);
		return this;
	}

	@Override
	public SmartCollection<E> removeAllReturn(Collection<E> coll) {
		removeAll(coll);
		return this;
	}

	@Override
	public SmartCollection<E> retainAllReturn(Collection<E> coll) {
		retainAll(coll);
		return this;
	}

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
    public E find(final Predicate<? super E> pred) {
        for (E elem : internalColl) {
            if (pred.test(elem)) {
                return elem;
            }
        }

        throw new NoSuchElementException("No element matches the given predicate");
    }

    @Override
    public SmartCollection<E> filter(final Predicate<? super E> predicate) {
        List<E> toRemove = new ArrayList<E>();

        for (E elem : internalColl) {
            if (!predicate.test(elem)) {
                toRemove.add(elem);
            }
        }

        internalColl.removeAll(toRemove);
        return this;
    }

    @Override
    public SmartCollection<E> remove(final Predicate<? super E> predicate) {
        List<E> toRemove = new ArrayList<E>();

        for (E elem : internalColl) {
            if (predicate.test(elem)) {
                toRemove.add(elem);
            }
        }

        internalColl.removeAll(toRemove);
        return this;
    }

    @Override
    public SmartCollection<E> replace(final E seek, final E replacement) {
        final E seekVar = seek;

        this.replace(new Predicate<E>() {
            @Override
            public boolean test(final E input) {
                return input.equals(seekVar);
            }
        }, replacement);
        
        return this;
    }

    @Override
    public SmartCollection<E> replace(final Predicate<? super E> predicate, final E replacement) {
        List<E> tempList = new ArrayList<E>(internalColl);
        internalColl.clear();

        for (E elem : tempList) {
            if (predicate.test(elem)) {
                internalColl.add(replacement);
            } else {
                internalColl.add(elem);
            }
        }
        
        return this;
    }

    @Override
    public <R> R reduce(final R initial, final BinaryFunction<R, ? super E> funct) {
        R result = initial;

        for (E elem : internalColl) {
            result = funct.apply(result, elem);
        }

        return result;
    }

	@Override
	@SuppressWarnings("unchecked")
    public E reduce(final BinaryFunction<? super E, ? super E> funct) {
        Iterator<E> it = internalColl.iterator();
        E result = null;

        if (it.hasNext()) {
            result = it.next();
        }

        while (it.hasNext()) {
            result = (E) funct.apply(result, it.next());
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
    public int count(final Predicate<? super E> predicate) {
        int counter = 0;
        for (E elem : internalColl) {
            if (predicate.test(elem)) {
                counter++;
            }
        }

        return counter;
    }

    @Override
    public boolean exists(final Predicate<? super E> pred) {
        for (E elem : internalColl) {
            if (pred.test(elem)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public SmartCollection<E> replace(final Map<E, E> replacements) {
        List<E> tempList = new ArrayList<E>(internalColl);
        internalColl.clear();

        for (E elem : tempList) {
            if (replacements.get(elem) != null) {
                internalColl.add(replacements.get(elem));
            } else {
                internalColl.add(elem);
            }
        }
        
        return this;
    }

    @Override
    public boolean forall(final Predicate<? super E> pred) {
        for (E elem : internalColl) {
            if (!pred.test(elem)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <R> SmartCollection<R> map(final UnaryFunction<R, ? super E> function) {
        SmartCollection<R> resultList = createNewInstance(new ArrayList<R>());
        for (E elem : internalColl) {
            resultList.add(function.apply(elem));
        }

        return resultList;
    }

    @Override
    public <T> SmartCollection<T> castEach(final Class<T> clazz) {
        SmartCollection<T> result = createNewInstance(new ArrayList<T>());

        for (E elem : internalColl) {
            result.add(clazz.cast(elem));
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(final Class<T> clazz) {
        return toArray((T[]) Array.newInstance(clazz, size()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(final Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            return internalColl.equals(((AbstractSmartCollection<E>) obj).internalColl);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return internalColl.hashCode();
    }

    @Override
    public String toString() {
        return internalColl.toString();
    }
    
    @Override
    public Collection<E> toStandardCollection() {
    	return internalColl;
    }
}
