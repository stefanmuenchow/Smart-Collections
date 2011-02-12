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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public class SmartListTest {
    private SmartList<Integer> smartList1 = null;
    private SmartList<Integer> smartList2 = null;
    private SmartList<Integer> smartList3 = null;

    @Before
    public void setUp() throws Exception {
        smartList1 = new SmartArrayList<Integer>(9, 56, 23, 11, 67, 12, 9, 10);
        smartList2 = new SmartArrayList<Integer>(87, 13, 11, 56, 85, 19);
        smartList3 = new SmartArrayList<Integer>(1, 2, 3, 4, 5);
    }

    @Test
    public void testAddReturn() {
    	assertEquals(new SmartArrayList<Integer>(9, 56, 23, 11, 67, 12, 9, 10, 1), smartList1.addReturn(1));
    	assertEquals(new SmartArrayList<Integer>(87, 13, 11, 1, 56, 85, 19), smartList2.addReturn(3, 1));
    }
    
    @Test
    public void testAddAllReturn() {
    	List<Integer> toAdd = new ArrayList<Integer>();
    	toAdd.add(23);
    	toAdd.add(24);
    	
    	assertEquals(new SmartArrayList<Integer>(9, 56, 23, 11, 67, 12, 9, 10, 23, 24), smartList1.addAllReturn(toAdd));
    	assertEquals(new SmartArrayList<Integer>(87, 13, 11, 56, 23, 24, 85, 19), smartList2.addAllReturn(4, toAdd));
    }
    
    @Test
    public void testRemoveReturn() {
    	assertEquals(new SmartArrayList<Integer>(56, 23, 11, 67, 12, 9, 10), smartList1.removeReturn(9));
    }
    
    @Test
    public void testRemoveIndexReturn() {
    	 assertEquals(new SmartArrayList<Integer>(87, 13, 11, 85, 19), smartList2.removeIndexReturn(3));
    }
    
    @Test
    public void testRemoveAllReturn() {
    	List<Integer> toRemove = new ArrayList<Integer>();
    	toRemove.add(23);
    	toRemove.add(67);
    	
    	assertEquals(new SmartArrayList<Integer>(9, 56, 11, 12, 9, 10), smartList1.removeAllReturn(toRemove));
    }
    
    @Test
    public void testRetainAllReturn() {
    	List<Integer> toRetain = new ArrayList<Integer>();
    	toRetain.add(23);
    	toRetain.add(67);
    	toRetain.add(100);
    	
    	assertEquals(new SmartArrayList<Integer>(23, 67), smartList1.retainAllReturn(toRetain));
    }
    
    @Test
    public void testFind() {
        int value1 = smartList1.find(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input > 10;
            }
        });

        assertEquals(56, value1);
    }

    @Test(expected=NoSuchElementException.class)
    public void testFindFail() {
        smartList1.find(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input == 66;
            }
        });
    }

    @Test
    public void testFilter() {
		assertEquals(new SmartArrayList<Integer>(56),
				smartList2.filter(new Predicate<Integer>() {
					@Override
					public boolean test(final Integer input) {
						return input % 2 == 0;
					}
				}));
    }

    @Test
    public void testRemove() {
		assertEquals(new SmartArrayList<Integer>(56),
				smartList2.remove(new Predicate<Integer>() {
					@Override
					public boolean test(final Integer input) {
						return input % 2 == 1;
					}
				}));
    }

    @Test
    public void testReplace() {
		assertEquals(
				new SmartArrayList<Integer>(10, 56, 23, 11, 67, 12, 10, 10),
				smartList1.replace(9, 10).replace(90, 91));

		assertEquals(new SmartArrayList<Integer>(10, 13, 11, 56, 10, 19),
				smartList2.replace(new Predicate<Integer>() {
					@Override
					public boolean test(final Integer input) {
						return input > 80;
					}
				}, 10));
		
		Map<Integer, Integer> replacements = new HashMap<Integer, Integer>();
		replacements.put(3, 5);
		replacements.put(5, 10);
		assertEquals(new SmartArrayList<Integer>(1, 2, 5, 4, 10), smartList3.replace(replacements));
    }
    
    @Test
    public void testMap() {
		assertEquals(new SmartArrayList<Double>(87d, 13d, 11d, 56d, 85d, 19d),
				smartList2.map(new UnaryFunction<Double, Integer>() {
					@Override
					public Double apply(final Integer input) {
						return Integer.valueOf(input).doubleValue();
					}
				}));
    }

    @Test
    public void testReduce() {
		assertEquals(Integer.valueOf(197),
				smartList1.reduce(new BinaryFunction<Integer, Integer>() {
					@Override
					public Integer apply(final Integer input1,
							final Integer input2) {
						return input1 + input2;
					}
				}));

		assertEquals("ArrayList: 87 13 11 56 85 19", smartList2.reduce(
				"ArrayList:", new BinaryFunction<String, Integer>() {
					@Override
					public String apply(final String input1,
							final Integer input2) {
						return input1 + " " + String.valueOf(input2);
					}
				}));
    }

    @Test
    public void testJoin() {
        assertEquals("87|13|11|56|85|19", smartList2.join("|"));
    }

    @Test
    public void testCount() {
        assertEquals(3, smartList1.count(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input % 2 == 0;
            }
        }));
    }

    @Test
    public void testExists() {
        assertTrue(smartList2.exists(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input.equals(11);
            }
        }));

        assertFalse(smartList2.exists(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return (input / 3) == 5;
            }
        }));
    }

    @Test
    public void testForall() {
        assertTrue(smartList2.forall(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input.compareTo(100) < 0;
            }
        }));

        assertFalse(smartList2.forall(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input.compareTo(5) < 0;
            }
        }));
    }

    @Test
    public void testCastEach() {
        SmartCollection<Object> aColl = new SmartArrayList<Object>(3, 6, 8);
        SmartCollection<Integer> castedColl = aColl.castEach(Integer.class);

        assertEquals(new SmartArrayList<Integer>(3, 6, 8), castedColl);
    }

    @Test
    public void testToArray() {
        Integer[] anArray = smartList2.toArray(Integer.class);
        assertArrayEquals(new Integer[] { 87, 13, 11, 56, 85, 19 }, anArray);
    }

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

    @Test
    public void testDrop() {
        assertEquals(new SmartArrayList<Integer>(67, 12, 9, 10), smartList1.drop(4));
        assertEquals(new SmartArrayList<Integer>(), smartList1.drop(50));
    }

    @Test
    public void testTakeWhile() {
        assertEquals(new SmartArrayList<Integer>(87, 13, 11), smartList2.takeWhile(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input % 2 == 1;
            }
        }));
    }

    @Test
    public void testDropWhile() {
        assertEquals(new SmartArrayList<Integer>(56, 85, 19), smartList2.dropWhile(new Predicate<Integer>() {
            @Override
            public boolean test(final Integer input) {
                return input % 2 == 1;
            }
        }));
    }

    @Test
    public void testRemoveDuplicates() {
        SmartList<Integer> testList = new SmartArrayList<Integer>(1,1,1,2,3,3,3,4,5);
        testList.removeDuplicates();

        assertEquals(new SmartArrayList<Integer>(1,2,3,4,5), testList);
    }

    @Test
    public void testIntersperse() {
        SmartList<Integer> testList = new SmartArrayList<Integer>(1,2,3);
        testList.intersperse(0);
        assertEquals(new SmartArrayList<Integer>(1,0,2,0,3), testList);

        testList = new SmartArrayList<Integer>();
        testList.intersperse(0);
        assertEquals(new SmartArrayList<Integer>(), testList);
    }

    @Test
    public void testZipWith() {
        SmartMap<Integer, String> resultMap = new SmartArrayList<Integer>(4,5,6).zipWith(
                new SmartArrayList<String>("foo", "bar", "baz"));

        SmartMap<Integer, String> expectedMap = new SmartHashMap<Integer, String>();
        expectedMap.put(4, "foo");
        expectedMap.put(5, "bar");
        expectedMap.put(6, "baz");

        assertEquals(expectedMap, resultMap);
    }

    @Test
    public void testGetIndicesList() {
        assertEquals(new SmartArrayList<Integer>(0,1,2,3,4),
                new SmartArrayList<String>("a", "b", "c", "d", "e").getIndicesList());
        assertEquals(new SmartArrayList<Integer>(), new SmartArrayList<String>());
    }

    @Test
    public void testGetOccurenceCountMap() {
        SmartMap<Character, Integer> resultMap =
            new SmartArrayList<Character>('a', 'a', 'c', 'a', 'c', 'x').getOccurenceCountMap();

        SmartMap<Character, Integer> expectedMap = new SmartHashMap<Character, Integer>();
        expectedMap.put('a', 3);
        expectedMap.put('c', 2);
        expectedMap.put('x', 1);

        assertEquals(expectedMap, resultMap);
    }

    @Test
    public void testReverse() {
        SmartList<Integer> testList = new SmartArrayList<Integer>(2,3,1);
        assertEquals(new SmartArrayList<Integer>(1,3,2), testList.reverse());
        assertEquals(new SmartArrayList<Integer>(2,3,1), testList.reverse());
    }

    @Test
    public void testSizeWithoutNulls() {
        assertEquals(5, new SmartArrayList<Integer>(1,null,3,2,null).size());
        assertEquals(3, new SmartArrayList<Integer>(1,null,3,2,null).sizeWithoutNulls());
    }
}
