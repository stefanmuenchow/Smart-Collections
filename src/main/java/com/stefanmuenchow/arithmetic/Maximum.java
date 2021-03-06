package com.stefanmuenchow.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Strategy for maximum operation.
 * 
 * @author Stefan Münchow
 */
public class Maximum implements BinaryOperation {
	private static Maximum INSTANCE = null;
	
	private Maximum() { }
	
	/**
	 * Gets the singleton instance of this class.
	 * 
	 * @return	Singleton instance
	 */
	public static Maximum getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Maximum();
		}
		
		return INSTANCE;
	}

	@Override
	public Integer apply(Integer a, Integer b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	@Override
	public Long apply(Long a, Long b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	@Override
	public Short apply(Short a, Short b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	@Override
	public Byte apply(Byte a, Byte b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	@Override
	public Double apply(Double a, Double b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	@Override
	public Float apply(Float a, Float b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	@Override
	public BigDecimal apply(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	@Override
	public BigInteger apply(BigInteger a, BigInteger b) {
		return a.compareTo(b) >= 0 ? a : b;
	}
}
