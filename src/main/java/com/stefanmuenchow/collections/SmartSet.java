package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.stefanmuenchow.collections.function.IUnaryFunction;

public class SmartSet<E> extends AbstractSmartCollection<E> implements
        ISmartSet<E> {

    public SmartSet() {
        super(new HashSet<E>());
    }

    public SmartSet(final Collection<E> collection) {
        super(new HashSet<E>(collection));
    }

    /** Helper methods */

    private Set<E> getInternalSet() {
        return (Set<E>) internalColl;
    }

    /** ISmartCollection methods */

    @Override
    public <R> ISmartCollection<R> map(final IUnaryFunction<R, E> function) {
        ISmartSet<R> resultSet = new SmartSet<R>();
        for (E elem : getInternalSet()) {
            resultSet.add(function.execute(elem));
        }

        return resultSet;
    }

    @Override
    public ISmartCollection<E> flatten() {
        ISmartSet<E> resultSet = new SmartSet<E>();
        Iterator<E> it = getInternalSet().iterator();
        E first = null;

        if (it.hasNext()) {
            first = it.next();
        }

        if (first != null && first instanceof Collection) {
            for (E elem : getInternalSet()) {
                @SuppressWarnings("unchecked")
                Collection<? extends E> innerList = (Collection<? extends E>) elem;
                resultSet.addAll(new SmartList<E>(innerList).flatten());
            }
        }

        return resultSet;
    }

    /** ISmartSet methods */

    @Override
    public boolean isSubsetOf(final Set<E> anotherSet) {
        ISmartSet<E> temp = new SmartSet<E>(this);
        temp.difference(anotherSet);
        return temp.isEmpty();
    }

    @Override
    public boolean isProperSubsetOf(final Set<E> anotherSet) {
        return isSubsetOf(anotherSet) && !equals(anotherSet);
    }

    @Override
    public boolean isSupersetOf(final Set<E> anotherSet) {
        ISmartSet<E> temp = new SmartSet<E>(anotherSet);
        return temp.isSubsetOf(this);
    }

    @Override
    public boolean isProperSupersetOf(final Set<E> anotherSet) {
        return isSupersetOf(anotherSet) && !equals(anotherSet);
    }

    @Override
    public ISmartSet<E> union(final Set<E> anotherSet) {
        getInternalSet().addAll(anotherSet);
        return this;
    }

    @Override
    public ISmartSet<E> intersection(final Set<E> anotherSet) {
        getInternalSet().retainAll(anotherSet);
        return this;
    }

    @Override
    public ISmartSet<E> difference(final Set<E> anotherSet) {
        getInternalSet().removeAll(anotherSet);
        return this;
    }
}
