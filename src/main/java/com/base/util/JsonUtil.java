package com.base.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.Primitives;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class JsonUtil {

    private static final Gson GSON = new Gson();
    private static final JsonParser JSON_PARSER = new JsonParser();

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T parseJson(String json, Class<T> tClass) {
        return Primitives.wrap(tClass).cast(GSON.fromJson(json, tClass));
    }

    /**
     * 纯json数组: [{},{}...]
     *
     * @param tClass 数组内元素的运行时类
     */
    public static <T> List<T> parseJsonArray(String json, Class<T> tClass) {

        JsonElement jsonElement = JSON_PARSER.parse(json);
        if (!jsonElement.isJsonArray()) {
            return Collections.emptyList();
        }

        JsonArray jsonArray = jsonElement.getAsJsonArray();
        final List<T> list = new ArrayList<>(jsonArray.size());
        Iterator<JsonElement> iterator = jsonArray.iterator();
        iterator.forEachRemaining((e) -> {
            list.add(GSON.fromJson(e, tClass));
        });

        return list;
    }

}
