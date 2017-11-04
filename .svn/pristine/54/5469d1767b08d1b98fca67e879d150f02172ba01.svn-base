package com.qiaobei.hmp.security;

import com.qiaobei.hmp.modules.entity.Doctor;
import org.apache.shiro.SecurityUtils;

/**
 * Created by yanbin on 11/8/15.
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

}
