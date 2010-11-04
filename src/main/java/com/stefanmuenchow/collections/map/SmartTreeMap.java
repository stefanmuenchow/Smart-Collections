package com.stefanmuenchow.collections.map;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SmartTreeMap<K, V> extends SmartAbstractMap<K, V> implements ISmartSortedMap<K, V> {

	public SmartTreeMap() {
		super(new TreeMap<K, V>());
	}
	
	public SmartTreeMap(Map<K, V> map) {
		super(new TreeMap<K, V>(map));
	}
	
	protected TreeMap<K, V> getInternalMap() {
		return (TreeMap<K, V>)internalMap;
	}
	
	/** Delegate all standard TreeMap interface operations */

	@Override
	public Comparator<? super K> comparator() {
		return getInternalMap().comparator();
	}

	@Override
	public SortedMap<K, V> subMap(K fromKey, K toKey) {
		return getInternalMap().subMap(fromKey, toKey);
	}

	@Override
	public SortedMap<K, V> headMap(K toKey) {
		return getInternalMap().headMap(toKey);
	}

	@Override
	public SortedMap<K, V> tailMap(K fromKey) {
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
