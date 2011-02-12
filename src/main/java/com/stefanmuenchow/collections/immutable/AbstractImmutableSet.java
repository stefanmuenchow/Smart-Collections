package com.stefanmuenchow.collections.immutable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.stefanmuenchow.collections.SmartCollection;
import com.stefanmuenchow.collections.SmartSet;
import com.stefanmuenchow.collections.function.UnaryFunction;

public abstract class AbstractImmutableSet<E> extends AbstractImmutableCollection<E> implements ImmutableSet<E> {

    public AbstractImmutableSet(final SmartCollection<E> collection) {
        super(collection);
    }

    /** Helper methods */

    private SmartSet<E> getInternalSet() {
        return (SmartSet<E>) internalColl;
    }

    @Override
    protected abstract ImmutableSet<E> createNewInstance();

    @Override
    protected abstract <T> ImmutableSet<T> createNewInstance(Collection<T> aColl);

    @Override
    public boolean isSubsetOf(final Set<E> anotherSet) {
        return getInternalSet().isSubsetOf(anotherSet);
    }

    @Override
    public boolean isProperSubsetOf(final Set<E> anotherSet) {
        return getInternalSet().isSupersetOf(anotherSet);
    }

    @Override
    public boolean isSupersetOf(final Set<E> anotherSet) {
        return getInternalSet().isSupersetOf(anotherSet);
    }

    @Override
    public boolean isProperSupersetOf(final Set<E> anotherSet) {
        return getInternalSet().isProperSupersetOf(anotherSet);
    }

    @Override
    public ImmutableSet<E> union(final Set<E> anotherSet) {
        Set<E> temp = new HashSet<E>(getInternalSet());
        temp.addAll(anotherSet);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableSet<E> intersection(final Set<E> anotherSet) {
        Set<E> temp = new HashSet<E>(getInternalSet());
        temp.retainAll(anotherSet);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableSet<E> difference(final Set<E> anotherSet) {
        Set<E> temp = new HashSet<E>(getInternalSet());
        temp.removeAll(anotherSet);

        return createNewInstance(temp);
    }

    @Override
    public
    <R> ImmutableSet<R> map(final UnaryFunction<R, E> function) {
        ImmutableCollection<R> result = super.map(function);
        return (ImmutableSet<R>) result;
    }

    @Override
    public
    <T> ImmutableSet<T> castAllElements(final Class<T> clazz) {
        ImmutableCollection<T> result = super.castAllElements(clazz);
        return (ImmutableSet<T>) result;
    }
}
