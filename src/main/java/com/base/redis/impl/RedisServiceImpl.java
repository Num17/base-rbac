package com.base.redis.impl;

import com.base.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

    private StringRedisTemplate redisTemplate;

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

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
