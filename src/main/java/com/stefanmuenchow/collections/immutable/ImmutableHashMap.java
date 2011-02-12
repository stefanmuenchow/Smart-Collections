package com.stefanmuenchow.collections.immutable;

import java.util.Map;

import com.stefanmuenchow.collections.SmartHashMap;
import com.stefanmuenchow.collections.SmartMap;

public class ImmutableHashMap<K, V> extends AbstractImmutableMap<K, V> implements ImmutableMap<K, V> {

    protected ImmutableHashMap(final SmartMap<K, V> map) {
        super(map);
    }

    public ImmutableHashMap() {
        this(new SmartHashMap<K, V>());
    }

    public ImmutableHashMap(final Map<K, V> map) {
        this(new SmartHashMap<K, V>(map));
    }

    /** Helper methods */

    @Override
    protected ImmutableMap<K, V> createNewInstance() {
        return new ImmutableHashMap<K, V>();
    }

    @Override
    protected <S, R> ImmutableMap<S, R> createNewInstance(final Map<S, R> aMap) {
        return new ImmutableHashMap<S, R>(aMap);
    }
}
