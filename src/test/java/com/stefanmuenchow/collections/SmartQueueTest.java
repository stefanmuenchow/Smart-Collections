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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public class SmartQueueTest {
    private SmartQueue<Integer> smartQueue1 = null;
    private SmartQueue<Integer> smartQueue2 = null;

    @Before
    public void setUp() throws Exception {
        smartQueue1 = new SmartLinkedQueue<Integer>(9, 56, 23, 11, 67, 12, 9, 10);
        smartQueue2 = new SmartLinkedQueue<Integer>(87, 13, 11, 56, 85, 19);
    }

    /** Test SmartCollection methods */

    @Test
    public void testFind() {
        int value1 = smartQueue1.find(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input > 10;
            }
        });

        assertEquals(56, value1);
    }

    @Test(expected=NoSuchElementException.class)
    public void testFindFail() {
        smartQueue1.find(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input == 66;
            }
        });
    }

    @Test
    public void testFilter() {
        smartQueue2.filter(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input % 2 == 0;
            }
        });

        boolean a = smartQueue2.equals(new SmartLinkedQueue<Integer>(56));
        boolean b = new SmartLinkedQueue<Integer>(56).equals(smartQueue2);

        assertEquals(a, b);
        assertEquals(new SmartLinkedQueue<Integer>(56), smartQueue2);
    }

    @Test
    public void testRemove() {
        smartQueue2.remove(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input % 2 == 1;
            }
        });

        assertEquals(new SmartLinkedQueue<Integer>(56), smartQueue2);
    }

    @Test
    public void testReplace() {
        // Replace specific value
        smartQueue1.replace(9, 10);
        smartQueue1.replace(90, 91);

        assertEquals(new SmartLinkedQueue<Integer>(10, 56, 23, 11, 67, 12,
                10, 10), smartQueue1);

        // Replace value that fits predicate
        smartQueue2.replace(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input > 80;
            }
        }, 10);

        assertEquals(new SmartLinkedQueue<Integer>(10, 13, 11, 56, 10, 19), smartQueue2);
    }

    @Test
    public void testReduce() {
        // Without initial value
        int result = smartQueue1
        .reduce(new BinaryFunction<Integer, Integer>() {
            @Override
            public Integer apply(final Integer input1,
                    final Integer input2) {
                return input1 + input2;
            }
        });

        assertEquals(197, result);

        // With initial value
        String stringList = smartQueue2.reduce("ArrayList:",
                new BinaryFunction<String, Integer>() {
            @Override
            public String apply(final String input1,
                    final Integer input2) {
                return input1 + " " + String.valueOf(input2);
            }
        });

        assertEquals("ArrayList: 87 13 11 56 85 19", stringList);
    }

    @Test
    public void testJoin() {
        assertEquals("87|13|11|56|85|19", smartQueue2.join("|"));
    }

    @Test
    public void testCount() {
        int count = smartQueue1.count(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input % 2 == 0;
            }
        });

        assertEquals(3, count);
    }

    @Test
    public void testExists() {
        // Predicate that matches
        boolean exist = smartQueue2.exists(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input.equals(11);
            }
        });

        assertTrue(exist);

        // Predicate that doesn't match
        exist = smartQueue2.exists(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return (input / 3) == 5;
            }
        });

        assertFalse(exist);
    }

    @Test
    public void testForall() {
        // Predicate that matches
        boolean allTrue = smartQueue2.forall(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input.compareTo(100) < 0;
            }
        });

        assertTrue(allTrue);

        // Predicate that doesn't match
        allTrue = smartQueue2.forall(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input.compareTo(5) < 0;
            }
        });

        assertFalse(allTrue);
    }

    @Test
    public void testEquals() {
        assertTrue(smartQueue2.equals(smartQueue2));
        assertTrue(smartQueue2.equals(new SmartLinkedQueue<Integer>(87, 13,
                11, 56, 85, 19)));
        assertFalse(smartQueue2.equals(smartQueue1));
    }

    @Test
    public void testMap() {
        SmartQueue<Double> doubleList = smartQueue2
        .map(new UnaryFunction<Double, Integer>() {
            @Override
            public Double apply(final Integer input) {
                return Integer.valueOf(input).doubleValue();
            }
        });

        assertEquals(new SmartLinkedQueue<Double>(87d, 13d, 11d, 56d, 85d,
                19d), doubleList);
    }

    @Test
    public void testFlatten() {
        @SuppressWarnings("unchecked")
        SmartQueue<SmartQueue<SmartQueue<Integer>>> deepList = new SmartLinkedQueue<SmartQueue<SmartQueue<Integer>>>(
                new SmartLinkedQueue<SmartQueue<Integer>>(
                        new SmartLinkedQueue<Integer>(5, 3),
                        new SmartLinkedQueue<Integer>(1, 2, 3)),
                        new SmartLinkedQueue<SmartQueue<Integer>>(
                                new SmartLinkedQueue<Integer>(6),
                                new SmartLinkedQueue<Integer>(7, 6, 5, 4)),
                                new SmartLinkedQueue<SmartQueue<Integer>>(
                                        new SmartLinkedQueue<Integer>(1, 3),
                                        new SmartLinkedQueue<Integer>(8)));

        assertEquals(new SmartLinkedQueue<Integer>(5, 3, 1, 2, 3, 6, 7, 6,
                5, 4, 1, 3, 8), deepList.flatten());
    }

    @Test
    public void testCastAllElements() {
        SmartCollection<Object> aColl = new SmartLinkedQueue<Object>(3, 6, 8);
        SmartCollection<Integer> castedColl = aColl.castAllElements(Integer.class);

        assertEquals(new SmartLinkedQueue<Integer>(3, 6, 8), castedColl);
    }

    @Test
    public void testToArray() {
        Integer[] anArray = smartQueue2.toArray(Integer.class);
        assertArrayEquals(new Integer[] { 87, 13, 11, 56, 85, 19 }, anArray);
    }
}
