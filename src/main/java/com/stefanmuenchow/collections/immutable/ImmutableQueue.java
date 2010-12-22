package com.stefanmuenchow.collections.immutable;

import com.stefanmuenchow.collections.function.UnaryFunction;

public interface ImmutableQueue<E> extends ImmutableCollection<E> {
    @Override
    <R> ImmutableQueue<R> map(UnaryFunction<R, E> function);

    @Override
    ImmutableQueue<Object> flatten();

    @Override
    <T> ImmutableQueue<T> castAllElements(Class<T> clazz);
}
