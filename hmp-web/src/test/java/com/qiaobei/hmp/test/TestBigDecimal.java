package com.qiaobei.hmp.test;

import java.math.BigDecimal;

/**
 * Created by iceyanbin on 2016-01-19.
 */
public class TestBigDecimal {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal(1F);
        BigDecimal r = bd.divide(new BigDecimal(11F), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(r.doubleValue());

    }
}
