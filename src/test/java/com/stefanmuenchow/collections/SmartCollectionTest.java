package com.stefanmuenchow.collections;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public class SmartCollectionTest {
	private SmartList<Integer> list;
	private SmartSet<Integer> set;
	private SmartQueue<Integer> queue;
	
	private Predicate<Integer> greaterThan3 = new Predicate<Integer>() {
		@Override
		public boolean test(Integer input) {
			return input.compareTo(3) > 0;
		}
	};
	
	private Predicate<Integer> smallerThan0 = new Predicate<Integer>() {
		@Override
		public boolean test(Integer input) {
			return input.compareTo(0) < 0;
		}
	};
	
	@Before
	public void setUp() throws Exception {
		list = new SmartArrayList<Integer>(1,2,3,4,5);
		set = new SmartHashSet<Integer>(1,2,3,4,5);
		queue = new SmartLinkedQueue<Integer>(1,2,3,4,5);
	}
	
	@Test
	public void testAddReturn() {
		assertEquals(new SmartArrayList<Integer>(1,2,3,4,5,6), list.addReturn(6));
		assertEquals(new SmartHashSet<Integer>(1,2,3,4,5,6), set.addReturn(6));
		assertEquals(new SmartLinkedQueue<Integer>(1,2,3,4,5,6), queue.addReturn(6));
	}

	@Test
	public void testAddAllReturn() {
		List<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(6);
		toAdd.add(7);

		assertEquals(new SmartArrayList<Integer>(1,2,3,4,5,6,7), list.addAllReturn(toAdd));
		assertEquals(new SmartHashSet<Integer>(1,2,3,4,5,6,7), set.addAllReturn(toAdd));
		assertEquals(new SmartLinkedQueue<Integer>(1,2,3,4,5,6,7), queue.addAllReturn(toAdd));
	}

	@Test
	public void testRemoveReturn() {
		assertEquals(new SmartArrayList<Integer>(1,2,4,5), list.removeReturn(3));
		assertEquals(new SmartHashSet<Integer>(1,2,4,5), set.removeReturn(3));
		assertEquals(new SmartLinkedQueue<Integer>(1,2,4,5), queue.removeReturn(3));
	}

	@Test
	public void testRemoveAllReturn() {
		List<Integer> toRemove = new ArrayList<Integer>();
		toRemove.add(1);
		toRemove.add(3);

		assertEquals(new SmartArrayList<Integer>(2,4,5), list.removeAllReturn(toRemove));
		assertEquals(new SmartHashSet<Integer>(2,4,5), set.removeAllReturn(toRemove));
		assertEquals(new SmartLinkedQueue<Integer>(2,4,5), queue.removeAllReturn(toRemove));
	}

	@Test
	public void testRetainAllReturn() {
		List<Integer> toRetain = new ArrayList<Integer>();
		toRetain.add(2);
		toRetain.add(4);
		toRetain.add(5);

		assertEquals(new SmartArrayList<Integer>(2,4,5), list.retainAllReturn(toRetain));
		assertEquals(new SmartHashSet<Integer>(2,4,5), set.retainAllReturn(toRetain));
		assertEquals(new SmartLinkedQueue<Integer>(2,4,5), queue.retainAllReturn(toRetain));
	}
	
	@Test
	public void testFind() {
		int listRes = list.find(greaterThan3);
		assertEquals(4, listRes);
		
		int setRes = set.find(greaterThan3);
		assertEquals(4, setRes);
		
		int queueRes = queue.find(greaterThan3);
		assertEquals(4, queueRes);
	}

	@Test
	public void testFindFail() {
		int numExceptions = 0;
		
		try {
			list.find(smallerThan0);
		} catch (NoSuchElementException ex) {
			numExceptions++;
		}
		
		try {
			set.find(smallerThan0);
		} catch (NoSuchElementException ex) {
			numExceptions++;
		}
		
		try {
			queue.find(smallerThan0);
		} catch (NoSuchElementException ex) {
			numExceptions++;
		}
		
		assertEquals(3, numExceptions);
	}

	@Test
	public void testFilter() {
		assertEquals(new SmartArrayList<Integer>(4,5), list.filter(greaterThan3));
		assertEquals(new SmartHashSet<Integer>(4,5), set.filter(greaterThan3));
		assertEquals(new SmartLinkedQueue<Integer>(4,5), queue.filter(greaterThan3));
	}

	@Test
	public void testRemove() {
		assertEquals(new SmartArrayList<Integer>(1,2,3), list.remove(greaterThan3));
		assertEquals(new SmartHashSet<Integer>(1,2,3), set.remove(greaterThan3));
		assertEquals(new SmartLinkedQueue<Integer>(1,2,3), queue.remove(greaterThan3));
	}

	@Test
	public void testReplaceExplicitValue() {
		assertEquals(new SmartArrayList<Integer>(1,2,10,4,5), list.replace(3, 10));
		assertEquals(new SmartHashSet<Integer>(1,2,10,4,5), set.replace(3, 10));
		assertEquals(new SmartLinkedQueue<Integer>(1,2,10,4,5), queue.replace(3, 10));
	}
	
	@Test
	public void testReplacePredicate() {
		assertEquals(new SmartArrayList<Integer>(1,2,3,10,10), list.replace(greaterThan3, 10));
		assertEquals(new SmartHashSet<Integer>(1,2,3,10), set.replace(greaterThan3, 10));
		assertEquals(new SmartLinkedQueue<Integer>(1,2,3,10,10), queue.replace(greaterThan3, 10));
	}
	
	@Test
	public void testReplaceWithMap() {
		Map<Integer, Integer> replacements = new HashMap<Integer, Integer>();
		replacements.put(3, 5);
		replacements.put(5, 10);
		
		assertEquals(new SmartArrayList<Integer>(1,2,5,4,10), list.replace(replacements));
		assertEquals(new SmartHashSet<Integer>(1,2,5,4,10), set.replace(replacements));
		assertEquals(new SmartLinkedQueue<Integer>(1,2,5,4,10), queue.replace(replacements));
	}

	@Test
	public void testMap() {
		UnaryFunction<String, Integer> toStr = new UnaryFunction<String, Integer>() {
			@Override
			public String apply(final Integer input) {
				return String.valueOf(input);
			}
		};
		
		assertEquals(new SmartArrayList<String>("1","2","3","4","5"), list.map(toStr));
		assertEquals(new SmartHashSet<String>("1","2","3","4","5"), set.map(toStr));
		assertEquals(new SmartLinkedQueue<String>("1","2","3","4","5"), queue.map(toStr));
	}

	@Test
	public void testReduce() {
		BinaryFunction<Integer, Integer> addInteger = new BinaryFunction<Integer, Integer>() {
			@Override
			public Integer apply(final Integer input1, final Integer input2) {
				return input1 + input2;
			}
		};
		
		assertEquals(Integer.valueOf(15), list.reduce(addInteger));
		assertEquals(Integer.valueOf(15), set.reduce(addInteger));
		assertEquals(Integer.valueOf(15), queue.reduce(addInteger));
		
		BinaryFunction<String, Integer> appendStr = new BinaryFunction<String, Integer>() {
			@Override
			public String apply(final String input1, final Integer input2) {
				return input1 + String.valueOf(input2);
			}
		};
		
		assertEquals("L: 12345", list.reduce("L: ", appendStr));
		assertEquals("S: 12345", set.reduce("S: ", appendStr));
		assertEquals("Q: 12345", queue.reduce("Q: ", appendStr));
	}

	@Test
	public void testJoin() {
		assertEquals("1 2 3 4 5", list.join(" "));
		assertEquals("1 2 3 4 5", queue.join(" "));		
		assertEquals("1 2 3 4 5".length(), set.join(" ").length());
	}

	@Test
	public void testCount() {
		assertEquals(2, list.count(greaterThan3));
		assertEquals(2, set.count(greaterThan3));
		assertEquals(2, queue.count(greaterThan3));
	}

	@Test
	public void testExists() {
		assertTrue(list.exists(greaterThan3));
		assertTrue(set.exists(greaterThan3));
		assertTrue(queue.exists(greaterThan3));
		
		assertFalse(list.exists(smallerThan0));
		assertFalse(set.exists(smallerThan0));
		assertFalse(queue.exists(smallerThan0));
	}

	@Test
	public void testForall() {
		Predicate<Integer> positive = new Predicate<Integer>() {
			@Override
			public boolean test(Integer input) {
				return input.compareTo(0) > 0;
			}
		};
		
		assertTrue(list.forall(positive));
		assertTrue(set.forall(positive));
		assertTrue(queue.forall(positive));
		
		assertFalse(list.forall(greaterThan3));
		assertFalse(set.forall(greaterThan3));
		assertFalse(queue.forall(greaterThan3));
	}

	@Test
	public void testCastEach() {
		assertEquals(new SmartArrayList<Object>(1,2,3,4,5), list.castEach(Object.class));
		assertEquals(new SmartHashSet<Object>(1,2,3,4,5), set.castEach(Object.class));
		assertEquals(new SmartLinkedQueue<Object>(1,2,3,4,5), queue.castEach(Object.class));
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new Integer[] {1,2,3,4,5}, list.toArray(Integer.class));
		assertArrayEquals(new Integer[] {1,2,3,4,5}, queue.toArray(Integer.class));
		assertEquals(5, set.toArray(Integer.class).length);
	}
	
	@Test
	public void testToStandardCollection() {
		assertEquals(new ArrayList<Integer>(list), list.toStandardCollection());
		assertEquals(new HashSet<Integer>(list), set.toStandardCollection());
		assertEquals(new LinkedList<Integer>(list), queue.toStandardCollection());
	}
}
