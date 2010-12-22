package com.stefanmuenchow.collections.immutable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.stefanmuenchow.collections.SmartArrayList;
import com.stefanmuenchow.collections.SmartCollection;
import com.stefanmuenchow.collections.SmartList;
import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public abstract class ImmutableAbstractCollection<E> implements ImmutableCollection<E> {
    private final SmartCollection<E> internalColl;

    public ImmutableAbstractCollection(final SmartCollection<E> collection) {
        internalColl = collection;
    }

    /** Helper methods */

    protected abstract ImmutableCollection<E> createNewInstance();

    protected abstract <T> ImmutableCollection<T> createNewInstance(Collection<T> aColl);

    /** ImmutableCollection methods */

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
    public ImmutableCollection<E> remove(final Object o) {
        List<E> temp = new ArrayList<E>(internalColl);
        temp.remove(o);

        return createNewInstance(temp);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return internalColl.containsAll(c);
    }

    @Override
    public ImmutableCollection<E> addAll(final Collection<? extends E> c) {
        List<E> temp = new ArrayList<E>(internalColl);
        temp.addAll(c);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableCollection<E> removeAll(final Collection<?> c) {
        List<E> temp = new ArrayList<E>(internalColl);
        temp.removeAll(c);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableCollection<E> retainAll(final Collection<?> c) {
        List<E> temp = new ArrayList<E>(internalColl);
        temp.retainAll(c);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableCollection<E> clear() {
        return createNewInstance();
    }

    @Override
    public ImmutableCollection<E> add(final E elem) {
        List<E> temp = new ArrayList<E>(internalColl);
        temp.add(elem);

        return createNewInstance(temp);
    }

    @Override
    public E find(final Predicate<E> pred) throws NoSuchElementException {
        return internalColl.find(pred);
    }

    @Override
    public ImmutableCollection<E> filter(final Predicate<E> predicate) {
        SmartList<E> temp = new SmartArrayList<E>(internalColl);
        temp.filter(predicate);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableCollection<E> remove(final Predicate<E> predicate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableCollection<E> replace(final E seek, final E replacement) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableCollection<E> replace(final Predicate<E> predicate, final E replacement) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <R> ImmutableCollection<R> map(final UnaryFunction<R, E> function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <R> R reduce(final R initial, final BinaryFunction<R, E> funct) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E reduce(final BinaryFunction<E, E> funct) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String join(final String delimiter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int count(final Predicate<E> predicate) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean exists(final Predicate<E> pred) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean forall(final Predicate<E> pred) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ImmutableCollection<E> replace(final Map<E, E> replacements) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableCollection<Object> flatten() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> ImmutableCollection<T> castAllElements(final Class<T> clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArray(final Class<T> clazz) {
        // TODO Auto-generated method stub
        return null;
    }
}
