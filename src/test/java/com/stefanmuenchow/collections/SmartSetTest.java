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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SmartSetTest {
    private SmartSet<Integer> smartSet1 = null;
    private SmartSet<Integer> smartSet2 = null;

    @Before
    public void setUp() throws Exception {
        smartSet1 = new SmartHashSet<Integer>(9, 56, 23);
        smartSet2 = new SmartTreeSet<Integer>(9, 56, 23);
    }
    
    @Test
    public void testIsSubsetOf() {
    	assertTrue(smartSet1.isSubsetOf(new SmartHashSet<Integer>(23, 9, 56)));
    	assertTrue(smartSet1.isSubsetOf(new SmartHashSet<Integer>(23, 9, 56, 10)));
    	assertFalse(smartSet1.isSubsetOf(new SmartHashSet<Integer>(11, 22)));
    	
    	assertTrue(smartSet2.isSubsetOf(new SmartTreeSet<Integer>(23, 9, 56)));
    	assertTrue(smartSet2.isSubsetOf(new SmartTreeSet<Integer>(23, 9, 56, 10)));
    	assertFalse(smartSet2.isSubsetOf(new SmartTreeSet<Integer>(11, 22)));
    }
    
    @Test
    public void testIsProperSubsetOf() {
    	assertFalse(smartSet1.isProperSubsetOf(new SmartHashSet<Integer>(11, 22)));
    	assertFalse(smartSet1.isProperSubsetOf(new SmartHashSet<Integer>(23, 9, 56)));
    	assertTrue(smartSet1.isProperSubsetOf(new SmartHashSet<Integer>(23, 9, 56, 10)));
    	
    	assertFalse(smartSet2.isProperSubsetOf(new SmartTreeSet<Integer>(11, 22)));
    	assertFalse(smartSet2.isProperSubsetOf(new SmartTreeSet<Integer>(23, 9, 56)));
    	assertTrue(smartSet2.isProperSubsetOf(new SmartTreeSet<Integer>(23, 9, 56, 10)));
    }
    
    @Test
    public void testIsSupersetOf() {
    	assertTrue(smartSet1.isSupersetOf(new SmartHashSet<Integer>(23, 56, 9)));
    	assertTrue(smartSet1.isSupersetOf(new SmartHashSet<Integer>(23, 9)));
    	assertFalse(smartSet1.isSupersetOf(new SmartHashSet<Integer>(11, 22)));
    	
    	assertTrue(smartSet2.isSupersetOf(new SmartTreeSet<Integer>(23, 56, 9)));
    	assertTrue(smartSet2.isSupersetOf(new SmartTreeSet<Integer>(23, 9)));
    	assertFalse(smartSet2.isSupersetOf(new SmartTreeSet<Integer>(11, 22)));
    }
    
    @Test
    public void testIsProperSupersetOf() {
    	assertFalse(smartSet1.isSupersetOf(new SmartHashSet<Integer>(11, 22)));
    	assertFalse(smartSet1.isProperSupersetOf(new SmartHashSet<Integer>(23, 56, 9)));
    	assertTrue(smartSet1.isProperSupersetOf(new SmartHashSet<Integer>(23, 9)));
    	
    	assertFalse(smartSet2.isSupersetOf(new SmartTreeSet<Integer>(11, 22)));
    	assertFalse(smartSet2.isProperSupersetOf(new SmartTreeSet<Integer>(23, 56, 9)));
    	assertTrue(smartSet2.isProperSupersetOf(new SmartTreeSet<Integer>(23, 9)));
    }
    
    @Test
    public void testUnion() {
    	assertEquals(new SmartHashSet<Integer>(9, 56, 11, 12, 23), 
    			smartSet1.union(new SmartHashSet<Integer>(11, 12)));
    	assertEquals(new SmartTreeSet<Integer>(9, 56, 11, 12, 23), 
    			smartSet2.union(new SmartTreeSet<Integer>(11, 12)));
    }
    
    @Test
    public void testIntersection() {
    	assertEquals(new SmartHashSet<Integer>(56, 23), 
    			smartSet1.intersection(new SmartHashSet<Integer>(23, 56, 99)));
    	assertEquals(new SmartTreeSet<Integer>(56, 23), 
    			smartSet2.intersection(new SmartTreeSet<Integer>(23, 56, 99)));
    }
    
    @Test
    public void testDifference() {
    	assertEquals(new SmartHashSet<Integer>(56, 23), 
    			smartSet1.difference(new SmartHashSet<Integer>(9)));
    	assertEquals(new SmartTreeSet<Integer>(56, 23), 
    			smartSet2.difference(new SmartTreeSet<Integer>(9)));
    }
}
