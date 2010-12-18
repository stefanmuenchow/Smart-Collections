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

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SmartTreeMap<K, V> extends SmartAbstractMap<K, V> implements SmartSortedMap<K, V> {

    public SmartTreeMap() {
        super(new TreeMap<K, V>());
    }

    public SmartTreeMap(final Map<K, V> map) {
        super(new TreeMap<K, V>(map));
    }

    /** Helper methods */

    private SortedMap<K, V> getInternalMap() {
        return (SortedMap<K, V>) internalMap;
    }

    @Override
    protected SmartSortedMap<K, V> createNewInstance() {
        return new SmartTreeMap<K, V>();
    }

    @Override
    protected <S, R> SmartSortedMap<S, R> createNewInstance(final Map<S, R> aMap) {
        return new SmartTreeMap<S, R>(aMap);
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
