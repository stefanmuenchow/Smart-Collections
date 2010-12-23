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

import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.MapPredicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public class SmartMapTest {
    private SmartMap<Integer, String> smartMap1 = null;
    private SmartMap<Integer, String> smartMap2 = null;

    @Before
    public void setUp() throws Exception {
        smartMap1 = new SmartHashMap<Integer, String>();
        smartMap1.put(1, "Frodo");
        smartMap1.put(2, "Sam");
        smartMap1.put(3, "Merry");
        smartMap1.put(4, "Pippin");
        smartMap2 = new SmartHashMap<Integer, String>();
        smartMap2.put(1, "Aragorn");
        smartMap2.put(2, "Boromir");
        smartMap2.put(3, "Legolas");
        smartMap2.put(4, "Gimli");
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
        SmartMap<Integer, String> someMap = new SmartHashMap<Integer, String>(smartMap1);
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
        smartMap1.mergeWith(smartMap2, new BinaryFunction<String, String>() {

            @Override
            public String apply(final String input1, final String input2) {
                return input1 + " and " + input2;
            }
        });

        assertEquals("Frodo and Aragorn", smartMap1.get(1));
    }

    @Test
    public void testGet() {
        assertEquals("Gimli", smartMap2.get(4, "Gandalf"));
        assertEquals("Gandalf", smartMap2.get(10, "Gandalf"));
    }

    @Test
    public void testFind() {
        String value = smartMap1.find(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.startsWith("P");
            }
        });

        assertEquals("Pippin", value);
    }

    @Test(expected=NoSuchElementException.class)
    public void testFindFail() {
        smartMap1.find(new MapPredicate<Integer, String>() {

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

        smartMap1.filter(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.startsWith("S") || val.startsWith("M");
            }
        });

        assertEquals(expectedMap, smartMap1);
    }

    @Test
    public void testRemove() {
        SmartMap<Integer, String> expectedMap = new SmartHashMap<Integer, String>();
        expectedMap.put(2, "Sam");
        expectedMap.put(3, "Merry");

        smartMap1.remove(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.startsWith("F") || val.startsWith("P");
            }
        });

        assertEquals(expectedMap, smartMap1);
    }

    @Test
    public void testReplace() {
        SmartMap<Integer, String> expectedMap = new SmartHashMap<Integer, String>();
        expectedMap.put(2, "Sam");
        expectedMap.put(3, "Merry");
        expectedMap.put(4, "Pippin");
        expectedMap.put(5, "Bilbo");

        smartMap1.replace(1, "Frodo", 5, "Bilbo");
        assertEquals(expectedMap, smartMap1);

        SmartMap<Integer, String> expectedMap2 = new SmartHashMap<Integer, String>(smartMap2);
        smartMap2.replace(3, "Gimli", 1, "Frodo");
        assertEquals(expectedMap2, smartMap2);
    }

    @Test
    public void testMap() {
        SmartMap<Integer,Integer> expectedMap = new SmartHashMap<Integer, Integer>();
        expectedMap.put(1, "Aragorn".length());
        expectedMap.put(2, "Boromir".length());
        expectedMap.put(3, "Legolas".length());
        expectedMap.put(4, "Gimli".length());

        SmartMap<Integer, Integer> resultMap =
            smartMap2.map(new UnaryFunction<KeyValuePair<Integer,Integer>, Map.Entry<Integer,String>>() {

                @Override
                public KeyValuePair<Integer, Integer> apply(final Entry<Integer, String> input) {
                    return new KeyValuePair<Integer, Integer>(input.getKey(), input.getValue().length());
                }
            });

        assertEquals(expectedMap, resultMap);
    }

    @Test
    public void testReduce() {
        String result = "FrodoSamMerryPippin";
        assertEquals(result, smartMap1.reduce("", new BinaryFunction<String, Map.Entry<Integer, String>>() {

            @Override
            public String apply(final String input1, final Entry<Integer, String> input2) {
                return input1.concat(input2.getValue());
            }
        }));
    }

    @Test
    public void testJoin() {
        String expectedString = "1 -> Frodo ; 2 -> Sam ; 3 -> Merry ; 4 -> Pippin";
        assertEquals(expectedString, smartMap1.join(" ; ", " -> "));
    }

    @Test
    public void testCount() {
        assertEquals(2, smartMap1.count(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return key > 2;
            }
        }));
    }

    @Test
    public void testExists() {
        assertTrue(smartMap2.exists(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return val.contains("romi");
            }
        }));
    }

    @Test
    public void testForall() {
        assertTrue(smartMap2.forall(new MapPredicate<Integer, String>() {

            @Override
            public boolean test(final Integer key, final String val) {
                return key < 10 && val.length() < 10;
            }
        }));
    }

    @Test
    public void testIsBijective() {
        assertTrue(smartMap1.isBijective());

        smartMap2.put(5, "Aragorn");
        assertFalse(smartMap2.isBijective());
    }

    @Test
    public void testSwap() {
        SmartMap<String, Integer> expectedMap = new SmartHashMap<String, Integer>();
        expectedMap.put("Frodo", 1);
        expectedMap.put("Sam", 2);
        expectedMap.put("Merry", 3);
        expectedMap.put("Pippin", 4);

        assertEquals(expectedMap, smartMap1.swap());
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testSwapFail() {
        smartMap1.put(5, "Frodo");
        smartMap1.swap();
    }
}
