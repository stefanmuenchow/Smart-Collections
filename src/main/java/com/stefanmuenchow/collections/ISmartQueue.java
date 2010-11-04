package com.stefanmuenchow.collections;

import java.util.Queue;

public interface ISmartQueue<E> extends Queue<E>, ISmartCollection<E> {
	ISmartQueue<E> offerElem(E elem);
}
