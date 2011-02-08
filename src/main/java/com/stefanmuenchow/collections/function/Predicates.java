package com.stefanmuenchow.collections.function;



public class Predicates {
    public static final <T extends Number> Predicate<T> even(final Class<T> clazz) {
        return new Predicate<T>() {

            @Override
            public boolean test(final T input) {
                return input.intValue() % 2 == 0;
            }
        };
    }
}
