package com.base.redis;

import java.util.List;
import java.util.Map;

public interface RedisService {

    //hash method
    void hashSetFromMap(String key, Map<String, String> map);

    String hashGet(String key, String field);

    Map<String, String> hashEntries(String key);

    List<String> hashValues(String key);

    //string method
    void stringSet(String key, String value);


}
