package com.qiaobei.hmp.redis;

import com.qiaobei.hmp.modules.entity.MedicineCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/8.
 */
public class MedicineCountCache {
    public static final String MedicineCount = "AllMedicineCount";

    @Autowired
    private RedisTemplate<String, MedicineCount> redisTemplate;

    /**
     * 压栈
     *
     * @param key
     * @return
     */
    public void push(String key, MedicineCount medicineCount) {
        redisTemplate.opsForHash().put(key, medicineCount.getId().toString(), medicineCount);
    }

    public Object getValue(MedicineCount medicineCount,String key){
        return redisTemplate.opsForHash().get(key,medicineCount.getId().toString());
    }


    /**
     * 压栈
     *
     * @param key
     * @return
     */
    public void pushAll(String key, List<MedicineCount> medicineCountList) {
        medicineCountList.forEach(medicineCount -> redisTemplate.opsForHash().put(key, medicineCount.getId(), medicineCount));
    }


    public void pushAll2(String key, Map<Long,MedicineCount> map) {
        redisTemplate.opsForHash().putAll(key,map);
        //medicineCountList.forEach(medicineCount -> redisTemplate.opsForHash().put(key, medicineCount.getId(), medicineCount));
    }

    /**
     * 出栈
     *
     * @param key
     * @return
     */
    public void pop(String key, MedicineCount medicineCount) {
        redisTemplate.opsForHash().delete(key, medicineCount.getId());
    }

    /**
     * 栈/队列长
     *
     * @param key
     * @return
     */
    public Long length(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 范围检索
     *
     * @param key
     * @return
     */
    public Map range(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public List getAllValue(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    public Map<Object, Object> getAllValue2(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public RedisTemplate<String, MedicineCount> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, MedicineCount> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


}
