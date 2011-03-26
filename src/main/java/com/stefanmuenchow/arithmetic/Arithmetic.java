/**
 * Copyright (c) Stefan Muenchow. All rights reserved.
 * 
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 **/

package com.stefanmuenchow.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidParameterException;

public class Arithmetic {
	
	public static <T extends Number> T add(T a, T b) {
		return binaryOperation(a, b, Addition.INSTANCE);
	}
	
	public static <T extends Number> T sub(T a, T b) {
		return binaryOperation(a, b, Subtraction.INSTANCE);
	}
	
	public static <T extends Number> T mul(T a, T b) {
		return binaryOperation(a, b, Multiplication.INSTANCE);
	}
	
	public static <T extends Number> T div(T a, T b) {
		return binaryOperation(a, b, Division.INSTANCE);
	}

	public static <T extends Number> T min(T a, T b) {
		return binaryOperation(a, b, Minimum.INSTANCE);
	}
	
	public static <T extends Number> T max(T a, T b) {
		return binaryOperation(a, b, Maximum.INSTANCE);
	}
	
	public static <T extends Number> T compareTo(T a, T b) {
		return binaryOperation(a, b, Comparison.INSTANCE);
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Number> T binaryOperation(T a, T b, BinaryOperation op) {
		
		if (a instanceof Integer) {
			return (T) op.apply((Integer) a, (Integer) b);
		} else if (a instanceof Long) {
			return (T) op.apply((Long) a, (Long) b);
		} else if (a instanceof Short) {
			return (T) op.apply((Short) a, (Short) b);
		} else if (a instanceof Byte) {
			return (T) op.apply((Byte) a, (Byte) b);
		} else if (a instanceof Double) {
			return (T) op.apply((Double) a, (Double) b);
		} else if (a instanceof Float) {
			return (T) op.apply((Float) a, (Float) b);
		} else if (a instanceof BigDecimal) {
			return (T) op.apply((BigDecimal) a, (BigDecimal) b);
		} else if (a instanceof BigInteger) {
			return (T) op.apply((BigInteger) a, (BigInteger) b);
		} else {
			throw new InvalidParameterException("One of the parameters is of an invalid class");
		}
	}
	
	public static <T extends Number> T negate(T a) {
		return unaryOperation(a, Negation.INSTANCE);
	}
	
	public static <T extends Number> T abs(T a) {
		return unaryOperation(a, Absolute.INSTANCE);
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Number> T unaryOperation(T a, UnaryOperation op) {
		
		if (a instanceof Integer) {
			return (T) op.apply((Integer) a);
		} else if (a instanceof Long) {
			return (T) op.apply((Long) a);
		} else if (a instanceof Short) {
			return (T) op.apply((Short) a);
		} else if (a instanceof Byte) {
			return (T) op.apply((Byte) a);
		} else if (a instanceof Double) {
			return (T) op.apply((Double) a);
		} else if (a instanceof Float) {
			return (T) op.apply((Float) a);
		} else if (a instanceof BigDecimal) {
			return (T) op.apply((BigDecimal) a);
		} else if (a instanceof BigInteger) {
			return (T) op.apply((BigInteger) a);
		} else {
			throw new InvalidParameterException("One of the parameters is of an invalid class");
		}
	}
}
