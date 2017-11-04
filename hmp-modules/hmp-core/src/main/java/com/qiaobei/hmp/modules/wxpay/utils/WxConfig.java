package com.qiaobei.hmp.modules.wxpay.utils;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/11 0011
 * Time 14:31
 */
public class WxConfig {

    //AppSecret(应用密钥这个是错的，坑爹的曾伟)
    public static String KEY = "yKUXklPtBpUHZwcScv9EO7RNPyggvLgC";
    //公众账号ID
    public static String APP_ID = "wxadb6502cd7503d27";
    //商户号
    public static String MCH_ID = "1280391801";
    //TODO 微信回调地址
    public static String CALL_BACK_URL = "http://www.yijiazhen.com/wx/callBack/";
    public static String CALL_BACK_Web_URL = "http://www.yijiazhen.com/opwebtest/wxtest/wx/callWebBack/";
}
