package com.stefanmuenchow.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Subtraction implements Operation {
	public static final Subtraction INSTANCE = new Subtraction();	
	private Subtraction() { }

	@Override
	public Integer apply(Integer a, Integer b) {
		return Integer.valueOf(a - b);
	}

	@Override
	public Long apply(Long a, Long b) {
		return Long.valueOf(a - b);
	}

	@Override
	public Short apply(Short a, Short b) {
		return Short.valueOf((short) (a - b));
	}

	@Override
	public Byte apply(Byte a, Byte b) {
		return Byte.valueOf((byte) (a - b));
	}

	@Override
	public Double apply(Double a, Double b) {
		return Double.valueOf(a - b);
	}

	@Override
	public Float apply(Float a, Float b) {
		return Float.valueOf(a - b);
	}

	@Override
	public BigDecimal apply(BigDecimal a, BigDecimal b) {
		return a.subtract(b);
	}

	@Override
	public BigInteger apply(BigInteger a, BigInteger b) {
		return a.subtract(b);
	}
}
