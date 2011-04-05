package com.stefanmuenchow.functors;

/**
 * Unary function to be used to cause side effects by some special operations 
 * on smart collections. For this purpose this interface is implemented by an 
 * anonymous class that is passed to the collection function.
 *
 * @author Stefan Münchow
 */
public interface VoidFunction<E> {

	/**
	 * Executes this function.
	 * 
	 * @param input		Input parameter
	 */
	void apply(E input);
}
