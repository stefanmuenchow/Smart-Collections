package com.stefanmuenchow.collections.function;



public class Predicates {
    public static final <T extends Number> Predicate<T> evenPred(final Class<T> clazz) {
        return new Predicate<T>() {

            @Override
            public boolean test(final T input) {
                return input.intValue() % 2 == 0;
            }
        };
    }
    
    public static final <T extends Number> Predicate<T> oddPred(final Class<T> clazz) {
        return new Predicate<T>() {

            @Override
            public boolean test(final T input) {
                return !evenPred(clazz).test(input);
            }
        };
    }
}
