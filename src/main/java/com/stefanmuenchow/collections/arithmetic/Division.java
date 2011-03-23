package com.stefanmuenchow.collections.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Division implements Operation {
	public static final Division INSTANCE = new Division();	
	private Division() { }

	@Override
	public Integer apply(Integer a, Integer b) {
		return Integer.valueOf(a / b);
	}

	@Override
	public Long apply(Long a, Long b) {
		return Long.valueOf(a / b);
	}

	@Override
	public Short apply(Short a, Short b) {
		return Short.valueOf((short) (a / b));
	}

	@Override
	public Byte apply(Byte a, Byte b) {
		return Byte.valueOf((byte) (a / b));
	}

	@Override
	public Double apply(Double a, Double b) {
		return Double.valueOf(a / b);
	}

	@Override
	public Float apply(Float a, Float b) {
		return Float.valueOf(a / b);
	}

	@Override
	public BigDecimal apply(BigDecimal a, BigDecimal b) {
		return a.divide(b);
	}

	@Override
	public BigInteger apply(BigInteger a, BigInteger b) {
		return a.divide(b);
	}
}
