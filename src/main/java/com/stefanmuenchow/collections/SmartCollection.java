package com.stefanmuenchow.collections;

import java.util.Collection;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

/**
 * Specifies the operations of all smart collections. Lists, Sets, SortedSets
 * and Queues are collections. Smart maps are specified in the interface
 * ISmartMap.
 * 
 * Smart Collections are compatible with the standard Collection interface, but
 * add some functionality to them. They are implemented as simple decorators
 * (see Gang of Four).
 * 
 * @author Stefan Münchow
 */
public interface SmartCollection<E> extends Collection<E> {

    /**
     * Seeks a single element based on a predicate. The first element for which
     * the predicate returns <code>true</code>, is returned. If the predicate is
     * <code>false</code> for all elements, null is returned.
     * 
     * @param pred
     *            Predicate
     * @return Element or null
     */
    E find(Predicate<E> pred);

    /**
     * Retains all elements in the collection for which the predicate is true.
     * 
     * @param predicate
     *            Predicate
     * @return Collection containing only elements for which predicate returns
     *         true
     */
    SmartCollection<E> filter(Predicate<E> predicate);

    /**
     * Removes all elements in the collection for which the predicate is true.
     * 
     * @param predicate
     *            Predicate
     * @return Collection containing only elements for which predicate returns
     *         false
     */
    SmartCollection<E> remove(Predicate<E> predicate);

    /**
     * Replaces each occurence of an element in the collection with a
     * replacement. The elements are compared by the equals method. If the
     * element is not found in the collection, nothing is done.
     * 
     * @param seek
     *            Element to be replaced
     * @param replacement
     *            Replacement
     * @return List with replaced elements
     */
    SmartCollection<E> replace(E seek, E replacement);

    /**
     * Replaces each element in the collection, for which the predicate returns
     * true, with a replacement. The elements are compared by the equals method.
     * If the predicate evaluates to false for all elements, nothing is done.
     * 
     * @param predicate
     *            Predicate to identify elements to replace
     * @param replacement
     *            Replacement
     * @return List with replaced elements
     */
    SmartCollection<E> replace(Predicate<E> predicate, E replacement);

    /**
     * Calls a given function for each element in the collection with the
     * element as the one and only parameter. Each element is replaced by the
     * return value of the function and the resulting collection is returned.
     * Creates a new Collection.
     * 
     * @param function
     *            Unary function
     * @see UnaryFunction
     * @return Altered collection
     */
    <R> SmartCollection<R> map(UnaryFunction<R, E> function);

    /**
     * Combines the elements of this list from left to right using a binary
     * function and an initial value. If the collection is empty, the initial
     * value is returned.
     * 
     * @param initial
     *            Initial value
     * @param funct
     *            Binary Function
     * @see BinaryFunction
     * @return A single value
     */
    <R> R reduce(R initial, BinaryFunction<R, R, E> funct);

    /**
     * Combines the elements of this list from left to right using a binary
     * function. If the collection is empty, null is returned.
     * 
     * @param funct
     *            Binary Function
     * @see BinaryFunction
     * @return A single value
     */
    E reduce(BinaryFunction<E, E, E> funct);

    /**
     * Calls the toString() method of each element in the collection and
     * intersperses the resulting strings with delimiter. The complete result is
     * returned.
     * 
     * @param delimiter
     *            String that is inserted between each two elements
     * @return Resulting string representation
     */
    String join(String delimiter);

    /**
     * Counts all entries for which the predicate evaluates to true.
     * 
     * @param predicate
     *            Predicate
     * @return Number of elements in collection for which predicate is true
     */
    int count(Predicate<E> predicate);

    /**
     * Checks if the predicate evaluates to true for any element in the
     * collection. If not, the result is false.
     * 
     * @param pred
     *            Predicate
     * @return true / false
     */
    boolean exists(Predicate<E> pred);

    /**
     * Checks if the predicate evaluates to true for all elements in the
     * collection. If not, the result is false.
     * 
     * @param pred
     *            Predicate
     * @return true / false
     */
    boolean forall(Predicate<E> pred);

    /**
     * If the collection contains other collections, then the values of all
     * collections are collected recursively and put into the resulting list. If
     * it is already a "flat" collection, nothing is done. A new collection is
     * created.
     * 
     * @return Flat collection not containing any other collection
     */
    SmartCollection<E> flatten();
}