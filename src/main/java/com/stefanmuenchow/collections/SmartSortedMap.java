/**
* Copyright (c) Stefan Muenchow. All rights reserved.
* The use and distribution terms for this software are covered by the
* Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
* which can be found in the file epl-v10.html at the root of this distribution.
* By using this software in any fashion, you are agreeing to be bound by
* the terms of this license.
* You must not remove this notice, or any other, from this software.
**/

package com.stefanmuenchow.collections;

import java.util.SortedMap;

/**
 * SmartSortedMaps are compatible with the standard SortedMap interface, but add
 * some functionality to them. They are implemented as simple decorators (see
 * Gang of Four).
 *
 * @author Stefan Muenchow
 */
public interface SmartSortedMap<K, V> extends SortedMap<K, V>, SmartMap<K, V> {
	
	@Override
	SortedMap<K, V> toStandardMap();
}
