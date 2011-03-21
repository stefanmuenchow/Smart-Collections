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

public class Tuple<K, V> {
    private final K key;
    private final V value;

    public Tuple(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof Tuple) {
    		@SuppressWarnings("rawtypes")
			Tuple tuple = (Tuple) obj;
    		return getKey().equals(tuple.getKey())
    				&& getValue().equals(tuple.getValue());
    		
    	}
    	
    	return false;
    }
    
    @Override
    public int hashCode() {
    	return getKey().hashCode() ^ getValue().hashCode();
    }
    
    @Override
    public String toString() {
    	return "(" + getKey() + "," + getValue() + ")";
    }
}
