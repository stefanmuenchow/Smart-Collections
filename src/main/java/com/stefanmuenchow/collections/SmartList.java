/**
 * Copyright (c) Stefan Muenchow. All rights reserved.
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 **/

package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

/**
 * SmartLists are compatible with the standard List interface, but add some
 * functionality to them. They are implemented as simple decorators (see Gang of
 * Four).
 *
 * @author Stefan Muenchow
 */
public interface SmartList<E> extends List<E>, SmartCollection<E> {

	SmartList<E> addReturn(int index, E element);
	SmartList<E> addAllReturn(int index, Collection<E> c);
	SmartList<E> removeIndexReturn(int index);
	SmartList<E> setReturn(int index, E elem);
	
    /**
     * Returns the first element in the List. Similar to
     *
     * <pre>
     * aList.get(0)
     * </pre>
     *
     * @throws NoSuchElementException If list is empty
     * @return First element
     */
    E head() throws NoSuchElementException;

    /**
     * Returns a new list without the first element.
     *
     * @throws UnsupportedOperationException If list is empty
     * @return Rest list
     */
    SmartList<E> tail() throws UnsupportedOperationException;

    /**
     * Returns the last element of the list. Similar to
     *
     * <pre>
     * aList.get(aList.size() - 1)
     * </pre>
     *
     * @throws NoSuchElementException If list is empty
     * @return Last element
     */
    E last() throws NoSuchElementException;

    /**
     * Gets the element at the specified index. If the index is not into the
     * range od valid indices, the default value is returned.
     *
     * @param index
     *            Index of element to get
     * @param defaultVal
     *            Default value
     * @return Element at index or defaultVal, if index is out of bounds.
     */
    E get(int index, E defaultVal);

    /**
     * Takes the first n elements of the collection and returns the result. A
     * new list is created. Similar to
     *
     * <pre>
     * aList.subList(0, n)
     * </pre>
     *
     * @param n
     *            Number of elements to take from beginning of list
     */
    SmartList<E> take(int n);

    /**
     * Removes the first n elements of the collection and returns the result. A
     * new list is created. Similar to
     *
     * <pre>
     * aList.subList(n, aList.size())
     * </pre>
     *
     * @param n
     *            Number of elements to remove from beginning of list
     */
    SmartList<E> drop(int n);

    /**
     * Takes elements from the List until the predicate evaluates to false the
     * first time. A new list is created.
     *
     * @param pred
     *            Predicate
     * @return Prefix list
     */
    SmartList<E> takeWhile(Predicate<? super E> pred);

    /**
     * Removes elements from the List until the predicate evaluates to false the
     * first time. A new list is created.
     *
     * @param pred
     *            Predicate
     * @return Postfix list
     */
    SmartList<E> dropWhile(Predicate<? super E> pred);

    /**
     * Removes all duplicate values from the List. Changes the original list.
     */
    SmartList<E> removeDuplicates();

    /**
     * Inserts the specified elem between each two elements of the list. Changes
     * the original list.
     *
     * @param elem
     *            Element to insert
     * @return List with elem at each 2nd index
     */
    SmartList<E> intersperse(E elem);

    /**
     * Creates a SmartMap from two lists. Elements in the first list are the
     * keys, elements with corresponding indices in the second list the values.
     * If one of the lists has less elements than the other one, the elements
     * are discarded.
     *
     * @param anotherList
     *            List to zip with
     * @return Map
     */
    <T> SmartMap<E, T> zipWith(List<T> anotherList);

    /**
     * Returns a list of the indices. This is the same as the range from 0 to
     *
     * <pre>
     * list.size() - 1
     * </pre>
     *
     * . The original list remains unchanged.
     *
     * @return List of indices
     */
    SmartList<Integer> getIndicesList();

    /**
     * Creates a Map with the elements of the list as keys and the number of
     * their occurences in the list as values.
     *
     * @return Map<E, Integer>
     */
    SmartMap<E, Integer> getOccurenceCountMap();

    /**
     * Reverses the ordering of the elements in the list.
     *
     * @return Reversed list
     */
    SmartList<E> reverse();

    /**
     * Returns the list size without all null elements. If there are no null
     * elements in list, it is equal to list.size().
     *
     * @return List size without null elements
     */
    int sizeWithoutNulls();

    @Override
    <R> SmartList<R> map(UnaryFunction<R, ? super E> function);

    @Override
    <T> SmartList<T> castEach(Class<T> clazz);
    
    @Override
    List<E> toStandardCollection();
}
