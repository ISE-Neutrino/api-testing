package com.customers.utils;

import com.google.gson.Gson;

public class JsonConverter {
    private static final Gson gson = new Gson();

    /**
     * Convert json string to object
     * @param <T>
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * Convert object to json string
     * @param src
     * @return
     */
    public static String toJson(Object src) {
        return gson.toJson(src);
    }
}
