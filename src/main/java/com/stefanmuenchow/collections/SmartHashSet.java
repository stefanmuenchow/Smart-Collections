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
import java.util.HashSet;
import java.util.Set;

import com.stefanmuenchow.collections.function.UnaryFunction;

public class SmartHashSet<E> extends SmartAbstractCollection<E> implements
        SmartSet<E> {

    public SmartHashSet() {
        this(new HashSet<E>());
    }

    public SmartHashSet(final E... elems) {
        this(Arrays.asList(elems));
    }

    public SmartHashSet(final Collection<E> collection) {
        super(new HashSet<E>(collection));
    }

    /** Helper methods */

    private Set<E> getInternalSet() {
        return (Set<E>) internalColl;
    }

    @Override
    protected SmartSet<E> createNewInstance() {
        return new SmartHashSet<E>();
    }

    @Override
    protected <T> SmartSet<T> createNewInstance(final Collection<T> aColl) {
        return new SmartHashSet<T>(aColl);
    }

    /** ISmartSet methods */

    @Override
    public boolean isSubsetOf(final Set<E> anotherSet) {
        SmartSet<E> temp = new SmartHashSet<E>(this);
        temp.difference(anotherSet);
        return temp.isEmpty();
    }

    @Override
    public boolean isProperSubsetOf(final Set<E> anotherSet) {
        return isSubsetOf(anotherSet) && !equals(anotherSet);
    }

    @Override
    public boolean isSupersetOf(final Set<E> anotherSet) {
        SmartSet<E> temp = new SmartHashSet<E>(anotherSet);
        return temp.isSubsetOf(this);
    }

    @Override
    public boolean isProperSupersetOf(final Set<E> anotherSet) {
        return isSupersetOf(anotherSet) && !equals(anotherSet);
    }

    @Override
    public SmartSet<E> union(final Set<E> anotherSet) {
        getInternalSet().addAll(anotherSet);
        return this;
    }

    @Override
    public SmartSet<E> intersection(final Set<E> anotherSet) {
        getInternalSet().retainAll(anotherSet);
        return this;
    }

    @Override
    public SmartSet<E> difference(final Set<E> anotherSet) {
        getInternalSet().removeAll(anotherSet);
        return this;
    }

    @Override
    public <R> SmartSet<R> map(final UnaryFunction<R, E> function) {
        SmartCollection<R> result = super.map(function);
        return (SmartSet<R>) result;
    }

    @Override
    public SmartSet<Object> flatten() {
        SmartCollection<Object> result = super.flatten();
        return (SmartSet<Object>) result;
    }

    @Override
    public <T> SmartSet<T> castAllElements(final Class<T> clazz) {
        SmartCollection<T> result = super.castAllElements(clazz);
        return (SmartSet<T>) result;
    }
}
