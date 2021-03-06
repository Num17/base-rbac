package com.base.redis.impl;

import com.base.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

    private StringRedisTemplate redisTemplate;

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public String stringGet(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void hashSetFromMap(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public String hashGet(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    @Override
    public void stringSet(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Map<String, String> hashEntries(String key) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    @Override
    public List<String> hashValues(String key) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return hashOperations.values(key);
    }

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
