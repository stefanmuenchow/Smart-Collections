package com.stefanmuenchow.collections.function;

/**
 * Predicate used by smart map functions. Checks a condition based on the key
 * and val. For this purpose this interface is implemented by an anonymous class
 * that is passed to the map function.
 * 
 * @author Stefan Münchow
 */
public interface IMapPredicate<K, V> {

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
