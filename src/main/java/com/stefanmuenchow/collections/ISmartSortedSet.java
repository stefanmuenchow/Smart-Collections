package com.stefanmuenchow.collections;

import java.util.SortedSet;

/**
 * Specifies the operations of SmartSortedSets.
 * 
 * SmartSortedSets are compatible with the standard SortedSet interface, but add
 * some functionality to them. They are implemented as simple decorators (see
 * Gang of Four).
 * 
 * @author Stefan Münchow
 */
public interface ISmartSortedSet<E extends Comparable<E>> extends SortedSet<E>,
        ISmartSet<E> {

}
