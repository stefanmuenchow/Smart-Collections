package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.Set;

import com.stefanmuenchow.collections.function.UnaryFunction;

public abstract class SmartAbstractSet<E> extends SmartAbstractCollection<E> implements SmartSet<E> {

    protected SmartAbstractSet(final Collection<E> innerSet) {
        super(innerSet);
    }

    /** Helper methods */

    @Override
    protected abstract SmartSet<E> createNewInstance();

    @Override
    protected abstract <T> SmartSet<T> createNewInstance(Collection<T> aColl);

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
    public void union(final Set<E> anotherSet) {
        addAll(anotherSet);
    }

    @Override
    public void intersection(final Set<E> anotherSet) {
        retainAll(anotherSet);
    }

    @Override
    public void difference(final Set<E> anotherSet) {
        removeAll(anotherSet);
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
