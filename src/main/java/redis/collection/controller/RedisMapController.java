package redis.collection.controller;

import lombok.RequiredArgsConstructor;
import redis.collection.domain.AddMapValueRequest;
import redis.collection.service.RedisMapService;

import java.util.Collection;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/redis/maps")
@RequiredArgsConstructor
public class RedisMapController {

    private final RedisMapService service;

    @GetMapping("/keys")
    public Set<String> getAllKeys() {
        return service.getKeys();
    }

    @GetMapping("/{mapKey}/values")
    public Collection<Integer> getValues(@PathVariable String mapKey) {
        return service.getValues(mapKey);
    }

    @PostMapping("/{mapKey}")
    public void putValue(@PathVariable String mapKey, @RequestBody AddMapValueRequest addMapValueRequest) {
        service.put(mapKey, addMapValueRequest.getKey(), addMapValueRequest.getValue());
    }

    @DeleteMapping("/{mapKey}")
    public void clearMap(@PathVariable String mapKey) {
        service.clearMap(mapKey);
    }

    @DeleteMapping("/{mapKey}/keys/{key}")
    public void remove(@PathVariable String mapKey, @PathVariable String key) {
        service.remove(mapKey, key);
    }


}
