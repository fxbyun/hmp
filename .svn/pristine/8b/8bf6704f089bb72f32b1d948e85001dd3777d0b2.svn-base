package com.qiaobei.hmp.redis;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/28 0028
 * Time 12:39
 */
public class WxInfo implements Serializable {
    private static final long serialVersionUID = 5796919931900340060L;

    //门诊服务号的ACCESS_TOKEN
    private String ACCESS_TOKEN_PHAR = null;
    //患者服务号的ACCESS_TOKEN
    private String ACCESS_TOKEN_OP = null;

    public WxInfo() {
    }

    public WxInfo(String ACCESS_TOKEN_PHAR, String ACCESS_TOKEN_OP) {
        this.ACCESS_TOKEN_PHAR = ACCESS_TOKEN_PHAR;
        this.ACCESS_TOKEN_OP = ACCESS_TOKEN_OP;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WxInfo other = (WxInfo) obj;
        if (ACCESS_TOKEN_PHAR == null) {
            if (other.ACCESS_TOKEN_PHAR != null)
                return false;
        } else if (!ACCESS_TOKEN_PHAR.equals(other.ACCESS_TOKEN_PHAR))
            return false;
        if (ACCESS_TOKEN_OP == null) {
            if (other.ACCESS_TOKEN_OP != null)
                return false;
        } else if (!ACCESS_TOKEN_OP.equals(other.ACCESS_TOKEN_OP))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ACCESS_TOKEN_PHAR == null) ? 0 : ACCESS_TOKEN_PHAR.hashCode());
        result = prime * result + ((ACCESS_TOKEN_OP == null) ? 0 : ACCESS_TOKEN_OP.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "WxInfo{" +
                "ACCESS_TOKEN_PHAR='" + ACCESS_TOKEN_PHAR + '\'' +
                ", ACCESS_TOKEN_OP='" + ACCESS_TOKEN_OP + '\'' +
                '}';
    }

    public String getACCESS_TOKEN_PHAR() {
        return ACCESS_TOKEN_PHAR;
    }

    public void setACCESS_TOKEN_PHAR(String ACCESS_TOKEN_PHAR) {
        this.ACCESS_TOKEN_PHAR = ACCESS_TOKEN_PHAR;
    }

    public String getACCESS_TOKEN_OP() {
        return ACCESS_TOKEN_OP;
    }

    public void setACCESS_TOKEN_OP(String ACCESS_TOKEN_OP) {
        this.ACCESS_TOKEN_OP = ACCESS_TOKEN_OP;
    }
}
