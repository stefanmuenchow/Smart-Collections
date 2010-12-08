/**
* Copyright (c) Stefan Muenchow. All rights reserved.
* The use and distribution terms for this software are covered by the
* Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
* which can be found in the file epl-v10.html at the root of this distribution.
* By using this software in any fashion, you are agreeing to be bound by
* the terms of this license.
* You must not remove this notice, or any other, from this software.
**/

package com.stefanmuenchow.collections.function;

/**
 * Predicate used by smart map functions. Checks a condition based on the key
 * and val. For this purpose this interface is implemented by an anonymous class
 * that is passed to the map function.
 *
 * @author Stefan Muenchow
 */
public interface MapPredicate<K, V> {

    /**
     * Checks a predicate based on the key and value of a map entry.
     *
     * @param key
     *            Entry key
     * @param val
     *            Entry value
     * @return true / false
     */
    boolean check(K key, V val);
}
