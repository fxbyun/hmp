package com.qiaobei.hmp.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/28 0028
 * Time 12:38
 */
public class WxCache {
    private RedisTemplate<String, WxInfo> redisTemplate;

    public void addOrUpdate(String wxInfoKey, WxInfo wxInfo, int minutes) {
        redisTemplate.opsForValue().set(wxInfoKey, wxInfo, minutes, TimeUnit.MINUTES);
    }

    public void remove(String wxInfoKey) {
        redisTemplate.delete(wxInfoKey);
    }

    public WxInfo load(String wxInfoKey) {
        //        redisTemplate.expire(wxInfoKey, 30, TimeUnit.MINUTES); //重置有效期为30分钟
        return redisTemplate.opsForValue().get(wxInfoKey);
    }

    public RedisTemplate<String, WxInfo> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, WxInfo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
