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

public class SmartSetTest {
    private SmartSet<Integer> smartSet1 = null;
    private SmartSet<Integer> smartSet2 = null;

    @Before
    public void setUp() throws Exception {
        smartSet1 = new SmartHashSet<Integer>(9, 56, 23, 11, 67, 12, 10);
        smartSet2 = new SmartHashSet<Integer>(87, 13, 11, 56, 85, 19);
    }

    /** Test SmartCollection methods */

    @Test
    public void testFind() {
        int result1 = 0;
        for (int val : smartSet1) {
            if (val > 10) {
                result1 = val;
                break;
            }
        }

        int result2 = smartSet1.find(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input > 10;
            }
        });

        assertEquals(result1, result2);
    }

    @Test(expected=NoSuchElementException.class)
    public void testFindFail() {
        smartSet1.find(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input == 66;
            }
        });
    }

    @Test
    public void testFilter() {
        smartSet2.filter(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input % 2 == 0;
            }
        });

        assertEquals(new SmartHashSet<Integer>(56), smartSet2);
    }

    @Test
    public void testRemove() {
        smartSet2.remove(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input % 2 == 1;
            }
        });

        assertEquals(new SmartHashSet<Integer>(56), smartSet2);
    }

    @Test
    public void testReplace() {
        // Replace specific value
        smartSet1.replace(9, 10);
        assertEquals(new SmartHashSet<Integer>(56, 23, 11, 67, 12, 10), smartSet1);

        // Replace value that fits predicate
        smartSet2.replace(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input > 80;
            }
        }, 10);

        assertEquals(new SmartHashSet<Integer>(10, 13, 11, 56, 19), smartSet2);
    }

    @Test
    public void testReduce() {
        // Without initial value
        int result = smartSet1
        .reduce(new BinaryFunction<Integer, Integer>() {
            @Override
            public Integer apply(final Integer input1,
                    final Integer input2) {
                return input1 + input2;
            }
        });

        assertEquals(188, result);

        // With initial value
        result = smartSet1
        .reduce(1000, new BinaryFunction<Integer, Integer>() {
            @Override
            public Integer apply(final Integer input1,
                    final Integer input2) {
                return input1 + input2;
            }
        });

        assertEquals(1188, result);
    }

    @Test
    public void testJoin() {
        String result1 = "";
        for (Integer i : smartSet2)
        {
            result1 += String.valueOf(i);
            result1 += "|";
        }
        result1 = result1.substring(0, result1.length() - 1);

        assertEquals(result1, smartSet2.join("|"));
    }

    @Test
    public void testCount() {
        int count = smartSet1.count(new Predicate<Integer>() {
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
        boolean exist = smartSet2.exists(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input.equals(11);
            }
        });

        assertTrue(exist);

        // Predicate that doesn't match
        exist = smartSet2.exists(new Predicate<Integer>() {
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
        boolean allTrue = smartSet2.forall(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input.compareTo(100) < 0;
            }
        });

        assertTrue(allTrue);

        // Predicate that doesn't match
        allTrue = smartSet2.forall(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input.compareTo(5) < 0;
            }
        });

        assertFalse(allTrue);
    }

    @Test
    public void testEquals() {
        assertTrue(smartSet2.equals(smartSet2));
        assertTrue(smartSet2.equals(new SmartHashSet<Integer>(87, 13,
                11, 56, 85, 19)));
        assertFalse(smartSet2.equals(smartSet1));
    }

    @Test
    public void testMap() {
        SmartSet<Double> doubleList = smartSet2
        .map(new UnaryFunction<Double, Integer>() {
            @Override
            public Double apply(final Integer input) {
                return Integer.valueOf(input).doubleValue();
            }
        });

        assertEquals(new SmartHashSet<Double>(87d, 13d, 11d, 56d, 85d,
                19d), doubleList);
    }

    @Test
    public void testFlatten() {
        @SuppressWarnings("unchecked")
        SmartSet<SmartSet<SmartSet<Integer>>> deepList = new SmartHashSet<SmartSet<SmartSet<Integer>>>(
                new SmartHashSet<SmartSet<Integer>>(
                        new SmartHashSet<Integer>(5, 3),
                        new SmartHashSet<Integer>(1, 2, 3)),
                        new SmartHashSet<SmartSet<Integer>>(
                                new SmartHashSet<Integer>(6),
                                new SmartHashSet<Integer>(7, 6, 5, 4)),
                                new SmartHashSet<SmartSet<Integer>>(
                                        new SmartHashSet<Integer>(1, 3),
                                        new SmartHashSet<Integer>(8)));

        assertEquals(new SmartHashSet<Integer>(5, 3, 1, 2, 3, 6, 7, 6,
                5, 4, 1, 3, 8), deepList.flatten());
    }

    @Test
    public void testCastAllElements() {
        SmartCollection<Object> aColl = new SmartHashSet<Object>(3, 6, 8);
        SmartCollection<Integer> castedColl = aColl.castAllElements(Integer.class);

        assertEquals(new SmartHashSet<Integer>(3, 6, 8), castedColl);
    }

    @Test
    public void testToArray() {
        Integer[] firstArray = smartSet2.toArray(new Integer[0]);
        Integer[] secondArray = smartSet2.toArray(Integer.class);
        assertArrayEquals(firstArray, secondArray);
    }

    /** Test SmartSet methods */

    @Test
    public void testIsSubsetOf() {
        SmartSet<Integer> firstSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> secondSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> thirdSet = new SmartHashSet<Integer>(1,2,3,4);

        assertTrue(firstSet.isSubsetOf(secondSet));
        assertTrue(secondSet.isSubsetOf(firstSet));
        assertFalse(thirdSet.isSubsetOf(secondSet));
    }

    @Test
    public void testIsProperSubsetOf() {
        SmartSet<Integer> firstSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> secondSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> thirdSet = new SmartHashSet<Integer>(1,2,3,4,5);

        assertFalse(firstSet.isProperSubsetOf(secondSet));
        assertFalse(secondSet.isProperSubsetOf(firstSet));
        assertTrue(firstSet.isProperSubsetOf(thirdSet));
    }

    @Test
    public void testIsSupersetOf() {
        SmartSet<Integer> firstSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> secondSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> thirdSet = new SmartHashSet<Integer>(1,2,3,4);

        assertTrue(firstSet.isSupersetOf(secondSet));
        assertTrue(secondSet.isSupersetOf(firstSet));
        assertFalse(secondSet.isSupersetOf(thirdSet));
    }

    @Test
    public void testIsProperSupersetOf() {
        SmartSet<Integer> firstSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> secondSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> thirdSet = new SmartHashSet<Integer>(1,2,3,4,5);

        assertFalse(firstSet.isProperSupersetOf(secondSet));
        assertFalse(secondSet.isProperSupersetOf(firstSet));
        assertTrue(thirdSet.isProperSupersetOf(secondSet));
    }

    @Test
    public void testUnion() {
        SmartSet<Integer> firstSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> secondSet = new SmartHashSet<Integer>(4,5,6);
        firstSet.union(secondSet);

        assertEquals(new SmartHashSet<Integer>(1,2,3,4,5,6), firstSet);
    }

    @Test
    public void testIntersection() {
        SmartSet<Integer> firstSet = new SmartHashSet<Integer>(1,2,3);
        SmartSet<Integer> secondSet = new SmartHashSet<Integer>(4,2,1);
        firstSet.intersection(secondSet);

        assertEquals(new SmartHashSet<Integer>(1,2), firstSet);
    }

    @Test
    public void testDifference() {
        SmartSet<Integer> firstSet = new SmartHashSet<Integer>(1,2,3,4,5,6);
        SmartSet<Integer> secondSet = new SmartHashSet<Integer>(4,5,6);
        firstSet.difference(secondSet);

        assertEquals(new SmartHashSet<Integer>(1,2,3), firstSet);
    }
}
