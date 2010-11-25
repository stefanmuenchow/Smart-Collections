package com.stefanmuenchow.collections.function;

/**
 * Unary Function to be used by special operations on some smart collections,
 * e.g. map. For this purpose this interface is implemented by an anonymous
 * class that is passed to the collection function.
 * 
 * @author Stefan Münchow
 */
public interface UnaryFunction<R, E> {

    /**
     * Executes this function.
     * 
     * @param input
     *            Input parameter
     * @return Result
     */
    R execute(E input);
}
