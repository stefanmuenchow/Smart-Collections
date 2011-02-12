package com.stefanmuenchow.collections.immutable;

import java.util.Arrays;
import java.util.Collection;

import com.stefanmuenchow.collections.SmartHashSet;
import com.stefanmuenchow.collections.SmartSet;

public class ImmutableHashSet<E> extends AbstractImmutableSet<E> implements ImmutableSet<E> {

    protected ImmutableHashSet(final SmartSet<E> collection) {
        super(collection);
    }

    public ImmutableHashSet() {
        this(new SmartHashSet<E>());
    }

    public ImmutableHashSet(final E... elems) {
        this(Arrays.asList(elems));
    }

    public ImmutableHashSet(final Collection<E> collection) {
        this(new SmartHashSet<E>(collection));
    }

    /** Helper methods */

    @Override
    protected ImmutableSet<E> createNewInstance() {
        return new ImmutableHashSet<E>();
    }

    @Override
    protected <T> ImmutableSet<T> createNewInstance(final Collection<T> aColl) {
        return new ImmutableHashSet<T>(aColl);
    }
}
