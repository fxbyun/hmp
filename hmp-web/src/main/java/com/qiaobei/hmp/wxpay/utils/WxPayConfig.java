package com.qiaobei.hmp.wxpay.utils;

import java.util.HashMap;
import java.util.Map;


public abstract class WxPayConfig {
    private static final Map<String, String> errorMap = new HashMap<String, String>();

    public static final String QRPAY = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String BARPAY = "https://api.mch.weixin.qq.com/pay/micropay";

    public static final String QR_CANCEL = "https://api.mch.weixin.qq.com/pay/closeorder";
    public static final String BAR_CANCEL = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

    public static final String QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    public static final String REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    public static final String QUERY_REFUND = "https://api.mch.weixin.qq.com/pay/refundquery";
    public static final String DOWNLOADBILL = "https://api.mch.weixin.qq.com/pay/downloadbill";

    public static final String SUCCESS = "SUCCESS";//支付成功
    public static final String NOTPAY = "NOTPAY";//未支付
    public static final String PAYERROR = "PAYERROR";//支付失败(其他原因，如银行返回失败)
    public static final String USERPAYING = "USERPAYING";//用户支付中
    public static final String CLOSED = "CLOSED";//已关闭
    public static final String REVOKED = "REVOKED";//已撤销（刷卡支付）

    public static Object getErrorMsg(String err_code) {
        return errorMap.get(err_code);
    }

    static {
        errorMap.put("NOAUTH", "商户无此接口权限");
        errorMap.put("NOTENOUGH", "余额不足");
        errorMap.put("ORDERPAID", "商户订单已支付");
        errorMap.put("ORDERCLOSED", "订单已关闭");
        errorMap.put("SYSTEMERROR", "系统错误");
        errorMap.put("APPID_NOT_EXIST", "APPID不存在");
        errorMap.put("MCHID_NOT_EXIST", "MCHID不存在");
        errorMap.put("APPID_MCHID_NOT_MATCH", "appid和mch_id不匹配");
        errorMap.put("LACK_PARAMS", "缺少参数");
        errorMap.put("OUT_TRADE_NO_USED", "商户订单号重复");
        errorMap.put("SIGNERROR", "签名错误");
        errorMap.put("XML_FORMAT_ERROR", "XML格式错误");
        errorMap.put("REQUIRE_POST_METHOD", "请使用post方法");
        errorMap.put("POST_DATA_EMPTY", "post数据为空");
        errorMap.put("NOT_UTF8", "编码格式错误");
        errorMap.put("ORDERNOTEXIST", "此交易订单号不存在");
        errorMap.put("USER_ACCOUNT_ABNORMAL", "退款请求失败");
        errorMap.put("INVALID_TRANSACTIONID", "无效transaction_id");
        errorMap.put("PARAM_ERROR", "参数错误");
        errorMap.put("BUYER_MISMATCH", "支付帐号错误");
        errorMap.put("AUTH_CODE_INVALID", "收银员扫描的不是微信支付的条码");
        errorMap.put("AUTH_CODE_ERROR", "每个二维码仅限使用一次，请刷新再试");
        errorMap.put("USERPAYING", "用户支付中，需要输入密码");
        errorMap.put("BANKERROR", "银行系统异常");
        errorMap.put("ORDERREVERSED", "当前订单已经被撤销");
        errorMap.put("NOTSUPORTCARD", "用户使用卡种不支持当前支付形式");
        errorMap.put("AUTHCODEEXPIRE", "支付码已过期，请用户在微信上刷新后再试");
    }

     /*
    * 文档地址：
    * 服务商：https://pay.weixin.qq.com/wiki/doc/api/sl.html
    * 普通商户：https://pay.weixin.qq.com/wiki/doc/api/index.html
    * */
}
