package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SmartSortedSet<E extends Comparable<E>> extends SmartSet<E>
        implements ISmartSortedSet<E> {

    public SmartSortedSet() {
        super(new TreeSet<E>());
    }

    public SmartSortedSet(final Collection<E> collection) {
        super(new TreeSet<E>(collection));
    }

    /** Helper methods */

    private SortedSet<E> getInternalSet() {
        return (SortedSet<E>) internalColl;
    }

    /** ISmartSortedSet methods */

    @Override
    public Comparator<? super E> comparator() {
        return getInternalSet().comparator();
    }

    @Override
    public SortedSet<E> subSet(final E fromElement, final E toElement) {
        return getInternalSet().subSet(fromElement, toElement);
    }

    @Override
    public SortedSet<E> headSet(final E toElement) {
        return getInternalSet().headSet(toElement);
    }

    @Override
    public SortedSet<E> tailSet(final E fromElement) {
        return getInternalSet().tailSet(fromElement);
    }

    @Override
    public E first() {
        return getInternalSet().first();
    }

    @Override
    public E last() {
        return getInternalSet().last();
    }
}
