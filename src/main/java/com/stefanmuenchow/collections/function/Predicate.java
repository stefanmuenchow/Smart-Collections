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
 * Predicate used by smart collection functions. Checks a condition based on a
 * single value. For this purpose this interface is implemented by an anonymous
 * class that is passed to the collection function.
 *
 * @author Stefan Muenchow
 */
public interface Predicate<E> {

    /**
     * Checks a predicate based on a single value.
     *
     * @param input     Input value
     * @return true / false
     */
    boolean test(E input);
}
