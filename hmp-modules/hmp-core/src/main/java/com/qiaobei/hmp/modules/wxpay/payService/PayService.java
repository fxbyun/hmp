package com.qiaobei.hmp.modules.wxpay.payService;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.wxpay.CreatePay;
import com.qiaobei.hmp.modules.wxpay.Pay;
import com.qiaobei.hmp.modules.wxpay.excption.ArgumentsException;
import com.qiaobei.hmp.modules.wxpay.excption.ResponsException;
import com.qiaobei.hmp.modules.wxpay.utils.PayUtil;
import com.qiaobei.hmp.modules.wxpay.utils.WxConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PayService {

    //QR是用户扫  BAR是商户扫用户
    public static Map<String, Object> wxpay(Long money, String orderNo) {
        //微信 金额计算单位以一分钱计数 所以需要 乘以100
        money = money * 100;
        //AppSecret(应用密钥)
        String key = WxConfig.KEY;
        //公众账号ID
        String appid = WxConfig.APP_ID;
        //商户号
        String mch_id = WxConfig.MCH_ID;
        //回调地址
        String call_back_url = WxConfig.CALL_BACK_URL;

        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        // 随机字符串  随机字符串，不长于32位。推荐随机数生成算法
        map.put("nonce_str", orderNo);
        //商户订单号   商户系统内部的订单号,32个字符内、可包含字母,
        // 其他说明见商户订单号   唯一值
        map.put("out_trade_no", orderNo);
        //商品描述
        map.put("body", "易佳诊短信平台充值");
        //总金额
        map.put("total_fee", money);
        //终端IP
        map.put("spbill_create_ip", "127.1.1.1");
        //回调地址    接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
        map.put("notify_url", call_back_url);
        // NATIVE--原生扫码支付    MICROPAY--刷卡支付
        map.put("trade_type", "NATIVE");
        //map.put("trade_type", "MICROPAY");

        Pay d = CreatePay.getWxPay();
        Map<String, Object> mapRet = Maps.newHashMap();
        try {
            mapRet = d.doQrPay(map);
        } catch (ArgumentsException | IOException | ResponsException e) {
            e.printStackTrace();
        } finally {
            return mapRet;
        }
    }

    /**
     * 公众号 支付
     *
     * @param money
     * @param orderNo
     * @return
     */
    public static Map<String, Object> wxWebPay(Long money, String orderNo, String openId) {
//微信 金额计算单位以一分钱计数 所以需要 乘以100
        money = money * 1;
        //AppSecret(应用密钥)
        String key = WxConfig.KEY;
        //公众账号ID
        String appid = WxConfig.APP_ID;
        //商户号
        String mch_id = WxConfig.MCH_ID;
        //回调地址
        String call_back_url = WxConfig.CALL_BACK_Web_URL;

        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        // 随机字符串  随机字符串，不长于32位。推荐随机数生成算法
        map.put("nonce_str", orderNo);
        //商户订单号   商户系统内部的订单号,32个字符内、可包含字母,
        // 其他说明见商户订单号   唯一值
        map.put("out_trade_no", orderNo);
        //商品描述
        map.put("body", "打赏金");
        //总金额
        map.put("total_fee", money);
        //终端IP
        map.put("spbill_create_ip", "127.1.1.1");
        //回调地址    接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
        map.put("notify_url", call_back_url);
        // NATIVE--原生扫码支付    MICROPAY--刷卡支付  JSAPI--公众号支付
        map.put("trade_type", "JSAPI");
        map.put("openid", openId);
//        map.put("trade_type", "NATIVE");
        //map.put("trade_type", "MICROPAY");

        Pay d = CreatePay.getWxPay();
        Map<String, Object> mapRet = Maps.newHashMap();
        try {
            mapRet = d.doWebPay(map);
        } catch (ArgumentsException | IOException | ResponsException e) {
            e.printStackTrace();
        } finally {
            return mapRet;
        }
    }


    public static void main(String[] args) {
        System.out.println(PayUtil.getRandomString(30));
    }
}
