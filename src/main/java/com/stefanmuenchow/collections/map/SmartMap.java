package com.stefanmuenchow.collections.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.stefanmuenchow.collections.exception.PreconditionViolatedException;
import com.stefanmuenchow.collections.function.IBinaryFunction;
import com.stefanmuenchow.collections.function.IMapPredicate;
import com.stefanmuenchow.collections.function.IUnaryFunction;

public class SmartMap<K, V> implements ISmartMap<K, V> {
    private final Map<K, V> internalMap;

    public SmartMap() {
        internalMap = new HashMap<K, V>();
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public V get(final Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public V put(final K key, final V value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public V remove(final Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> m) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<K> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    /** ISmartMap methods */

    @Override
    public <W> ISmartMap<K, W> mergeWith(
            final ISmartMap<K, V> anotherMap,
            final IBinaryFunction<java.util.Map.Entry<K, W>, java.util.Map.Entry<K, V>, java.util.Map.Entry<K, V>> mergeFunct) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public V get(final K key, final V defaultVal) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public V find(final IMapPredicate<K, V> predicate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISmartMap<K, V> filter(final IMapPredicate<K, V> predicate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISmartMap<K, V> remove(final IMapPredicate<K, V> predicate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISmartMap<K, V> replace(final K seekKey, final V seekValue,
            final K newKey, final V newValue) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISmartMap<K, V> replace(final IMapPredicate<K, V> predicate,
            final K newKey, final V newValue) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S, R> ISmartMap<S, R> map(
            final IUnaryFunction<java.util.Map.Entry<S, R>, java.util.Map.Entry<K, V>> function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <R> R reduce(final R initial,
            final IBinaryFunction<R, R, java.util.Map.Entry<K, V>> funct) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String join(final String entryDelimiter, final String keyValDelimiter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int count(final IMapPredicate<K, V> predicate) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean exists(final IMapPredicate<K, V> predicate) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean forall(final IMapPredicate<K, V> predicate) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isBijective() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ISmartMap<V, K> swap() throws PreconditionViolatedException {
        // TODO Auto-generated method stub
        return null;
    }
}
