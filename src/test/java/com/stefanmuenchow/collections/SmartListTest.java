package com.stefanmuenchow.collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public class SmartListTest {
    private SmartList<Integer> smartList1 = null;
    private SmartList<Integer> smartList2 = null;

    @Before
    public void setUp() throws Exception {
        smartList1 = new SmartArrayList<Integer>(9, 56, 23, 11, 67, 12, 9, 10);
        smartList2 = new SmartArrayList<Integer>(87, 13, 11, 56, 85, 19);
    }

    /** Test SmartCollection methods */

    @Test
    public void testFind() {
        // With predicate that matches
        int value1 = smartList1.find(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input > 10;
            }
        });

        // With predicate that doesn't match
        Integer value2 = smartList1.find(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input == 66;
            }
        });

        assertEquals(56, value1);
        assertNull(value2);
    }

    @Test
    public void testFilter() {
        smartList2.filter(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input % 2 == 0;
            }
        });

        assertEquals(new SmartArrayList<Integer>(56), smartList2);
    }

    @Test
    public void testRemove() {
        smartList2.remove(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input % 2 == 1;
            }
        });

        assertEquals(new SmartArrayList<Integer>(56), smartList2);
    }

    @Test
    public void testReplace() {
        // Replace specific value
        smartList1.replace(9, 10);
        smartList1.replace(90, 91);

        assertEquals(new SmartArrayList<Integer>(10, 56, 23, 11, 67, 12,
                10, 10), smartList1);

        // Replace value that fits predicate
        smartList2.replace(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input > 80;
            }
        }, 10);

        assertEquals(new SmartArrayList<Integer>(10, 13, 11, 56, 10, 19), smartList2);
    }

    @Test
    public void testReduce() {
        // Without initial value
        int result = smartList1
                .reduce(new BinaryFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer execute(final Integer input1,
                            final Integer input2) {
                        return input1 + input2;
                    }
                });

        assertEquals(197, result);

        // With initial value
        String stringList = smartList2.reduce("ArrayList:",
                new BinaryFunction<String, String, Integer>() {
                    @Override
                    public String execute(final String input1,
                            final Integer input2) {
                        return input1 + " " + String.valueOf(input2);
                    }
                });

        assertEquals("ArrayList: 87 13 11 56 85 19", stringList);
    }

    @Test
    public void testJoin() {
        Assert.assertEquals("87|13|11|56|85|19", smartList2.join("|"));
    }

    @Test
    public void testCount() {
        int count = smartList1.count(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input % 2 == 0;
            }
        });

        assertEquals(3, count);
    }

    @Test
    public void testExists() {
        // Predicate that matches
        boolean exist = smartList2.exists(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input.equals(11);
            }
        });

        assertTrue(exist);

        // Predicate that doesn't match
        exist = smartList2.exists(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return (input / 3) == 5;
            }
        });

        assertFalse(exist);
    }

    @Test
    public void testForall() {
        // Predicate that matches
        boolean allTrue = smartList2.forall(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input.compareTo(100) < 0;
            }
        });

        assertTrue(allTrue);

        // Predicate that doesn't match
        allTrue = smartList2.forall(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input.compareTo(5) < 0;
            }
        });

        assertFalse(allTrue);
    }

    @Test
    public void testEquals() {
        assertTrue(smartList2.equals(smartList2));
        assertTrue(smartList2.equals(new SmartArrayList<Integer>(87, 13,
                11, 56, 85, 19)));
        assertFalse(smartList2.equals(smartList1));
    }

    @Test
    public void testMap() {
        SmartList<Double> doubleList = smartList2
                .map(new UnaryFunction<Double, Integer>() {
                    @Override
                    public Double execute(final Integer input) {
                        return Integer.valueOf(input).doubleValue();
                    }
                });

        assertEquals(new SmartArrayList<Double>(87d, 13d, 11d, 56d, 85d,
                19d), doubleList);
    }

    @Test
    public void testFlatten() {
        @SuppressWarnings("unchecked")
        SmartList<SmartList<SmartList<Integer>>> deepList = new SmartArrayList<SmartList<SmartList<Integer>>>(
                new SmartArrayList<SmartList<Integer>>(
                        new SmartArrayList<Integer>(5, 3),
                        new SmartArrayList<Integer>(1, 2, 3)),
                new SmartArrayList<SmartList<Integer>>(
                        new SmartArrayList<Integer>(6),
                        new SmartArrayList<Integer>(7, 6, 5, 4)),
                new SmartArrayList<SmartList<Integer>>(
                        new SmartArrayList<Integer>(1, 3),
                        new SmartArrayList<Integer>(8)));

        assertEquals(new SmartArrayList<Integer>(5, 3, 1, 2, 3, 6, 7, 6,
                5, 4, 1, 3, 8), deepList.flatten());
    }

    @Test
    public void testCastAllElements() {
        SmartCollection<Object> aColl = new SmartArrayList<Object>(3, 6, 8);
        SmartCollection<Integer> castedColl = aColl.castAllElements(Integer.class);

        assertEquals(new SmartArrayList<Integer>(3, 6, 8), castedColl);
    }

    @Test
    public void testToArray() {
        Integer[] anArray = smartList2.ToArray(Integer.class);
        assertArrayEquals(new Integer[] { 87, 13, 11, 56, 85, 19 }, anArray);
    }

    /** Test SmartList methods */

    @Test
    public void testHead() {
        assertEquals(Integer.valueOf(9), smartList1.head());
    }

    @Test(expected= NoSuchElementException.class)
    public void testHeadFail() {
        new SmartArrayList<Object>().head();
    }

    @Test
    public void testTail() {
        assertEquals(new SmartArrayList<Integer>(56, 23, 11, 67, 12, 9, 10), smartList1.tail());
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testTailFail() {
        new SmartArrayList<Object>().tail();
    }

    @Test
    public void testLast() {
        assertEquals(new Integer(10), smartList1.last());
    }

    @Test(expected= NoSuchElementException.class)
    public void testLastFail() {
        new SmartArrayList<Object>().last();
    }

    @Test
    public void testGet() {
        assertEquals(Integer.valueOf(11), smartList1.get(3, -100));
        assertEquals(Integer.valueOf(-100), smartList1.get(20, -100));
    }

    @Test
    public void testTake() {
        assertEquals(new SmartArrayList<Integer>(87, 13, 11), smartList2.take(3));
        assertEquals(new SmartArrayList<Integer>(1, 2), new SmartArrayList<Integer>(1, 2).take(8));
        assertEquals(new SmartArrayList<Integer>(), new SmartArrayList<Integer>().take(8));
    }
}
