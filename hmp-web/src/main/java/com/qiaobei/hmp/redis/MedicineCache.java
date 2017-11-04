package com.qiaobei.hmp.redis;

import com.qiaobei.hmp.modules.entity.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/26 0026
 * Time 17:20
 */
public class MedicineCache {
    public static final String Medicine = "AllMedicine";

    @Autowired
    private RedisTemplate<String, Medicine> redisTemplate;

    /**
     * 压栈
     *
     * @param key
     * @return
     */
    public void push(String key, Medicine medicine) {
        redisTemplate.opsForHash().put(key, medicine.getId(), medicine);
    }

    /**
     * 压栈
     *
     * @param key
     * @return
     */
    public void pushAll(String key, List<Medicine> medicineList) {
        medicineList.forEach(medicine -> redisTemplate.opsForHash().put(key, medicine.getId(), medicine));
    }

    /**
     * 出栈
     *
     * @param key
     * @return
     */
    public void pop(String key, Medicine medicine) {
        redisTemplate.opsForHash().delete(key, medicine.getId());
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

    public RedisTemplate<String, Medicine> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Medicine> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
