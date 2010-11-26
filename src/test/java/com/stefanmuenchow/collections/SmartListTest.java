package com.stefanmuenchow.collections;

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

        Assert.assertEquals(56, value1);
        Assert.assertNull(value2);
    }

    @Test
    public void testFilter() {
        smartList2.filter(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input % 2 == 0;
            }
        });

        Assert.assertEquals(new SmartArrayList<Integer>(56), smartList2);
    }

    @Test
    public void testRemove() {
        smartList2.remove(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input % 2 == 1;
            }
        });

        Assert.assertEquals(new SmartArrayList<Integer>(56), smartList2);
    }

    @Test
    public void testReplace() {
        // Replace specific value
        smartList1.replace(9, 10);
        smartList1.replace(90, 91);

        Assert.assertEquals(new SmartArrayList<Integer>(10, 56, 23, 11, 67, 12,
                10, 10), smartList1);

        // Replace value that fits predicate
        smartList2.replace(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input > 80;
            }
        }, 10);

        Assert.assertEquals(
                new SmartArrayList<Integer>(10, 13, 11, 56, 10, 19), smartList2);
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

        Assert.assertEquals(197, result);

        // With initial value
        String stringList = smartList2.reduce("ArrayList:",
                new BinaryFunction<String, String, Integer>() {
                    @Override
                    public String execute(final String input1,
                            final Integer input2) {
                        return input1 + " " + String.valueOf(input2);
                    }
                });

        Assert.assertEquals("ArrayList: 87 13 11 56 85 19", stringList);
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

        Assert.assertEquals(3, count);
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

        Assert.assertTrue(exist);

        // Predicate that doesn't match
        exist = smartList2.exists(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return (input / 3) == 5;
            }
        });

        Assert.assertFalse(exist);
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

        Assert.assertTrue(allTrue);

        // Predicate that doesn't match
        allTrue = smartList2.forall(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input.compareTo(5) < 0;
            }
        });

        Assert.assertFalse(allTrue);
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(smartList2.equals(smartList2));
        Assert.assertTrue(smartList2.equals(new SmartArrayList<Integer>(87, 13,
                11, 56, 85, 19)));
        Assert.assertFalse(smartList2.equals(smartList1));
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

        Assert.assertEquals(new SmartArrayList<Double>(87d, 13d, 11d, 56d, 85d,
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

        Assert.assertEquals(new SmartArrayList<Integer>(5, 3, 1, 2, 3, 6, 7, 6,
                5, 4, 1, 3, 8), deepList.flatten());
    }
}
