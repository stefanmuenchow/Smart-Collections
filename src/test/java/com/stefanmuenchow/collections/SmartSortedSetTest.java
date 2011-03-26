package com.stefanmuenchow.collections;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SmartSortedSetTest {
	private SmartSortedSet<Integer> smartSortedSet = null;

	@Before
	public void setUp() throws Exception {
		smartSortedSet = new SmartTreeSet<Integer>(4, 8, 12, 3, 1, 9);
	}
	
	@Test
	public void testSmartSubSet() {
		assertEquals(new SmartTreeSet<Integer>(8, 9, 12), smartSortedSet.smartSubSet(8, 15));
	}
	
	@Test
	public void testSmartHeadSet() {
		assertEquals(new SmartTreeSet<Integer>(1, 3, 4), smartSortedSet.smartHeadSet(5));
	}
	
	@Test
	public void testSmartTailSet() {
		assertEquals(new SmartTreeSet<Integer>(9, 12), smartSortedSet.smartTailSet(9));
	}
}
