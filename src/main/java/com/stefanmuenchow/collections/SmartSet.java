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

import java.util.Set;

import com.stefanmuenchow.collections.function.UnaryFunction;

/**
 * SmartSets are compatible with the standard Set interface, but add some
 * functionality to them. They are implemented as simple decorators (see Gang of
 * Four).
 *
 * @author Stefan Muenchow
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
    void union(Set<E> anotherSet);

    /**
     * Creates the intersection of both sets. The first set is changed.
     *
     * @param anotherSet
     *            Another set
     * @return Intersection set
     */
    void intersection(Set<E> anotherSet);

    /**
     * Creates the difference of both sets. The first set is changed.
     *
     * @param anotherSet
     *            Another set
     * @return Difference set
     */
    void difference(Set<E> anotherSet);

    @Override
    <R> SmartSet<R> map(UnaryFunction<R, E> function);

    @Override
    <T> SmartSet<T> castEach(Class<T> clazz);
}
