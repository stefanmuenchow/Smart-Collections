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
 * Interface defining operations for binary functions. Each implementation
 * defines one actual operation (like strategy pattern by the gang of four).
 * 
 * @author Stefan Münchow
 */
public interface BinaryOperation {
	
	/**
	 * Applies this binary operation for {@link Integer} parameters.
	 * 
	 * @param a		First operand
	 * @param b		Second operand
	 * @return		Result of operation
	 */
	Integer apply(Integer a, Integer b);
	
	/**
	 * Applies this binary operation for {@link Long} parameters.
	 * 
	 * @param a		First operand
	 * @param b		Second operand
	 * @return		Result of operation
	 */
	Long apply(Long a, Long b);
	
	/**
	 * Applies this binary operation for {@link Short} parameters.
	 * 
	 * @param a		First operand
	 * @param b		Second operand
	 * @return		Result of operation
	 */
	Short apply(Short a, Short b);
	
	/**
	 * Applies this binary operation for {@link Byte} parameters.
	 * 
	 * @param a		First operand
	 * @param b		Second operand
	 * @return		Result of operation
	 */
	Byte apply(Byte a, Byte b);
	
	/**
	 * Applies this binary operation for {@link Double} parameters.
	 * 
	 * @param a		First operand
	 * @param b		Second operand
	 * @return		Result of operation
	 */
	Double apply(Double a, Double b);
	
	/**
	 * Applies this binary operation for {@link Float} parameters.
	 * 
	 * @param a		First operand
	 * @param b		Second operand
	 * @return		Result of operation
	 */
	Float apply(Float a, Float b);
	
	/**
	 * Applies this binary operation for {@link BigDecimal} parameters.
	 * 
	 * @param a		First operand
	 * @param b		Second operand
	 * @return		Result of operation
	 */
	BigDecimal apply(BigDecimal a, BigDecimal b);
	
	/**
	 * Applies this binary operation for {@link BigInteger} parameters.
	 * 
	 * @param a		First operand
	 * @param b		Second operand
	 * @return		Result of operation
	 */
	BigInteger apply(BigInteger a, BigInteger b);
}
