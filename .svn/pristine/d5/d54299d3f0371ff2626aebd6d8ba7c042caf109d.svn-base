package com.qiaobei.hmp.modules.wxpay.orderBranch;


import com.qiaobei.hmp.modules.wxpay.excption.ArgumentsException;
import com.qiaobei.hmp.modules.wxpay.utils.WxPayConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WxPayCancel implements _OrderBranch {
    private final Map<String, String> urlmap = new HashMap<String, String>();
    private String url = null;

    {
        urlmap.put(WxPayConfig.QR_CANCEL, WxPayConfig.QR_CANCEL);
        urlmap.put(WxPayConfig.BAR_CANCEL, WxPayConfig.BAR_CANCEL);
    }

    public WxPayCancel() {
    }

    private Map<String, Object> map = new HashMap<String, Object>();
    private List<String> requiredList = new ArrayList<String>();

    public String getMethod(String method) {
        this.url = method;
        return urlmap.get(method);
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
        if (WxPayConfig.QR_CANCEL.equals(url)) {
        } else if (WxPayConfig.BAR_CANCEL.equals(url)) {

        }
    }

    private void requiredParam() {
        if (WxPayConfig.QR_CANCEL.equals(url)) {
            this.requiredList.add("appid");
            this.requiredList.add("mch_id");
//            this.requiredList.add("sub_mch_id");
            this.requiredList.add("out_trade_no");
            this.requiredList.add("nonce_str");
        } else if (WxPayConfig.BAR_CANCEL.equals(url)) {
            this.requiredList.add("appid");
            this.requiredList.add("mch_id");
            this.requiredList.add("sub_mch_id");
            this.requiredList.add("out_trade_no");
            this.requiredList.add("nonce_str");

        }
    }

    private String appid = null;//微信分配的公众账号ID
    private String mch_id = null;//	微信支付分配的商户号
    private String sub_appid = null;//	微信分配的子商户公众账号ID
    private String sub_mch_id = null;//微信支付分配的子商户号
    private String transaction_id = null;//微信的订单号，优先使用
    private String out_trade_no = null;//商户系统内部的订单号
    private String nonce_str = null;//商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
    private String sign = null;//签名，详见签名生成算法


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

    public void setTransaction_id(String transaction_id) {
        if (StringUtils.isNotEmpty(transaction_id)) {
            this.map.put("transaction_id", transaction_id);
        }
        this.transaction_id = transaction_id;
    }

    public void setOut_trade_no(String out_trade_no) {
        if (StringUtils.isNotEmpty(out_trade_no)) {
            this.map.put("out_trade_no", out_trade_no);
        }
        this.out_trade_no = out_trade_no;
    }

    public void setNonce_str(String nonce_str) {
        if (StringUtils.isNotEmpty(nonce_str)) {
            this.map.put("nonce_str", nonce_str);
        }
        this.nonce_str = nonce_str;
    }

    public void setSign(String sign) {
        if (StringUtils.isNotEmpty(sign)) {
            this.map.put("sign", sign);
        }
        this.sign = sign;
    }
}
