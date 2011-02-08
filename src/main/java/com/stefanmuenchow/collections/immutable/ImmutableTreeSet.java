package com.stefanmuenchow.collections.immutable;

import java.util.Collection;
import java.util.Comparator;

import com.stefanmuenchow.collections.SmartCollection;

public class ImmutableTreeSet<E> extends ImmutableAbstractSet<E> implements ImmutableSortedSet<E> {

    public ImmutableTreeSet(final SmartCollection<E> collection) {
        super(collection);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Comparator<? super E> comparator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableSortedSet<E> subSet(final E fromElement, final E toElement) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableSortedSet<E> headSet(final E toElement) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableSortedSet<E> tailSet(final E fromElement) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E first() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E last() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ImmutableSet<E> createNewInstance() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected <T> ImmutableSet<T> createNewInstance(final Collection<T> aColl) {
        // TODO Auto-generated method stub
        return null;
    }

}
