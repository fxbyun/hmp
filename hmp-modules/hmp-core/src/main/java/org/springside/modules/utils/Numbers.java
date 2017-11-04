package org.springside.modules.utils;

import java.math.BigDecimal;

public class Numbers {
    private Numbers() {
    }

    public static boolean isNullOrZero(Long num) {
        if (num == null) {
            return true;
        }
        return num == 0L ? true : false;
    }

    public static boolean isNullOrZero(Integer num) {
        if (num == null) {
            return true;
        }
        return num == 0 ? true : false;
    }

    public static boolean isNullOrZero(Float num) {
        if (num == null) {
            return true;
        }
        return num == 0F ? true : false;
    }

    public static boolean isNullOrZero(Double num) {
        if (num == null) {
            return true;
        }
        return num == 0F ? true : false;
    }

    public static boolean isNullOrZero(BigDecimal num) {
        return num != null && num.floatValue() != 0F;
    }

    public static boolean isNotNullOrZero(Long num) {
        return num != null && num.longValue() != 0L;
    }

    public static boolean isNotNullOrZero(Integer num) {
        return num != null && num.intValue() != 0;
    }

    public static boolean isNotNullOrZero(Float num) {
        return num != null && num.floatValue() != 0F;
    }

    public static boolean isNotNullOrZero(Double num) {
        return num != null && num.doubleValue() != 0F;
    }

    public static boolean isNotNullOrZero(BigDecimal num) {
        return num != null && num.floatValue() != 0F;
    }

}
