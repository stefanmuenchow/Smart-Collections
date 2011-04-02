/**
 * Copyright (c) Stefan Münchow. All rights reserved.
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 **/

package com.stefanmuenchow.collections;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SmartQueueTest {
    private SmartQueue<Integer> smartQueue = null;

    @Before
    public void setUp() throws Exception {
        smartQueue = new SmartLinkedQueue<Integer>(9, 56, 23);
    }

    @Test
    public void testOfferReturn() {
    	assertEquals(new SmartLinkedQueue<Integer>(9, 56, 23, 11), smartQueue.offerReturn(11));
    }
}
