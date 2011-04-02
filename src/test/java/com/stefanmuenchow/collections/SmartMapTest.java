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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.functors.BinaryFunction;
import com.stefanmuenchow.functors.MapBinaryFunction;
import com.stefanmuenchow.functors.MapPredicate;
import com.stefanmuenchow.functors.MapUnaryFunction;

public class SmartMapTest {
    private SmartMap<Integer, String> smartMap1 = null;
    private SmartMap<Integer, String> smartMap2 = null;
    
    @Before
    public void setUp() throws Exception {
        smartMap1 = new SmartHashMap<Integer, String>(
        		new SmartArrayList<Integer>(1, 2, 3, 4),
        		new SmartArrayList<String>("Frodo", "Sam", "Merry", "Pippin"));
        
        smartMap2 = new SmartTreeMap<Integer, String>(
        		new SmartArrayList<Integer>(1, 2, 3, 4),
        		new SmartArrayList<String>("Frodo", "Sam", "Merry", "Pippin"));
    }
    
    @Test
    public void testPutReturn() {
    	SmartMap<Integer, String> expected1 = new SmartHashMap<Integer, String>(
        		new SmartArrayList<Integer>(1, 2, 3, 4, 5),
        		new SmartArrayList<String>("Frodo", "Sam", "Merry", "Pippin", "Legolas"));
    	SmartMap<Integer, String> expected2 = new SmartTreeMap<Integer, String>(expected1);
    	
    	assertEquals(expected1, smartMap1.putReturn(5, "Legolas"));
    	assertEquals(expected2, smartMap2.putReturn(5, "Legolas"));
    }
    
    @Test
    public void testPutAllReturn() {
    	SmartMap<Integer, String> expected1 = new SmartHashMap<Integer, String>(
        		new SmartArrayList<Integer>(1, 2, 3, 4, 5, 6),
        		new SmartArrayList<String>("Frodo", "Sam", "Merry", "Pippin", "Legolas", "Gandalf"));
    	SmartMap<Integer, String> expected2 = new SmartTreeMap<Integer, String>(expected1);
    	
    	Map<Integer, String> toAdd = new HashMap<Integer, String>();
    	toAdd.put(5, "Legolas");
    	toAdd.put(6, "Gandalf");
    	
    	assertEquals(expected1, smartMap1.putAllReturn(toAdd));
    	assertEquals(expected2, smartMap1.putAllReturn(toAdd));
    }
    
    @Test
    public void testRemoveReturn() {
    	SmartMap<Integer, String> expected1 = new SmartHashMap<Integer, String>(
        		new SmartArrayList<Integer>(1, 2, 4),
        		new SmartArrayList<String>("Frodo", "Sam", "Pippin"));
    	SmartMap<Integer, String> expected2 = new SmartTreeMap<Integer, String>(expected1);
    	
    	assertEquals(expected1, smartMap1.removeReturn(3));
    	assertEquals(expected2, smartMap1.removeReturn(3));
    }

    @Test
    public void testHead() {
        SmartMap<Integer, String> singleMap1 = new SmartHashMap<Integer, String>();
        singleMap1.put(43, "Gandalf");        
        SmartMap<Integer, String> singleMap2 = new SmartTreeMap<Integer, String>();
        singleMap2.put(43, "Gandalf");

        assertEquals(Integer.valueOf(43), singleMap1.head().getKey());
        assertEquals("Gandalf", singleMap1.head().getValue());
        assertEquals(Integer.valueOf(43), singleMap2.head().getKey());
        assertEquals("Gandalf", singleMap2.head().getValue());
    }

    public void testHeadFail() {
    	int numExceptions = 0;
    	
    	try {
    		new SmartHashMap<Integer, String>().head();
    	} catch (NoSuchElementException e) {
    		numExceptions++;
    	}
    	
    	try {
    		new SmartTreeMap<Integer, String>().head();
    	} catch (NoSuchElementException e) {
    		numExceptions++;
    	}
    	
    	assertEquals(2, numExceptions);        
    }

    @Test
    public void testTail() {
        SmartMap<Integer, String> tailMap1 = smartMap1.tail();
        smartMap1.remove(smartMap1.head().getKey());
        
        SmartMap<Integer, String> tailMap2 = smartMap2.tail();
        smartMap2.remove(smartMap2.head().getKey());

        assertEquals(smartMap1, tailMap1);
        assertEquals(smartMap2, tailMap2);
    }

