package com.stefanmuenchow.collections;


import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.list.ISmartList;
import com.stefanmuenchow.collections.list.SmartList;

public class SmartListTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testFlatten() {
		ISmartList<ISmartList<ISmartList<String>>> deepList = new SmartList<ISmartList<ISmartList<String>>>();
		ISmartList<ISmartList<String>> twoDimList1 = new SmartList<ISmartList<String>>();
		ISmartList<ISmartList<String>> twoDimList2 = new SmartList<ISmartList<String>>();
		ISmartList<String> oneDimList1 = new SmartList<String>();
		ISmartList<String> oneDimList2 = new SmartList<String>();
		ISmartList<String> oneDimList3 = new SmartList<String>();
		
		oneDimList1.add("Test");
		oneDimList1.add("one");
		oneDimList1.add("two");
		
		oneDimList2.add("Test2");
		oneDimList2.add("one2");
		oneDimList2.add("two2");
		
		oneDimList3.add("Test3");
		oneDimList3.add("one3");
		oneDimList3.add("two3");
		
		twoDimList1.add(oneDimList1);
		twoDimList1.add(oneDimList2);
		
		twoDimList2.add(oneDimList1);
		twoDimList2.add(oneDimList3);
		
		deepList.add(twoDimList1);
		deepList.add(twoDimList2);
		
		ISmartList<String> expected = new SmartList<String>(Arrays.asList(new String[] {"Test", "one", "two",
				"Test2", "one2", "two2", "Test", "one", "two", "Test3", "one3", "two3"}));
		
		Assert.assertEquals(expected, deepList.flatten());
	}
}
