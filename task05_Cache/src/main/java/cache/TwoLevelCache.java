package cache;

import java.io.Serializable;
import java.util.*;

public class TwoLevelCache<K, V extends Serializable> implements NLevelCache<K, V> {
    private final L1Cache<K, V> l1Cache;
    private final L2Cache<K, V> l2Cache;
    private final int MAX_RAM_CACHE_CAPACITY;
    private final int MAX_HDD_CACHE_CAPACITY;
    private final int MAX_NUMBER_OF_REQUESTS_FOR_RECACHE;


    public TwoLevelCache(int maxRamCacheCapacity, int maxHddCacheCapacity, int maxNumberOfRequestsForRecache) {
        this.MAX_RAM_CACHE_CAPACITY = maxRamCacheCapacity;
        this.MAX_HDD_CACHE_CAPACITY = maxHddCacheCapacity;

        this.MAX_NUMBER_OF_REQUESTS_FOR_RECACHE = maxNumberOfRequestsForRecache;
        this.l1Cache = new L1Cache<>();
        this.l2Cache = new L2Cache<>();
    }


    public L1Cache<K, V> getL1Cache() {
        return l1Cache;
    }

    public L2Cache<K, V> getL2Cache() {
        return l2Cache;
    }

    @Override
    public void cache(K key, V value) {
        l1Cache.cache(key, value);
        if (checkMaxCapacity(l1Cache, MAX_RAM_CACHE_CAPACITY)) {
            recacheRam();
        }
    }

    private boolean checkMaxCapacity(Cache<K, V> cache, int maxCapacity) {
        boolean needToRecache = false;

        if (maxCapacity < cache.size()) {
            needToRecache = true;
        }
        return needToRecache;
    }

    @Override
    public V getObject(K key) {
        V result;

        if ((result = l1Cache.getObject(key)) != null) {
            return result;

        } else if ((result = l2Cache.getObject(key)) != null) {
            if (l2Cache.getFrequencyOfCallingObject(key) > MAX_NUMBER_OF_REQUESTS_FOR_RECACHE) {
                l1Cache.cache(key, result);
                l2Cache.removeFromCache(key);
            }
            return result;
        }
        return null;
    }


    @Override
    public boolean deleteFromCache(K key) {
        return l1Cache.deleteFromCache(key) || l2Cache.deleteFromCache(key);
    }

    @Override
    public void clearCache() {
        l2Cache.clearCache();
        l1Cache.clearCache();
    }


    @Override
    public V removeFromCache(K key) {
        V result;

        if ((result = l1Cache.removeFromCache(key)) == null) {
            if ((result = l2Cache.removeFromCache(key)) == null) {
                return null;
            }
        }
        return result;
    }

    @Override
    public boolean containsKey(K key) {
        return l1Cache.deleteFromCache(key) || l2Cache.deleteFromCache(key);
    }


    @Override
    public int size() {
        return l1Cache.size() + l2Cache.size();
    }

    @Override
    public void recacheRam() {
        int boundFrequency = getCacheBoundFrequency(l1Cache);

        Set<K> ramKeys = l1Cache.getFrequencyKeySet();

        for (K key : ramKeys) {
            if (l1Cache.getFrequencyOfCallingObject(key) <= boundFrequency) {
                l2Cache.cache(key, l1Cache.removeFromCache(key));

                if (checkMaxCapacity(l2Cache, MAX_HDD_CACHE_CAPACITY)) {
                    recacheHdd();
                }
            }
        }
    }

    @Override
    public void recacheHdd() {
        int boundFrequency = getCacheBoundFrequency(l2Cache);

        Set<K> hddKeys = new HashSet<>(l2Cache.getCacheStore().keySet());

        for (K key : hddKeys) {
            if (l2Cache.getFrequencyOfCallingObject(key) <= boundFrequency) {
                l2Cache.removeFromCache(key);
            }
        }
    }

    @Override
    public Set<K> getFrequencyKeySet() {
        TreeSet<K> set = new TreeSet<K>(l1Cache.getFrequencyKeySet());
        set.addAll(l2Cache.getFrequencyKeySet());
        return set;
    }


    @Override
    public int getFrequencyOfCallingObject(K key) {
        if (l1Cache.containsKey(key)) {
            return l1Cache.getFrequencyOfCallingObject(key);
        }
        if (l2Cache.containsKey(key)) {
            return l2Cache.getFrequencyOfCallingObject(key);
        }
        return 0;
    }

    private int getCacheBoundFrequency(FrequencyCall<K> cache) {
        return (int) cache.getFrequencyValueSet().stream()
                .mapToInt(x -> x)
                .summaryStatistics()
                .getAverage();
    }

    @Override
    public Collection<Integer> getFrequencyValueSet() {
        return l1Cache.getFrequencyValueSet();
    }
}
