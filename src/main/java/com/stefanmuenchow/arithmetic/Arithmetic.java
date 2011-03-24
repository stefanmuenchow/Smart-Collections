package com.stefanmuenchow.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidParameterException;

public class Arithmetic {
	
	public static <T extends Number> T add(T a, T b) {
		return genericOp(a, b, Addition.INSTANCE);
	}
	
	public static <T extends Number> T sub(T a, T b) {
		return genericOp(a, b, Subtraction.INSTANCE);
	}
	
	public static <T extends Number> T mul(T a, T b) {
		return genericOp(a, b, Multiplication.INSTANCE);
	}
	
	public static <T extends Number> T div(T a, T b) {
		return genericOp(a, b, Division.INSTANCE);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Number> T genericOp(T a, T b, Operation op) {
		Class<? extends Number> clazz = a.getClass();
		
		if (clazz.equals(Integer.class)) {
			return (T) op.apply((Integer) a, (Integer) b);
		} else if (clazz.equals(Long.class)) {
			return (T) op.apply((Long) a, (Long) b);
		} else if (clazz.equals(Short.class)) {
			return (T) op.apply((Short) a, (Short) b);
		} else if (clazz.equals(Byte.class)) {
			return (T) op.apply((Byte) a, (Byte) b);
		} else if (clazz.equals(Double.class)) {
			return (T) op.apply((Double) a, (Double) b);
		} else if (clazz.equals(Float.class)) {
			return (T) op.apply((Float) a, (Float) b);
		} else if (clazz.equals(BigDecimal.class)) {
			return (T) op.apply((BigDecimal) a, (BigDecimal) b);
		} else if (clazz.equals(BigInteger.class)) {
			return (T) op.apply((BigInteger) a, (BigInteger) b);
		} else {
			throw new InvalidParameterException("One of the parameters is of an invalid class");
		}
	}
}
