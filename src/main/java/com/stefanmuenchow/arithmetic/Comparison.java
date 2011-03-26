package com.stefanmuenchow.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Comparison implements BinaryOperation {
	public static final Comparison INSTANCE = new Comparison();
	private Comparison() { }

	@Override
	public Integer apply(Integer a, Integer b) {
		return a.compareTo(b);
	}

	@Override
	public Long apply(Long a, Long b) {
		return Integer.valueOf(a.compareTo(b)).longValue();
	}

	@Override
	public Short apply(Short a, Short b) {
		return Integer.valueOf(a.compareTo(b)).shortValue();
	}

	@Override
	public Byte apply(Byte a, Byte b) {
		return Integer.valueOf(a.compareTo(b)).byteValue();
	}

	@Override
	public Double apply(Double a, Double b) {
		return Integer.valueOf(a.compareTo(b)).doubleValue();
	}

	@Override
	public Float apply(Float a, Float b) {
		return Integer.valueOf(a.compareTo(b)).floatValue();
	}

	@Override
	public BigDecimal apply(BigDecimal a, BigDecimal b) {
		return BigDecimal.valueOf(Integer.valueOf(a.compareTo(b)).doubleValue());
	}

	@Override
	public BigInteger apply(BigInteger a, BigInteger b) {
		return BigInteger.valueOf(Integer.valueOf(a.compareTo(b)).longValue());
	}

}