    public void testTailFail() {
    	int numExceptions = 0;
    	
    	try {
    		new SmartHashMap<Integer, String>().tail();
    	} catch (NoSuchElementException e) {
    		numExceptions++;
    	}
    	
    	try {
    		new SmartTreeMap<Integer, String>().tail();
    	} catch (NoSuchElementException e) {
    		numExceptions++;
    	}
    	
    	assertEquals(2, numExceptions);
    }

    @Test
    public void testMergeWith() {
    	SmartMap<Integer, String> toMerge = new SmartHashMap<Integer, String>(
    			new SmartArrayList<Integer>(1), 
    			new SmartArrayList<String>("Aragorn"));

    	BinaryFunction<String, String> joinWithAnd = new BinaryFunction<String, String>() {
	        @Override
	        public String apply(final String input1, final String input2) {
	            return input1 + " and " + input2;
	        }
	    };
    	
        assertEquals("Frodo and Aragorn", smartMap1.mergeWith(toMerge, joinWithAnd).get(1));
        assertEquals("Frodo and Aragorn", smartMap2.mergeWith(toMerge, joinWithAnd).get(1));
    }

    @Test
    public void testGet() {
        assertEquals("Sam", smartMap1.get(2, "Sauron"));
        assertEquals("Sauron", smartMap1.get(5, "Sauron"));
        assertEquals("Sam", smartMap2.get(2, "Sauron"));
        assertEquals("Sauron", smartMap2.get(5, "Sauron"));
    }

    @Test
    public void testFind() {
    	MapPredicate<Integer, String> startsWithP = new MapPredicate<Integer, String>() {
            @Override
            public boolean test(final Integer key, final String val) {
                return val.startsWith("P");
            }
        };
    	
        assertEquals("Pippin", smartMap1.find(startsWithP));
        assertEquals("Pippin", smartMap2.find(startsWithP));
    }

    public void testFindFail() {
        MapPredicate<Integer, String> equalsSauron = new MapPredicate<Integer, String>() {
            @Override
            public boolean test(final Integer key, final String val) {
                return val.equals("Sauron");
            }
        };
        
        int numException = 0;
    	
        try {
        	smartMap1.find(equalsSauron);
        } catch (NoSuchElementException e) {
        	numException++;
        }
        
        try {
        	smartMap2.find(equalsSauron);
        } catch (NoSuchElementException e) {
        	numException++;
        }
    	
        assertEquals(2, numException);
    }

    @Test
    public void testFilter() {
        SmartMap<Integer, String> expectedMap1 = new SmartHashMap<Integer, String>();
        expectedMap1.put(2, "Sam");
        expectedMap1.put(3, "Merry");
        SmartMap<Integer, String> expectedMap2 = new SmartTreeMap<Integer, String>(expectedMap1);

        MapPredicate<Integer, String> startsWithSorM = new MapPredicate<Integer, String>() {
            @Override
            public boolean test(final Integer key, final String val) {
                return val.startsWith("S") || val.startsWith("M");
            }
        };
        
        assertEquals(expectedMap1, smartMap1.filter(startsWithSorM));
        assertEquals(expectedMap2, smartMap2.filter(startsWithSorM));
    }

