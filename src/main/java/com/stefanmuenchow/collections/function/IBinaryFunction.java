package com.stefanmuenchow.collections.function;

/**
 * Binary Function to be used by special operations on some smart collections,
 * e.g. reduce. For this purpose this interface is implemented by an anonymous
 * class that is passed to the collection function.
 * 
 * @author Stefan Münchow
 */
public interface IBinaryFunction<R, E, T> {

    /**
     * Executes this function.
     * 
     * @param input1
     *            Input parameter 1
     * @param input2
     *            Input parameter 2
     * @return Result
     */
    R execute(E input1, T input2);
}
