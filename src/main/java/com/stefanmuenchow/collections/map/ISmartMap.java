package com.stefanmuenchow.collections.map;

import java.util.Map;

public interface ISmartMap<K, V> extends Map<K, V> {
	ISmartMap<K, V> putElem(K key, V val);
	ISmartMap<K, V> putAllElems(Map<K, V> map);
	ISmartMap<K, V> removeElem(K key);
	ISmartMap<K, V> clearAll();
}
