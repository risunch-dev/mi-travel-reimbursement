package com.xiaomi.info.service;

import java.util.List;

public interface RedisService {
    /*
     * 判断key是否存在
     * 
     * @param key 对应的key
     * @return boolean 是否存在
     */
    public boolean hasKey(String key);

    /*
     * 设置过期时间
     * 
     * @param key 对应的key
     * @param time 过期时间
     * @return boolean 是否设置成功
     */
    public boolean expire(String key, long time);

    /*
     * 获取过期时间
     * 
     * @param key 对应的key
     * @return long 过期时间
     */
    public long getTime(String key);

    /*
     * 移除过期时间
     * 
     * @param key 对应的key
     * @return boolean 是否移除成功
     */
    public boolean persist(String key);

    /*
     * 获取key对应的value
     * 
     * @param key 对应的key
     * @return String 对应的value
     */
    public String get(String key);

    /*
     * 批量获取key对应的value
     * 
     * @param keys 对应的key
     * @return List<String> 对应的value
     */
    public List<String> batchGet(List<String> keys);

    /*
     * 设置key对应的value
     * 
     * @param key 对应的key
     * @param value 对应的value
     */
    public void set(String key, String value);

    /*
     * 设置key对应的value和过期时间
     * 
     * @param key 对应的key
     * @param value 对应的value
     * @param time 过期时间 -1表示永不过期
     */
    public void set(String key, String value, long time);

    /*
     * 删除key
     * 
     * @param key 对应的key
     */
    public void del(String key);
}
