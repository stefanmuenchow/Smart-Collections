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
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@link SmartMap} interface decorating a
 * standard {@link HashMap}.
 * 
 * @see HashMap
 * 
 * @author Stefan Münchow
 */
public class SmartHashMap<K, V> extends AbstractSmartMap<K, V> implements
        SmartMap<K, V> {

	/**
	 * Creates a new instance containig all entries of the specified map.
	 * 
	 * @param map	Entries to be contained
	 */
    public SmartHashMap(final Map<K, V> map) {
        super(new HashMap<K, V>(map));
    }
    
    /**
     * Creates a new empty map.
     */
    public SmartHashMap() {
        this(new HashMap<K, V>());
    }
    
    /**
     * Creates a new map from a list of tuples. Each tuple is converted to a map
     * entry by using the first value as entry key and the second value as entry 
     * value.
     * 
     * @param tupleColl		Collection of tuples
     */
    public SmartHashMap(Collection<Tuple<K, V>> tupleColl) {
    	this(new HashMap<K, V>());
    	
    	for (Tuple<K, V> tuple : tupleColl) {
    		put(tuple.getFirst(), tuple.getSecond());
    	}
    }
    
    /**
     * Creates a new map from two lists, using each element of the first list 
     * as a key and the element at the corresponding position in the second 
     * list as its value.
     * 
     * @param keys		List containing keys
     * @param vals		List containing values
     */
    public SmartHashMap(SmartList<K> keys, SmartList<V> vals) {
    	this(keys.zipWith(vals));
    }

    @Override
    protected SmartMap<K, V> createNewInstance() {
        return new SmartHashMap<K, V>();
    }

    @Override
    protected <S, R> SmartMap<S, R> createNewInstance(final Map<S, R> aMap) {
        return new SmartHashMap<S, R>(aMap);
    }
}
