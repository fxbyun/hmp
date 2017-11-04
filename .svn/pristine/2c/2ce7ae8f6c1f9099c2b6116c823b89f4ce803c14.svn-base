package com.qiaobei.hmp.modules.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

/**
 * 采用redis hash存储的缓存
 */
public class RedisManager<K, HK, HV> {
    private static final Logger log = LoggerFactory.getLogger(RedisManager.class);

    private RedisTemplate<K, HV> template;

    private HashOperations<K, HK, HV> hashOperations;


    public RedisManager(RedisTemplate<K, HV> redisTemplate) {
        Assert.notNull(redisTemplate, "redis template cannot be null");
        this.template = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }


    /**
     * 初始化这个类
     */
    public void init() {
        Assert.notNull(this.template, "redis template cannot be null");
        this.hashOperations = this.template.opsForHash();
    }

    /**
     * 读取缓存
     *
     * @param key key值
     * @return hv
     */
    public HV get(K key, HK hashKey) {
        log.debug("从redis读取[" + key + "]的key[" + hashKey + "]");
        if (key == null) {
            return null;
        } else {
            return hashOperations.get(key, hashKey);
        }
    }

    /**
     * 设置缓存
     *
     * @param key       key
     * @param hashKey   hashKey
     * @param hashValue hashValue
     * @return hashValue
     */
    public HV put(K key, HK hashKey, HV hashValue) {
        log.trace("从redis存储[" + key + "]的key[" + hashKey + "]");
        hashOperations.put(key, hashKey, hashValue);
        return hashValue;
    }

    /**
     * 删除缓存
     *
     * @param key key
     * @return value
     */
    public HV remove(K key, HK hashKey) {
        log.trace("从redis删除[" + key + "]的key[" + hashKey + "]");
        HV _value = get(key, hashKey);
        hashOperations.delete(key, hashKey);
        return _value;
    }

    /**
     * 清空keyPrefix的key
     */
    public void clear(K key) {
        template.delete(key);
    }

    /**
     * 返回keyPrefix的数量
     *
     * @return int
     */
    public int size(K key) {
        return hashOperations.size(key).intValue();
    }

    /**
     * 返回keyPrefix中的所有key
     *
     * @return set key
     */
    public Set<HK> keys(K key) {
        return hashOperations.keys(key);
    }

    /**
     * 返回keyPrefix中的所有value
     *
     * @return list value
     */
    public Collection<HV> values(K key) {
        return hashOperations.values(key);
    }

    public HashOperations<K, HK, HV> getHashOperations() {
        return hashOperations;
    }

    public void setHashOperations(HashOperations<K, HK, HV> hashOperations) {
        this.hashOperations = hashOperations;
    }

    public RedisTemplate<K, HV> getTemplate() {
        return template;
    }

    public void setTemplate(RedisTemplate<K, HV> template) {
        this.template = template;
    }
}
