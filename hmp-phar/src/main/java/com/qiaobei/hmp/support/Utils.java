package com.qiaobei.hmp.support;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.Pharmacist;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import java.util.Random;

public final class Utils {


    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public static void entryptUserPassword(Doctor doctor) {
        byte[] salt = Digests.generateSalt(Constants.PASSWORD_SALT_SIZE);
        doctor.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(doctor.getPlainPassword().getBytes(), salt, Constants
                .PASSWORD_HASH_INTERATIONS);
        doctor.setPassword(Encodes.encodeHex(hashPassword));
    }




    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public static void entryptUserPassword(Pharmacist phar) {
        byte[] salt = Digests.generateSalt(Constants.PASSWORD_SALT_SIZE);
        phar.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(phar.getPassword().getBytes(), salt, Constants.PASSWORD_HASH_INTERATIONS);
        phar.setPassword(Encodes.encodeHex(hashPassword));
    }

    public static void entryptUserPassword(Patient patient) {
        byte[] salt = Digests.generateSalt(Constants.PASSWORD_SALT_SIZE);
        patient.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(patient.getPlainPassword().getBytes(), salt, Constants
                .PASSWORD_HASH_INTERATIONS);
        patient.setPassword(Encodes.encodeHex(hashPassword));
    }
    /**
     * 密码加密
     */
    public static String encodePwd(String pwd, String salt) {
        byte[] hashPassword = Digests.sha1(pwd.getBytes(), salt.getBytes(), Constants.PASSWORD_HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

    /**
     * 生成验证码-随机数.
     */
    public static String random(int count) {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        return sb.toString();
    }

    /**
     * 创建分页请求.
     */
    public static PageRequest buildPageRequest(int pageNo) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        ;
        return new PageRequest(pageNo - 1, Constants.PAGZ_SIZE, sort);
    }
}
