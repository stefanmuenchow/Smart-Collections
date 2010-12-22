/**
 * Copyright (c) Stefan Muenchow. All rights reserved.
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 **/

package com.stefanmuenchow.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SmartArrayList<E> extends SmartAbstractList<E> implements SmartList<E> {

    public SmartArrayList() {
        this(new ArrayList<E>());
    }

    public SmartArrayList(final E... elems) {
        this(Arrays.asList(elems));
    }

    public SmartArrayList(final Collection<E> internalColl) {
        super(new ArrayList<E>(internalColl));
    }

    /** Helper methods */

    @Override
    protected SmartList<E> createNewInstance() {
        return new SmartArrayList<E>();
    }

    @Override
    protected <T> SmartList<T> createNewInstance(final Collection<T> aColl) {
        return new SmartArrayList<T>(aColl);
    }
}
