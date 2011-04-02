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

package com.stefanmuenchow.functors;

import com.stefanmuenchow.arithmetic.Arithmetic;

/**
 * Factory class for often used functions.
 * 
 * @author Stefan Münchow
 */
public class Functions {
	
	/**
	 * Function that converts an object to a string by calling its
	 * {@link Object#toString()} method.
	 * 
	 * @return		ToString function
	 */
    public static final UnaryFunction<String, Object> toStringFn() {
        return new UnaryFunction<String, Object>() {

            @Override
            public String apply(final Object input) {
                return String.valueOf(input);
            }
        };
    }
    
	/**
	 * Function that concatenates two strings.
	 * 
	 * @return		concatStrings function
	 */
    public static final BinaryFunction<String, String> concatStringsFn() {
    	return joinStringsFn("");
    }
    
	/**
	 * Function that concatenates two strings, inserting the specified delimiter
	 * between them.
	 * 
	 * @return		joinStrings function
	 */
    public static final BinaryFunction<String, String> joinStringsFn(final String delimiter) {
    	return new BinaryFunction<String, String>() {
			@Override
			public String apply(String input1, String input2) {
				return input1 + delimiter + input2;
			}
		};
    }
   
    /**
     * Function to increment a number with.
     * 
     * @param inc	Incrementor to be used
     * @return		Increment function
     */
    public static final <T extends Number> UnaryFunction<T, T> incFn(final T inc) {
    	return new UnaryFunction<T, T>() {
			@Override
			public T apply(T input) {
				return Arithmetic.add(input, inc);
			}
		};
    }
    
    /**
     * Function to decrement a number with.
     * 
     * @param dec	Decrementor to be used
     * @return		Decrement function
     */
    public static final <T extends Number> UnaryFunction<T, T> decFn(final T dec) {
    	return new UnaryFunction<T, T>() {
			@Override
			public T apply(T input) {
				return Arithmetic.sub(input, dec);
			}
		};
    }
    
    /**
     * Function to add two numbers.
     * 	 
     * @return		Add function
     */
    public static final <T extends Number> BinaryFunction<T, T> addFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.add(input1, input2);
			}
		};
    }
    
    /**
     * Function to subtract two numbers.
     * 	 
     * @return		Subtract function
     */
    public static final <T extends Number> BinaryFunction<T, T> subtractFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.sub(input1, input2);
			}
		};
    }
    
    /**
     * Function to multiply two numbers.
	 *
     * @return		Multiply function
     */
    public static final <T extends Number> BinaryFunction<T, T> multiplyFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.mul(input1, input2);
			}
		};
    }
    
    /**
     * Function to divide two numbers.
     * 
     * @return		Divide function
     */
    public static final <T extends Number> BinaryFunction<T, T> divideFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.div(input1, input2);
			}
		};
    }
    
    /**
     * Function to get the minimum of two numbers.
     * 
     * @return		Minimum function
     */
    public static final <T extends Number> BinaryFunction<T, T> minFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.min(input1, input2);
			}
		};
    }
    
    /**
     * Function to get the maximum of two numbers.
     * 
     * @return		Maximum function
     */
    public static final <T extends Number> BinaryFunction<T, T> maxFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.max(input1, input2);
			}
		};
    }
    
    /**
     * Function to get the absolute of a number.
     * 
     * @return		Absolute function
     */
    public static final <T extends Number> UnaryFunction<T, T> absFn() {
    	return new UnaryFunction<T, T>() {
			@Override
			public T apply(T input) {
				return Arithmetic.abs(input);
			}
		};
    }
    
    /**
     * Function to negate a number.
     * 
     * @return		Negation function
     */
    public static final <T extends Number> UnaryFunction<T, T> negateFn() {
    	return new UnaryFunction<T, T>() {
			@Override
			public T apply(T input) {
				return Arithmetic.negate(input);
			}
		};
    }
}
