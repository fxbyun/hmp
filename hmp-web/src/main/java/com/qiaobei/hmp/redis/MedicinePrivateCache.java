package com.qiaobei.hmp.redis;

import com.qiaobei.hmp.modules.entity.MedicinePrivate;
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
public class MedicinePrivateCache {
    public static final String MedicinePrivate = "AllMedicinePrivatePrivate";
    private RedisTemplate<String, MedicinePrivate> redisTemplate;

    /**
     * 压栈
     *
     * @param key
     * @return
     */
    public void push(String key, MedicinePrivate MedicinePrivate) {
        redisTemplate.opsForHash().put(key, MedicinePrivate.getId(), MedicinePrivate);
    }

    /**
     * 压栈
     *
     * @param key
     * @return
     */
    public void pushAll(String key, List<MedicinePrivate> MedicinePrivateList) {
        MedicinePrivateList.forEach(medicinePrivate -> redisTemplate.opsForHash().put(key, medicinePrivate.getId(), medicinePrivate));
    }

    /**
     * 出栈
     *
     * @param key
     * @return
     */
    public void pop(String key, MedicinePrivate medicine) {
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

    public RedisTemplate<String, MedicinePrivate> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, MedicinePrivate> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
