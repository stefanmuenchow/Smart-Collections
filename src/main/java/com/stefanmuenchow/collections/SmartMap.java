package com.stefanmuenchow.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.stefanmuenchow.collections.exception.PreconditionViolatedException;
import com.stefanmuenchow.collections.function.IBinaryFunction;
import com.stefanmuenchow.collections.function.IMapPredicate;
import com.stefanmuenchow.collections.function.IUnaryFunction;

public class SmartMap<K, V> implements ISmartMap<K, V> {
    protected final Map<K, V> internalMap;

    public SmartMap() {
        internalMap = new HashMap<K, V>();
    }

    public SmartMap(final Map<K, V> map) {
        internalMap = map;
    }

    /** Map Methods */

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
    public ISmartMap<K, V> mergeWith(final ISmartMap<K, V> anotherMap,
            final IBinaryFunction<V, V, V> mergeFunct) {

        ISmartMap<K, V> resultMap = new SmartMap<K, V>(internalMap);

        for (Map.Entry<K, V> entry : anotherMap.entrySet()) {
            if (resultMap.containsKey(entry.getKey())) {
                V mergedVal = mergeFunct.execute(resultMap.get(entry.getKey()),
                        entry.getValue());
                resultMap.put(entry.getKey(), mergedVal);
            } else {
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }

        return resultMap;
    }

    @Override
    public V get(final K key, final V defaultVal) {
        V value = internalMap.get(key);
        return value != null ? value : defaultVal;
    }

    @Override
    public V find(final IMapPredicate<K, V> predicate) {
        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (predicate.check(entry.getKey(), entry.getValue())) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public ISmartMap<K, V> filter(final IMapPredicate<K, V> predicate) {
        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (!predicate.check(entry.getKey(), entry.getValue())) {
                internalMap.remove(entry.getKey());
            }
        }

        return this;
    }

    @Override
    public ISmartMap<K, V> remove(final IMapPredicate<K, V> predicate) {
        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (predicate.check(entry.getKey(), entry.getValue())) {
                internalMap.remove(entry.getKey());
            }
        }

        return this;
    }

    @Override
    public ISmartMap<K, V> replace(final K seekKey, final V seekValue,
            final K newKey, final V newValue) {
        V foundVal = internalMap.get(seekKey);

        if (foundVal.equals(seekValue)) {
            internalMap.remove(seekKey);
            internalMap.put(newKey, newValue);
        }

        return this;
    }

    @Override
    public ISmartMap<K, V> replace(final IMapPredicate<K, V> predicate,
            final K newKey, final V newValue) {

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (predicate.check(entry.getKey(), entry.getValue())) {
                internalMap.remove(entry.getKey());
                internalMap.put(newKey, newValue);
            }
        }

        return this;
    }

    @Override
    public <S, R> ISmartMap<S, R> map(
            final IUnaryFunction<java.util.Map.Entry<S, R>, java.util.Map.Entry<K, V>> function) {
        ISmartMap<S, R> resultMap = new SmartMap<S, R>();

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            Map.Entry<S, R> mappedEntry = function.execute(entry);
            resultMap.put(mappedEntry.getKey(), mappedEntry.getValue());
        }

        return resultMap;
    }

    @Override
    public <R> R reduce(final R initial,
            final IBinaryFunction<R, R, java.util.Map.Entry<K, V>> funct) {

        R result = null;

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            result = funct.execute(result, entry);
        }

        return result;
    }

    @Override
    public String join(final String entryDelimiter, final String keyValDelimiter) {
        StringBuffer sb = new StringBuffer();

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            sb.append(entry.getKey() + keyValDelimiter + entry.getKey());
            sb.append(entryDelimiter);
        }
        sb.setLength(sb.length() - entryDelimiter.length());

        return sb.toString();
    }

    @Override
    public int count(final IMapPredicate<K, V> predicate) {
        int counter = 0;

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (predicate.check(entry.getKey(), entry.getValue())) {
                counter++;
            }
        }

        return counter;
    }

    @Override
    public boolean exists(final IMapPredicate<K, V> predicate) {
        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (predicate.check(entry.getKey(), entry.getValue())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean forall(final IMapPredicate<K, V> predicate) {
        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            if (!predicate.check(entry.getKey(), entry.getValue())) {
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
    public ISmartMap<V, K> swap() throws PreconditionViolatedException {
        if (!isBijective()) {
            throw new PreconditionViolatedException("Map is not bijective!");
        }

        ISmartMap<V, K> swappedMap = new SmartMap<V, K>();

        for (Map.Entry<K, V> entry : internalMap.entrySet()) {
            swappedMap.put(entry.getValue(), entry.getKey());
        }

        return swappedMap;
    }
}
