package com.stefanmuenchow.collections.immutable;

import java.util.Arrays;
import java.util.Collection;

import com.stefanmuenchow.collections.SmartArrayList;
import com.stefanmuenchow.collections.SmartList;

public class ImmutableArrayList<E> extends ImmutableAbstractList<E> implements ImmutableList<E> {

    protected ImmutableArrayList(final SmartList<E> collection) {
        super(collection);
    }

    public ImmutableArrayList() {
        this(new SmartArrayList<E>());
    }

    public ImmutableArrayList(final E... elems) {
        this(Arrays.asList(elems));
    }

    public ImmutableArrayList(final Collection<E> collection) {
        this(new SmartArrayList<E>(collection));
    }

    /** Helper methods */

    @Override
    protected ImmutableList<E> createNewInstance() {
        return new ImmutableArrayList<E>();
    }

    @Override
    protected <T> ImmutableList<T> createNewInstance(final Collection<T> aColl) {
        return new ImmutableArrayList<T>(aColl);
    }
}
