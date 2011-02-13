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

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.function.Predicate;

public class SmartListTest {
	private SmartList<Integer> smartList1 = null;
	private SmartList<Integer> smartList2 = null;

	@Before
	public void setUp() throws Exception {
		smartList1 = new SmartArrayList<Integer>(9, 56, 23, 11, 67, 12, 9, 10);
		smartList2 = new SmartArrayList<Integer>(87, 13, 11, 56, 85, 19);
	}

	@Test
	public void testHead() {
		assertEquals(Integer.valueOf(9), smartList1.head());
	}

	@Test(expected = NoSuchElementException.class)
	public void testHeadFail() {
		new SmartArrayList<Object>().head();
	}

	@Test
	public void testTail() {
		assertEquals(new SmartArrayList<Integer>(56, 23, 11, 67, 12, 9, 10), smartList1.tail());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testTailFail() {
		new SmartArrayList<Object>().tail();
	}

	@Test
	public void testLast() {
		assertEquals(new Integer(10), smartList1.last());
	}

	@Test(expected = NoSuchElementException.class)
	public void testLastFail() {
		new SmartArrayList<Object>().last();
	}

	@Test
	public void testGet() {
		assertEquals(Integer.valueOf(11), smartList1.get(3, -100));
		assertEquals(Integer.valueOf(-100), smartList1.get(20, -100));
	}

	@Test
	public void testTake() {
		assertEquals(new SmartArrayList<Integer>(87, 13, 11), smartList2.take(3));
		assertEquals(new SmartArrayList<Integer>(1, 2), new SmartArrayList<Integer>(1, 2).take(8));
		assertEquals(new SmartArrayList<Integer>(), new SmartArrayList<Integer>().take(8));
	}

	@Test
	public void testDrop() {
		assertEquals(new SmartArrayList<Integer>(67, 12, 9, 10), smartList1.drop(4));
		assertEquals(new SmartArrayList<Integer>(), smartList1.drop(50));
	}

	@Test
	public void testTakeWhile() {
		assertEquals(new SmartArrayList<Integer>(87, 13, 11),
				smartList2.takeWhile(new Predicate<Integer>() {
					@Override
					public boolean test(final Integer input) {
						return input % 2 == 1;
					}
				}));
	}

	@Test
	public void testDropWhile() {
		assertEquals(new SmartArrayList<Integer>(56, 85, 19),
				smartList2.dropWhile(new Predicate<Integer>() {
					@Override
					public boolean test(final Integer input) {
						return input % 2 == 1;
					}
				}));
	}

	@Test
	public void testRemoveDuplicates() {
		SmartList<Integer> testList = new SmartArrayList<Integer>(1, 1, 1, 2, 3, 3, 3, 4, 5);
		assertEquals(new SmartArrayList<Integer>(1, 2, 3, 4, 5), testList.removeDuplicates());
	}

	@Test
	public void testIntersperse() {
		SmartList<Integer> testList = new SmartArrayList<Integer>(1, 2, 3);

		assertEquals(new SmartArrayList<Integer>(1, 0, 2, 0, 3), testList.intersperse(0));
		assertEquals(new SmartArrayList<Integer>(), new SmartArrayList<Integer>().intersperse(0));
	}

	@Test
	public void testZipWith() {
		SmartMap<Integer, String> resultMap = new SmartArrayList<Integer>(4, 5, 6)
				.zipWith(new SmartArrayList<String>("foo", "bar", "baz"));

		SmartMap<Integer, String> expectedMap = new SmartHashMap<Integer, String>();
		expectedMap.put(4, "foo");
		expectedMap.put(5, "bar");
		expectedMap.put(6, "baz");

		assertEquals(expectedMap, resultMap);
	}

	@Test
	public void testGetIndicesList() {
		assertEquals(new SmartArrayList<Integer>(0, 1, 2, 3, 4), new SmartArrayList<String>("a",
				"b", "c", "d", "e").getIndicesList());
	}

	@Test
	public void testGetOccurenceCountMap() {
		SmartMap<Character, Integer> resultMap = new SmartArrayList<Character>('a', 'a', 'c', 'a',
				'c', 'x').getOccurenceCountMap();

		SmartMap<Character, Integer> expectedMap = new SmartHashMap<Character, Integer>();
		expectedMap.put('a', 3);
		expectedMap.put('c', 2);
		expectedMap.put('x', 1);

		assertEquals(expectedMap, resultMap);
	}

	@Test
	public void testReverse() {
		SmartList<Integer> testList = new SmartArrayList<Integer>(2, 3, 1);
		assertEquals(new SmartArrayList<Integer>(1, 3, 2), testList.reverse());
		assertEquals(new SmartArrayList<Integer>(2, 3, 1), testList.reverse());
	}

	@Test
	public void testSizeWithoutNulls() {
		assertEquals(5, new SmartArrayList<Integer>(1, null, 3, 2, null).size());
		assertEquals(3, new SmartArrayList<Integer>(1, null, 3, 2, null).sizeWithoutNulls());
	}
}
