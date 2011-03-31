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

/**
 * Interface defining operations for unary functions. Each implementation
 * defines one actual operation (like strategy pattern by the gang of four).
 * 
 * @author Stefan Münchow
 */
public interface UnaryOperation {
	
	/**
	 * Applies this unary operation for an {@link Integer} parameter.
	 * 
	 * @param a		Operand
	 * @return		Result of operation
	 */
	Integer apply(Integer a);
	
	/**
	 * Applies this unary operation for a {@link Long} parameter.
	 * 
	 * @param a		Operand
	 * @return		Result of operation
	 */
	Long apply(Long a);
	
	/**
	 * Applies this unary operation for a {@link Short} parameter.
	 * 
	 * @param a		Operand
	 * @return		Result of operation
	 */
	Short apply(Short a);
	
	/**
	 * Applies this unary operation for a {@link Byte} parameter.
	 * 
	 * @param a		Operand
	 * @return		Result of operation
	 */
	Byte apply(Byte a);
	
	/**
	 * Applies this unary operation for a {@link Double} parameter.
	 * 
	 * @param a		Operand
	 * @return		Result of operation
	 */
	Double apply(Double a);
	
	/**
	 * Applies this unary operation for a {@link Float} parameter.
	 * 
	 * @param a		Operand
	 * @return		Result of operation
	 */
	Float apply(Float a);
	
	/**
	 * Applies this unary operation for a {@link BigDecimal} parameter.
	 * 
	 * @param a		Operand
	 * @return		Result of operation
	 */
	BigDecimal apply(BigDecimal a);
	
	/**
	 * Applies this unary operation for a {@link BigInteger} parameter.
	 * 
	 * @param a		Operand
	 * @return		Result of operation
	 */
	BigInteger apply(BigInteger a);
}
