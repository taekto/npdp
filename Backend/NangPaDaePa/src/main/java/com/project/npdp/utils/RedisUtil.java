package com.project.npdp.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisUtil {

    private final StringRedisTemplate template;

    public String getDate(String key){
        ValueOperations<String, String> valueOperations = template.opsForValue();
        return valueOperations.get(key);
    }

    // value가 존재 확인
    public boolean existData(String key){
        return Boolean.TRUE.equals(template.hasKey(key));
    }

    // key-value 쌍을 저장하되, 만료시간을 정할 수 있음
    public void setDataExpire(String key, String value, long duration){
        ValueOperations<String, String> valueOperations = template.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, expireDuration);
    }

    public void deleteData(String key){
        template.delete(key);
    }

}
