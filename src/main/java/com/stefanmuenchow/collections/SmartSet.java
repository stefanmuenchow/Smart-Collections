/**
 * Copyright (c) Stefan Muenchow. All rights reserved.
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
import java.util.Set;

import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

/**
 * A SmartSet is a collection that contains no duplicate elements. It models
 * the notion of a mathematical set.
 * 
 * @see Set
 * @see SmartHashSet
 * @see SmartTreeSet
 * @see SmartSortedSet
 *
 * @author Stefan Muenchow
 */
public interface SmartSet<E> extends Set<E>, SmartCollection<E> {

    /**
     * Checks if this set is a subset of anotherSet. This is the case if all
     * elements of this set are contained in anotherSet (also if the sets are
     * equal).
     *
     * @param anotherSet 		Supposed superset
     * @return 					True if this set is a subset of anotherSet, 
     * 							otherwise false.
     */
    boolean isSubsetOf(Set<E> anotherSet);

    /**
     * Checks if this set is a proper subset of anotherSet. This is the case if
     * all elements of this set are contained in anotherSet and the sets are 
     * not equal.
     *
     * @param anotherSet		Supposed proper superset
     * @return					True if this set is proper subset of anotherSet,
     * 							otherwise false
     */
    boolean isProperSubsetOf(Set<E> anotherSet);

    /**
     * Checks if this set is a superset of anotherSet. This is the case if all
     * elements of anotherSet are contained in this set (also if the sets are
     * equal).
     *
     * @param anotherSet 		Supposed subset
     * @return 					True if this set is a superset of anotherSet, 
     * 							otherwise false.
     */
    boolean isSupersetOf(Set<E> anotherSet);

    /**
     * Checks if this set is a proper superset of anotherSet. This is the case if
     * all elements of anotherSet are contained in this set and the sets are 
     * not equal.
     *
     * @param anotherSet		Supposed proper subset
     * @return					True if this set is proper superset of anotherSet,
     * 							otherwise false
     */
    boolean isProperSupersetOf(Set<E> anotherSet);

    /**
     * Creates the union of the two sets and returns the result. Changes the
     * original set.
     *
     * @param anotherSet		Set to union this set with
     * @return 					Union of both sets
     */
    SmartSet<E> union(Set<E> anotherSet);

    /**
     * Creates the intersection of the two sets and returns the result. Changes
     * the original set.
     *
     * @param anotherSet		Set to intersect this set with
     * @return 					Intersection of both sets
     */
    SmartSet<E> intersection(Set<E> anotherSet);

    /**
     * Creates the difference of the two sets and returns the result. Changes 
     * the original set.
     *
     * @param anotherSet		Set to create difference with
     * @return 					Difference of both sets
     */
    SmartSet<E> difference(Set<E> anotherSet);

	/** Overridden methods from SmartCollection */
    
    /**
     * @see SmartCollection#addReturn(Object)
     */
    SmartSet<E> addReturn(E elem);

    /**
     * @see SmartCollection#addAll(Collection)
     */
    SmartSet<E> addAllReturn(Collection<E> coll);
    
    /**
     * @see SmartCollection#removeReturn(Object)
     */
    SmartSet<E> removeReturn(E elem);
    
    /**
     * @see SmartCollection#removeAllReturn(Collection)
     */
    SmartSet<E> removeAllReturn(Collection<E> coll);
    
    /**
     * @see SmartCollection#retainAllReturn(Collection)
     */
    SmartSet<E> retainAllReturn(Collection<E> coll);
    
    /**
     * @see SmartCollection#filter(Predicate)
     */
    SmartSet<E> filter(Predicate<? super E> predicate);
    
    /**
     * @see SmartCollection#remove(Predicate)
     */
    SmartSet<E> remove(Predicate<? super E> predicate);
    
    /**
     * @see SmartCollection#replace(Object, Object)
     */
    SmartSet<E> replace(E seek, E replacement);
    
    /**
     * @see SmartCollection#replace(Predicate, Object)
     */
    SmartSet<E> replace(Predicate<? super E> predicate, E replacement);
    
    /**
     * @see SmartCollection#replace(Map)
     */
    SmartSet<E> replace(Map<E, E> replacements);
    
    /**
     * @see SmartCollection#map(UnaryFunction)
     */
    @Override
    <R> SmartSet<R> map(UnaryFunction<R, ? super E> function);

    /**
     * @see SmartCollection#castEach(Class)
     */
    @Override
    <T> SmartSet<T> castEach(Class<T> clazz);
    
    /**
     * @see SmartCollection#toStandardCollection()
     */
    @Override
    Set<E> toStandardCollection();
}
