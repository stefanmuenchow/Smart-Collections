package com.stefanmuenchow.collections.immutable;

import java.util.Set;

import com.stefanmuenchow.collections.function.UnaryFunction;

public interface ImmutableSet<E> extends ImmutableCollection<E> {

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
    ImmutableSet<E> union(Set<E> anotherSet);

    /**
     * Creates the intersection of both sets. The first set is changed.
     *
     * @param anotherSet
     *            Another set
     * @return Intersection set
     */
    ImmutableSet<E> intersection(Set<E> anotherSet);

    /**
     * Creates the difference of both sets. The first set is changed.
     *
     * @param anotherSet
     *            Another set
     * @return Difference set
     */
    ImmutableSet<E> difference(Set<E> anotherSet);

    @Override
    <R> ImmutableSet<R> map(UnaryFunction<R, E> function);

    @Override
    <T> ImmutableSet<T> castAllElements(Class<T> clazz);
}
