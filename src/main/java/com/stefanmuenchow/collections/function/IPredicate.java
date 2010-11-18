package com.stefanmuenchow.collections.function;

/**
 * Predicate used by smart collection functions. Checks a condition based on a
 * single value. For this purpose this interface is implemented by an anonymous
 * class that is passed to the collection function.
 * 
 * @author Stefan Münchow
 */
public interface IPredicate<E> {

    /**
     * Checks a predicate based on a single value.
     * 
     * @param input
     *            Input value
     * @return true / false
     */
    boolean check(E input);
}
