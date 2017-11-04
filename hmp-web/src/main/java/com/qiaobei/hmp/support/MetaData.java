package com.qiaobei.hmp.support;

import java.lang.annotation.*;

/**
 * Created by teemoer@cntv.cn on 2016/7/1 0001.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PACKAGE})
public @interface MetaData {


    String value();

    /**
     * 提示信息:一般对应 表单项的提示说明
     */
    String tooltips() default "";

    /**
     * 注释说明:用于描述代码内部用法说明
     */
    String comments() default "";
}
