package com.dayuanit.emall.service;

import java.util.List;
import java.util.Map;

public interface JedisService {

    boolean haskey(String key);

    void setProvince(String key, List<Map<String, String>> value);

    List<Map<String, String>> getProvince(String key);

    void setCity(String key, List<Map<String, String>> value);

    List<Map<String, String>> getCity(String key);

    void setArea(String key, List<Map<String, String>> value);

    List<Map<String, String>> getAres(String key);
}
