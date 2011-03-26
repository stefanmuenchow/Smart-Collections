package com.stefanmuenchow.collections;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SmartSortedMapTest {
	private SmartSortedMap<Integer, String> smartSortedMap = null;
	
	@Before
	public void setUp() throws Exception {
		smartSortedMap = new SmartTreeMap<Integer, String>(
				new SmartArrayList<Integer>(10, 6, 1, 3),
				new SmartArrayList<String>("Red", "Blue", "Green", "Orange"));
	}
	
	@Test
	public void testSmartSubMap() {
		SmartSortedMap<Integer, String> expected = new SmartTreeMap<Integer, String>(
				new SmartArrayList<Integer>(3, 6),
				new SmartArrayList<String>("Orange", "Blue"));
		
		assertEquals(expected, smartSortedMap.smartSubMap(3, 8));
	}
	
	@Test
	public void testSmartHeadMap() {
		SmartSortedMap<Integer, String> expected = new SmartTreeMap<Integer, String>(
				new SmartArrayList<Integer>(1, 3),
				new SmartArrayList<String>("Green", "Orange"));
		
		assertEquals(expected, smartSortedMap.smartHeadMap(5));
	}
	
	@Test
	public void testSmartTailMap() {
		SmartSortedMap<Integer, String> expected = new SmartTreeMap<Integer, String>(
				new SmartArrayList<Integer>(6, 10),
				new SmartArrayList<String>("Blue", "Red"));
		
		assertEquals(expected, smartSortedMap.smartTailMap(6));
	}
}
