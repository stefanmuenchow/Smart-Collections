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

import com.stefanmuenchow.collections.exception.PreconditionViolatedException;
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

    /**
     * Merges this map with anotherMap using the given merging function. The
     * original map is changed.
     *
     * @param anotherMap
     *            Map to merge with
     * @param mergeFunct
     *            Merging function
     * @see BinaryFunction
     * @return Merged map
     */
    SmartMap<K, V> mergeWith(SmartMap<K, V> anotherMap,
            BinaryFunction<V, V, V> mergeFunct);

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
     * predicate evaluates to false for all elements in the map, null is
     * returned.
     *
     * @param predicate
     *            Predicate
     * @return Value satisfying predicate or null
     */
    V find(MapPredicate<K, V> predicate);

    /**
     * Retains all entries in map for which the predicate evaluates to true.
     *
     * @param predicate
     *            Predicate
     * @return Map containing only elements for which predicate returns true
     */
    SmartMap<K, V> filter(MapPredicate<K, V> predicate);

    /**
     * Removes all entries in map for which the predicate evaluates to true.
     *
     * @param predicate
     *            Predicate
     * @return Map containing only elements for which predicate returns false
     */
    SmartMap<K, V> remove(MapPredicate<K, V> predicate);

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
    SmartMap<K, V> replace(K seekKey, V seekValue, K newKey, V newValue);

    /**
     * Same as replace(K seekKey, V seekValue, K newKey, V newValue) but using a
     * predicate to find the entry to replace.
     *
     * @param predicate
     *            Predicate
     * @param newKey
     *            Replacement key
     * @param newValue
     *            Replacement value
     * @return Map with entries replaced
     */
    SmartMap<K, V> replace(MapPredicate<K, V> predicate, K newKey, V newValue);

    /**
     * Applies the function to all entries of map replacing each original entry
     * with the result of function. Return the resulting map.
     *
     * @param function
     *            Function
     * @return Changed map
     */
    <S, R> SmartMap<S, R> map(
            UnaryFunction<Map.Entry<S, R>, Map.Entry<K, V>> function);

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
    <R> R reduce(R initial, BinaryFunction<R, R, Map.Entry<K, V>> funct);

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
    SmartMap<V, K> swap() throws PreconditionViolatedException;
}
