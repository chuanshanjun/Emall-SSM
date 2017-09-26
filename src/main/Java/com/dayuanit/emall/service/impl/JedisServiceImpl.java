package com.dayuanit.emall.service.impl;

import com.alibaba.fastjson.JSON;
import com.dayuanit.emall.datautils.JedisUtils;
import com.dayuanit.emall.service.JedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("jedisServiceImpl")//因为有两个实现类继承JedisService所以要用名字区分开来
public class JedisServiceImpl implements JedisService {

    Logger log = LoggerFactory.getLogger(JedisServiceImpl.class);

    /**
     * jedis序列化的只接收两种值String bytes[]
     */
    @Override
    public boolean haskey(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            return jedis.exists(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void setProvince(String key, List<Map<String, String>> value) {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            String valueStr = JSON.toJSONString(value);//将list序列化成json
            log.info("=============保存省份内容:{}", valueStr);
            jedis.set(key, valueStr);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public List<Map<String, String>> getProvince(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            List list = JSON.parseArray(jedis.get(key), Map.class);//将取出来的数据反序列化
            return list;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void setCity(String key, List<Map<String, String>> value) {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            String valueStr = JSON.toJSONString(value);
            log.info(">>>>>>>>>>保存市地址:{}", valueStr);
            jedis.set(key, valueStr);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public List<Map<String, String>> getCity(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            String msg = jedis.get(key);
            List list = JSON.parseArray(msg, Map.class);
            return list;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void setArea(String key, List<Map<String, String>> value) {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            String valueStr = JSON.toJSONString(value);
            log.info(">>>>>>>>>>>将城市信息存入缓存{}", valueStr);
            jedis.set(key,valueStr);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public List<Map<String, String>> getAres(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            String msg = jedis.get(key);
            List list = JSON.parseArray(msg, Map.class);
            return list;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public String popOrder() {
        return null;
    }

    @Override
    public void saveCartId(List<Integer> cartIds, int userId) {

    }

    @Override
    public Set<Integer> getCartId(int userId) {
        return null;
    }

    @Override
    public void delKey(String key) {

    }

}
