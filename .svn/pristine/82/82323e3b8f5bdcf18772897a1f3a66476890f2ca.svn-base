package com.qiaobei.hmp.security;

import com.qiaobei.hmp.modules.entity.Doctor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/8/15
 * Time 15:04
 */
public class SecuritySupport extends SecurityUtils {
    public static Doctor getDoctor() {
        return (Doctor) getSubject().getPrincipal();
    }

    public static Long getDoctorId() {
        Doctor doctor = getDoctor();
        if (null != doctor) return doctor.getId();
        return null;
    }

    //设置session
    public static void setAttribute(Object key, Object value) {
        getSubject().getSession().setAttribute(key, value);
    }

    //设置session，并且设置过期的时间
    public static void setAttribute(Object key, Object value, Long timeout) {
        Session session = getSubject().getSession();
        session.setTimeout(timeout);
        session.setAttribute(key, value);
    }

    //获取session
    public static Object getAttribute(Object key) {
        return getSubject().getSession().getAttribute(key);
    }

}
