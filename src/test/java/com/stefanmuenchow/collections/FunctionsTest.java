package com.stefanmuenchow.collections;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import com.stefanmuenchow.collections.function.Functions;

public class FunctionsTest {
	
	@Test
	public void testToStringFn() {
		assertEquals(new SmartArrayList<String>("1", "2", "3"), 
				new SmartArrayList<Integer>(1, 2, 3).map(Functions.toStringFn()));
	}

	@Test
	public void testAddFn() {
		assertEquals(Integer.valueOf(6), new SmartArrayList<Integer>(1, 2, 3).reduce(Functions.addFn()));
		assertEquals(Long.valueOf(11l), new SmartArrayList<Long>(8l, 3l).reduce(Functions.addFn()));
		assertEquals(Byte.valueOf((byte) 14), new SmartArrayList<Byte>((byte)7, (byte)7).reduce(Functions.addFn()));
		assertEquals(Short.valueOf((short) 9), new SmartArrayList<Short>((short)4, (short)5).reduce(Functions.addFn()));
		assertEquals(Double.valueOf(3.8d), new SmartArrayList<Double>(1.4, 2.4).reduce(Functions.addFn()));
		assertEquals(Float.valueOf(4f), new SmartArrayList<Float>(1.5f, 2.5f).reduce(Functions.addFn()));
		assertEquals(BigDecimal.valueOf(10.1), new SmartArrayList<BigDecimal>(BigDecimal.valueOf(4.1), BigDecimal.valueOf(6)).reduce(Functions.addFn()));
		assertEquals(BigInteger.valueOf(9l), new SmartArrayList<BigInteger>(BigInteger.valueOf(3), BigInteger.valueOf(6)).reduce(Functions.addFn()));
	}
	
	@Test
	public void testSubtractFn() {
		assertEquals(Integer.valueOf(-4), new SmartArrayList<Integer>(1, 2, 3).reduce(Functions.subtractFn()));
		assertEquals(Long.valueOf(5l), new SmartArrayList<Long>(8l, 3l).reduce(Functions.subtractFn()));
		assertEquals(Byte.valueOf((byte) 0), new SmartArrayList<Byte>((byte)7, (byte)7).reduce(Functions.subtractFn()));
		assertEquals(Short.valueOf((short) -1), new SmartArrayList<Short>((short)4, (short)5).reduce(Functions.subtractFn()));
		assertEquals(Double.valueOf(-1.0d), new SmartArrayList<Double>(1.4, 2.4).reduce(Functions.subtractFn()));
		assertEquals(Float.valueOf(-1.0f), new SmartArrayList<Float>(1.5f, 2.5f).reduce(Functions.subtractFn()));
		assertEquals(BigDecimal.valueOf(-1.9), new SmartArrayList<BigDecimal>(BigDecimal.valueOf(4.1), BigDecimal.valueOf(6)).reduce(Functions.subtractFn()));
		assertEquals(BigInteger.valueOf(-3l), new SmartArrayList<BigInteger>(BigInteger.valueOf(3), BigInteger.valueOf(6)).reduce(Functions.subtractFn()));
	}

	
	@Test
	public void testMultiplyFn() {
		assertEquals(Integer.valueOf(6), new SmartArrayList<Integer>(1, 2, 3).reduce(Functions.multiplyFn()));
		assertEquals(Long.valueOf(24l), new SmartArrayList<Long>(8l, 3l).reduce(Functions.multiplyFn()));
		assertEquals(Byte.valueOf((byte) 49), new SmartArrayList<Byte>((byte)7, (byte)7).reduce(Functions.multiplyFn()));
		assertEquals(Short.valueOf((short) 20), new SmartArrayList<Short>((short)4, (short)5).reduce(Functions.multiplyFn()));
		assertEquals(Double.valueOf(3.36d), new SmartArrayList<Double>(1.4, 2.4).reduce(Functions.multiplyFn()));
		assertEquals(Float.valueOf(3.75f), new SmartArrayList<Float>(1.5f, 2.5f).reduce(Functions.multiplyFn()));
		assertEquals(BigDecimal.valueOf(24), new SmartArrayList<BigDecimal>(BigDecimal.valueOf(4), BigDecimal.valueOf(6)).reduce(Functions.multiplyFn()));
		assertEquals(BigInteger.valueOf(18l), new SmartArrayList<BigInteger>(BigInteger.valueOf(3), BigInteger.valueOf(6)).reduce(Functions.multiplyFn()));
	}

	
	@Test
	public void testDivideFn() {
		assertEquals(Integer.valueOf(0), new SmartArrayList<Integer>(1, 2).reduce(Functions.divideFn()));
		assertEquals(Long.valueOf(2l), new SmartArrayList<Long>(8l, 3l).reduce(Functions.divideFn()));
		assertEquals(Byte.valueOf((byte) 1), new SmartArrayList<Byte>((byte)7, (byte)7).reduce(Functions.divideFn()));
		assertEquals(Short.valueOf((short) 0), new SmartArrayList<Short>((short)4, (short)5).reduce(Functions.divideFn()));
		assertEquals(Double.valueOf(3d), new SmartArrayList<Double>(1.5, 0.5).reduce(Functions.divideFn()));
		assertEquals(Float.valueOf(3f), new SmartArrayList<Float>(1.5f, 0.5f).reduce(Functions.divideFn()));
		assertEquals(BigDecimal.valueOf(2), new SmartArrayList<BigDecimal>(BigDecimal.valueOf(12), BigDecimal.valueOf(6)).reduce(Functions.divideFn()));
		assertEquals(BigInteger.valueOf(2l), new SmartArrayList<BigInteger>(BigInteger.valueOf(6), BigInteger.valueOf(3)).reduce(Functions.divideFn()));
	}
}
