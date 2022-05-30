package redis.collection.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redis.collection.service.RedisClusterService;
import redis.collection.utils.JedisClusterManager;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisClusterServiceImpl implements RedisClusterService {

    private final JedisClusterManager clusterManager;

    @Override
    public void refreshCluster() {
        clusterManager.refreshCluster();
    }


}
