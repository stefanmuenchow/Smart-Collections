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

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.stefanmuenchow.functors.Predicate;
import com.stefanmuenchow.functors.UnaryFunction;

/**
 * Abstract base class for set types.
 *  
 * @author Stefan Münchow
 */
public abstract class AbstractSmartSet<E> extends AbstractSmartCollection<E> implements SmartSet<E> {

    protected AbstractSmartSet(final Collection<E> innerSet) {
        super(innerSet);
    }

    /** Helper methods */

    @Override
    protected abstract SmartSet<E> createNewInstance();

    @Override
    protected abstract <T> SmartSet<T> createNewInstance(Collection<T> aColl);

    /** ISmartSet methods */

    @Override
    public boolean isSubsetOf(final Set<E> anotherSet) {
        SmartSet<E> temp = new SmartHashSet<E>(this);
        temp.difference(anotherSet);
        return temp.isEmpty();
    }

    @Override
    public boolean isProperSubsetOf(final Set<E> anotherSet) {
        return isSubsetOf(anotherSet) && !equals(anotherSet);
    }

    @Override
    public boolean isSupersetOf(final Set<E> anotherSet) {
        SmartSet<E> temp = new SmartHashSet<E>(anotherSet);
        return temp.isSubsetOf(this);
    }

    @Override
    public boolean isProperSupersetOf(final Set<E> anotherSet) {
        return isSupersetOf(anotherSet) && !equals(anotherSet);
    }

    @Override
    public SmartSet<E> union(final Set<E> anotherSet) {
        addAll(anotherSet);
        return this;
    }

    @Override
    public SmartSet<E> intersection(final Set<E> anotherSet) {
        retainAll(anotherSet);
        return this;
    }

    @Override
    public SmartSet<E> difference(final Set<E> anotherSet) {
        removeAll(anotherSet);
        return this;
    }

    /** Overridden methods from SmartCollection */
    
    public SmartSet<E> addReturn(E elem) {
    	return (SmartSet<E>) super.addReturn(elem);
    }

    public SmartSet<E> addAllReturn(Collection<E> coll) {
    	return (SmartSet<E>) super.addAllReturn(coll);
    }
    
    public SmartSet<E> removeReturn(E elem) {
    	return (SmartSet<E>) super.removeReturn(elem);
    }
    
    public SmartSet<E> removeAllReturn(Collection<E> coll) {
    	return (SmartSet<E>) super.removeAllReturn(coll);
    }
    
    public SmartSet<E> retainAllReturn(Collection<E> coll) {
    	return (SmartSet<E>) super.retainAllReturn(coll);
    }
    
    public SmartSet<E> filter(Predicate<? super E> predicate) {
    	return (SmartSet<E>) super.filter(predicate);
    }
    
    public SmartSet<E> remove(Predicate<? super E> predicate) {
    	return (SmartSet<E>) super.remove(predicate);
    }
    
    public SmartSet<E> replace(E seek, E replacement) {
    	return (SmartSet<E>) super.replace(seek, replacement);
    }
    
    public SmartSet<E> replace(Predicate<? super E> predicate, E replacement) {
    	return (SmartSet<E>) super.replace(predicate, replacement);
    }
    
    public SmartSet<E> replace(Map<E, E> replacements) {
    	return (SmartSet<E>) super.replace(replacements);
    }
    
    @Override
    public <R> SmartSet<R> map(final UnaryFunction<R, ? super E> function) {
        return (SmartSet<R>) super.map(function);
    }

    @Override
    public <T> SmartSet<T> castEach(final Class<T> clazz) {
        return (SmartSet<T>) super.castEach(clazz);
    }
    
    @Override
    public Set<E> toStandardCollection() {
    	return (Set<E>) super.toStandardCollection();
    }
}
