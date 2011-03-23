package com.stefanmuenchow.collections.function;

import com.stefanmuenchow.collections.arithmetic.Addition;
import com.stefanmuenchow.collections.arithmetic.Arithmetic;
import com.stefanmuenchow.collections.arithmetic.Division;
import com.stefanmuenchow.collections.arithmetic.Multiplication;
import com.stefanmuenchow.collections.arithmetic.Subtraction;


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
				return Arithmetic.genericOp(input1, input2, Addition.INSTANCE);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> subtractFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.genericOp(input1, input2, Subtraction.INSTANCE);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> multiplyFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.genericOp(input1, input2, Multiplication.INSTANCE);
			}
		};
    }
    
    public static final <T extends Number> BinaryFunction<T, T> divideFn() {
    	return new BinaryFunction<T, T>() {
			@Override
			public T apply(T input1, T input2) {
				return Arithmetic.genericOp(input1, input2, Division.INSTANCE);
			}
		};
    }
}
