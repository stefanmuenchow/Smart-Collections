/**
* Copyright (c) Stefan Muenchow. All rights reserved.
* The use and distribution terms for this software are covered by the
* Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
* which can be found in the file epl-v10.html at the root of this distribution.
* By using this software in any fashion, you are agreeing to be bound by
* the terms of this license.
* You must not remove this notice, or any other, from this software.
**/

package com.stefanmuenchow.collections.function;

/**
 * Tertiary Function to be used by special operations on smart map, e.g. merge.
 * For this purpose this interface is implemented by an anonymous class that is
 * passed to the map function.
 *
 * @author Stefan Muenchow
 */
public interface TertiaryFunction<R, E, T, F> {

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
