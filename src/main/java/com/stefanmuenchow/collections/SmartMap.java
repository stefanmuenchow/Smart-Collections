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

import java.util.Map;
import java.util.NoSuchElementException;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.MapPredicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

/**
 * Smart Maps are compatible with the standard Map interface, but add some
 * functionality to them. They are implemented as simple decorators (see Gang of
 * Four).
 *
 * @author Stefan Muenchow
 */
public interface SmartMap<K, V> extends Map<K, V> {

	SmartMap<K, V> putReturn(K key, V value);
	SmartMap<K, V> putAllReturn(Map<K, V> t);
	SmartMap<K, V> removeReturn(K key);
	
    /**
     * Returns the first element of the Map. If it is not a sorted map, the ordering
     * is arbitrary.
     * @throws NoSuchElementException
     * @return Map.Entry
     */
    Map.Entry<K, V> head() throws NoSuchElementException;

    /**
     * Returns the map without the first element. If it is not a sorted map, the ordering
     * is arbitrary.
     * @throws UnsupportedOperationException
     * @return Map without first element
     */
    SmartMap<K, V> tail() throws UnsupportedOperationException;

    /**
     * Merges this map with anotherMap using the given merging function. A new map is
     * returned.
     *
     * @param anotherMap
     *            Map to merge with
     * @param mergeFunct
     *            Merging function
     * @see BinaryFunction
     * @return Merged map
     */
    void mergeWith(SmartMap<K, V> anotherMap, BinaryFunction<V, V> mergeFunct);

    /**
     * Gets the value to the given key if it exists. Else returns defaultVal.
     *
     * @param key
     *            Key to get value to
     * @param defaultVal
     *            Default value
     * @return Value to given key or defaultVal
     */
    V get(K key, V defaultVal);

    /**
     * Returns the first value for which the predicate evaluates to true. If the
     * predicate evaluates to false for all elements in the map, an exception is
     * thrown.
     *
     * @param predicate                 Predicate to test entries against
     * @throws NoSuchElementException   If no element matches
     * @return                          Value satisfying predicate
     */
    V find(MapPredicate<K, V> predicate) throws NoSuchElementException;

    /**
     * Retains all entries in map for which the predicate evaluates to true.
     *
     * @param predicate
     *            Predicate
     * @return Map containing only elements for which predicate returns true
     */
    void filter(MapPredicate<K, V> predicate);

    /**
     * Removes all entries in map for which the predicate evaluates to true.
     *
     * @param predicate
     *            Predicate
     * @return Map containing only elements for which predicate returns false
     */
    void remove(MapPredicate<K, V> predicate);

    /**
     * Seeks the map for a given key-value-pair and replaces it with the
     * specified pair. The equals() method is used to compare items. If the pair
     * is not found, nothing is changed.
     *
     * @param seekKey
     *            Key to replace
     * @param seekValue
     *            Value to replace
     * @param newKey
     *            Replacement key
     * @param newValue
     *            Replacement value
     * @return Map with entries replaced
     */
    void replace(K seekKey, V seekValue, K newKey, V newValue);

    /**
     * Applies the function to all entries of map replacing each original entry
     * with the result of function. Return the resulting map.
     *
     * @param function
     *            Function
     * @return Changed map
     */
    <S, R> SmartMap<S, R> map(UnaryFunction<KeyValuePair<S, R>, Map.Entry<K, V>> function);

    /**
     * Combines the elements of this map using a binary function and an initial
     * value.
     *
     * @param initial
     *            Initial value
     * @param funct
     *            Binary Function
     * @see BinaryFunction
     * @return A single value
     */
    <R> R reduce(R initial, BinaryFunction<R, Map.Entry<K, V>> funct);

    /**
     * Calls the toString() method for the key and value of each entry in the
     * map and inserts keyValDelimiter between them. Then it intersperses the
     * resulting strings with entryDelimiter and returns the result.
     *
     * @param entryDelimiter
     *            String to insert between each two entries
     * @param keyValDelimiter
     *            String to insert between key and value of each map entry
     * @return Resulting string representation
     */
    String join(String entryDelimiter, String keyValDelimiter);

    /**
     * Counts all entries for which the predicate evaluates to true.
     *
     * @param predicate
     *            Predicate
     * @return Number of elements in map for which predicate is true
     */
    int count(MapPredicate<K, V> predicate);

    /**
     * Checks if the predicate evaluates to true for any element in the map. If
     * not, the result is false.
     *
     * @param predicate
     *            Predicate
     * @return true / false
     */
    boolean exists(MapPredicate<K, V> predicate);

    /**
     * Checks if the predicate evaluates to true for all elements in the map. If
     * not, the result is false.
     *
     * @param predicate
     *            Predicate
     * @return true / false
     */
    boolean forall(MapPredicate<K, V> predicate);

    /**
     * Checks if this map describes a bijective mapping. This is the case when
     * each value in the map is unique.
     *
     * @return true / false
     */
    boolean isBijective();

    /**
     * Swaps keys and values. If this map is bijective (can be checked with
     * isBijective() method) operation will be successful and return the new
     * map, otherwise a BadOperationException will be thrown.
     *
     * @return
     */
    SmartMap<V, K> swap() throws UnsupportedOperationException;
}
