package cache;

public interface NLevelCache<K, V> extends Cache<K, V>, FrequencyCall<K> {
    //void recache() throws IOException, ClassNotFoundException;

    void recacheRam();

    void recacheHdd();
}
