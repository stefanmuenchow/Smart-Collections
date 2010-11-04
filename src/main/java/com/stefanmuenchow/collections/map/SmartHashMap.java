package com.stefanmuenchow.collections.map;

import java.util.HashMap;
import java.util.Map;

public class SmartHashMap<K, V> extends SmartAbstractMap<K, V> {

	public SmartHashMap() {
		super(new HashMap<K, V>());
	}
	
	public SmartHashMap(Map<K, V> map) {
		super(new HashMap<K, V>(map));
	}
}
