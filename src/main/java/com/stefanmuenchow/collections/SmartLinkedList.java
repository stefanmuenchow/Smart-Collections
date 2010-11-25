package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class SmartLinkedList<E> extends SmartAbstractList<E> implements
        SmartQueue<E>, SmartList<E> {

    public SmartLinkedList() {
        this(new LinkedList<E>());
    }

    public SmartLinkedList(final Collection<E> collection) {
        super(new LinkedList<E>(collection));
    }

    /** Helper methods */

    private Queue<E> getInternalQueue() {
        return (Queue<E>) internalColl;
    }

    @Override
    protected SmartList<E> createNewInstance() {
        return new SmartLinkedList<E>();
    }

    @Override
    protected <T> SmartList<T> createNewInstance(final Collection<T> aColl) {
        return new SmartLinkedList<T>(aColl);
    }

    /** Queue methods */

    @Override
    public boolean offer(final E e) {
        return getInternalQueue().offer(e);
    }

    @Override
    public E remove() {
        return getInternalQueue().remove();
    }

    @Override
    public E poll() {
        return getInternalQueue().poll();
    }

    @Override
    public E element() {
        return getInternalQueue().element();
    }

    @Override
    public E peek() {
        return getInternalQueue().peek();
    }
}
