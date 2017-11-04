package org.springside.modules.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 */
public class AnnotationUtil {
    private static final String SETTER_PREFIX = "set";
    private static final String GETTER_PREFIX = "get";
    private static ConcurrentReferenceHashMap<AnnotationCacheKey, List<Field>> entityFieldCache = new ConcurrentReferenceHashMap<>(256);
    private static ConcurrentReferenceHashMap<Enum<?>, String> metaDataValueCache = new ConcurrentReferenceHashMap<>(256);


    //    @MetaData(value = "检验")
    //    JianYan,//检验

    /**
     * 获取如上 @MetaData 中的value值
     *
     * @param enumValue
     * @return
     */
    public static String getMetaValue(final Enum<?> enumValue) {
        return getMetaValue(enumValue, "");
    }


    //    @MetaData(value = "检验")
    //    JianYan,//检验

    /**
     * 获取如上 @MetaData 中的value值
     *
     * @param enumValue
     * @param defaultValue
     * @return
     */
    public static String getMetaValue(final Enum<?> enumValue, final String defaultValue) {
        String value = "";
        if (enumValue == null) {
            return defaultValue;
        }
        String cacheValue = metaDataValueCache.get(enumValue);
        if (cacheValue != null) {
            return cacheValue;
        }
        try {
            MetaData metaData = enumValue.getClass().getField(enumValue.name()).getAnnotation(MetaData.class);
            value = metaData.value();
            metaDataValueCache.put(enumValue, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取传递进来的类字段
     *
     * @param entityClass
     * @param annotationType
     * @return
     */
    public static List<Field> getEntityField(Class<?> entityClass, Class<? extends Annotation> annotationType) {
        List<Field> fields = Lists.newArrayList();
        Assert.notNull(entityClass, "entityClass 不能为空!!");
        Assert.notNull(annotationType, "annotation 不能为空!!");
        AnnotationCacheKey cacheKey = new AnnotationCacheKey(entityClass, annotationType);

        List<Field> _cacheFields = entityFieldCache.get(cacheKey);
        if (_cacheFields != null) {
            return _cacheFields;
        }

        // 读取字段中的注解
        for (Field field : entityClass.getDeclaredFields()) {
            if (AnnotationUtils.getAnnotation(field, annotationType) == null) {
                continue;
            }
            fields.add(field);
        }
        // 读取method中的字段
        for (Method method : entityClass.getMethods()) {
            if (AnnotationUtils.getAnnotation(method, annotationType) == null) {
                continue;
            }
            String methodName = method.getName();
            String fieldName = null;
            if (methodName.startsWith(GETTER_PREFIX)) {
                fieldName = StringUtils.uncapitalize(methodName.replace(GETTER_PREFIX, ""));
            }
            if (methodName.startsWith(SETTER_PREFIX)) {
                fieldName = StringUtils.uncapitalize(methodName.replace(SETTER_PREFIX, ""));
            }
            if (StringUtils.isBlank(fieldName)) {
                continue;
            }
            fields.add(Reflections.getAccessibleField(entityClass, fieldName));
        }
        entityFieldCache.put(cacheKey, fields);

        return fields;
    }


    private static class AnnotationCacheKey {

        private final Class<?> target;

        private final Class<? extends Annotation> annotationType;

        public AnnotationCacheKey(Class<?> element, Class<? extends Annotation> annotationType) {
            this.target = element;
            this.annotationType = annotationType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AnnotationCacheKey that = (AnnotationCacheKey) o;

            if (target != null ? !target.equals(that.target) : that.target != null) return false;
            return !(annotationType != null ? !annotationType.equals(that.annotationType) : that.annotationType != null);

        }

        @Override
        public int hashCode() {
            int result = target != null ? target.hashCode() : 0;
            result = 31 * result + (annotationType != null ? annotationType.hashCode() : 0);
            return result;
        }
    }

}