    @Test
    public void testRemove() {
        SmartMap<Integer, String> expectedMap1 = new SmartHashMap<Integer, String>();
        expectedMap1.put(2, "Sam");
        expectedMap1.put(3, "Merry");
        SmartMap<Integer, String> expectedMap2 = new SmartTreeMap<Integer, String>(expectedMap1);

        MapPredicate<Integer, String> startsWithForP = new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.startsWith("F") || val.startsWith("P");
            }
        };

        assertEquals(expectedMap1, smartMap1.remove(startsWithForP));
        assertEquals(expectedMap2, smartMap2.remove(startsWithForP));
    }

    @Test
    public void testReplace() {
        SmartMap<Integer, String> expectedMap1 = new SmartHashMap<Integer, String>();
        expectedMap1.put(2, "Sam");
        expectedMap1.put(3, "Merry");
        expectedMap1.put(4, "Pippin");
        expectedMap1.put(5, "Bilbo");
        SmartMap<Integer, String> expectedMap2 = new SmartTreeMap<Integer, String>(expectedMap1);
        
        assertEquals(expectedMap1, smartMap1.replace(1, "Frodo", 5, "Bilbo"));
        assertEquals(expectedMap2, smartMap2.replace(1, "Frodo", 5, "Bilbo"));
    }

    @Test
    public void testMap() {
        SmartList<Tuple<Integer, Integer>> expected = new SmartArrayList<Tuple<Integer,Integer>>();
        expected.add(new Tuple<Integer, Integer>(1, "Frodo".length()));
        expected.add(new Tuple<Integer, Integer>(2, "Sam".length()));
        expected.add(new Tuple<Integer, Integer>(3, "Merry".length()));
        expected.add(new Tuple<Integer, Integer>(4, "Pippin".length()));

        MapUnaryFunction<Tuple<Integer, Integer>, Integer, String> valueLen = 
        	new MapUnaryFunction<Tuple<Integer,Integer>, Integer, String>() {
	            @Override
	            public Tuple<Integer, Integer> apply(Integer key, String val) {
	                return new Tuple<Integer, Integer>(key, val.length());
	            }
        	};

        assertEquals(expected, smartMap1.map(valueLen));
        assertEquals(expected, smartMap2.map(valueLen));
    }

    @Test
    public void testReduce() {
        String result = "FrodoSamMerryPippin";
        
        MapBinaryFunction<String, Integer, String> concat = 
        	new MapBinaryFunction<String, Integer, String>() {
	            @Override
	            public String apply(final String input1, Integer key, String val) {
	                return input1.concat(val);
	            }
	        };
        
        assertEquals(result, smartMap1.reduce("", concat));
        assertEquals(result, smartMap2.reduce("", concat));
    }

    @Test
    public void testJoin() {
        String expectedString = "1 -> Frodo ; 2 -> Sam ; 3 -> Merry ; 4 -> Pippin";
        
        assertEquals(expectedString, smartMap1.join(" ; ", " -> "));
        assertEquals(expectedString, smartMap2.join(" ; ", " -> "));
    }

    @Test
    public void testCount() {
    	MapPredicate<Integer, String> keyGreaterThan2 = 
    		new MapPredicate<Integer, String>() {
	            @Override
	            public boolean test(final Integer key, final String val) {
	                return key > 2;
	            }
	        };
    	
        assertEquals(2, smartMap1.count(keyGreaterThan2));
        assertEquals(2, smartMap2.count(keyGreaterThan2));
    }

    @Test
    public void testExists() {
    	MapPredicate<Integer, String> containsEr = new MapPredicate<Integer, String>() {
            @Override
            public boolean test(final Integer key, final String val) {
                return val.contains("er");
            }
        };
    	
        assertTrue(smartMap1.exists(containsEr));
        assertTrue(smartMap2.exists(containsEr));
    }

    @Test
    public void testForall() {
    	MapPredicate<Integer, String> keyAndValLess10 = 
    		new MapPredicate<Integer, String>() {
	            @Override
	            public boolean test(final Integer key, final String val) {
	                return key < 10 && val.length() < 10;
	            }
	        };
    	
        assertTrue(smartMap1.forall(keyAndValLess10));
        assertTrue(smartMap2.forall(keyAndValLess10));
    }

    @Test
    public void testIsBijective() {
        assertTrue(smartMap1.isBijective());
        assertTrue(smartMap2.isBijective());

        smartMap1.put(5, "Frodo");
        assertFalse(smartMap1.isBijective());
        
        smartMap2.put(5, "Frodo");
        assertFalse(smartMap2.isBijective());
    }

    @Test
    public void testSwap() {
        SmartMap<String, Integer> expectedMap1 = new SmartHashMap<String, Integer>();
        expectedMap1.put("Frodo", 1);
        expectedMap1.put("Sam", 2);
        expectedMap1.put("Merry", 3);
        expectedMap1.put("Pippin", 4);
        SmartMap<String, Integer> expectedMap2 = new SmartTreeMap<String, Integer>(expectedMap1);

        assertEquals(expectedMap1, smartMap1.swap());
        assertEquals(expectedMap2, smartMap2.swap());
    }

    public void testSwapFail() {
    	int numExceptions = 0;
    	
    	try {
    		smartMap1.put(5, "Frodo");
            smartMap1.swap();
    	} catch (UnsupportedOperationException e) {
    		numExceptions++;
    	}
    	
    	try {
    		smartMap2.put(5, "Frodo");
            smartMap2.swap();
    	} catch (UnsupportedOperationException e) {
    		numExceptions++;
    	}
        
    	assertEquals(2, numExceptions);
    }
    
    @Test
    public void testToStandardMap() {
    	Map<Integer, String> expected1 = new HashMap<Integer, String>();
    	expected1.put(1, "Frodo");
    	expected1.put(2, "Sam");
    	expected1.put(3, "Merry");
    	expected1.put(4, "Pippin");
    	Map<Integer, String> expected2 = new TreeMap<Integer, String>(expected1);
    	
    	assertEquals(expected1, smartMap1.toStandardMap());
    	assertEquals(expected2, smartMap2.toStandardMap());
    }
}
