package com.stefanmuenchow.collections.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidParameterException;

public class Arithmetic {
	
	@SuppressWarnings("unchecked")
	public static <T extends Number> T genericOp(T input1, T input2, Operation op) {
		Class<? extends Number> clazz = input1.getClass();
		
		if (clazz.equals(Integer.class)) {
			return (T) op.apply((Integer) input1, (Integer) input2);
		} else if (clazz.equals(Long.class)) {
			return (T) op.apply((Long) input1, (Long) input2);
		} else if (clazz.equals(Short.class)) {
			return (T) op.apply((Short) input1, (Short) input2);
		} else if (clazz.equals(Byte.class)) {
			return (T) op.apply((Byte) input1, (Byte) input2);
		} else if (clazz.equals(Double.class)) {
			return (T) op.apply((Double) input1, (Double) input2);
		} else if (clazz.equals(Float.class)) {
			return (T) op.apply((Float) input1, (Float) input2);
		} else if (clazz.equals(BigDecimal.class)) {
			return (T) op.apply((BigDecimal) input1, (BigDecimal) input2);
		} else if (clazz.equals(BigInteger.class)) {
			return (T) op.apply((BigInteger) input1, (BigInteger) input2);
		} else {
			throw new InvalidParameterException("One of the parameters is of an invalid class");
		}
	}
}
