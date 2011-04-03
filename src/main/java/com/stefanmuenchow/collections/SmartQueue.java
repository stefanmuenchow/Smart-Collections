/**
 * Copyright (c) Stefan Münchow. All rights reserved.
 * 
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 **/

package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Queue;

import com.stefanmuenchow.functors.Predicate;
import com.stefanmuenchow.functors.UnaryFunction;

/**
 * A SmartQueue is an ordered collection that can hold duplicate elements.
 * In contrast to lists, there are some restrictions on the elements'
 * accessability. Elements are ordered in an FIFO (First-In-First-Out) manner.
 * This means that elements can be retrieved only from the head of the queue
 * and inserted only at the end.
 * 
 * @see Queue
 * @see SmartLinkedQueue
 *
 * @author Stefan Münchow
 */
public interface SmartQueue<E> extends Queue<E>, SmartCollection<E> {

    /**
     * Inserts the specified element at the end of this queue. Changes the
     * original queue.
     * 
     * @param o        Element to insert
     * @return         Queue with element added.
     */
    SmartQueue<E> offerReturn(E o);
    
    /** Overridden methods from SmartCollection */
    
    /**
     * @see SmartCollection#addReturn(Object)
     */
    SmartQueue<E> addReturn(E elem);

    /**
     * @see SmartCollection#addAll(Collection)
     */
    SmartQueue<E> addAllReturn(Collection<E> coll);
    
    /**
     * @see SmartCollection#removeReturn(Object)
     */
    SmartQueue<E> removeReturn(E elem);
    
    /**
     * @see SmartCollection#removeAllReturn(Collection)
     */
    SmartQueue<E> removeAllReturn(Collection<E> coll);
    
    /**
     * @see SmartCollection#retainAllReturn(Collection)
     */
    SmartQueue<E> retainAllReturn(Collection<E> coll);
    
    /**
     * @see SmartCollection#filter(Predicate)
     */
    SmartQueue<E> filter(Predicate<? super E> predicate);
    
    /**
     * @see SmartCollection#remove(Predicate)
     */
    SmartQueue<E> remove(Predicate<? super E> predicate);
    
    /**
     * @see SmartCollection#replace(Object, Object)
     */
    SmartQueue<E> replace(E seek, E replacement);
    
    /**
     * @see SmartCollection#replace(Predicate, Object)
     */
    SmartQueue<E> replace(Predicate<? super E> predicate, E replacement);
    
    /**
     * @see SmartCollection#replace(Map)
     */
    SmartQueue<E> replace(Map<E, E> replacements);
    
    /**
     * @see SmartCollection#map(UnaryFunction)
     */
    @Override
    <R> SmartQueue<R> map(UnaryFunction<R, ? super E> function);

    /**
     * @see SmartCollection#castEach(Class)
     */
    @Override
    <T> SmartQueue<T> castEach(Class<T> clazz);
    
    /**
     * @see SmartCollection#toStandardCollection()
     */
    @Override
    Queue<E> toStandardCollection();
}
