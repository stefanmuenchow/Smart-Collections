package com.stefanmuenchow.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Strategy for minimum operation.
 * 
 * @author Stefan Münchow
 */
public class Minimum implements BinaryOperation {
	private static Minimum INSTANCE = null;
	
	private Minimum() { }
	
	/**
	 * Gets the singleton instance of this class.
	 * 
	 * @return	Singleton instance
	 */
	public static Minimum getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Minimum();
		}
		
		return INSTANCE;
	}

	@Override
	public Integer apply(Integer a, Integer b) {
		return a.compareTo(b) < 0 ? a : b;
	}

	@Override
	public Long apply(Long a, Long b) {
		return a.compareTo(b) < 0 ? a : b;
	}

	@Override
	public Short apply(Short a, Short b) {
		return a.compareTo(b) < 0 ? a : b;
	}

	@Override
	public Byte apply(Byte a, Byte b) {
		return a.compareTo(b) < 0 ? a : b;
	}

	@Override
	public Double apply(Double a, Double b) {
		return a.compareTo(b) < 0 ? a : b;
	}

	@Override
	public Float apply(Float a, Float b) {
		return a.compareTo(b) < 0 ? a : b;
	}

	@Override
	public BigDecimal apply(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) < 0 ? a : b;
	}

	@Override
	public BigInteger apply(BigInteger a, BigInteger b) {
		return a.compareTo(b) < 0 ? a : b;
	}
}
