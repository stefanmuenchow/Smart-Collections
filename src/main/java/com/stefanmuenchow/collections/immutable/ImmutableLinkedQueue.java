package com.stefanmuenchow.collections.immutable;

import java.util.Arrays;
import java.util.Collection;

import com.stefanmuenchow.collections.SmartLinkedQueue;
import com.stefanmuenchow.collections.SmartQueue;
import com.stefanmuenchow.collections.function.UnaryFunction;

public class ImmutableLinkedQueue<E> extends ImmutableAbstractCollection<E> implements ImmutableQueue<E> {

	public ImmutableLinkedQueue(SmartQueue<E> collection) {
		super(collection);
	}
	
    public ImmutableLinkedQueue() {
        this(new SmartLinkedQueue<E>());
    }

    public ImmutableLinkedQueue(final E... elems) {
        this(Arrays.asList(elems));
    }

    public ImmutableLinkedQueue(final Collection<E> collection) {
        this(new SmartLinkedQueue<E>(collection));
    }

	/** Helper methods */
    
    protected SmartQueue<E> getInternalQueue() {
    	return (SmartQueue<E>) internalColl;
    }
	
	@Override
	protected ImmutableQueue<E> createNewInstance() {
		return new ImmutableLinkedQueue<E>();
	}

	@Override
	protected <T> ImmutableQueue<T> createNewInstance(Collection<T> aColl) {
		return new ImmutableLinkedQueue<T>(aColl);
	}
	
	/** ImmutableQueue methods */
	
	@Override
	public ImmutableQueue<E> offer(E e) {
		SmartQueue<E> temp = new SmartLinkedQueue<E>(getInternalQueue());
		temp.offer(e);
		return createNewInstance(temp);
	}

	@Override
	public ImmutableQueue<E> remove() {
		SmartQueue<E> temp = new SmartLinkedQueue<E>(getInternalQueue());
		temp.remove();
		return createNewInstance(temp);
	}

	@Override
	public E element() {
		return getInternalQueue().element();
	}

	@Override
	public E peek() {
		return getInternalQueue().peek();
	}
	
	@Override
	public <R> ImmutableQueue<R> map(UnaryFunction<R, E> function) {
		return (ImmutableQueue<R>) super.map(function);
	}
	
	@Override
	public ImmutableQueue<Object> flatten() {
		return (ImmutableQueue<Object>) super.flatten();
	}
	
	@Override
	public <T> ImmutableQueue<T> castAllElements(Class<T> clazz) {
		return (ImmutableQueue<T>) super.castAllElements(clazz);
	}
}
