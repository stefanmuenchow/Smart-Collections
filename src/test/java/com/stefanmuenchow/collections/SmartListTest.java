package com.stefanmuenchow.collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.function.Predicate;

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
        // Find an element
        Integer value1 = smartList1.find(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input > 10;
            }
        });

        // If no element found, return null
        Integer value2 = smartList1.find(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input == 66;
            }
        });

        Assert.assertEquals(new Integer(56), value1);
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
        smartList2.replace(90, 91);

        // Replace value that fits predicate
        smartList2.replace(new Predicate<Integer>() {
            @Override
            public boolean check(final Integer input) {
                return input > 80;
            }
        }, 10);

        Assert.assertEquals(new SmartArrayList<Integer>(10, 56, 23, 11, 67, 12,
                10, 10), smartList1);

        Assert.assertEquals(
                new SmartArrayList<Integer>(10, 13, 11, 56, 10, 19), smartList2);
    }
}
