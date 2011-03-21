package com.stefanmuenchow.collections;

import java.util.HashMap;
import java.util.Map;

public class SmartHashMap<K, V> extends AbstractSmartMap<K, V> implements
        SmartMap<K, V> {

    public SmartHashMap() {
        super(new HashMap<K, V>());
    }

    public SmartHashMap(final Map<K, V> map) {
        super(new HashMap<K, V>(map));
    }
    
    public SmartHashMap(SmartList<K> keys, SmartList<V> vals) {
    	super(new HashMap<K, V>(keys.zipWith(vals)));
    }

    /** Helper methods */

    @Override
    protected SmartMap<K, V> createNewInstance() {
        return new SmartHashMap<K, V>();
    }

    @Override
    protected <S, R> SmartMap<S, R> createNewInstance(final Map<S, R> aMap) {
        return new SmartHashMap<S, R>(aMap);
    }
}
