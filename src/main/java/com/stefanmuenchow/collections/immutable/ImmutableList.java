package com.stefanmuenchow.collections.immutable;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public interface ImmutableList<E> extends ImmutableCollection<E> {

    public ImmutableList<E> addAll(final int index, final Collection<? extends E> c);
    public E get(final int index);
    public ImmutableList<E> set(final int index, final E element);
    public ImmutableList<E> add(final int index, final E element);
    public ImmutableList<E> remove(final int index);
    public int indexOf(final Object o);
    public int lastIndexOf(final Object o);
    public ListIterator<E> listIterator();
    public ListIterator<E> listIterator(final int index);
    public ImmutableList<E> subList(final int fromIndex, final int toIndex);

    /**
     * Returns the first element in the List. Similar to
     *
     * <pre>
     * aList.get(0)
     * </pre>
     *
     * @throws NoSuchElementException If list is empty
     * @return First element
     */
    E head() throws NoSuchElementException;

    /**
     * Returns a new list without the first element.
     *
     * @throws UnsupportedOperationException If list is empty
     * @return Rest list
     */
    ImmutableList<E> tail() throws UnsupportedOperationException;

    /**
     * Returns the last element of the list. Similar to
     *
     * <pre>
     * aList.get(aList.size() - 1)
     * </pre>
     *
     * @throws NoSuchElementException If list is empty
     * @return Last element
     */
    E last() throws NoSuchElementException;

    /**
     * Gets the element at the specified index. If the index is not into the
     * range od valid indices, the default value is returned.
     *
     * @param index
     *            Index of element to get
     * @param defaultVal
     *            Default value
     * @return Element at index or defaultVal, if index is out of bounds.
     */
    E get(int index, E defaultVal);

    /**
     * Takes the first n elements of the collection and returns the result. A
     * new list is created. Similar to
     *
     * <pre>
     * aList.subList(0, n)
     * </pre>
     *
     * @param n
     *            Number of elements to take from beginning of list
     * @return Prefix list
     */
    ImmutableList<E> take(int n);

    /**
     * Removes the first n elements of the collection and returns the result. A
     * new list is created. Similar to
     *
     * <pre>
     * aList.subList(n, aList.size())
     * </pre>
     *
     * @param n
     *            Number of elements to remove from beginning of list
     * @return Postfix list
     */
    ImmutableList<E> drop(int n);

    /**
     * Takes elements from the List until the predicate evaluates to false the
     * first time. A new list is created.
     *
     * @param pred
     *            Predicate
     * @return Prefix list
     */
    ImmutableList<E> takeWhile(Predicate<E> pred);

    /**
     * Removes elements from the List until the predicate evaluates to false the
     * first time. A new list is created.
     *
     * @param pred
     *            Predicate
     * @return Postfix list
     */
    ImmutableList<E> dropWhile(Predicate<E> pred);

    /**
     * Removes all duplicate values from the List. Changes the original list.
     *
     * @return List without duplicates
     */
    ImmutableList<E> removeDuplicates();

    /**
     * Inserts the specified elem between each two elements of the list. Changes
     * the original list.
     *
     * @param elem
     *            Element to insert
     * @return List with elem at each 2nd index
     */
    ImmutableList<E> intersperse(E elem);

    /**
     * Creates a SmartMap from two lists. Elements in the first list are the
     * keys, elements with corresponding indices in the second list the values.
     * If one of the lists has less elements than the other one, the elements
     * are discarded.
     *
     * @param anotherList
     *            List to zip with
     * @return Map
     */
    <T> ImmutableMap<E, T> zipWith(List<T> anotherList);

    /**
     * Returns a list of the indices. This is the same as the range from 0 to
     *
     * <pre>
     * list.size() - 1
     * </pre>
     *
     * . The original list remains unchanged.
     *
     * @return List of indices
     */
    ImmutableList<Integer> getIndicesList();

    /**
     * Creates a Map with the elements of the list as keys and the number of
     * their occurences in the list as values.
     *
     * @return Map<E, Integer>
     */
    ImmutableMap<E, Integer> getOccurenceCountMap();

    /**
     * Reverses the ordering of the elements in the list.
     *
     * @return Reversed list
     */
    ImmutableList<E> reverse();

    /**
     * Returns the list size without all null elements. If there are no null
     * elements in list, it is equal to list.size().
     *
     * @return List size without null elements
     */
    int sizeWithoutNulls();

    @Override
    <R> ImmutableList<R> map(UnaryFunction<R, E> function);

    @Override
    <T> ImmutableList<T> castAllElements(Class<T> clazz);
}
