package com.dayuanit.emall.service.impl;

import com.dayuanit.emall.service.JedisService;
import com.dayuanit.emall.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("redisTempServiceImpl")
public class RedisTempServiceImpl implements JedisService, InitializingBean{

    public static final Logger log = LoggerFactory.getLogger(RedisTempServiceImpl.class);

    /**
     * 注入redisTemplate，自带两个序列化一个是JDK的但不好用，另外一个就是String的序列化
     * RedisTemplate中默认序列化是JDK序列化，但自己也可设置keySerializer或者valueSerializer序列化等，并且自身也带有stringSerializer
     * 该序列化工具有序列化和反序列化两种方法，分别将String序列化成bytes[]数组存到硬盘中，和将bytes[]数组反序列化成String
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * StringRedisTemplate继承RedisTemplate，并且自带String序列化功能，redis最终放的东西如对象之类的最终都要序列化成字符串再存储
     * StringRedisTemplate构造的时候帮你序列化好，但此时因为我们的值不需要String序列化所以不使用StringRedisTemplate
     *
     * @Autowired
     * private StringRedisTemplate<String, Object> stringRedisTemplate;
     */

    /**
     * 使用Spring的Editor机制
     */
    @Resource(name="redisTemplate")
    private ValueOperations<String, List<Map<String, String>>> provinceOper;

    /**
     * 因为需要使用redis的队列操作所以使用List操作，而且key和value都是String
     */
    @Resource(name="redisTemplate")
    private ListOperations<String, String> orderQueue;

    @Autowired
    private OrderService orderService;

    @Override
    public boolean haskey(String key) {
        /**
         * 使用该方法返回值为ValueOperations<K, V>，这是一个操作字符串类型的对象类，此对象类中就有set,get方法
         */
//        redisTemplate.opsForValue(String, Object);
        return redisTemplate.hasKey(key);
    }

    @Override
    public void setProvince(String key, List<Map<String, String>> value) {
        log.info(">>>>>>>>>>>>将省份信息存储在redis中");
        provinceOper.set(key, value);
    }

    @Override
    public List<Map<String, String>> getProvince(String key) {
        return provinceOper.get(key);
    }

    @Override
    public void setCity(String key, List<Map<String, String>> value) {
        log.info(">>>>>>>>>>>>>>>>>>将城市信息存储在redis中");
        provinceOper.set(key, value);
    }

    @Override
    public List<Map<String, String>> getCity(String key) {
        return provinceOper.get(key);
    }

    @Override
    public void setArea(String key, List<Map<String, String>> value) {
        log.info(">>>>>>>>>>>>>>>>>>>将区域信息存储在redis中");
        provinceOper.set(key, value);
    }

    @Override
    public List<Map<String, String>> getAres(String key) {
        return provinceOper.get(key);
    }

    /**
     * 操作队列 从队列右边取值
     */
    @Override
    public String popOrder() {
        String key = "dayuanit:pay:order";
        String orderInfo = orderQueue.rightPop(key);
        log.info(">>>orderInfo:{}", orderInfo);
        //使用apache lang3下的String 工具类来判断orderInfo的数据是否为空
        if (StringUtils.isBlank(orderInfo)) {
            try {
//                log.info(">>>队列无值，等待三秒");
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        //数据格式bizId$payId
        try {
            log.info(">>>订单支付数据{}", orderInfo);
            String msg[] = orderInfo.split("\\$");
            String orderId = msg[0];
            String payId = msg[1];

            orderService.processPayResult(Integer.parseInt(orderId), payId);
        } catch (Throwable e) {
            orderQueue.leftPush(key, orderInfo);
            log.error("处理支付成功的订单失败",e);
        }

        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    popOrder();
                }
            }
        },"处理订单支付成功").start();
    }
}
