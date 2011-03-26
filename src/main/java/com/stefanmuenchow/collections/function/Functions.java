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

package com.stefanmuenchow.collections.function;

import com.stefanmuenchow.arithmetic.Arithmetic;


public class Functions {
    public static final UnaryFunction<String, Object> toStringFn() {
        return new UnaryFunction<String, Object>() {

            @Override
            public String apply(final Object input) {
                return String.valueOf(input);
            }
        };
    }
    
    public static final BinaryFunction<String, String> appendStringFn() {
    	return joinStringsFn("");
    }
    
    public static final BinaryFunction<String, String> joinStringsFn(final String str) {
    	return new BinaryFunction<String, String>() {
    		private final String joinStr = str;
    		
			@Override
			public String apply(String input1, String input2) {
				return input1 + joinStr + input2;
			}
		};
    }
   
    public static final <T extends Number> UnaryFunction<T, T> incrementFn(final T inc) {
    	return new UnaryFunction<T, T>() {
    		private final T toInc = inc;
    		
			@Override
			public T apply(T input) {
				return Arithmetic.add(input, toInc);
			}
		};
    }
    
    public static final <T extends Number> UnaryFunction<T, T> decrementFn(final T dec) {
    	return new UnaryFunction<T, T>() {
    		private final T toDec = dec;
    		
			@Override
			public T apply(T input) {
				return Arithmetic.sub(input, toDec);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> addFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.add(input1, input2);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> subtractFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.sub(input1, input2);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> multiplyFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.mul(input1, input2);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> divideFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.div(input1, input2);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> minFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.min(input1, input2);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> maxFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.max(input1, input2);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> compareToFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.compareTo(input1, input2);
			}
		};
    }
    
    public static final <T extends Number> UnaryFunction<T, T> absFn() {
    	return new UnaryFunction<T, T>() {
			@Override
			public T apply(T input) {
				return Arithmetic.abs(input);
			}
		};
    }
    
    public static final <T extends Number> UnaryFunction<T, T> negateFn() {
    	return new UnaryFunction<T, T>() {
			@Override
			public T apply(T input) {
				return Arithmetic.negate(input);
			}
		};
    }
}
