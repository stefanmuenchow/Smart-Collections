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

package com.stefanmuenchow.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * Implementation of the {@link SmartSet} interface decorating a
 * standard {@link HashSet}.
 * 
 * @see HashSet
 * 
 * @author Stefan Münchow
 */
public class SmartHashSet<E> extends AbstractSmartSet<E> implements SmartSet<E> {

    public SmartHashSet(final Collection<E> collection) {
        super(new HashSet<E>(collection));
    }
    
    public SmartHashSet() {
        this(new HashSet<E>());
    }

    public SmartHashSet(final E... elems) {
        this(Arrays.asList(elems));
    }

    /** Helper methods */

    @Override
    protected SmartSet<E> createNewInstance() {
        return new SmartHashSet<E>();
    }

    @Override
    protected <T> SmartSet<T> createNewInstance(final Collection<T> aColl) {
        return new SmartHashSet<T>(aColl);
    }
}
