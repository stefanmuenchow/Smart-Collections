package com.stefanmuenchow.collections.immutable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import com.stefanmuenchow.collections.SmartCollection;
import com.stefanmuenchow.collections.SmartSortedSet;
import com.stefanmuenchow.collections.SmartTreeSet;

public class ImmutableTreeSet<E> extends AbstractImmutableSet<E> implements ImmutableSortedSet<E> {

    protected ImmutableTreeSet(final SmartCollection<E> collection) {
        super(collection);
    }
    
    public ImmutableTreeSet() {
        this(new SmartTreeSet<E>());
    }

    public ImmutableTreeSet(final E... elems) {
        this(Arrays.asList(elems));
    }

    public ImmutableTreeSet(final Collection<E> collection) {
        this(new SmartTreeSet<E>(collection));
    }
    
    /** Helper methods */

    private SmartSortedSet<E> getInternalSet() {
        return (SmartSortedSet<E>) internalColl;
    }
    
    @Override
    protected ImmutableSortedSet<E> createNewInstance() {
        return new ImmutableTreeSet<E>();
    }

    @Override
    protected <T> ImmutableSortedSet<T> createNewInstance(final Collection<T> aColl) {
        return new ImmutableTreeSet<T>(aColl);
    }
    
    /** ImmutableSortedSet methods */

    @Override
    public Comparator<? super E> comparator() {
        return getInternalSet().comparator();
    }

    @Override
    public ImmutableSortedSet<E> subSet(final E fromElement, final E toElement) {
        return createNewInstance(getInternalSet().subSet(fromElement, toElement));
    }

    @Override
    public ImmutableSortedSet<E> headSet(final E toElement) {
        return createNewInstance(getInternalSet().headSet(toElement));
    }

    @Override
    public ImmutableSortedSet<E> tailSet(final E fromElement) {
        return createNewInstance(getInternalSet().tailSet(fromElement));
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
