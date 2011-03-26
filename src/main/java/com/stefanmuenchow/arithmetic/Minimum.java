package com.stefanmuenchow.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Minimum implements BinaryOperation {
	public static final Minimum INSTANCE = new Minimum();
	private Minimum() { }

	@Override
	public Integer apply(Integer a, Integer b) {
		return Comparison.INSTANCE.apply(a, b) < 0 ? a : b;
	}

	@Override
	public Long apply(Long a, Long b) {
		return Comparison.INSTANCE.apply(a, b) < 0 ? a : b;
	}

	@Override
	public Short apply(Short a, Short b) {
		return Comparison.INSTANCE.apply(a, b) < 0 ? a : b;
	}

	@Override
	public Byte apply(Byte a, Byte b) {
		return Comparison.INSTANCE.apply(a, b) < 0 ? a : b;
	}

	@Override
	public Double apply(Double a, Double b) {
		return Comparison.INSTANCE.apply(a, b) < 0 ? a : b;
	}

	@Override
	public Float apply(Float a, Float b) {
		return Comparison.INSTANCE.apply(a, b) < 0 ? a : b;
	}

	@Override
	public BigDecimal apply(BigDecimal a, BigDecimal b) {
		return Comparison.INSTANCE.apply(a, b).intValue() < 0 ? a : b;
	}

	@Override
	public BigInteger apply(BigInteger a, BigInteger b) {
		return Comparison.INSTANCE.apply(a, b).intValue() < 0 ? a : b;
	}
}
