package com.qiaobei.hmp.modules.wxpay.orderBranch;


import com.qiaobei.hmp.modules.wxpay.excption.ArgumentsException;
import com.qiaobei.hmp.modules.wxpay.utils.PayUtil;
import com.qiaobei.hmp.modules.wxpay.utils.WxPayConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WxPayQuery implements _OrderBranch {
    private final Map<String, String> urlmap = new HashMap<String, String>();
    private String url = null;

    {
        urlmap.put(WxPayConfig.QUERY, WxPayConfig.QUERY);
        urlmap.put(WxPayConfig.QUERY_REFUND, WxPayConfig.QUERY_REFUND);
    }

    public WxPayQuery() {
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
        if (StringUtils.isEmpty(this.transaction_id) && StringUtils.isEmpty(this.out_trade_no)) {
            throw new ArgumentsException("transaction_id 和 out_trade_no 必须填写一项！");
        }
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
        this.setNonce_str(PayUtil.getRandomString(30));//30位长度的随机字符串
        if (WxPayConfig.QUERY.equals(url)) {
        } else if (WxPayConfig.QUERY_REFUND.equals(url)) {
        }
    }

    private void requiredParam() {
        if (WxPayConfig.QUERY.equals(url)) {
            requiredList.add("appid");
            requiredList.add("mch_id");
//            requiredList.add("sub_mch_id");
            requiredList.add("nonce_str");
        } else if (WxPayConfig.QUERY_REFUND.equals(url)) {
            requiredList.add("out_trade_no");
        }
    }

    private String appid = null;//微信分配的公众账号ID
    private String mch_id = null;//	微信支付分配的商户号
    private String sub_appid = null;//	微信分配的子商户公众账号ID
    private String sub_mch_id = null;//微信支付分配的子商户号，开发者模式下必填
    private String transaction_id = null;//微信订单号
    private String out_trade_no = null;//商户订单号
    private String nonce_str = null;//随机字符串，不长于32位。推荐随机数生成算法
    private String device_info = null;//商户自定义的终端设备号，如门店编号、设备的ID等
    private String out_refund_no = null;//商户退款单号
    private String refund_id = null;//微信退款单号
    // refund_id、out_refund_no、out_trade_no、transaction_id四个参数必填一个，如果同时存在优先级为：
    // refund_id>out_refund_no>transaction_id>out_trade_no

    public void setDevice_info(String device_info) {
        if (StringUtils.isNotEmpty(device_info)) {
            this.map.put("device_info", device_info);
        }
        this.device_info = device_info;
    }

    public void setOut_refund_no(String out_refund_no) {
        if (StringUtils.isNotEmpty(out_refund_no)) {
            this.map.put("out_refund_no", out_refund_no);
        }
        this.out_refund_no = out_refund_no;
    }

    public void setRefund_id(String refund_id) {
        if (StringUtils.isNotEmpty(refund_id)) {
            this.map.put("refund_id", refund_id);
        }
        this.refund_id = refund_id;
    }

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

    public void setNonce_str(String nonce_str) {
        if (StringUtils.isNotEmpty(nonce_str)) {
            this.map.put("nonce_str", nonce_str);
        }
        this.nonce_str = nonce_str;
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
}
