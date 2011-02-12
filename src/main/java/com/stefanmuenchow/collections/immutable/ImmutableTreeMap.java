package com.stefanmuenchow.collections.immutable;

import java.util.Comparator;
import java.util.Map;

import com.stefanmuenchow.collections.SmartMap;
import com.stefanmuenchow.collections.SmartTreeMap;

public class ImmutableTreeMap<K,V> extends AbstractImmutableMap<K, V> implements ImmutableSortedMap<K, V> {

    protected ImmutableTreeMap(final SmartMap<K, V> map) {
        super(map);
    }

    public ImmutableTreeMap() {
        this(new SmartTreeMap<K, V>());
    }

    public ImmutableTreeMap(final Map<K, V> map) {
        this(new SmartTreeMap<K, V>(map));
    }

    /** Helper methods */

    @Override
    protected SmartTreeMap<K, V> getInternalMap() {
        return (SmartTreeMap<K, V>) super.getInternalMap();
    }

    @Override
    protected ImmutableSortedMap<K, V> createNewInstance() {
        return new ImmutableTreeMap<K, V>();
    }

    @Override
    protected <S, R> ImmutableSortedMap<S, R> createNewInstance(final Map<S, R> aMap) {
        return new ImmutableTreeMap<S, R>(aMap);
    }

    @Override
    public Comparator<? super K> comparator() {
        return getInternalMap().comparator();
    }

    @Override
    public ImmutableSortedMap<K, V> subMap(final K fromKey, final K toKey) {
        return createNewInstance(getInternalMap().subMap(fromKey, toKey));
    }

    @Override
    public ImmutableSortedMap<K, V> headMap(final K toKey) {
        return createNewInstance(getInternalMap().headMap(toKey));
    }

    @Override
    public ImmutableSortedMap<K, V> tailMap(final K fromKey) {
        return createNewInstance(getInternalMap().tailMap(fromKey));
    }

    @Override
    public K firstKey() {
        return getInternalMap().firstKey();
    }

    @Override
    public K lastKey() {
        return getInternalMap().lastKey();
    }
}
