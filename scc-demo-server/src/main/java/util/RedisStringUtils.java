package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @Date 2020/3/1 0001 16:34
 */
@Component
public class RedisStringUtils {

    public static final String USER_CACHE_PREFIX = "user:";

    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key,String value,int expireSeconds){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value,expireSeconds, TimeUnit.SECONDS);
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }
}
