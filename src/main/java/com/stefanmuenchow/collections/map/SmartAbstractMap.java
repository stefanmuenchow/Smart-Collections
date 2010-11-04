package com.stefanmuenchow.collections.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SmartAbstractMap<K, V> implements ISmartMap<K, V> {
	protected final Map<K, V> internalMap;
	
	protected SmartAbstractMap(Map<K, V> map) {
		this.internalMap = map;
	}
	
	/** Delegate all standard Map interface operations */

	public int size() {
		return internalMap.size();
	}

	public boolean isEmpty() {
		return internalMap.isEmpty();
	}

	public boolean containsKey(Object key) {
		return internalMap.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return internalMap.containsValue(value);
	}

	public V get(Object key) {
		return internalMap.get(key);
	}

	public V put(K key, V value) {
		return internalMap.put(key, value);
	}

	public V remove(Object key) {
		return internalMap.remove(key);
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		internalMap.putAll(m);
	}

	public void clear() {
		internalMap.clear();
	}

	public Set<K> keySet() {
		return internalMap.keySet();
	}

	public Collection<V> values() {
		return internalMap.values();
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return internalMap.entrySet();
	}

	public boolean equals(Object o) {
		return internalMap.equals(o);
	}

	public int hashCode() {
		return internalMap.hashCode();
	}
	
	/** Add some new smarter operations */

	@Override
	public ISmartMap<K, V> putElem(K key, V val) {
		internalMap.put(key, val);
		return this;
	}

	@Override
	public ISmartMap<K, V> putAllElems(Map<K, V> map) {
		internalMap.putAll(map);
		return this;
	}

	@Override
	public ISmartMap<K, V> removeElem(K key) {
		internalMap.remove(key);
		return this;
	}

	@Override
	public ISmartMap<K, V> clearAll() {
		internalMap.clear();
		return this;
	}
}
