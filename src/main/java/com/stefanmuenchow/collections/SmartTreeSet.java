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

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SmartTreeSet<E> extends SmartAbstractSet<E> implements SmartSortedSet<E> {

    public SmartTreeSet() {
        this(new TreeSet<E>());
    }

    public SmartTreeSet(final E... elems) {
        this(Arrays.asList(elems));
    }

    public SmartTreeSet(final Collection<E> collection) {
        super(new TreeSet<E>(collection));
    }

    /** Helper methods */

    private SortedSet<E> getInternalSet() {
        return (SortedSet<E>) internalColl;
    }

    @Override
    protected SmartSortedSet<E> createNewInstance() {
        return new SmartTreeSet<E>();
    }

    @Override
    protected <T> SmartSortedSet<T> createNewInstance(final Collection<T> aColl) {
        return new SmartTreeSet<T>(aColl);
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
