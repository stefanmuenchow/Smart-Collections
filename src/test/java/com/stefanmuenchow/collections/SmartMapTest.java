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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.MapBinaryFunction;
import com.stefanmuenchow.collections.function.MapPredicate;
import com.stefanmuenchow.collections.function.MapUnaryFunction;

public class SmartMapTest {
    private SmartMap<Integer, String> smartMap = null;

    @Before
    public void setUp() throws Exception {
        smartMap = new SmartHashMap<Integer, String>(
        		new SmartArrayList<Integer>(1, 2, 3, 4),
        		new SmartArrayList<String>("Frodo", "Sam", "Merry", "Pippin"));
    }
    
    @Test
    public void testPutReturn() {
    	SmartMap<Integer, String> expected = new SmartHashMap<Integer, String>(
        		new SmartArrayList<Integer>(1, 2, 3, 4, 5),
        		new SmartArrayList<String>("Frodo", "Sam", "Merry", "Pippin", "Legolas"));
    	
    	assertEquals(expected, smartMap.putReturn(5, "Legolas"));
    }
    
    @Test
    public void testPutAllReturn() {
    	SmartMap<Integer, String> expected = new SmartHashMap<Integer, String>(
        		new SmartArrayList<Integer>(1, 2, 3, 4, 5, 6),
        		new SmartArrayList<String>("Frodo", "Sam", "Merry", "Pippin", "Legolas", "Gandalf"));
    	
    	Map<Integer, String> toAdd = new HashMap<Integer, String>();
    	toAdd.put(5, "Legolas");
    	toAdd.put(6, "Gandalf");
    	
    	assertEquals(expected, smartMap.putAllReturn(toAdd));
    }
    
    @Test
    public void testRemoveReturn() {
    	SmartMap<Integer, String> expected = new SmartHashMap<Integer, String>(
        		new SmartArrayList<Integer>(1, 2, 4),
        		new SmartArrayList<String>("Frodo", "Sam", "Pippin"));
    	
    	assertEquals(expected, smartMap.removeReturn(3));
    }

    @Test
    public void testHead() {
        SmartMap<Integer, String> singleMap = new SmartHashMap<Integer, String>();
        singleMap.put(43, "Gandalf");

        assertEquals(Integer.valueOf(43), singleMap.head().getKey());
        assertEquals("Gandalf", singleMap.head().getValue());
    }

    @Test(expected=NoSuchElementException.class)
    public void testHeadFail() {
        new SmartHashMap<Integer, String>().head();
    }

    @Test
    public void testTail() {
        SmartMap<Integer, String> someMap = new SmartHashMap<Integer, String>(smartMap);
        SmartMap<Integer, String> anotherMap = someMap.tail();
        someMap.remove(someMap.head().getKey());

        assertEquals(someMap, anotherMap);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testTailFail() {
        new SmartHashMap<Integer, String>().tail();
    }

    @Test
    public void testMergeWith() {
    	SmartMap<Integer, String> toMerge = new SmartHashMap<Integer, String>(
    			new SmartArrayList<Integer>(1), 
    			new SmartArrayList<String>("Aragorn"));
    	
        smartMap.mergeWith(toMerge, new BinaryFunction<String, String>() {
            @Override
            public String apply(final String input1, final String input2) {
                return input1 + " and " + input2;
            }
        });

        assertEquals("Frodo and Aragorn", smartMap.get(1));
    }

    @Test
    public void testGet() {
        assertEquals("Sam", smartMap.get(2, "Sauron"));
        assertEquals("Sauron", smartMap.get(5, "Sauron"));
    }

    @Test
    public void testFind() {
        String value = smartMap.find(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.startsWith("P");
            }
        });

        assertEquals("Pippin", value);
    }

    @Test(expected=NoSuchElementException.class)
    public void testFindFail() {
        smartMap.find(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.equals("Sauron");
            }
        });
    }

    @Test
    public void testFilter() {
        SmartMap<Integer, String> expectedMap = new SmartHashMap<Integer, String>();
        expectedMap.put(2, "Sam");
        expectedMap.put(3, "Merry");

        smartMap.filter(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.startsWith("S") || val.startsWith("M");
            }
        });

        assertEquals(expectedMap, smartMap);
    }

    @Test
    public void testRemove() {
        SmartMap<Integer, String> expectedMap = new SmartHashMap<Integer, String>();
        expectedMap.put(2, "Sam");
        expectedMap.put(3, "Merry");

        smartMap.remove(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.startsWith("F") || val.startsWith("P");
            }
        });

        assertEquals(expectedMap, smartMap);
    }

    @Test
    public void testReplace() {
        SmartMap<Integer, String> expectedMap = new SmartHashMap<Integer, String>();
        expectedMap.put(2, "Sam");
        expectedMap.put(3, "Merry");
        expectedMap.put(4, "Pippin");
        expectedMap.put(5, "Bilbo");

        smartMap.replace(1, "Frodo", 5, "Bilbo");
        assertEquals(expectedMap, smartMap);
    }

    @Test
    public void testMap() {
        SmartMap<Integer,Integer> expectedMap = new SmartHashMap<Integer, Integer>();
        expectedMap.put(1, "Frodo".length());
        expectedMap.put(2, "Sam".length());
        expectedMap.put(3, "Merry".length());
        expectedMap.put(4, "Pippin".length());

        SmartMap<Integer, Integer> resultMap =
            smartMap.map(new MapUnaryFunction<KeyValuePair<Integer,Integer>, Integer, String>() {

                @Override
                public KeyValuePair<Integer, Integer> apply(Integer key, String val) {
                    return new KeyValuePair<Integer, Integer>(key, val.length());
                }
            });

        assertEquals(expectedMap, resultMap);
    }

    @Test
    public void testReduce() {
        String result = "FrodoSamMerryPippin";
        assertEquals(result, smartMap.reduce("", new MapBinaryFunction<String, Integer, String>() {

            @Override
            public String apply(final String input1, Integer key, String val) {
                return input1.concat(val);
            }
        }));
    }

    @Test
    public void testJoin() {
        String expectedString = "1 -> Frodo ; 2 -> Sam ; 3 -> Merry ; 4 -> Pippin";
        assertEquals(expectedString, smartMap.join(" ; ", " -> "));
    }

    @Test
    public void testCount() {
        assertEquals(2, smartMap.count(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return key > 2;
            }
        }));
    }

    @Test
    public void testExists() {
        assertTrue(smartMap.exists(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.contains("er");
            }
        }));
    }

    @Test
    public void testForall() {
        assertTrue(smartMap.forall(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return key < 10 && val.length() < 10;
            }
        }));
    }

    @Test
    public void testIsBijective() {
        assertTrue(smartMap.isBijective());

        smartMap.put(5, "Frodo");
        assertFalse(smartMap.isBijective());
    }

    @Test
    public void testSwap() {
        SmartMap<String, Integer> expectedMap = new SmartHashMap<String, Integer>();
        expectedMap.put("Frodo", 1);
        expectedMap.put("Sam", 2);
        expectedMap.put("Merry", 3);
        expectedMap.put("Pippin", 4);

        assertEquals(expectedMap, smartMap.swap());
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testSwapFail() {
        smartMap.put(5, "Frodo");
        smartMap.swap();
    }
    
    @Test
    public void testToStandardMap() {
    	Map<Integer, String> expected = new HashMap<Integer, String>();
    	expected.put(1, "Frodo");
    	expected.put(2, "Sam");
    	expected.put(3, "Merry");
    	expected.put(4, "Pippin");
    	
    	assertEquals(expected, smartMap.toStandardMap());
    }
}
