package com.stefanmuenchow.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import com.stefanmuenchow.collections.function.UnaryFunction;

public class SmartLinkedQueue<E> extends SmartAbstractCollection<E> implements SmartQueue<E> {

    public SmartLinkedQueue() {
        this(new LinkedList<E>());
    }

    public SmartLinkedQueue(final E... elems) {
        this(Arrays.asList(elems));
    }

    public SmartLinkedQueue(final Collection<E> collection) {
        super(new LinkedList<E>(collection));
    }

    /** Overwritten methods */
    
    @Override
    public <R> SmartQueue<R> map(final UnaryFunction<R, E> function) {
        SmartCollection<R> result = super.map(function);
        return (SmartQueue<R>) result;
    }

    @Override
    public SmartQueue<Object> flatten() {
        SmartCollection<Object> result = super.flatten();
        return (SmartQueue<Object>) result;
    }

    @Override
    public <T> SmartQueue<T> castAllElements(final Class<T> clazz) {
        SmartCollection<T> result = super.castAllElements(clazz);
        return (SmartQueue<T>) result;
    }

    /** Helper methods */

    private Queue<E> getInternalQueue() {
        return (Queue<E>) internalColl;
    }

    @Override
    protected SmartLinkedQueue<E> createNewInstance() {
        return new SmartLinkedQueue<E>();
    }

    @Override
    protected <T> SmartLinkedQueue<T> createNewInstance(final Collection<T> aColl) {
        return new SmartLinkedQueue<T>(aColl);
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
