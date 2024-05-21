package com.xiaomi.info.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xiaomi.info.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public boolean expire(String key, long time) {
        return stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public long getTime(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public boolean persist(String key) {
        return stringRedisTemplate.persist(key);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public List<String> batchGet(List<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override 
    public void set(String key, String value, long time) {
        if (time > 0){
            stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            stringRedisTemplate.opsForValue().set(key, value);
        } 
    }

    @Override
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }
}
