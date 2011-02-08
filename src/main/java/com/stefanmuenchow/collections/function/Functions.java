package com.stefanmuenchow.collections.function;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Functions {
    public static final UnaryFunction<String, Object> string() {
        return new UnaryFunction<String, Object>() {

            @Override
            public String apply(final Object input) {
                return String.valueOf(input);
            }
        };
    }

    public static final <T extends Number> BinaryFunction<T, T> add(final Class<T> clazz) {
        return new BinaryFunction<T, T>() {

            @Override
            @SuppressWarnings("unchecked")
            public T apply(final T input1, final T input2) {
                if (input1.getClass().equals(input2.getClass())) {
                    if (input1 instanceof Integer) {
                        return (T) Integer.valueOf(input1.intValue() + input2.intValue());
                    } else if (input1 instanceof Long) {
                        return (T) Long.valueOf(input1.longValue() + input2.longValue());
                    } else if (input1 instanceof Double) {
                        return (T) Double.valueOf(input1.doubleValue() + input2.doubleValue());
                    } else if (input1 instanceof Float) {
                        return (T) Float.valueOf(input1.floatValue() + input2.floatValue());
                    } else if (input1 instanceof Short) {
                        return (T) Short.valueOf((short) (input1.shortValue() + input2.shortValue()));
                    } else if (input1 instanceof Byte) {
                        return (T) Byte.valueOf((byte) (input1.byteValue() + input2.byteValue()));
                    } else if (input1 instanceof BigInteger) {
                        return (T) ((BigInteger)input1).add((BigInteger)input2);
                    } else if (input1 instanceof BigDecimal) {
                        return (T) ((BigDecimal)input1).add((BigDecimal)input2);
                    }
                } else {
                    throw new IllegalArgumentException("Parameter types are not equal.");
                }

                throw new UnsupportedOperationException("Add function is only defined for numerical types.");
            }
        };
    }
}
