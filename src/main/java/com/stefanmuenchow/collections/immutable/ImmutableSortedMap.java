package com.stefanmuenchow.collections.immutable;

import java.util.Comparator;

public interface ImmutableSortedMap<K, V> extends ImmutableMap<K, V> {

    public Comparator<? super K> comparator();
    public ImmutableSortedMap<K, V> subMap(final K fromKey, final K toKey);
    public ImmutableSortedMap<K, V> headMap(final K toKey);
    public ImmutableSortedMap<K, V> tailMap(final K fromKey);
    public K firstKey();
    public K lastKey();
}
