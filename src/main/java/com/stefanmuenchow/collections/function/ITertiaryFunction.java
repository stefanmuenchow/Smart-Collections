package com.stefanmuenchow.collections.function;

/**
 * Tertiary Function to be used by special operations on smart map, e.g. merge.
 * For this purpose this interface is implemented by an anonymous class that is
 * passed to the map function.
 * 
 * @author Stefan Münchow
 */
public interface ITertiaryFunction<R, E, T, F> {

    /**
     * Executes this function.
     * 
     * @param input1
     *            Input parameter 1
     * @param input2
     *            Input parameter 2
     * @param input3
     *            Input parameter 3
     * @return Result
     */
    R execute(E input1, T input2, F input3);
}
