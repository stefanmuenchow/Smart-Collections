package com.stefanmuenchow.collections.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface Operation {
	Integer apply(Integer a, Integer b);
	Long apply(Long a, Long b);
	Short apply(Short a, Short b);
	Byte apply(Byte a, Byte b);
	Double apply(Double a, Double b);
	Float apply(Float a, Float b);
	BigDecimal apply(BigDecimal a, BigDecimal b);
	BigInteger apply(BigInteger a, BigInteger b);
}
