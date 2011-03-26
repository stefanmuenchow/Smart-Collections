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
import java.util.SortedSet;

/**
 * A SmartSortedSet keeps its elements in ascending order, sorted according to 
 * the natural ordering of its elements or by a given {@link Comparator}.
 * 
 * @see SortedSet
 * @see SmartSet
 * @see SmartTreeSet
 *
 * @author Stefan Muenchow
 */
public interface SmartSortedSet<E> extends SortedSet<E>, SmartSet<E> {

	/**
	 * Same as {@link SortedSet#subSet(Object, Object)}, but returns
	 * a SmartSortedSet.
	 * 
	 * @see SortedSet#subSet(Object, Object)
	 */
	public SmartSortedSet<E> smartSubSet(final E fromElement, final E toElement);
	
	/**
	 * Same as {@link SortedSet#headSet(Object)}, but returns
	 * a SmartSortedSet.
	 * 
	 * @see SortedSet#headSet(Object)
	 */
	public SmartSortedSet<E> smartHeadSet(final E toElement);
	
	/**
	 * Same as {@link SortedSet#tailSet(Object)}, but returns
	 * a SmartSortedSet.
	 * 
	 * @see SortedSet#tailSet(Object)
	 */
	public SmartSortedSet<E> smartTailSet(final E fromElement);
	
	/**
	 * @see SmartCollection#toStandardCollection()
	 */
    @Override
    SortedSet<E> toStandardCollection();
}
