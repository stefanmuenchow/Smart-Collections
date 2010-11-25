package com.stefanmuenchow.collections;

import java.util.Set;

/**
 * Specifies the operations of SmartSets.
 * 
 * SmartSets are compatible with the standard Set interface, but add some
 * functionality to them. They are implemented as simple decorators (see Gang of
 * Four).
 * 
 * @author Stefan Münchow
 */
public interface SmartSet<E> extends Set<E>, SmartCollection<E> {

    /**
     * Checks if this set is a subset of anotherSet.
     * 
     * @param anotherSet
     *            Another set
     * @return true / false
     */
    boolean isSubsetOf(Set<E> anotherSet);

    /**
     * Checks if this set is a proper subset of anotherSet.
     * 
     * @param anotherSet
     *            Another set
     * @return true / false
     */
    boolean isProperSubsetOf(Set<E> anotherSet);

    /**
     * Checks if this set is a superset of anotherSet.
     * 
     * @param anotherSet
     *            Another set
     * @return true / false
     */
    boolean isSupersetOf(Set<E> anotherSet);

    /**
     * Checks if this set is a proper superset of anotherSet.
     * 
     * @param anotherSet
     *            Another set
     * @return true / false
     */
    boolean isProperSupersetOf(Set<E> anotherSet);

    /**
     * Creates the union of the two sets. The first set is changed.
     * 
     * @param anotherSet
     *            Another set
     * @return Union set
     */
    SmartSet<E> union(Set<E> anotherSet);

    /**
     * Creates the intersection of both sets. The first set is changed.
     * 
     * @param anotherSet
     *            Another set
     * @return Intersection set
     */
    SmartSet<E> intersection(Set<E> anotherSet);

    /**
     * Creates the difference of both sets. The first set is changed.
     * 
     * @param anotherSet
     *            Another set
     * @return Difference set
     */
    SmartSet<E> difference(Set<E> anotherSet);
}
