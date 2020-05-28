package cache;

import java.io.*;
import java.util.*;

public class L2Cache<K, V> implements Cache<K, V>, FrequencyCall<K> {
    private HashMap<K, String> cacheStore;
    private HashMap<K, Integer> frequencyStore;

    public L2Cache() {
        cacheStore = new HashMap<>();
        frequencyStore = new HashMap<>();
    }


    public HashMap<K, String> getCacheStore() {
        return cacheStore;
    }

    @Override
    public void cache(K key, V value) {
        String pathToObject = "temp" + File.separator + UUID.randomUUID().toString() + ".temp";

        frequencyStore.put(key, 1);
        cacheStore.put(key, pathToObject);

        try (FileOutputStream fileStream = new FileOutputStream(pathToObject);
             ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            objectStream.writeObject(value);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public V getObject(K key) {
        V result = null;

        if (cacheStore.containsKey(key)) {

            String pathToObject = cacheStore.get(key);

            try (FileInputStream fileStream = new FileInputStream(pathToObject);
                 ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {

                result = (V) objectStream.readObject();

                int count = frequencyStore.get(key);
                frequencyStore.put(key, ++count);

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean deleteFromCache(K key) {
        boolean result = false;

        if (cacheStore.containsKey(key)) {
            File deletingFile = new File(cacheStore.remove(key));
            frequencyStore.remove(key);
            result = deletingFile.delete();
        }
        return result;
    }

    @Override
    public void clearCache() {
        cacheStore.keySet().forEach(key -> new File(cacheStore.get(key)).delete());
        cacheStore.clear();
        frequencyStore.clear();
    }

    @Override
    public V removeFromCache(K key) {
        if (cacheStore.containsKey(key)) {
            V result = getObject(key);
            deleteFromCache(key);
            return result;
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
