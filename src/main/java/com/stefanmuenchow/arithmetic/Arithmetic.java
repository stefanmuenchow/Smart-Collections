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
	public static <T extends Number> T genericOp(T a, T b, BinaryOperation op) {
		
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
}
