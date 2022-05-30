package redis.collection.utils.map;

import redis.clients.jedis.ConnectionPool;
import redis.clients.jedis.JedisCluster;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

public class RedisMap implements Map<String, Integer> {

    public static final AtomicInteger count = new AtomicInteger(0);
    private final JedisCluster cluster;
    private String mapKey;

    public RedisMap(final JedisCluster cluster) {
        this.mapKey = this.getClass().getSimpleName() + "::" + count.getAndIncrement();
        this.cluster = cluster;
    }

    public RedisMap(final JedisCluster cluster, String key, Integer value) {
        this.cluster = cluster;
        this.mapKey = this.getClass().getSimpleName() + "::" + count.getAndIncrement();
        put(key, value);
    }

    public RedisMap(final JedisCluster cluster, Map<? extends String, ? extends Integer> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("Passed map can't be null or empty");
        }
        this.cluster = cluster;
        this.mapKey = this.getClass().getSimpleName() + "::" + count.getAndIncrement();
        putAll(map);
    }

    @Override
    public int size() {
        return cluster.hgetAll(mapKey).size();
    }

    @Override
    public boolean isEmpty() {
        return cluster.hgetAll(mapKey).isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return cluster.hgetAll(mapKey).containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return cluster.hgetAll(mapKey).containsValue(value);
    }

    @Override
    public Integer get(final Object key) {
        return Integer.valueOf(cluster.hget(mapKey, valueOf(key)));
    }


    @Override
    public Integer put(final String key, final Integer value) {
        return (int) cluster.hset(mapKey, key, value.toString());
    }

    @Override
    public Integer remove(final Object key) {
        return (int) cluster.hdel(mapKey, String.valueOf(key));
    }

    @Override
    public void putAll(final Map<? extends String, ? extends Integer> m) {
        m.forEach((key, value) -> cluster.hset(mapKey, key, String.valueOf(value)));
    }

    @Override
    public void clear() {
        cluster.del(mapKey);
    }

    @Override
    public Set<String> keySet() {
        return cluster.hgetAll(mapKey).keySet();
    }

    @Override
    public Collection<Integer> values() {
        return cluster.hgetAll(mapKey).values().stream().map(Integer::valueOf).collect(Collectors.toSet());
    }

    @Override
    public Set<Entry<String, Integer>> entrySet() {
        Map<String, Integer> map = new HashMap<>();
        cluster.hgetAll(mapKey).forEach((key, value) -> map.put(key, Integer.valueOf(value)));
        return map.entrySet();
    }

    public String getMapKey(){
        return mapKey;
    }

}
