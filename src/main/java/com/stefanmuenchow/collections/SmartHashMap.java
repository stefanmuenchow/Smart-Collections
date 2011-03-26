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
import java.util.HashMap;
import java.util.Map;

public class SmartHashMap<K, V> extends AbstractSmartMap<K, V> implements
        SmartMap<K, V> {

    public SmartHashMap(final Map<K, V> map) {
        super(new HashMap<K, V>(map));
    }
    
    public SmartHashMap() {
        this(new HashMap<K, V>());
    }
    
    public SmartHashMap(Collection<Tuple<K, V>> tupleColl) {
    	this(new HashMap<K, V>());
    	
    	for (Tuple<K, V> tuple : tupleColl) {
    		put(tuple.getFirst(), tuple.getSecond());
    	}
    }
    
    public SmartHashMap(SmartList<K> keys, SmartList<V> vals) {
    	this(keys.zipWith(vals));
    }

    /** Helper methods */

    @Override
    protected SmartMap<K, V> createNewInstance() {
        return new SmartHashMap<K, V>();
    }

    @Override
    protected <S, R> SmartMap<S, R> createNewInstance(final Map<S, R> aMap) {
        return new SmartHashMap<S, R>(aMap);
    }
}
