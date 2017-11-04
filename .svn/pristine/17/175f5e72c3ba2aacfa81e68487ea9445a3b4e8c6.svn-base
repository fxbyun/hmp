package com.qiaobei.hmp.test.test;

/**
 * Created by IntelliJ IDEA 2016.4
 * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * <p>
 * Spring profile 常用方法与profile名称。
 *
 * @author 凉生
 *         Date 2017/2/10 0010.
 *         Time 9:35.
 */
public class Profiles {
    public static final String ACTIVE_PROFILE = "spring.profiles.active";
    public static final String DEFAULT_PROFILE = "spring.profiles.default";

    public static final String PRODUCTION = "production";
    public static final String DEVELOPMENT = "development";
    public static final String UNIT_TEST = "test";
    public static final String FUNCTIONAL_TEST = "functional";

    /**
     * 在Spring启动前，设置profile的环境变量。
     */
    public static void setProfileAsSystemProperty(String profile) {
        System.setProperty(ACTIVE_PROFILE, profile);
    }
}
