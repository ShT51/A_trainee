package cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TwoLevelCacheTest {

    private TwoLevelCache<String, String> twoLevelCache = new TwoLevelCache<>(2, 5, 2);
    private String one = "one";
    private String two = "two";
    private String three = "three";
    private String four = "four";
    private String five = "five";

    @BeforeEach
    void cache() {
        twoLevelCache.cache("1", one);
        twoLevelCache.cache("2", two);
        twoLevelCache.cache("3", three);
        twoLevelCache.cache("4", four);
        twoLevelCache.cache("5", five);
    }

    @AfterEach
    void clearCache() {
        twoLevelCache.clearCache();
    }

    @Test
    void getObject() {
        assertEquals("one", twoLevelCache.getObject("1"));
        assertEquals("five", twoLevelCache.getObject("5"));
    }

    @Test
    void deleteObject() {
        assertEquals(5, twoLevelCache.size());
        twoLevelCache.deleteFromCache("1");
        assertEquals(4, twoLevelCache.size());
    }

    @Test
    void removeObject() {
        assertEquals("one", twoLevelCache.removeFromCache("1"));
        assertEquals("five", twoLevelCache.removeFromCache("5"));
        assertEquals(3, twoLevelCache.size());
    }

    @Test
    void retrieveFromHddToRamCache() {
        twoLevelCache.getObject("1");
        twoLevelCache.getObject("1");
        twoLevelCache.getObject("3");
        twoLevelCache.getObject("3");
        ArrayList<String> ramCache = new ArrayList<>(twoLevelCache.getL1Cache().getCacheStore().values());

        assertTrue(ramCache.containsAll(Arrays.asList(one, three)));


    }
}