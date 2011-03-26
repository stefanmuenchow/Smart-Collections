/**
 * Copyright (c) Stefan Muenchow. All rights reserved.
 * 
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 **/

package com.stefanmuenchow.collections;

import java.util.Comparator;
import java.util.SortedMap;

/**
 * A SmartSortedMap keeps its entries in ascending order, sorted according to 
 * the natural ordering of the keys or by a given {@link Comparator}.
 * 
 * @see SortedMap
 * @see SmartMap
 * @see SmartTreeMap
 *
 * @author Stefan Muenchow
 */
public interface SmartSortedMap<K, V> extends SortedMap<K, V>, SmartMap<K, V> {
	
	/**
	 * Same as {@link SortedMap#subMap(Object, Object)}, but returns
	 * a SmartSortedMap.
	 * 
	 * @see SortedMap#subMap(Object, Object)
	 */
	public SmartSortedMap<K, V> smartSubMap(final K fromKey, final K toKey);
	
	/**
	 * Same as {@link SortedMap#headMap(Object)}, but returns
	 * a SmartSortedMap.
	 * 
	 * @see SortedMap#headMap(Object)
	 */
	public SmartSortedMap<K, V> smartHeadMap(final K toKey);
	
	/**
	 * Same as {@link SortedMap#tailMap(Object)}, but returns
	 * a SmartSortedMap.
	 * 
	 * @see SortedMap#tailMap(Object)
	 */
	public SmartSortedMap<K, V> smartTailMap(final K fromKey);
	
	/**
	 * @see SmartMap#toStandardMap()
	 */
	@Override
	SortedMap<K, V> toStandardMap();
}
