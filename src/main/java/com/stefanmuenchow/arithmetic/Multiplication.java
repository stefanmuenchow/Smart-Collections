/**
 * Copyright (c) Stefan Münchow. All rights reserved.
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

/**
 * Strategy for multiplication operation.
 * 
 * @author Stefan Münchow
 */
public class Multiplication implements BinaryOperation {
	public static final Multiplication INSTANCE = new Multiplication();	
	private Multiplication() { }

	@Override
	public Integer apply(Integer a, Integer b) {
		return Integer.valueOf(a * b);
	}

	@Override
	public Long apply(Long a, Long b) {
		return Long.valueOf(a * b);
	}

	@Override
	public Short apply(Short a, Short b) {
		return Short.valueOf((short) (a * b));
	}

	@Override
	public Byte apply(Byte a, Byte b) {
		return Byte.valueOf((byte) (a * b));
	}

	@Override
	public Double apply(Double a, Double b) {
		return Double.valueOf(a * b);
	}

	@Override
	public Float apply(Float a, Float b) {
		return Float.valueOf(a * b);
	}

	@Override
	public BigDecimal apply(BigDecimal a, BigDecimal b) {
		return a.multiply(b);
	}

	@Override
	public BigInteger apply(BigInteger a, BigInteger b) {
		return a.multiply(b);
	}
}
