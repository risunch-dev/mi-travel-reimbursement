package com.xiaomi.info.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.exception.BasicRunException;
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
        if (stringRedisTemplate.hasKey(key) == null) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "异常");
        } else {
            return stringRedisTemplate.hasKey(key);
        }
    }

    @Override
    public boolean expire(String key, long time) {
        if (stringRedisTemplate.expire(key, time, TimeUnit.SECONDS)) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "异常");
        } else {
            return stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    @Override
    public long getTime(String key) {
        try {
            if (stringRedisTemplate.getExpire(key, TimeUnit.SECONDS) == null) {
                return -1;
            } else {
                return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "异常");
        }
    }

    @Override
    public boolean persist(String key) {
        try {
            if(stringRedisTemplate.persist(key) == null) {
                return false;
            } else {
                return stringRedisTemplate.persist(key);
            }
        } catch (Exception e) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "异常");
        }
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
