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
}
