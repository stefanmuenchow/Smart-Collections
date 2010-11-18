package com.stefanmuenchow.collections.map;

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
public interface ISmartSortedMap<K extends Comparable<K>, V> extends
        SortedMap<K, V>, ISmartMap<K, V> {

}
