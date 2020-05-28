package cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class L1CacheTest {

    private L1Cache<String, Object> l1Cache = new L1Cache<>();
    private Object one = "one";
    private Object two = "two";
    private Object three = "three";

    @BeforeEach
    void cache() {
        l1Cache.cache("1", one);
        l1Cache.cache("2", two);
        l1Cache.cache("3", three);
    }

    @AfterEach
    void clearCache() {
        l1Cache.clearCache();
    }

    @Test
    void getObject() {
        assertEquals("two", l1Cache.getObject("2"));
        assertEquals("two", l1Cache.getObject("2"));
        assertEquals(3, l1Cache.getFrequencyOfCallingObject("2"));
    }

    @Test
    void deleteObject() {
        assertEquals(3, l1Cache.size());
        l1Cache.deleteFromCache("1");
        assertEquals(2, l1Cache.size());
    }

    @Test
    void removeObject() {
        assertEquals("one", l1Cache.removeFromCache("1"));
    }

    @Test
    void containsKey() {
        assertTrue(l1Cache.containsKey("1"));
    }

    @Test
    void getFrequencyKeySet() {
        Set<String> expectedSet = new HashSet<>(Arrays.asList("1", "2", "3"));
        assertEquals(expectedSet, l1Cache.getFrequencyKeySet());
    }
}