package redis.collection.service;

import java.util.Collection;
import java.util.Set;

public interface RedisMapService {

    void fillMap();

    void clearMap(String key);

    void clearAllMaps();

    void put(String mapKey, String key, Integer value);

    Integer get(String mapKey, String key);


    Set<String> getKeys();

    Collection<Integer> getValues(String mapKey);

    void remove(String mapKey, String key);


}
