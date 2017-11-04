package com.qiaobei.hmp.wxpay.orderBranch;


import com.qiaobei.hmp.wxpay.excption.ArgumentsException;
import com.qiaobei.hmp.wxpay.utils.PayUtil;
import com.qiaobei.hmp.wxpay.utils.WxPayConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WxPayPrecreate implements _OrderBranch {
    private String url = null;
    private final Map<String, String> urlmap = new HashMap<>();

    {
        urlmap.put(WxPayConfig.QRPAY, WxPayConfig.QRPAY);
        urlmap.put(WxPayConfig.BARPAY, WxPayConfig.BARPAY);
    }

    public String getMethod(String method) {
        this.url = method;
        return urlmap.get(method);
    }

    private Map<String, Object> map = new HashMap<>();
    private List<String> requiredList = new ArrayList<>();

    public WxPayPrecreate(String out_trade_no, String total_fee, String body) {
        this.setOut_trade_no(out_trade_no);
        this.setTotal_fee(total_fee);
        this.setBody(body);
    }

    public WxPayPrecreate() {
    }

    private void requiredParam() {
        //定义不能为空的字段
        requiredList.add("appid");
        requiredList.add("mch_id");
//            requiredList.add("sub_mch_id");
        requiredList.add("nonce_str");
//            requiredList.add("sign");
        requiredList.add("body");
        requiredList.add("out_trade_no");
        requiredList.add("total_fee");
        requiredList.add("spbill_create_ip");
        if (WxPayConfig.QRPAY.equals(url)) {
            requiredList.add("notify_url");
            requiredList.add("trade_type");
        } else if (WxPayConfig.BARPAY.equals(url)) {
            requiredList.add("auth_code");
        }
        if (WxPayConfig.REFUND.equals(url)) {
            requiredList.add("transaction_id");
            requiredList.add("out_refund_no");
            requiredList.add("refund_fee");
            requiredList.add("op_user_id");
        }
    }

    public Map<String, Object> getMap() throws ArgumentsException {
        this.requiredParam();
        this.setDefaultValue();
        if (null != this.requiredList) {
            for (String s : this.requiredList) {//检查为空的值是否必填
                if (!this.map.containsKey(s)) {//出现未包含的必填项
                    throw new ArgumentsException(s + "--为必填项。请检查必填项：" + this.requiredList + " 是否填写正确。");
                }
            }
        }
        return this.map;
    }


    private void setDefaultValue() {
        this.setSpbill_create_ip(PayUtil.getLocalIp());
        if (this.map.get("trade_type") == null) {
            if (WxPayConfig.QRPAY.equals(url)) {
                this.setTrade_type("NATIVE");
            } else if (WxPayConfig.BARPAY.equals(url)) {
                this.setTrade_type("MICROPAY");
            }
        }
    }

    private String appid = null;//微信分配的公众账号ID
    private String mch_id = null;//	微信支付分配的商户号
    private String sub_appid = null;//	微信分配的子商户公众账号ID
    private String sub_mch_id = null;//微信支付分配的子商户号，开发者模式下必填
    private String device_info = null;//终端设备号
    private String nonce_str = null;//随机字符串，不长于32位。推荐随机数生成算法
    //    private String sign = null;//签名，详见签名生成算法
    private String body = null;//商品描述
    private Object detail = null;//商品详情
    private String attach = null;//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
    private String out_trade_no = null;//商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
    private String fee_type = null;//符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String total_fee = null;//订单总金额，只能为整数，详见支付金额
    private String spbill_create_ip = null;//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
    private String time_start = null;//订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
    private String time_expire = null;//订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
    private String goods_tag = null;//	商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
    private String notify_url = null;//	接收微信支付异步通知回调地址
    private String trade_type = null;//取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
    private String product_id = null;//trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
    private String limit_pay = null;//no_credit--指定不能使用信用卡支付
    private String openid = null;//trade_type=JSAPI，此参数必传，用户在主商户appid下的唯一标识。openid和sub_openid
    // 可以选传其中之一，如果选择传sub_openid,则必须传sub_appid。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
    private String sub_openid = null;//trade_type=JSAPI，此参数必传，用户在子商户appid下的唯一标识。openid和sub_openid
    // 可以选传其中之一，如果选择传sub_openid,则必须传sub_appid。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。

    private String auth_code = null;//	扫码支付授权码，设备读取用户微信中的条码或者二维码信息

    public void setAppid(String appid) {
        if (StringUtils.isNotEmpty(appid)) {
            this.map.put("appid", appid);
        }
        this.appid = appid;
    }

    public void setMch_id(String mch_id) {
        if (StringUtils.isNotEmpty(mch_id)) {
            this.map.put("mch_id", mch_id);
        }
        this.mch_id = mch_id;
    }

    public void setSub_appid(String sub_appid) {
        if (StringUtils.isNotEmpty(sub_appid)) {
            this.map.put("sub_appid", sub_appid);
        }
        this.sub_appid = sub_appid;
    }

    public void setSub_mch_id(String sub_mch_id) {
        if (StringUtils.isNotEmpty(sub_mch_id)) {
            this.map.put("sub_mch_id", sub_mch_id);
        }
        this.sub_mch_id = sub_mch_id;
    }

    public void setDevice_info(String device_info) {
        if (StringUtils.isNotEmpty(device_info)) {
            this.map.put("device_info", device_info);
        }
        this.device_info = device_info;
    }

    public void setNonce_str(String nonce_str) {
        if (StringUtils.isNotEmpty(nonce_str)) {
            this.map.put("nonce_str", nonce_str);
        }
        this.nonce_str = nonce_str;
    }

//    public void setSign(String sign){
//        if(StringUtils.isNotEmpty(sign)){
//            this.map.put("sign",sign);
//        }
//        this.sign = sign;
//    }

    public void setBody(String body) {
        if (StringUtils.isNotEmpty(body)) {
            this.map.put("body", body);
        }
        this.body = body;
    }

    public void setDetail(Object detail) {
        if (null != detail) {
            this.map.put("detail", detail);
        }
        this.detail = detail;
    }

    public void setAttach(String attach) {
        if (StringUtils.isNotEmpty(attach)) {
            this.map.put("attach", attach);
        }
        this.attach = attach;
    }

    public void setOut_trade_no(String out_trade_no) {
        if (StringUtils.isNotEmpty(out_trade_no)) {
            this.map.put("out_trade_no", out_trade_no);
        }
        this.out_trade_no = out_trade_no;
    }

    public void setFee_type(String fee_type) {
        if (StringUtils.isNotEmpty(fee_type)) {
            this.map.put("fee_type", fee_type);
        }
        this.fee_type = fee_type;
    }

    public void setTotal_fee(String total_fee) {
        if (StringUtils.isNotEmpty(total_fee)) {
            this.map.put("total_fee", total_fee);
        }
        this.total_fee = total_fee;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        if (StringUtils.isNotEmpty(spbill_create_ip)) {
            this.map.put("spbill_create_ip", spbill_create_ip);
        }
        this.spbill_create_ip = spbill_create_ip;
    }

    public void setTime_start(String time_start) {
        if (StringUtils.isNotEmpty(time_start)) {
            this.map.put("time_start", time_start);
        }
        this.time_start = time_start;
    }

    public void setTime_expire(String time_expire) {
        if (StringUtils.isNotEmpty(time_expire)) {
            this.map.put("time_expire", time_expire);
        }
        this.time_expire = time_expire;
    }

    public void setGoods_tag(String goods_tag) {
        if (StringUtils.isNotEmpty(goods_tag)) {
            this.map.put("goods_tag", goods_tag);
        }
        this.goods_tag = goods_tag;
    }

    public void setNotify_url(String notify_url) {
        if (StringUtils.isNotEmpty(notify_url)) {
            this.map.put("notify_url", notify_url);
        }
        this.notify_url = notify_url;
    }

    public void setTrade_type(String trade_type) {
        if (StringUtils.isNotEmpty(trade_type)) {
            this.map.put("trade_type", trade_type);
        }
        this.trade_type = trade_type;
    }

    public void setProduct_id(String product_id) {
        if (StringUtils.isNotEmpty(product_id)) {
            this.map.put("product_id", product_id);
        }
        this.product_id = product_id;
    }

    public void setLimit_pay(String limit_pay) {
        if (StringUtils.isNotEmpty(limit_pay)) {
            this.map.put("limit_pay", limit_pay);
        }
        this.limit_pay = limit_pay;
    }

    public void setOpenid(String openid) {
        if (StringUtils.isNotEmpty(openid)) {
            this.map.put("openid", openid);
        }
        this.openid = openid;
    }

    public void setSub_openid(String sub_openid) {
        if (StringUtils.isNotEmpty(sub_openid)) {
            this.map.put("sub_openid", sub_openid);
        }
        this.sub_openid = sub_openid;
    }

    public void setAuth_code(String auth_code) {
        if (StringUtils.isNotEmpty(auth_code)) {
            this.map.put("auth_code", auth_code);
        }
        this.auth_code = auth_code;
    }
}
