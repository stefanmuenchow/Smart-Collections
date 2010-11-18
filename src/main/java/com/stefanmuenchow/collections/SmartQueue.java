package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import com.stefanmuenchow.collections.function.IUnaryFunction;

public class SmartQueue<E> extends AbstractSmartCollection<E> implements
        ISmartQueue<E> {

    public SmartQueue() {
        super(new LinkedList<E>());
    }

    public SmartQueue(final Collection<E> collection) {
        super(new LinkedList<E>(collection));
    }

    /** Helper methods */

    private Queue<E> getInternalQueue() {
        return (Queue<E>) internalColl;
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

    /** ISmartCollection methods */

    @Override
    public <R> ISmartCollection<R> map(final IUnaryFunction<R, E> function) {
        ISmartQueue<R> resultQueue = new SmartQueue<R>();
        for (E elem : getInternalQueue()) {
            resultQueue.add(function.execute(elem));
        }

        return resultQueue;
    }

    @Override
    public ISmartCollection<E> flatten() {
        ISmartQueue<E> resultQueue = new SmartQueue<E>();

        if (!isEmpty() && element() instanceof Collection) {
            for (E elem : getInternalQueue()) {
                @SuppressWarnings("unchecked")
                Collection<? extends E> innerList = (Collection<? extends E>) elem;
                resultQueue.addAll(new SmartList<E>(innerList).flatten());
            }
        }

        return resultQueue;
    }
}
