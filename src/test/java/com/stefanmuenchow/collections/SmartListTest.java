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

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.function.Predicate;

public class SmartListTest {
	private SmartList<Integer> smartList1 = null;
	private SmartList<Integer> smartList2 = null;

	@Before
	public void setUp() throws Exception {
		smartList1 = new SmartArrayList<Integer>(9, 56, 23, 9, 10);
		smartList2 = new SmartLinkedList<Integer>(87, 13, 56, 85, 19);
	}
	
	@Test
	public void testAddIndexReturn() {
		assertEquals(new SmartArrayList<Integer>(9, 11, 56, 23, 9, 10), smartList1.addReturn(1, 11));
		assertEquals(new SmartLinkedList<Integer>(87, 13, 56, 85, 20, 19), smartList2.addReturn(4, 20));
	}
	
	@Test
	public void testAddAllIndexReturn() {
		List<Integer> toAdd = new SmartArrayList<Integer>(3, 4);
		
		assertEquals(new SmartArrayList<Integer>(9, 3, 4, 56, 23, 9, 10), smartList1.addAllReturn(1, toAdd));
		assertEquals(new SmartLinkedList<Integer>(87, 13, 56, 85, 3, 4, 19), smartList2.addAllReturn(4, toAdd));
	}
	
	@Test
	public void testRemoveIndexReturn() {
		assertEquals(new SmartArrayList<Integer>(56, 23, 9, 10), smartList1.removeIndexReturn(0));
		assertEquals(new SmartLinkedList<Integer>(87, 13, 56, 19), smartList2.removeIndexReturn(3));
	}
	
	@Test
	public void testSetReturn() {
		assertEquals(new SmartArrayList<Integer>(13, 56, 23, 9, 10), smartList1.setReturn(0, 13));
		assertEquals(new SmartLinkedList<Integer>(87, 13, 56, 2, 19), smartList2.setReturn(3, 2));
	}

	@Test
	public void testHead() {
		assertEquals(Integer.valueOf(9), smartList1.head());
		assertEquals(Integer.valueOf(87), smartList2.head());
	}

	public void testHeadFail() {
		int numExceptions = 0;
		
		try {
			new SmartArrayList<Object>().head();
		} catch (NoSuchElementException e) {
			numExceptions++;
		}
		
		try {
			new SmartLinkedList<Object>().head();
		} catch (NoSuchElementException e) {
			numExceptions++;
		}
		
		assertEquals(2, numExceptions);
	}

	@Test
	public void testTail() {
		assertEquals(new SmartArrayList<Integer>(56, 23, 9, 10), smartList1.tail());
		assertEquals(new SmartLinkedList<Integer>(13, 56, 85, 19), smartList2.tail());
	}

	public void testTailFail() {
		int numExceptions = 0;
		
		try {
			new SmartArrayList<Object>().tail();
		} catch (UnsupportedOperationException e) {
			numExceptions++;
		}
		
		try {
			new SmartLinkedList<Object>().tail();
		} catch (UnsupportedOperationException e) {
			numExceptions++;
		}
		
		assertEquals(2, numExceptions);
	}

	@Test
	public void testLast() {
		assertEquals(new Integer(10), smartList1.last());
		assertEquals(new Integer(19), smartList2.last());
	}

	public void testLastFail() {
		int numExceptions = 0;
		
		try {
			new SmartArrayList<Object>().last();
		} catch (NoSuchElementException e) {
			numExceptions++;
		}
		
		try {
			new SmartLinkedList<Object>().last();
		} catch (NoSuchElementException e) {
			numExceptions++;
		}
		
		assertEquals(2, numExceptions);
	}

	@Test
	public void testGet() {
		assertEquals(Integer.valueOf(23), smartList1.get(2, 42));
		assertEquals(Integer.valueOf(42), smartList1.get(10, 42));
		assertEquals(Integer.valueOf(56), smartList2.get(2, 42));
		assertEquals(Integer.valueOf(42), smartList2.get(10, 42));
	}

	@Test
	public void testTake() {
		assertEquals(new SmartArrayList<Integer>(9, 56, 23), smartList1.take(3));
		assertEquals(new SmartLinkedList<Integer>(87, 13, 56), smartList2.take(3));
		
		assertEquals(new SmartArrayList<Integer>(1, 2), new SmartArrayList<Integer>(1, 2).take(8));
		assertEquals(new SmartLinkedList<Integer>(1, 2), new SmartLinkedList<Integer>(1, 2).take(8));
		
		assertEquals(new SmartArrayList<Integer>(), new SmartArrayList<Integer>().take(8));
		assertEquals(new SmartLinkedList<Integer>(), new SmartLinkedList<Integer>().take(8));
	}

	@Test
	public void testDrop() {
		assertEquals(new SmartArrayList<Integer>(9, 10), smartList1.drop(3));
		assertEquals(new SmartLinkedList<Integer>(85, 19), smartList2.drop(3));
		
		assertEquals(new SmartArrayList<Integer>(), new SmartArrayList<Integer>(1, 2).drop(5));
		assertEquals(new SmartLinkedList<Integer>(), new SmartLinkedList<Integer>(1, 2).drop(5));
	}

	@Test
	public void testTakeWhile() {
		assertEquals(new SmartArrayList<Integer>(9, 56),
				smartList1.takeWhile(new Predicate<Integer>() {
					@Override
					public boolean test(final Integer input) {
						return !input.equals(23);
					}
				}));
		
		assertEquals(new SmartLinkedList<Integer>(87, 13),
				smartList2.takeWhile(new Predicate<Integer>() {
					@Override
					public boolean test(final Integer input) {
						return input % 2 == 1;
					}
				}));
	}

