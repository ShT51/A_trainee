package cache;

import java.util.Collection;
import java.util.Set;

public interface FrequencyCall<K> {

    Set<K> getFrequencyKeySet();

    Collection<Integer> getFrequencyValueSet();

    int getFrequencyOfCallingObject(K key);

}
