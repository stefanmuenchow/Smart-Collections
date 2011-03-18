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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SmartSetTest {
    private SmartSet<Integer> smartSet = null;

    @Before
    public void setUp() throws Exception {
        smartSet = new SmartHashSet<Integer>(9, 56, 23);
    }
    
    @Test
    public void testIsSubsetOf() {
    	assertTrue(smartSet.isSubsetOf(new SmartHashSet<Integer>(23, 9, 56)));
    	assertTrue(smartSet.isSubsetOf(new SmartHashSet<Integer>(23, 9, 56, 10)));
    }
    
    @Test
    public void testIsProperSubsetOf() {
    	assertFalse(smartSet.isProperSubsetOf(new SmartHashSet<Integer>(23, 9, 56)));
    	assertTrue(smartSet.isProperSubsetOf(new SmartHashSet<Integer>(23, 9, 56, 10)));
    }
    
    @Test
    public void testIsSupersetOf() {
    	assertTrue(smartSet.isSupersetOf(new SmartHashSet<Integer>(23, 56, 9)));
    	assertTrue(smartSet.isSupersetOf(new SmartHashSet<Integer>(23, 9)));
    }
    
    @Test
    public void testIsProperSupersetOf() {
    	assertFalse(smartSet.isProperSupersetOf(new SmartHashSet<Integer>(23, 56, 9)));
    	assertTrue(smartSet.isProperSupersetOf(new SmartHashSet<Integer>(23, 9)));
    }
    
    @Test
    public void testUnion() {
    	assertEquals(new SmartHashSet<Integer>(9, 56, 11, 12, 23), 
    			smartSet.union(new SmartHashSet<Integer>(11, 12)));
    }
    
    @Test
    public void testIntersection() {
    	assertEquals(new SmartHashSet<Integer>(56, 23), 
    			smartSet.intersection(new SmartHashSet<Integer>(23, 56, 99)));
    }
    
    @Test
    public void testDifference() {
    	assertEquals(new SmartHashSet<Integer>(56, 23), 
    			smartSet.difference(new SmartHashSet<Integer>(9)));
    }
}
