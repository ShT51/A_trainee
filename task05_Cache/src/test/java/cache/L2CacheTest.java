package cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class L2CacheTest {

    private L2Cache<String, Object> rddCache = new L2Cache<>();
    private Object one = "one";
    private Object two = "two";
    private Object three = "three";

    @BeforeEach
    void cache() {
        rddCache.cache("1", one);
        rddCache.cache("2", two);
        rddCache.cache("3", three);
    }

    @AfterEach
    void clearCache() {
       rddCache.clearCache();
    }

    @Test
    void getObject() {
        assertEquals("two", rddCache.getObject("2"));
        assertEquals("two", rddCache.getObject("2"));
        assertEquals(3, rddCache.getFrequencyOfCallingObject("2"));
    }

    @Test
    void deleteObject() {
        assertEquals(3, rddCache.size());
        rddCache.deleteFromCache("1");
        assertEquals(2, rddCache.size());
    }

    @Test
    void removeObject() {
        assertEquals("one", rddCache.removeFromCache("1"));
    }

    @Test
    void containsKey() {
        assertTrue(rddCache.containsKey("1"));
    }

    @Test
    void getFrequencyKeySet() {
        Set<String> expectedSet = new HashSet<>(Arrays.asList("1", "2", "3"));
        assertEquals(expectedSet, rddCache.getFrequencyKeySet());
    }
}