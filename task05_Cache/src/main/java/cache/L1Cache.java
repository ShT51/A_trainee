package cache;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class L1Cache<K, V> implements Cache<K, V>, FrequencyCall<K> {

    private HashMap<K, V> cacheStore;
    private HashMap<K, Integer> frequencyStore;

    public L1Cache() {
        cacheStore = new HashMap<>();
        frequencyStore = new HashMap<>();
    }

    public HashMap<K, V> getCacheStore() {
        return cacheStore;
    }

    @Override
    public void cache(K key, V value) {
        frequencyStore.put(key, 1);
        cacheStore.put(key, value);
    }

    @Override
    public V getObject(K key) {
        if (cacheStore.containsKey(key)) {
            int frequency = frequencyStore.get(key);
            frequencyStore.put(key, ++frequency);
            return cacheStore.get(key);
        }
        return null;
    }

    @Override
    public boolean deleteFromCache(K key) {
        boolean result = false;
        if (cacheStore.containsKey(key)) {
            cacheStore.remove(key);
            frequencyStore.remove(key);
            result = true;
        }
        return result;
    }

    @Override
    public void clearCache() {
        cacheStore.clear();
        frequencyStore.clear();
    }

    @Override
    public V removeFromCache(K key) {
        if (cacheStore.containsKey(key)) {
            frequencyStore.remove(key);
            return cacheStore.remove(key);
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return cacheStore.containsKey(key);
    }

    @Override
    public int size() {
        return cacheStore.size();
    }

    @Override
    public Collection<Integer> getFrequencyValueSet() {
        return frequencyStore.values();
    }

    @Override
    public Set<K> getFrequencyKeySet() {
        return new HashSet<>(frequencyStore.keySet());
    }

    @Override
    public int getFrequencyOfCallingObject(K key) {
        Integer result;
        if ((result = frequencyStore.get(key)) == null) {
            result = 0;
        }
        return result;
    }
}

