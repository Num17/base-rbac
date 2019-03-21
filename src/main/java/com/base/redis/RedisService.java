package com.base.redis;

import java.util.Map;

public interface RedisService {

    void hashSetFromMap(String key, Map<String, String> map);

    String hashGet(String key, String field);

    void stringSet(String key, String value);

}
