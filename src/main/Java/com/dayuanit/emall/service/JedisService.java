package com.dayuanit.emall.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JedisService {

    boolean haskey(String key);

    void setProvince(String key, List<Map<String, String>> value);

    List<Map<String, String>> getProvince(String key);

    void setCity(String key, List<Map<String, String>> value);

    List<Map<String, String>> getCity(String key);

    void setArea(String key, List<Map<String, String>> value);

    List<Map<String, String>> getAres(String key);

    String popOrder();

    void saveCartId(List<Integer> cartIds, int userId);

    Set<Integer> getCartId(int userId);

    void delKey(String key);
}
