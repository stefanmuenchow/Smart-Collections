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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Implementation of the {@link SmartList} interface decorating a
 * standard {@link ArrayList}.
 * 
 * @see ArrayList
 * 
 * @author Stefan Münchow
 */
public class SmartArrayList<E> extends AbstractSmartList<E> implements SmartList<E> {

	/**
	 * Creates a new instance containing all elements of the specified 
	 * collection.
	 * 
	 * @param coll		Elements to be contained
	 */
    public SmartArrayList(final Collection<E> coll) {
        super(new ArrayList<E>(coll));
    }
    
    /**
     * Creates a new empty list.
     */
    public SmartArrayList() {
        this(new ArrayList<E>());
    }

    /**
     * Creates a new instance containing all specified elements.
     * 
     * @param elems		Elements to be contained
     */
    public SmartArrayList(final E... elems) {
        this(Arrays.asList(elems));
    }

    @Override
    protected SmartList<E> createNewInstance() {
        return new SmartArrayList<E>();
    }

    @Override
    protected <T> SmartList<T> createNewInstance(final Collection<T> aColl) {
        return new SmartArrayList<T>(aColl);
    }
}
