package redis.collection.utils;

import lombok.RequiredArgsConstructor;
import redis.collection.exception.NoSuchRedisMapException;
import redis.collection.map.RedisMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import static redis.collection.utils.ExceptionMessages.NO_MAP_WITH_KEY_MESSAGE;

@Component
@RequiredArgsConstructor
public class JedisMapManager {
    private Map<String, RedisMap> redisMaps = new HashMap<>();
    private final JedisClusterManager clusterManager;

    @PostConstruct
    private void initMaps() {
        for (int i = 0; ; i++) {
            final String mapKey = RedisMap.class.getSimpleName() + "::" + i;
            final Map<String, String> clusterMap = clusterManager.getCluster().hgetAll(mapKey);
            if (clusterMap.isEmpty()) {
                return;
            }
            createMap();
        }
    }

    public RedisMap createMap() {
        RedisMap redisMap = new RedisMap(clusterManager.getCluster());
        redisMaps.put(redisMap.getMapKey(), redisMap);
        return redisMap;
    }

    public RedisMap createMap(String key, Integer value) {
        RedisMap redisMap = new RedisMap(clusterManager.getCluster(), key, value);
        redisMaps.put(redisMap.getMapKey(), redisMap);
        return redisMap;
    }

    public RedisMap createMap(Map<? extends String, ? extends Integer> map) {
        final RedisMap redisMap = new RedisMap(clusterManager.getCluster(), map);
        redisMaps.put(redisMap.getMapKey(), redisMap);
        return redisMap;
    }

    public RedisMap get(String mapKey) {
        if (Objects.isNull(mapKey)) {
            throw new IllegalArgumentException("Map key can't be null");
        }
        final RedisMap redisMap = redisMaps.get(mapKey);
        if (Objects.isNull(redisMap)) {
            throw new NoSuchRedisMapException(String.format(NO_MAP_WITH_KEY_MESSAGE, mapKey));
        }
        return redisMap;
    }

    public Set<String> getMapsKeys() {
        return redisMaps.keySet();
    }

    public void clearAllMaps() {
        for (Map.Entry<String, RedisMap> entry : redisMaps.entrySet()) {
            final RedisMap value = entry.getValue();
            value.clear();
        }
    }

    public void clearMap(String mapKey) {
        redisMaps.get(mapKey).clear();
    }

}
