package com.stefanmuenchow.collections;

import java.util.ArrayList;
import java.util.Collection;

public class SmartArrayList<E> extends SmartAbstractList<E> implements
        SmartList<E> {

    public SmartArrayList() {
        this(new ArrayList<E>());
    }

    protected SmartArrayList(final Collection<E> internalColl) {
        super(new ArrayList<E>(internalColl));
    }

    /** Helper methods */

    @Override
    protected SmartList<E> createNewInstance() {
        return new SmartArrayList<E>();
    }

    @Override
    protected <T> SmartList<T> createNewInstance(final Collection<T> aColl) {
        return new SmartArrayList<T>(aColl);
    }
}
