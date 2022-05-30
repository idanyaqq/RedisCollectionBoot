package redis.collection.utils;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisCluster;
import redis.collection.config.RedisClusterProperties;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JedisClusterManager {

    private JedisCluster cluster;
    private final RedisClusterProperties redisClusterProperties;

    public JedisCluster getCluster() {
        if (Objects.isNull(cluster)) {
            cluster = new JedisCluster(redisClusterProperties.getHostAndPorts());
        }
        return cluster;
    }

    public void refreshCluster() {
        cluster = new JedisCluster(redisClusterProperties.getHostAndPorts());
    }

}
