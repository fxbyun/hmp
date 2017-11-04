package com.qiaobei.hmp.modules.wxpay.orderBranch;


import com.qiaobei.hmp.modules.wxpay.excption.ArgumentsException;
import com.qiaobei.hmp.modules.wxpay.utils.WxPayConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WxPayRefund implements _OrderBranch {
    private final Map<String, String> urlmap = new HashMap<String, String>();
    private String url = null;

    {
        urlmap.put(WxPayConfig.REFUND, WxPayConfig.REFUND);
    }

    private Map<String, Object> map = new HashMap<String, Object>();
    private List<String> requiredList = new ArrayList<String>();

    public String getMethod(String method) {
        this.url = method;
        return urlmap.get(method);
    }

    public WxPayRefund(String transaction_id, String out_trade_no, String out_refund_no, String total_fee, String
            refund_fee, String op_user_id) {
        this.setTransaction_id(transaction_id);
        this.setOut_trade_no(out_trade_no);
        this.setOut_refund_no(out_refund_no);
        this.setTotal_fee(total_fee);
        this.setRefund_fee(refund_fee);
        this.setOp_user_id(op_user_id);
    }

    public WxPayRefund() {
    }

    public Map<String, Object> getMap() throws ArgumentsException {
        this.requiredParam();
        this.setDefaultValue();
        if (StringUtils.isEmpty(transaction_id) && StringUtils.isEmpty(out_trade_no)) {
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
        if (WxPayConfig.QRPAY.equals(url)) {
        }
    }

    private void requiredParam() {
        if (WxPayConfig.QRPAY.equals(url)) {
            //定义不能为空的字段
            requiredList.add("appid");
            requiredList.add("mch_id");
//            requiredList.add("sub_mch_id");
            requiredList.add("nonce_str");
//            requiredList.add("sign");
            requiredList.add("transaction_id");
//            requiredList.add("out_trade_no");
//            requiredList.add("out_refund_no");
            requiredList.add("total_fee");

            requiredList.add("refund_fee");
            requiredList.add("op_user_id");
        }
    }

    private String appid = null;//微信分配的公众账号ID
    private String mch_id = null;//微信支付分配的商户号
    private String sub_appid = null;//	微信分配的子商户公众账号ID
    private String sub_mch_id = null;//微信支付分配的子商户号
    private String device_info = null;//终端设备号
    private String nonce_str = null;//随机字符串，不长于32位。推荐随机数生成算法
    //    private String  sign= null;//签名，详见签名生成算法
    private String transaction_id = null;//微信订单号
    private String out_trade_no = null;//商户系统内部的订单号,    transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id>
    // out_trade_no
    private String out_refund_no = null;//商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
    private String total_fee = null;//订单总金额，单位为分，只能为整数，详见支付金额
    private String refund_fee = null;//退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
    private String refund_fee_type = null;//货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String op_user_id = null;//操作员帐号, 默认为商户号

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

    public void setRefund_fee_type(String refund_fee_type) {
        if (StringUtils.isNotEmpty(refund_fee_type)) {
            this.map.put("refund_fee_type", refund_fee_type);
        }
        this.refund_fee_type = refund_fee_type;
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

    public void setOut_refund_no(String out_refund_no) {
        if (StringUtils.isNotEmpty(out_refund_no)) {
            this.map.put("out_refund_no", out_refund_no);
        }
        this.out_refund_no = out_refund_no;
    }

    public void setTotal_fee(String total_fee) {
        if (StringUtils.isNotEmpty(total_fee)) {
            this.map.put("total_fee", total_fee);
        }
        this.total_fee = total_fee;
    }

    public void setRefund_fee(String refund_fee) {
        if (StringUtils.isNotEmpty(refund_fee)) {
            this.map.put("refund_fee", refund_fee);
        }
        this.refund_fee = refund_fee;
    }

    public void setOp_user_id(String op_user_id) {
        if (StringUtils.isNotEmpty(op_user_id)) {
            this.map.put("op_user_id", op_user_id);
        }
        this.op_user_id = op_user_id;
    }
}

