package com.stefanmuenchow.collections.list;

import java.util.List;

import com.stefanmuenchow.collections.ISmartCollection;
import com.stefanmuenchow.collections.function.IPredicate;
import com.stefanmuenchow.collections.map.ISmartMap;

/**
 * Specifies the operations of SmartLists.
 * 
 * SmartLists are compatible with the standard List
 * interface, but add some functionality to them. They are implemented
 * as simple decorators (see Gang of Four).
 * 
 * @author Stefan Münchow
 */
public interface ISmartList<E> extends List<E>, ISmartCollection<E> {
	
	/**
	 * Returns the first element in the List. Similar to
	 * <pre>aList.get(0)</pre>
	 * @return	First element
	 */
	E first();
	
	/**
	 * Returns the last element of the list. Similar to
	 * <pre>aList.get(aList.size() - 1)</pre>
	 * @return	Last element
	 */
	E last();
	
	/**
	 * Gets the element at the specified index. If the index is not into
	 * the range od valid indices, the default value is returned.
	 * @param index			Index of element to get
	 * @param defaultVal	Default value
	 * @return				Element at index or defaultVal, if index is
	 * 						out of bounds.
	 */
	E get(int index, E defaultVal);	
	
	/**
	 * Takes the first n elements of the collection and returns the result.
	 * The original list is changed. Similar to 
	 * <pre>aList.subList(0, n)</pre>
	 * @param n		Number of elements to take from beginning of list
	 * @return		Prefix list
	 */
	ISmartList<E> take(int n);
	
	/**
	 * Removes the first n elements of the collection and returns the result.
	 * The original list is changed. Similar to 
	 * <pre>aList.subList(n, aList.size())</pre>
	 * @param n		Number of elements to remove from beginning of list
	 * @return		Postfix list
	 */
	ISmartList<E> drop(int n);
	
	/**
	 * Takes elements from the List until the predicate evaluates to false
	 * the first time. The original list is changed.
	 * @param pred	Predicate
	 * @return		Prefix list
	 */
	ISmartList<E> takeWhile(IPredicate<E> pred);
	
	/**
	 * Removes elements from the List until the predicate evaluates to false
	 * the first time. The original list is changed.
	 * @param pred	Predicate
	 * @return		Postfix list
	 */
	ISmartList<E> dropWhile(IPredicate<E> pred);
	
	/**
	 * Removes all duplicate values from the List.
	 * @return	List without duplicates
	 */
	ISmartList<E> removeDuplicates();	
	
	/**
	 * Inserts the specified elem between each two elements of the list.
	 * @param elem	Element to insert
	 * @return		List with elem at each 2nd index
	 */
	ISmartList<E> intersperse(E elem);	
	
	/**
	 * Creates a SmartMap from two lists. Elements in the first list are
	 * the keys, elements with corresponding indices in the second list
	 * the values. If one of the lists has less elements than the other
	 * one, the elements are discarded.
	 * @param anotherList	List to zip with
	 * @return				Map
	 */
	<T> ISmartMap<E, T> zipWith(List<T> anotherList); 
	
	/**
	 * Returns a list of the indices. This is the same as the range from
	 * 0 to <pre>list.size() - 1</pre>. The original list remains unchanged.
	 * @return	List of indices
	 */
	ISmartList<Integer> getIndicesList();
	
	/**
	 * Creates a Map with the elements of the list as keys and
	 * the number of their occurences in the list as values.
	 * @return	Map<E, Integer>
	 */
	ISmartMap<E, Integer> getOccurenceCountMap();
	
	/**
	 * Reverses the ordering of the elements in the list.
	 * @return	Reversed list
	 */
	ISmartList<E> reverse();
	
	/**
	 * Returns the list size without all null elements. If there
	 * are no null elements in list, it is equal to list.size().
	 * @return	List size without null elements
	 */
	int sizeWithoutNulls();	
}
