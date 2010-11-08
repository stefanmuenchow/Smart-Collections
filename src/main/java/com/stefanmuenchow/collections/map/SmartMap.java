package com.stefanmuenchow.collections.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.stefanmuenchow.collections.exception.PreconditionViolatedException;
import com.stefanmuenchow.collections.function.IBinaryFunction;
import com.stefanmuenchow.collections.function.IMapPredicate;
import com.stefanmuenchow.collections.function.IUnaryFunction;

public class SmartMap<K, V> implements ISmartMap<K, V> {

	public SmartMap() {
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <W> ISmartMap<K, W> mergeWith(
			ISmartMap<K, V> anotherMap,
			IBinaryFunction<java.util.Map.Entry<K, W>, java.util.Map.Entry<K, V>, java.util.Map.Entry<K, V>> mergeFunct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key, V defaultVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V find(IMapPredicate<K, V> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISmartMap<K, V> filter(IMapPredicate<K, V> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISmartMap<K, V> remove(IMapPredicate<K, V> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISmartMap<K, V> replace(K seekKey, V seekValue, K newKey, V newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISmartMap<K, V> replace(IMapPredicate<K, V> predicate, K newKey,
			V newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S, R> ISmartMap<S, R> map(
			IUnaryFunction<java.util.Map.Entry<S, R>, java.util.Map.Entry<K, V>> function) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R reduce(R initial,
			IBinaryFunction<R, R, java.util.Map.Entry<K, V>> funct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String join(String entryDelimiter, String keyValDelimiter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(IMapPredicate<K, V> predicate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exists(IMapPredicate<K, V> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean forall(IMapPredicate<K, V> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBijective() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ISmartMap<V, K> swap() throws PreconditionViolatedException {
		// TODO Auto-generated method stub
		return null;
	}
}
