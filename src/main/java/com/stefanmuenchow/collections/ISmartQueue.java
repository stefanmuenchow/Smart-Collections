package com.stefanmuenchow.collections;

import java.util.Queue;

/**
 * Specifies the operations of SmartQueues.
 * 
 * SmartQueues are compatible with the standard Queue interface, but add some
 * functionality to them. They are implemented as simple decorators (see Gang of
 * Four).
 * 
 * @author Stefan Münchow
 */
public interface ISmartQueue<E> extends Queue<E>, ISmartCollection<E> {

}
