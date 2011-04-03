package com.stefanmuenchow.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Strategy for negation operation.
 * 
 * @author Stefan Münchow
 */
public class Negation implements UnaryOperation {
	private static Negation INSTANCE = null;
	
	private Negation() { }
	
	/**
	 * Gets the singleton instance of this class.
	 * 
	 * @return	Singleton instance
	 */
	public static Negation getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Negation();
		}
		
		return INSTANCE;
	}

	@Override
	public Integer apply(Integer a) {
		return Integer.valueOf(a * (-1));
	}

	@Override
	public Long apply(Long a) {
		return Long.valueOf(a * (-1));
	}

	@Override
	public Short apply(Short a) {
		return Short.valueOf((short) (a * (-1)));
	}

	@Override
	public Byte apply(Byte a) {
		return Byte.valueOf((byte) (a * (-1)));
	}

	@Override
	public Double apply(Double a) {
		return Double.valueOf(a * (-1));
	}

	@Override
	public Float apply(Float a) {
		return Float.valueOf(a * (-1));
	}

	@Override
	public BigDecimal apply(BigDecimal a) {
		return a.negate();
	}

	@Override
	public BigInteger apply(BigInteger a) {
		return a.negate();
	}
}
