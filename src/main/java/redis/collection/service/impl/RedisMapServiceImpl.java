package redis.collection.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redis.collection.service.RedisMapService;
import redis.collection.utils.JedisMapManager;
import redis.collection.map.RedisMap;

import java.util.Collection;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisMapServiceImpl implements RedisMapService {

    private final JedisMapManager mapManager;

    private final static Integer FILL_AMOUNT = 15000;

    @Override
    public void fillMap() {
        RedisMap map = mapManager.createMap();
        IntStream.range(0, FILL_AMOUNT).forEach(i -> map.put(i + "name", i));
    }

    @Override
    public void clearMap(final String key) {
        mapManager.clearMap(key);
    }

    @Override
    public void clearAllMaps() {
        mapManager.clearAllMaps();
    }

    @Override
    public void put(final String mapKey, final String key, final Integer value) {
        final RedisMap redisMap = mapManager.get(mapKey);
        redisMap.put(key, value);
    }

    @Override
    public Integer get(final String mapKey, final String key) {
        return mapManager.get(mapKey).get(key);
    }

    @Override
    public Set<String> getKeys() {
        return mapManager.getMapsKeys();
    }

    @Override
    public Collection<Integer> getValues(final String mapKey) {
        return mapManager.get(mapKey).values();
    }

    @Override
    public void remove(final String mapKey, final String key) {
        mapManager.get(mapKey).remove(key);
    }

}
