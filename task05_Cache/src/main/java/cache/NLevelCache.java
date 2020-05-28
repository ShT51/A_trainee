package cache;

import java.io.IOException;

public interface NLevelCache<K, V> extends Cache<K, V>, FrequencyCall<K> {
    //void recache() throws IOException, ClassNotFoundException;

    void recacheRam();

    void recacheHdd();
}
