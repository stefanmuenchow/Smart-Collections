package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SmartHashSet<E> extends SmartAbstractCollection<E> implements
        SmartSet<E> {

    public SmartHashSet() {
        this(new HashSet<E>());
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
}
