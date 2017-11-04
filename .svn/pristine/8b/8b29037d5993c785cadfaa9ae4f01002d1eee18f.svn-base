package com.qiaobei.hmp.modules.wxpay;


import com.qiaobei.hmp.modules.wxpay.excption.ArgumentsException;
import com.qiaobei.hmp.modules.wxpay.orderBranch._OrderBranch;
import com.qiaobei.hmp.modules.wxpay.utils.WxPayConfig;
import com.qiaobei.hmp.modules.wxpay.utils.WxSign;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxRequestImpl {
    private String url = null;
    private String key = null;
    private Map<String, Object> map = new HashMap<String, Object>();
    private List<String> requiredList = new ArrayList<String>();

    public WxRequestImpl(String method, _OrderBranch orderBranch, String key) throws ArgumentsException {
        this.url = orderBranch.getMethod(method);
        this.key = key;
        this.map.putAll(orderBranch.getMap());
    }

    public String getUrl() {
        return this.url;
    }

    @Override
    public String toString() {
        return this.map.toString();
    }

    /**
     * 将不能为空的列指定到list中这个list
     *
     * @return
     */
    private void requiredParam() {
        this.requiredList.add("appid");
        this.requiredList.add("mch_id");
        this.requiredList.add("nonce_str");
    }

    /**
     * 将所有不为null的属性封装成map并返回
     */
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
        if (WxPayConfig.QRPAY.equals(url)) {
            this.map.remove("auth_code");
        } else if (WxPayConfig.BARPAY.equals(url)) {
            this.map.remove("notify_url");
            this.map.remove("trade_type");
        }
        return this.map;
    }

    public String getXml() throws ArgumentsException {
        Map<String, Object> tmap = this.getMap();
        StringBuffer sbf = null;
        sbf = new StringBuffer();
        sbf.append("<xml>");
        for (String s : tmap.keySet()) {
            Object tmp = tmap.get(s);
            sbf.append("<" + s + ">" + tmp + "</" + s + ">");
        }
        sbf.append("</xml>");

        try {
            String str = new String(sbf.toString().getBytes("UTF-8"), "ISO8859-1");
            return str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置默认值
     */
    private void setDefaultValue() {
        if (WxPayConfig.QRPAY.equals(url)) {
            map.remove("auth_code");
        } else if (WxPayConfig.BARPAY.equals(url)) {
            map.remove("notify_url");
            map.remove("trade_type");
        }
        this.setSign(WxSign.getSign(this.map, this.key));
    }


    private String sign = null;//签名，详见签名生成算法


    public void setSign(String sign) {
        if (null != sign) {
            map.put("sign", sign);
        }
        this.sign = sign;
    }


}
