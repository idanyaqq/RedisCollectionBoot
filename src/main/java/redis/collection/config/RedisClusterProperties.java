package redis.collection.config;

import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.HostAndPort;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Component
@ConfigurationProperties(prefix = "redis.cluster")
public class RedisClusterProperties {

    private List<RedisNodeProperty> nodes;

    public Set<HostAndPort> getHostAndPorts() {
        return nodes.stream()
                .map(prop -> new HostAndPort(prop.getHost(), prop.getPort()))
                .collect(Collectors.toSet());
    }

}
