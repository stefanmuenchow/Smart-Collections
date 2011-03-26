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

public class Tuple<K, V> {
    private final K first;
    private final V second;

    public Tuple(final K key, final V value) {
        this.first = key;
        this.second = value;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof Tuple) {
    		@SuppressWarnings("rawtypes")
			Tuple tuple = (Tuple) obj;
    		return getFirst().equals(tuple.getFirst())
    				&& getSecond().equals(tuple.getSecond());
    		
    	}
    	
    	return false;
    }
    
    @Override
    public int hashCode() {
    	return getFirst().hashCode() ^ getSecond().hashCode();
    }
    
    @Override
    public String toString() {
    	return "(" + getFirst() + "," + getSecond() + ")";
    }
}
