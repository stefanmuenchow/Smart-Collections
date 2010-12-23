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

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class SmartLinkedList<E> extends SmartAbstractList<E> implements SmartList<E> {

    public SmartLinkedList() {
        this(new LinkedList<E>());
    }

    public SmartLinkedList(final E... elems) {
        this(Arrays.asList(elems));
    }

    public SmartLinkedList(final Collection<E> collection) {
        super(new LinkedList<E>(collection));
    }

    /** Helper methods */

    @Override
    protected SmartList<E> createNewInstance() {
        return new SmartLinkedList<E>();
    }

    @Override
    protected <T> SmartList<T> createNewInstance(final Collection<T> aColl) {
        return new SmartLinkedList<T>(aColl);
    }
}
