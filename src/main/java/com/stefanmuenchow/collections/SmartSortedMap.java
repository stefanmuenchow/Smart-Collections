package com.stefanmuenchow.collections;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SmartSortedMap<K extends Comparable<K>, V> extends SmartMap<K, V>
        implements ISmartSortedMap<K, V> {

    public SmartSortedMap() {
        super(new TreeMap<K, V>());
    }

    public SmartSortedMap(final Map<K, V> map) {
        super(new TreeMap<K, V>(map));
    }

    /** Helper methods */

    private SortedMap<K, V> getInternalMap() {
        return (SortedMap<K, V>) internalMap;
    }

    /** SortedMap methods */

    @Override
    public Comparator<? super K> comparator() {
        return getInternalMap().comparator();
    }

    @Override
    public SortedMap<K, V> subMap(final K fromKey, final K toKey) {
        return getInternalMap().subMap(fromKey, toKey);
    }

    @Override
    public SortedMap<K, V> headMap(final K toKey) {
        return getInternalMap().headMap(toKey);
    }

    @Override
    public SortedMap<K, V> tailMap(final K fromKey) {
        return getInternalMap().tailMap(fromKey);
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
