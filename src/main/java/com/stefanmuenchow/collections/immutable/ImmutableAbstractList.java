package com.stefanmuenchow.collections.immutable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import com.stefanmuenchow.collections.SmartArrayList;
import com.stefanmuenchow.collections.SmartCollection;
import com.stefanmuenchow.collections.SmartList;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public abstract class ImmutableAbstractList<E> extends ImmutableAbstractCollection<E> implements ImmutableList<E> {

    public ImmutableAbstractList(final SmartCollection<E> collection) {
        super(collection);
    }

    /** Helper methods */

    private SmartList<E> getInternalList() {
        return (SmartList<E>) internalColl;
    }

    @Override
    protected abstract ImmutableList<E> createNewInstance();

    @Override
    protected abstract <T> ImmutableList<T> createNewInstance(Collection<T> aColl);

    /** ImmutableList methods */

    @Override
    public ImmutableList<E> addAll(final int index, final Collection<? extends E> c) {
        ArrayList<E> temp = new ArrayList<E>(getInternalList());
        temp.addAll(index, c);

        return createNewInstance(temp);
    }

    @Override
    public E get(final int index) {
        return getInternalList().get(index);
    }

    @Override
    public ImmutableList<E> set(final int index, final E element) {
        ArrayList<E> temp = new ArrayList<E>(getInternalList());
        temp.set(index, element);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableList<E> add(final int index, final E element) {
        ArrayList<E> temp = new ArrayList<E>(getInternalList());
        temp.add(index, element);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableList<E> remove(final int index) {
        ArrayList<E> temp = new ArrayList<E>(getInternalList());
        temp.remove(index);

        return createNewInstance(temp);
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
    public ImmutableList<E> subList(final int fromIndex, final int toIndex) {
        ArrayList<E> temp = new ArrayList<E>(getInternalList());
        temp.subList(fromIndex, toIndex);

        return createNewInstance(temp);
    }

    @Override
    public E head() throws NoSuchElementException {
        return getInternalList().head();
    }

    @Override
    public ImmutableList<E> tail() throws UnsupportedOperationException {
        return createNewInstance(getInternalList().tail());
    }

    @Override
    public E last() throws NoSuchElementException {
        return getInternalList().last();
    }

    @Override
    public E get(final int index, final E defaultVal) {
        return getInternalList().get(index, defaultVal);
    }

    @Override
    public ImmutableList<E> take(final int n) {
        return createNewInstance(getInternalList().take(n));
    }

    @Override
    public ImmutableList<E> drop(final int n) {
        return createNewInstance(getInternalList().drop(n));
    }

    @Override
    public ImmutableList<E> takeWhile(final Predicate<E> pred) {
        return createNewInstance(getInternalList().takeWhile(pred));
    }

    @Override
    public ImmutableList<E> dropWhile(final Predicate<E> pred) {
        return createNewInstance(getInternalList().dropWhile(pred));
    }

    @Override
    public ImmutableList<E> removeDuplicates() {
        SmartList<E> temp = new SmartArrayList<E>(getInternalList());
        temp.removeDuplicates();

        return createNewInstance(temp);
    }

    @Override
    public ImmutableList<E> intersperse(final E elem) {
        SmartList<E> temp = new SmartArrayList<E>(getInternalList());
        temp.intersperse(elem);

        return createNewInstance(temp);
    }

    @Override
    public <T> ImmutableMap<E, T> zipWith(final List<T> anotherList) {
        return new ImmutableHashMap<E, T>(getInternalList().zipWith(anotherList));
    }

    @Override
    public ImmutableList<Integer> getIndicesList() {
        return createNewInstance(getInternalList().getIndicesList());
    }

    @Override
    public ImmutableMap<E, Integer> getOccurenceCountMap() {
        return new ImmutableHashMap<E, Integer>(getInternalList().getOccurenceCountMap());
    }

    @Override
    public ImmutableList<E> reverse() {
        SmartList<E> temp = new SmartArrayList<E>(getInternalList());
        temp.reverse();

        return createNewInstance(temp);
    }

    @Override
    public int sizeWithoutNulls() {
        return getInternalList().sizeWithoutNulls();
    }

    @Override
    public
    <R> ImmutableList<R> map(final UnaryFunction<R, E> function) {
        ImmutableCollection<R> result = super.map(function);
        return (ImmutableList<R>) result;
    }

    @Override
    public
    ImmutableList<Object> flatten() {
        ImmutableCollection<Object> result = super.flatten();
        return (ImmutableList<Object>) result;
    }

    @Override
    public
    <T> ImmutableList<T> castAllElements(final Class<T> clazz) {
        ImmutableCollection<T> result = super.castAllElements(clazz);
        return (ImmutableList<T>) result;
    }
}
