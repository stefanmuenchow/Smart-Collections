package com.stefanmuenchow.collections.immutable;

import java.util.Arrays;
import java.util.Collection;

import com.stefanmuenchow.collections.SmartLinkedList;
import com.stefanmuenchow.collections.SmartList;

public class ImmutableLinkedList<E> extends ImmutableAbstractList<E> implements ImmutableList<E> {

    protected ImmutableLinkedList(final SmartList<E> collection) {
        super(collection);
    }

    public ImmutableLinkedList() {
        this(new SmartLinkedList<E>());
    }

    public ImmutableLinkedList(final E... elems) {
        this(Arrays.asList(elems));
    }

    public ImmutableLinkedList(final Collection<E> collection) {
        this(new SmartLinkedList<E>(collection));
    }

    /** Helper methods */

    @Override
    protected ImmutableList<E> createNewInstance() {
        return new ImmutableLinkedList<E>();
    }

    @Override
    protected <T> ImmutableList<T> createNewInstance(final Collection<T> aColl) {
        return new ImmutableLinkedList<T>(aColl);
    }

}
