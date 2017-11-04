package com.qiaobei.hmp.support;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Pharmacist;
import org.apache.shiro.SecurityUtils;

import javax.print.Doc;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class PharSecuritySupport extends SecurityUtils {
    public static Doctor getDoctor() {
        Doctor doctor;
        try {
            doctor = ((Pharmacist) getSubject().getPrincipal()).getDoctor();
        } catch (Exception e) {
            return null;
        }

        return doctor;
    }

    public static Long getDoctorId() {
        Doctor doctor = getDoctor();
        if (null != doctor) return doctor.getId();
        return null;
    }


    public static Pharmacist getPharmacist() {
        return (Pharmacist) getSubject().getPrincipal();
    }
}
