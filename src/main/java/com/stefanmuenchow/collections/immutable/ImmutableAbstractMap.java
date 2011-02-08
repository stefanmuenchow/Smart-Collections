package com.stefanmuenchow.collections.immutable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import com.stefanmuenchow.collections.KeyValuePair;
import com.stefanmuenchow.collections.SmartHashMap;
import com.stefanmuenchow.collections.SmartMap;
import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.MapPredicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public abstract class ImmutableAbstractMap<K, V> implements ImmutableMap<K, V> {
    protected final SmartMap<K, V> internalMap;

    public ImmutableAbstractMap(final SmartMap<K,V> map) {
        internalMap = map;
    }

    /** Helper methods */

    protected SmartMap<K, V> getInternalMap() {
        return internalMap;
    }

    protected abstract ImmutableMap<K, V> createNewInstance();
    protected abstract <S, R> ImmutableMap<S, R> createNewInstance(final Map<S, R> aMap);

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
    public ImmutableMap<K, V> put(final K key, final V value) {
        HashMap<K, V> temp = new HashMap<K, V>(internalMap);
        temp.put(key, value);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableMap<K, V> remove(final Object key) {
        HashMap<K, V> temp = new HashMap<K, V>(internalMap);
        temp.remove(key);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableMap<K, V> putAll(final Map<? extends K, ? extends V> m) {
        HashMap<K, V> temp = new HashMap<K, V>(internalMap);
        temp.putAll(m);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableMap<K, V> clear() {
        return createNewInstance();
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
    public Set<Entry<K, V>> entrySet() {
        return internalMap.entrySet();
    }

    @Override
    public Entry<K, V> head() throws NoSuchElementException {
        return internalMap.head();
    }

    @Override
    public ImmutableMap<K, V> tail() throws UnsupportedOperationException {
        return createNewInstance(internalMap.tail());
    }

    @Override
    public ImmutableMap<K, V> mergeWith(final SmartMap<K, V> anotherMap,
            final BinaryFunction<V, V> mergeFunct) {

        SmartMap<K, V> temp = new SmartHashMap<K, V>(internalMap);
        temp.mergeWith(anotherMap, mergeFunct);

        return createNewInstance(temp);
    }

    @Override
    public V get(final K key, final V defaultVal) {
        return internalMap.get(key, defaultVal);
    }

    @Override
    public V find(final MapPredicate<K, V> predicate) throws NoSuchElementException {
        return internalMap.find(predicate);
    }

    @Override
    public ImmutableMap<K, V> filter(final MapPredicate<K, V> predicate) {
        SmartMap<K, V> temp = new SmartHashMap<K, V>(internalMap);
        temp.filter(predicate);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableMap<K, V> remove(final MapPredicate<K, V> predicate) {
        SmartMap<K, V> temp = new SmartHashMap<K, V>(internalMap);
        temp.remove(predicate);

        return createNewInstance(temp);
    }

    @Override
    public ImmutableMap<K, V> replace(final K seekKey, final V seekValue, final K newKey,
            final V newValue) {
        SmartMap<K, V> temp = new SmartHashMap<K, V>(internalMap);
        temp.replace(seekKey, seekValue, newKey, newValue);

        return createNewInstance(temp);
    }

    @Override
    public <S, R> ImmutableMap<S, R> map(
            final UnaryFunction<KeyValuePair<S, R>, Entry<K, V>> function) {
        return createNewInstance(internalMap.map(function));
    }

    @Override
    public <R> R reduce(final R initial, final BinaryFunction<R, Entry<K, V>> funct) {
        return internalMap.reduce(initial, funct);
    }

    @Override
    public String join(final String entryDelimiter, final String keyValDelimiter) {
        return internalMap.join(entryDelimiter, keyValDelimiter);
    }

    @Override
    public int count(final MapPredicate<K, V> predicate) {
        return internalMap.count(predicate);
    }

    @Override
    public boolean exists(final MapPredicate<K, V> predicate) {
        return internalMap.exists(predicate);
    }

    @Override
    public boolean forall(final MapPredicate<K, V> predicate) {
        return internalMap.forall(predicate);
    }

    @Override
    public boolean isBijective() {
        return internalMap.isBijective();
    }

    @Override
    public ImmutableMap<V, K> swap() throws UnsupportedOperationException {
        return createNewInstance(internalMap.swap());
    }

}
