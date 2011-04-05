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
import java.util.NoSuchElementException;

import com.stefanmuenchow.functors.BinaryFunction;
import com.stefanmuenchow.functors.Predicate;
import com.stefanmuenchow.functors.UnaryFunction;
import com.stefanmuenchow.functors.VoidFunction;

/**
 * The root interface of the smart collection hierarchy. Extends the java
 * standard collection interface and provides additional methods that are 
 * implemented by all special smart collection types.
 * 
 * Smart collections are mutable, meaning that most operations modify the
 * collection they are operating on. See the documentation of the actual
 * methods to get further information.
 * 
 * @see Collection
 * @see SmartList
 * @see SmartQueue
 * @see SmartSet
 * @see SmartSortedSet
 *
 * @author Stefan Münchow
 */
public interface SmartCollection<E> extends Collection<E> {
    
    /**
     * Adds an element and returns the collection. Modifies the original 
     * collection.
     * 
     * @param elem            Element to be added
     * @return                Collection with element added
     */
    SmartCollection<E> addReturn(E elem);
    
    /**
     * Adds all elements of coll and returns the collection. Modifies the 
     * original collection.
     * 
     * @param coll            Collections of elements to be added
     * @return                Collection with elements added
     */
    SmartCollection<E> addAllReturn(Collection<E> coll);
    
    /**
     * Removes an element and returns the collection. Modifies the original 
     * collection.
     * 
     * @param elem            Element to be removed
     * @return                Collection with element removed
     */
    SmartCollection<E> removeReturn(E elem);
    
    /**
     * Removes all elements of coll and returns the collection. Modifies the 
     * original collection.
     * 
     * @param coll            Collections of elements to be removed
     * @return                Collection with elements removed
     */
    SmartCollection<E> removeAllReturn(Collection<E> coll);
    
    /**
     * Removes all elements from the collection that are not in coll. Modifies 
     * the original collection.
     * 
     * @param coll            Collections of elements to be retained
     * @return                Collection containing only elements contained in 
     *                        coll
     */
    SmartCollection<E> retainAllReturn(Collection<E> coll);

    /**
     * Seeks a single element based on a predicate. The first element for which
     * the predicate returns <code>true</code>, is returned. If the predicate is
     * <code>false</code> for all elements, an Exception is thrown.
     *
     * @param pred                      Predicate to find element
     * @throws NoSuchElementException   If no element matches
     * @return                          First element matching predicate
     */
    E find(Predicate<? super E> pred) throws NoSuchElementException;

    /**
     * Retains all elements in the collection for which the predicate evaluates
     * to <code>true</code>. Modifies the original collection.
     *
     * @param predicate        	Predicate to identify elements
     * @return                 	Filtered collection
     */
    SmartCollection<E> filter(Predicate<? super E> predicate);

    /**
     * Removes all elements in the collection for which the predicate evaluates
     * to <code>true</code>. Modifies the original collection.
     * 
     * @param predicate    		Predicate to identify elements
     * @return                	Filtered collection
     */
    SmartCollection<E> remove(Predicate<? super E> predicate);

    /**
     * Replaces each occurence of an element in the collection with a 
     * replacement. The elements are compared by the {@link #equals(Object)} 
     * method. If the element is not found in the collection, nothing is done.
     * Modifies the original collection.
     *
     * @param seek             	Element to be replaced
     * @param replacement    	Replacement element
     * @return                	Collection with elements replaced
     */
    SmartCollection<E> replace(E seek, E replacement);

    /**
     * Replaces each element in the collection, for which the predicate 
     * evaluates to <code>true</code>, with the replacement. If the predicate 
     * evaluates to <code>false</code> for all elements, nothing is done.
     * Modifies the original collection.
     *
     * @param predicate     	Predicate to identify elements
     * @param replacement    	Replacement element
     * @return                	Collection with elements replaced
     */
    SmartCollection<E> replace(Predicate<? super E> predicate, E replacement);

    /**
     * Replaces each occurence of a key that is contained in replacements
     * with the corresponding value. Modifies the original collection.
     * 
     * @param replacements     	Map including all replacements
     * @return                 	Collection with elements replaced
     */
    SmartCollection<E> replace(Map<E, E> replacements);
    
    /**
     * Calls the function for each element of the collection and creates a new
     * collection with the return values. The original collection remains
     * unmodified.
     *
     * @see UnaryFunction
     *
     * @param function        	Unary function applied to each element
     * @return                 	New collection containing return values
     */
    <R> SmartCollection<R> map(UnaryFunction<R, ? super E> function);

    /**
     * Combines the elements of this list from left to right using a binary
     * function and an initial value. If the collection is empty, the initial
     * value is returned. The original collection remains unmodified.
     * 
     * @see BinaryFunction
     *
     * @param initial        	Initial value
     * @param funct            	Binary Function to combine two values a time
     * @return                 	Resulting value
     */
    <R> R reduce(R initial, BinaryFunction<R, ? super E> funct);

    /**
     * Combines the elements of this list from left to right using a binary
     * function. If the collection is empty, null is returned. The original 
     * collection remains unmodified.
     *
     * @see BinaryFunction
     *
     * @param funct            	Binary Function to combine two values a time
     * @return                 	Resulting value
     */
    E reduce(BinaryFunction<? super E, ? super E> funct);

    /**
     * Calls the toString-method of each element in the collection and inserts
     * delimiter between each pair of elements. Returns the resulting String.
     *
     * @param delimiter        	String to insert between each two elements
     * @return                 	Resulting string representation
     */
    String join(String delimiter);

    /**
     * Counts the number of entries for which the predicate evaluates to 
     * <code>true</code>.
     *
     * @param predicate        	Predicate to identify elements
     * @return                 	Number of elements for which predicate was 
     *                         	<code>true</code>
     */
    int count(Predicate<? super E> predicate);

    /**
     * Checks if the predicate evaluates to <code>true</code> for any element 
     * of the collection. If yes, the result is <code>true</code>, otherwise
     * <code>false</code>.
     *
     * @param pred            	Predicate to identify elements
     * @return                 	True if element exists, otherwise false
     */
    boolean exists(Predicate<? super E> pred);

    /**
     * Checks if the predicate evaluates to <code>true</code> for all elements 
     * of the collection. If yes, the result is <code>true</code>, otherwise
     * <code>false</code>.
     *
     * @param pred            	Predicate to identify elements
     * @return                 	True if element exists, otherwise false
     */
    boolean forall(Predicate<? super E> pred);
    
    /**
     * Applies a function to each element of the collection discarding the
     * result value. Can be used for causing side-effects. 
     * 
     * @param function			Function to be applied
     */
    void foreach(VoidFunction<? super E> function);

    /**
     * Casts all elements of the collecion to the specified type. If that
     * is not possible, an Exception is thrown. A new collection is created,
     * the original one remains unmodified.
     * 
     * @throws ClassCastException	If cast is not possible
     * 
     * @param clazz                 Class to cast all elements to
     * @return                      New collection containing elements of type 
     *                              clazz
     */
    <T> SmartCollection<T> castEach(Class<T> clazz);

    /**
     * Creates an array of the same type from this collection.
     * 
     * @param clazz                 Type of the array elements
     * @return                      Array containg all elements
     */
    <T> T[] toArray(Class<T> clazz);
    
    /**
     * Returns the corresponding java standard collection.
     * As smart collections are simple decorators around the standard 
     * collections, just the internal collection is returned and no new
     * one has to be created. 
     * 
     * Changes to the returned collection will also affect this one.
     * 
     * @return                Standard collection wrapped by this decorator
     */
    Collection<E> toStandardCollection();
}
