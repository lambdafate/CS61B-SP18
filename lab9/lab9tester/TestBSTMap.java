package lab9tester;

import lab9.BSTMap;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Tests by Brendan Hu, Spring 2015, revised for 2018 by Josh Hug
 */
public class TestBSTMap {

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<String, String>();
            BSTMap<String, Integer> b = new BSTMap<String, Integer>();
            BSTMap<Integer, String> c = new BSTMap<Integer, String>();
            BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i));
            assertTrue(b.get("hi" + i).equals(1 + i));
            assertTrue(b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null, b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertTrue(b.get("hi") != null);
    }

    // test get/put/size
    @Test
    public void testGetPutSize() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertEquals(b.size(), 0);
        assertEquals(b.get("first"), null);
        b.put("first", 10);
        assertEquals(b.get("first"), (Integer) 10);
        assertEquals(b.size(), 1);
        b.put("first", 20);
        b.put("aaa", 10);
        b.put("zzz", 30);
        assertEquals(b.size(), 3);
        assertEquals(b.get("first"), (Integer) 20);
    }

    // test keySet
    @Test
    public void testKeySet() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("f", 10);
        map.put("a", 1);
        map.put("b", 20);
        BSTMap<String, Integer> b = new BSTMap<>();
        int size = 0;
        for (String key : map.keySet()) {
            b.put(key, map.get(key));
            size += 1;
            assertEquals(b.size(), size);
        }
        assertEquals(b.keySet().size(), map.keySet().size());
        for (String key : b.keySet()) {
            assertEquals(map.get(key), b.get(key));
        }
    }


    // test remove
    @Test
    public void testRemove() {
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("f", 10);
        map.put("a", 1);
        map.put("b", 20);
        assertEquals(map.size(), 3);
        map.remove("f", 100);
        assertEquals(map.size(), 3);
        assertEquals(map.get("f"), (Integer) 10);
        map.remove("f");
        assertEquals(map.size(), 2);
        assertEquals(null, map.get("f"));

    }

    // test iterator
    @Test
    public void testIterator() {
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("b", 10);
        map.put("a", 1);
        map.put("c", 20);
        String[] keys = {"a", "b", "c"};
        int index = 0;
        for (String k : map) {
            // System.out.println(k);
            assertEquals(keys[index++], k);
        }
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }
}
