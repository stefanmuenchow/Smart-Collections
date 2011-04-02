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

/**
 * Binary function to be used by special operations on some smart collections,
 * e.g. reduce. For this purpose this interface is implemented by an anonymous
 * class that is passed to the collection function.
 *
 * @author Stefan Münchow
 */
public interface BinaryFunction<R, E> {

    /**
     * Executes this function.
     *
     * @param input1
     *            Input parameter 1
     * @param input2
     *            Input parameter 2
     * @return Result
     */
    R apply(R input1, E input2);
}
