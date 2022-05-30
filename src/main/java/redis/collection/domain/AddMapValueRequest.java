package redis.collection.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMapValueRequest {

    private String key;
    private Integer value;

}
