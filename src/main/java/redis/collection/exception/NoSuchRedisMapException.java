package redis.collection.exception;

public class NoSuchRedisMapException extends RuntimeException {

    public NoSuchRedisMapException() {
        super();
    }

    public NoSuchRedisMapException(final String message) {
        super(message);
    }

}
