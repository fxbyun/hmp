package com.qiaobei.hmp.modules.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private static final Logger log = LoggerFactory.getLogger(ShiroRedisCache.class);

    private RedisManager<String, K, V> cache;

    private String key = "shiro_redis_cache";

    public ShiroRedisCache(RedisManager<String, K, V> cache) {
        Assert.notNull(cache, "Cache argument cannot be null");
        this.cache = cache;
    }

    public ShiroRedisCache(RedisManager<String, K, V> cache, String key) {
        Assert.notNull(cache, "Cache argument cannot be null");
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("Key argument cannot be null");
        }
        this.cache = cache;
        this.key = key;
    }

    @Override
    public V get(K hashKey) throws CacheException {
        if (hashKey == null) {
            return null;
        } else {
            return cache.get(this.key, hashKey);
        }
    }

    @Override
    public V put(K hashKey, V hashValue) throws CacheException {
        return cache.put(this.key, hashKey, hashValue);
    }

    @Override
    public V remove(K hashKey) throws CacheException {
        return cache.remove(this.key, hashKey);
    }

    @Override
    public void clear() throws CacheException {
        cache.clear(this.key);
    }

    @Override
    public int size() {
        return cache.size(this.key);
    }

    @Override
    public Set<K> keys() {
        return cache.keys(this.key);
    }

    @Override
    public Collection<V> values() {
        return cache.values(this.key);
    }

    public RedisManager<String, K, V> getCache() {
        return cache;
    }

    public void setCache(RedisManager<String, K, V> cache) {
        this.cache = cache;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
