package com.stefanmuenchow.collections.map;

import java.util.Map;
import java.util.WeakHashMap;

public class SmartWeakHashMap<K, V> extends SmartAbstractMap<K, V> {

	public SmartWeakHashMap() {
		super(new WeakHashMap<K, V>());
	}
	
	public SmartWeakHashMap(Map<K, V> map) {
		super(new WeakHashMap<K, V>(map));
	}
}
