package com.stefanmuenchow.collections;

import java.util.Arrays;

import com.stefanmuenchow.collections.list.ISmartList;
import com.stefanmuenchow.collections.list.SmartArrayList;
import com.stefanmuenchow.collections.list.SmartLinkedList;

import junit.framework.TestCase;

public class SmartListTest extends TestCase {
	private ISmartList<Integer> arrayList1 = null;
	private ISmartList<Integer> arrayList2 = null;
	private ISmartList<Integer> linkedList1 = null;
	private ISmartList<Integer> linkedList2 = null;

	protected void setUp() throws Exception {
		arrayList1 = new SmartArrayList<Integer>(4, 10, 5, 3, 7, 4, 9, 1);
		arrayList2 = new SmartArrayList<Integer>(5, 3, 4, 1);
		linkedList1 = new SmartLinkedList<Integer>(4, 10, 5, 3, 7, 4, 9, 1);
		linkedList2 = new SmartLinkedList<Integer>(5, 3, 4, 1);
	}

	public void testSubSmartList() {
		assertEquals(new SmartArrayList<Integer>(10, 5, 3), arrayList1.subSmartList(1, 4));
		assertEquals(new SmartLinkedList<Integer>(10, 5, 3), linkedList1.subSmartList(1, 4));
	}

	public void testAddElem() {
		assertEquals(new SmartArrayList<Integer>(5, 3, 4, 1, 6), arrayList2.addElem(6));
		assertEquals(new SmartArrayList<Integer>(5, 3, 12, 4, 1, 6), arrayList2.addElem(2, 12));	
		assertEquals(new SmartLinkedList<Integer>(5, 3, 4, 1, 6), linkedList2.addElem(6));
		assertEquals(new SmartLinkedList<Integer>(5, 3, 12, 4, 1, 6), linkedList2.addElem(2, 12));
	}

	public void testAddAllElems() {
		assertEquals(new SmartArrayList<Integer>(5, 3, 4, 1, 12, 13), arrayList2.addAllElems(12, 13));
		assertEquals(new SmartArrayList<Integer>(4, 10, 5, 3, 7, 4, 9, 1, 14, 15), 
				arrayList1.addAllElems(Arrays.asList(new Integer[] {14, 15})));
		assertEquals(new SmartLinkedList<Integer>(5, 3, 4, 1, 12, 13), linkedList2.addAllElems(12, 13));
		assertEquals(new SmartLinkedList<Integer>(4, 10, 5, 3, 7, 4, 9, 1, 14, 15), 
				linkedList1.addAllElems(Arrays.asList(new Integer[] {14, 15})));
	}

	public void testRemoveElem() {
		assertEquals(new SmartArrayList<Integer>(10, 5, 3, 7, 4, 1), 
				arrayList1.removeElem(0).removeElem(Integer.valueOf(9)));		
		assertEquals(new SmartLinkedList<Integer>(10, 5, 3, 7, 4, 1), 
				linkedList1.removeElem(0).removeElem(Integer.valueOf(9)));
	}

	public void testSetElem() {
		assertEquals(new SmartArrayList<Integer>(5, 10, 4, 1), arrayList2.setElem(1, 10));
		assertEquals(new SmartLinkedList<Integer>(5, 10, 4, 1), linkedList2.setElem(1, 10));
	}

	public void testRemoveAllElems() {
		assertEquals(new SmartArrayList<Integer>(10, 3, 7), 
				arrayList1.removeAllElems(4, 5).removeAllElems(Arrays.asList(new Integer[] {9, 1})));
		assertEquals(new SmartLinkedList<Integer>(10, 3, 7), 
				linkedList1.removeAllElems(4, 5).removeAllElems(Arrays.asList(new Integer[] {9, 1})));
	}

	public void testRetainAllElems() {
		assertEquals(new SmartArrayList<Integer>(10, 5, 3), arrayList1.retainAllElems(10, 5, 3, 12));
		assertEquals(new SmartArrayList<Integer>(3), arrayList2.retainAllElems(Arrays.asList(new Integer[] {3, 10})));
		assertEquals(new SmartLinkedList<Integer>(10, 5, 3), linkedList1.retainAllElems(10, 5, 3, 12));
		assertEquals(new SmartLinkedList<Integer>(3), linkedList2.retainAllElems(Arrays.asList(new Integer[] {3, 10})));
	}

	public void testClearAll() {
		assertEquals(new SmartArrayList<Integer>(), arrayList1.clearAll());
		assertEquals(0, arrayList1.size());
		assertEquals(new SmartLinkedList<Integer>(), linkedList1.clearAll());
		assertEquals(0, linkedList1.size());
	}
}
