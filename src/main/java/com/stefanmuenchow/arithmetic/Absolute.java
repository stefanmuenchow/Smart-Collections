package com.stefanmuenchow.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Strategy for absolute operation.
 * 
 * @author Stefan Münchow
 */
public class Absolute implements UnaryOperation {
	public static final Absolute INSTANCE = new Absolute();
	private Absolute() { }
	
	@Override
	public Integer apply(Integer a) {
		return Math.abs(a);
	}

	@Override
	public Long apply(Long a) {
		return Math.abs(a);
	}

	@Override
	public Short apply(Short a) {
		return Integer.valueOf(Math.abs(a)).shortValue();
	}

	@Override
	public Byte apply(Byte a) {
		return Integer.valueOf(Math.abs(a)).byteValue();
	}

	@Override
	public Double apply(Double a) {
		return Math.abs(a);
	}

	@Override
	public Float apply(Float a) {
		return Math.abs(a);
	}

	@Override
	public BigDecimal apply(BigDecimal a) {
		return a.abs();
	}

	@Override
	public BigInteger apply(BigInteger a) {
		return a.abs();
	}
}
