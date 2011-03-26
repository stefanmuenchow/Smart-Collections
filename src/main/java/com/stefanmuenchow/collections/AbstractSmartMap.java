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
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.MapBinaryFunction;
import com.stefanmuenchow.collections.function.MapPredicate;
import com.stefanmuenchow.collections.function.MapUnaryFunction;

public abstract class AbstractSmartMap<K, V> implements SmartMap<K, V> {
    protected final Map<K, V> internalMap;

    public AbstractSmartMap(final Map<K, V> map) {
        internalMap = map;
    }

    /** Helper methods */

    protected abstract SmartMap<K, V> createNewInstance();
    protected abstract <S, R> SmartMap<S, R> createNewInstance(final Map<S, R> aMap);

    /** Map Methods */
    
	@Override
	public SmartMap<K, V> putReturn(K key, V value) {
		put(key, value);
		return this;
	}

	@Override
	public SmartMap<K, V> putAllReturn(Map<K, V> t) {
		putAll(t);
		return this;
	}

	@Override
	public SmartMap<K, V> removeReturn(K key) {
		remove(key);
		return this;
	}

    @Override
    public int size() {
        return internalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return internalMap.containsValue(value);
    }

    @Override
    public V get(final Object key) {
        return internalMap.get(key);
    }

    @Override
    public V put(final K key, final V value) {
        return internalMap.put(key, value);
    }

    @Override
    public V remove(final Object key) {
        return internalMap.remove(key);
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> m) {
        internalMap.putAll(m);
    }

    @Override
    public void clear() {
        internalMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return internalMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return internalMap.values();
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return internalMap.entrySet();
    }

    /** ISmartMap methods */

    @Override
    public java.util.Map.Entry<K, V> head() {
        if (size() > 0) {
            return new SmartArrayList<Map.Entry<K, V>>(internalMap.entrySet()).head();
        }

        throw new NoSuchElementException("Map is empty. No head element available.");
    }

    @Override
    public SmartMap<K, V> tail() {
        if (size() > 0) {
            SmartMap<K, V> resultMap = createNewInstance(internalMap);
            resultMap.remove(new SmartArrayList<K>(resultMap.keySet()).head());
            return resultMap;
        }

        throw new UnsupportedOperationException("Map is empty. No tail map available.");
    }

    @Override
    public SmartMap<K, V> mergeWith(final SmartMap<K, V> anotherMap, final BinaryFunction<V, V> mergeFunct) {
        SmartMap<K, V> resultMap = createNewInstance(internalMap);
        clear();

        for (Map.Entry<K, V> entry : anotherMap.entrySet()) {
            if (resultMap.containsKey(entry.getKey())) {
                V mergedVal = mergeFunct.apply(resultMap.get(entry.getKey()),
                        entry.getValue());
                internalMap.put(entry.getKey(), mergedVal);
            } else {
                internalMap.put(entry.getKey(), entry.getValue());
            }
        }
        
        return this;
    }

    @Override
    public V get(final K key, final V defaultVal) {
        V value = internalMap.get(key);
        return value != null ? value : defaultVal;
    }

    @Override
    public V find(final MapPredicate<? super K, ? super V> predicate) {
        for (Map.Entry<K, V> entry : entrySet()) {
            if (predicate.test(entry.getKey(), entry.getValue())) {
                return entry.getValue();
            }
        }

        throw new NoSuchElementException("No element matches the given predicate");
    }

    @Override
    public SmartMap<K, V> filter(final MapPredicate<? super K, ? super V> predicate) {
        SmartMap<K, V> tempMap = createNewInstance(internalMap);
        clear();

        for (Map.Entry<K, V> entry : tempMap.entrySet()) {
            if (predicate.test(entry.getKey(), entry.getValue())) {
                put(entry.getKey(), entry.getValue());
            }
        }
        
        return this;
    }

    @Override
    public SmartMap<K, V> remove(final MapPredicate<? super K, ? super V> predicate) {
        SmartMap<K, V> tempMap = createNewInstance(internalMap);
        clear();

        for (Map.Entry<K, V> entry : tempMap.entrySet()) {
            if (!predicate.test(entry.getKey(), entry.getValue())) {
                put(entry.getKey(), entry.getValue());
            }
        }
        
        return this;
    }

    @Override
    public SmartMap<K, V> replace(final K seekKey, final V seekValue, final K newKey, final V newValue) {
        V foundVal = get(seekKey);

        if (foundVal.equals(seekValue)) {
            remove(seekKey);
            put(newKey, newValue);
        }
        
        return this;
    }

    @Override
    public <R> SmartList<R> map(final MapUnaryFunction<R, ? super K, ? super V> function) {
    	SmartList<R> result = new SmartArrayList<R>();

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            R mappedEntry = function.apply(entry.getKey(), entry.getValue());
            result.add(mappedEntry);
        }

        return result;
    }

    @Override
    public <R> R reduce(final R initial, final MapBinaryFunction<R, ? super K, ? super V> funct) {
        R result = initial;

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            result = funct.apply(result, entry.getKey(), entry.getValue());
        }

        return result;
    }

    @Override
    public String join(final String entryDelimiter, final String keyValDelimiter) {
        StringBuffer sb = new StringBuffer();

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            sb.append(entry.getKey() + keyValDelimiter + entry.getValue());
            sb.append(entryDelimiter);
        }
        sb.setLength(sb.length() - entryDelimiter.length());

        return sb.toString();
    }

    @Override
    public int count(final MapPredicate<? super K, ? super V> predicate) {
        int counter = 0;

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (predicate.test(entry.getKey(), entry.getValue())) {
                counter++;
            }
        }

        return counter;
    }

    @Override
    public boolean exists(final MapPredicate<? super K, ? super V> predicate) {
        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (predicate.test(entry.getKey(), entry.getValue())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean forall(final MapPredicate<? super K, ? super V> predicate) {
        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (!predicate.test(entry.getKey(), entry.getValue())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isBijective() {
        return new HashSet<V>(values()).size() == size();
    }

    @Override
    public SmartMap<V, K> swap() {
        if (!isBijective()) {
            throw new UnsupportedOperationException("Map is not bijective!");
        }

        SmartMap<V, K> swappedMap = createNewInstance(new HashMap<V, K>());

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            swappedMap.put(entry.getValue(), entry.getKey());
        }

        return swappedMap;
    }

    @Override
    public boolean equals(final Object obj) {
        return internalMap.equals(obj);
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }

    @Override
    public String toString() {
        return internalMap.toString();
    }
    
    @Override
    public Map<K, V> toStandardMap() {
    	return internalMap;
    }
}
