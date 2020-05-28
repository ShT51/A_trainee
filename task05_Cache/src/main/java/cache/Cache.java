package cache;

import java.io.IOException;

public interface Cache<K, V> {
    void cache(K key, V value);

    V getObject(K key);

    boolean deleteFromCache(K key);

    void clearCache();

    V removeFromCache(K key);

    boolean containsKey(K key);

    int size();
}
