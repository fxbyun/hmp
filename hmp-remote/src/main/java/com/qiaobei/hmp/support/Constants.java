package com.qiaobei.hmp.support;

public final class Constants {


    public static final String PASSWORD_HASH_ALGORITHM = "SHA-1";
    public static final int PASSWORD_HASH_INTERATIONS = 1024;
    public static final int PASSWORD_SALT_SIZE = 8;

    public static final long SUPERVISOR_ACCOUNT_ID = 1L;
    public static final long DOCTOR_ROLE_ID = 2L;

    public static final Long COMPANY_NS_ID = -1L;
    public static final String COMPANY_NS_NAME = "未指定药厂";

    public static final String PATTERN_MONTH = "yyyy/MM";
    public static final String PATTERN_DATE = "yyyy/MM/dd";
    public static final String PATTERN_DATETIME = "yyyy/MM/dd HH:mm";
    public static final Double MSG_PRICE = 0.09;

    public static final char TEXT_SEPARATOR = ';';

    public static final String AUTH_CODE = "AUTH_CODE";
    public static final String CURRENT_USER = "user";

    public static final int SEND_WAIT_TIME = 1;

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int RP_PAGE_SIZE = 10;
    public static final int TAG_PAGE_SIZE = 30;
    public static final int DIATELS_PAGE_SIZE = 20;
    public static final int EVA_PAGE_SIZE = 5;

}
