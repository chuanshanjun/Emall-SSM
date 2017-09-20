package com.dayuanit.emall.datautils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

    private static final JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.8.135");

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static String setKey(String key, String value) {
        Jedis jedis = null;

        try {
            jedis = pool.getResource();
            return jedis.set(key, value);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static String getKey(String key) {
        Jedis jedis = null;

        try {
            jedis = pool.getResource();
            return jedis.get(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static void main(String[] args) {
        JedisUtils.setKey("xxx", "buyongyia");

        String msg = JedisUtils.getKey("xxx");

        System.out.println(msg);
    }
}