	@Test
	public void testDropWhile() {
		assertEquals(new SmartArrayList<Integer>(23, 9, 10),
				smartList1.dropWhile(new Predicate<Integer>() {
					@Override
					public boolean test(final Integer input) {
						return !input.equals(23);
					}
				}));
		
		assertEquals(new SmartLinkedList<Integer>(56, 85, 19),
				smartList2.dropWhile(new Predicate<Integer>() {
					@Override
					public boolean test(final Integer input) {
						return input % 2 == 1;
					}
				}));
	}
	
	@Test
	public void testSplitAt() {
		assertEquals(new Tuple<SmartList<Integer>, SmartList<Integer>>(
				new SmartArrayList<Integer>(9, 56, 23),
				new SmartArrayList<Integer>(9, 10)
			), smartList1.splitAt(3));
		
		assertEquals(new Tuple<SmartList<Integer>, SmartList<Integer>>(
				new SmartLinkedList<Integer>(87, 13, 56),
				new SmartLinkedList<Integer>(85, 19)
			), smartList2.splitAt(3));
	}
	
	@Test
	public void testPartition() {
		Predicate<Integer> even =  new Predicate<Integer>() {
			@Override
			public boolean test(Integer input) {
				return input % 2 == 0;
			}
		};
		
		assertEquals(new Tuple<SmartList<Integer>, SmartList<Integer>>(
				new SmartArrayList<Integer>(56, 10),
				new SmartArrayList<Integer>(9, 23, 9)
			), smartList1.partition(even));
		
		assertEquals(new Tuple<SmartList<Integer>, SmartList<Integer>>(
				new SmartLinkedList<Integer>(56),
				new SmartLinkedList<Integer>(87, 13, 85, 19)
			), smartList2.partition(even));
	}

	@Test
	public void testRemoveDuplicates() {
		SmartList<Integer> testList = new SmartArrayList<Integer>(1, 1, 1, 2, 3, 3, 3, 4, 5);
		assertEquals(new SmartArrayList<Integer>(1, 2, 3, 4, 5), testList.removeDuplicates());
		
		SmartList<Integer> testList2 = new SmartLinkedList<Integer>(1, 1, 1, 2, 3, 3, 3, 4, 5);
		assertEquals(new SmartLinkedList<Integer>(1, 2, 3, 4, 5), testList2.removeDuplicates());
	}

	@Test
	public void testIntersperse() {
		SmartList<Integer> testList = new SmartArrayList<Integer>(1, 2, 3);
		assertEquals(new SmartArrayList<Integer>(1, 0, 2, 0, 3), testList.intersperse(0));
		
		SmartList<Integer> testList2 = new SmartLinkedList<Integer>(1, 2, 3);
		assertEquals(new SmartLinkedList<Integer>(1, 0, 2, 0, 3), testList2.intersperse(0));

	}

	@Test
	public void testZipWith() {
		SmartMap<Integer, String> expectedMap = new SmartHashMap<Integer, String>();
		expectedMap.put(4, "foo");
		expectedMap.put(5, "bar");
		expectedMap.put(6, "baz");
		
		SmartMap<Integer, String> resultMap = new SmartHashMap<Integer, String>(
				new SmartArrayList<Integer>(4, 5, 6).zipWith(new SmartArrayList<String>("foo", "bar", "baz")));
		assertEquals(expectedMap, resultMap);
		
		SmartMap<Integer, String> resultMap2 = new SmartHashMap<Integer, String>(
			new SmartLinkedList<Integer>(4, 5, 6).zipWith(new SmartLinkedList<String>("foo", "bar", "baz")));
		assertEquals(expectedMap, resultMap2);
	}

	@Test
	public void testGetIndicesList() {
		assertEquals(new SmartArrayList<Integer>(0, 1, 2), 
				new SmartArrayList<String>("a", "b", "c").getIndicesList());
		assertEquals(new SmartLinkedList<Integer>(0, 1, 2), 
				new SmartLinkedList<String>("a", "b", "c").getIndicesList());
	}

	@Test
	public void testGetOccurenceCountMap() {
		SmartMap<Character, Integer> expectedMap = new SmartHashMap<Character, Integer>();
		expectedMap.put('a', 3);
		expectedMap.put('c', 2);
		expectedMap.put('x', 1);
		
		SmartMap<Character, Integer> resultMap = new SmartArrayList<Character>('a', 'a', 'c', 'a',
				'c', 'x').getOccurenceCountMap();
		assertEquals(expectedMap, resultMap);
		
		SmartMap<Character, Integer> resultMap2 = new SmartLinkedList<Character>('a', 'a', 'c', 'a',
				'c', 'x').getOccurenceCountMap();
		assertEquals(expectedMap, resultMap2);
	}

	@Test
	public void testReverse() {
		assertEquals(new SmartArrayList<Integer>(10, 9, 23, 56, 9), smartList1.reverse());
		assertEquals(new SmartLinkedList<Integer>(19, 85, 56, 13, 87), smartList2.reverse());
	}

	@Test
	public void testSizeWithoutNulls() {
		assertEquals(3, new SmartArrayList<Integer>(1, null, 3, 2, null).sizeWithoutNulls());
		assertEquals(3, new SmartLinkedList<Integer>(1, null, 3, 2, null).sizeWithoutNulls());
	}
}
