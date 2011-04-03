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

import java.util.Collection;

/**
 * Factory class for often used predicates.
 * 
 * @author Stefan Münchow
 */
public class Predicates {
	
	/**
	 * Predicate to test if a number is even.
	 * 
	 * @return		Even predicate
	 */
    public static final <T extends Number> Predicate<T> evenPred() {
        return new Predicate<T>() {

            @Override
            public boolean test(final T input) {
                return input.intValue() % 2 == 0;
            }
        };
    }
    
    /**
     * Predicate to test if a number is odd.
     * 
     * @return		Odd predicate
     */
    public static final <T extends Number> Predicate<T> oddPred() {
        return new Predicate<T>() {

            @Override
            public boolean test(final T input) {
                return !evenPred().test(input);
            }
        };
    }
    
    /**
     * Predicate to test if a string is contained in another string.
     * 
     * @param obj	String to be contained
     * @return		Contains predicate
     */
    public static final Predicate<String> stringContainsPred(final String obj) {
    	return new Predicate<String>() {
			@Override
			public boolean test(String input) {
				return input.contains(obj);
			}
		};
    }
    
    /**
     * Predicate to test if an object is contained in a collection.
     * 
     * @param obj	Object to be contained
     * @return		Contains predicate
     */
    public static final Predicate<Collection<? extends Object>> containsPred(final Object obj) {
    	return new Predicate<Collection<? extends Object>>() {
			@Override
			public boolean test(Collection<? extends Object> input) {
				return input.contains(obj);
			}
		};
    }
    
    /**
     * Predicate to test if two objects are equal. Uses the 
     * {@link Object#equals(Object)} method.
     * 
     * @param obj	Object to check on equality
     * @return		Equals predicate
     */
    public static final <T> Predicate<T> equalsPred(final T obj) {
    	return new Predicate<T>() {
			@Override
			public boolean test(T input) {
				return input.equals(obj);
			}
		};
    }
    
    /**
     * Predicate to test if an object is less than another one according
     * to the {@link Comparable#compareTo(Object)} method.
     * 
     * @param obj	Object to compare to
     * @return		LessThan function
     */
    public static final <T extends Comparable<T>> Predicate<T> lessThanPred(final T obj) {
    	return new Predicate<T>() {
			@Override
			public boolean test(T input) {
				return input.compareTo(obj) < 0;
			}
		};
    }
    
    /**
     * Predicate to test if an object is less or equal than another one 
     * according to the {@link Comparable#compareTo(Object)} method.
     * 
     * @param obj	Object to compare to
     * @return		LessEqualThan function
     */
    public static final <T extends Comparable<T>> Predicate<T> lessEqualThanPred(final T obj) {
    	return new Predicate<T>() {
			@Override
			public boolean test(T input) {
				return input.compareTo(obj) <= 0;
			}
		};
    }
    
    /**
     * Predicate to test if an object is greater than another one according
     * to the {@link Comparable#compareTo(Object)} method.
     * 
     * @param obj	Object to compare to
     * @return		GreaterThan function
     */
    public static final <T extends Comparable<T>> Predicate<T> greaterThanPred(final T obj) {
    	return new Predicate<T>() {
			@Override
			public boolean test(T input) {
				return input.compareTo(obj) > 0;
			}
		};
    }
    
    /**
     * Predicate to test if an object is greater or equal than another one 
     * according to the {@link Comparable#compareTo(Object)} method.
     * 
     * @param obj	Object to compare to
     * @return		GreaterEqualThan function
     */
    public static final <T extends Comparable<T>> Predicate<T> greaterEqualThanPred(final T obj) {
    	return new Predicate<T>() {
			@Override
			public boolean test(T input) {
				return input.compareTo(obj) >= 0;
			}
		};
    }
}
