package com.stefanmuenchow.collections.immutable;

import java.util.Comparator;

public interface ImmutableSortedSet<E> extends ImmutableSet<E> {

    public Comparator<? super E> comparator();
    public ImmutableSortedSet<E> subSet(final E fromElement, final E toElement);
    public ImmutableSortedSet<E> headSet(final E toElement);
    public ImmutableSortedSet<E> tailSet(final E fromElement);
    public E first();
    public E last();
}
