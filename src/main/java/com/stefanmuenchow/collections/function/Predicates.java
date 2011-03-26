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
