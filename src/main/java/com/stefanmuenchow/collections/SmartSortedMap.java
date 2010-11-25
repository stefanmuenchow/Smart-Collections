package com.stefanmuenchow.collections;

import java.util.SortedMap;

/**
 * Specifies the operations of SmartSortedMaps.
 * 
 * SmartSortedMaps are compatible with the standard SortedMap interface, but add
 * some functionality to them. They are implemented as simple decorators (see
 * Gang of Four).
 * 
 * @author Stefan Münchow
 */
public interface SmartSortedMap<K, V> extends SortedMap<K, V>, SmartMap<K, V> {

}
