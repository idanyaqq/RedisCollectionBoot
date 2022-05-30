package redis.collection.controller;

import lombok.RequiredArgsConstructor;
import redis.collection.service.RedisClusterService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/redis/cluster")
@RequiredArgsConstructor
public class RedisClusterController {

    private final RedisClusterService service;

    @PostMapping
    public void refreshCluster() {
        service.refreshCluster();
    }

}
