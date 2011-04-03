package com.stefanmuenchow.collections;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import java.math.BigInteger;

import org.junit.Test;

import com.stefanmuenchow.functors.Predicates;

public class PredicatesTest {

	@Test
	public void testEvenPred() {
		assertTrue(Predicates.evenPred().test(4));
		assertTrue(Predicates.evenPred().test(2.0));
		assertTrue(Predicates.evenPred().test(BigInteger.valueOf(10l)));
		
		assertFalse(Predicates.evenPred().test(7));
		assertFalse(Predicates.evenPred().test(3.2));
		assertFalse(Predicates.evenPred().test(BigInteger.valueOf(9)));
	}
	
	@Test
	public void testOddPred() {
		assertTrue(Predicates.oddPred().test(3));
		assertTrue(Predicates.oddPred().test(5.1));
		assertTrue(Predicates.oddPred().test(BigInteger.valueOf(1l)));
		
		assertFalse(Predicates.oddPred().test(10));
		assertFalse(Predicates.oddPred().test(8));
		assertFalse(Predicates.oddPred().test(BigInteger.valueOf(2)));
	}
	
	@Test
	public void testStringContainsPred() {
		assertTrue(Predicates.stringContainsPred("World").test("Hello World!"));
		assertFalse(Predicates.stringContainsPred("Banana").test("Hello World!"));
	}
	
	@Test
	public void testCollectionContainsPred() {
		SmartList<String> aList = new SmartArrayList<String>("This", "is", "cool");
		assertTrue(Predicates.containsPred("cool").test(aList));
		assertFalse(Predicates.containsPred("bad").test(aList));
	}
	
	@Test
	public void testEqualsPred() {
		assertTrue(Predicates.equalsPred("aString").test("aString"));
		assertFalse(Predicates.equalsPred("aString").test("anInteger"));
	}
	
	@Test
	public void testLessThanPred() {
		assertTrue(Predicates.lessThanPred(3).test(2));
		assertFalse(Predicates.lessThanPred(3).test(3));
		assertFalse(Predicates.lessThanPred(3).test(4));
	}
	
	@Test
	public void testLessEqualThanPred() {
		assertTrue(Predicates.lessEqualThanPred(3).test(2));
		assertTrue(Predicates.lessEqualThanPred(3).test(3));
		assertFalse(Predicates.lessEqualThanPred(3).test(4));
	}
	
	@Test
	public void testGreaterThanPred() {
		assertFalse(Predicates.greaterThanPred(3).test(2));
		assertFalse(Predicates.greaterThanPred(3).test(3));
		assertTrue(Predicates.greaterThanPred(3).test(4));
	}
	
	@Test
	public void testGreaterEqualThanPred() {
		assertFalse(Predicates.greaterEqualThanPred(3).test(2));
		assertTrue(Predicates.greaterEqualThanPred(3).test(3));
		assertTrue(Predicates.greaterEqualThanPred(3).test(4));
	}
}
