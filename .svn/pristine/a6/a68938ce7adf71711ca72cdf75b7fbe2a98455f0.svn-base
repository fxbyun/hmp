package com.qiaobei.hmp.modules.wxpay;


import com.qiaobei.hmp.modules.wxpay.excption.ArgumentsException;
import com.qiaobei.hmp.modules.wxpay.excption.ResponsException;
import com.qiaobei.hmp.modules.wxpay.orderBranch.WxPayPrecreate;
import com.qiaobei.hmp.modules.wxpay.orderBranch.WxPayQuery;
import com.qiaobei.hmp.modules.wxpay.orderBranch.WxPayRefund;
import com.qiaobei.hmp.modules.wxpay.utils.HttpClientUtil;
import com.qiaobei.hmp.modules.wxpay.utils.WxPayConfig;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;


public class WxPay implements Pay {

    public WxPay() {
    }

    /**
     * @param params
     * @return
     * @throws ArgumentsException
     * @throws IOException
     * @throws ResponsException
     */
    public Map<String, Object> doWebPay(Map<String, Object> params) throws ArgumentsException, IOException, ResponsException {
        String key = (String) params.get("key");
        if (StringUtils.isEmpty(key)) {
            throw new ArgumentsException("参数Map<String,Object> params中，必须包含 key.且不能为\"\" 或 null");
        }
        WxPayPrecreate p = new WxPayPrecreate();
        checkMap(params);
        p.setAppid((String) params.get("appid"));
        p.setMch_id((String) params.get("mch_id"));
        p.setNonce_str((String) params.get("nonce_str"));
        p.setBody((String) params.get("body"));
        p.setOut_trade_no((String) params.get("out_trade_no"));
        p.setTotal_fee((String) params.get("total_fee"));
        p.setNotify_url((String) params.get("notify_url"));
        p.setTrade_type((String) params.get("trade_type"));
        p.setSpbill_create_ip((String) params.get("spbill_create_ip"));
        p.setOpenid((String) params.get("openid"));
        WxRequestImpl wxRequest = new WxRequestImpl(WxPayConfig.QRPAY, p, key);
        String str = HttpClientUtil.doPostXML(wxRequest.getUrl(), wxRequest.getXml());
        WxResponseImpl wxResponse = new WxResponseImpl(str, key);
        Map<String, Object> map = wxResponse.getMap();
        return map;
    }

    /**
     * 二维码支付
     *
     * @return
     * @throws ArgumentsException
     * @throws IOException
     * @throws ResponsException
     */
    public Map<String, Object> doQrPay(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException {
        String key = (String) params.get("key");
        if (StringUtils.isEmpty(key)) {
            throw new ArgumentsException("参数Map<String,Object> params中，必须包含 key.且不能为\"\" 或 null");
        }
        WxPayPrecreate p = new WxPayPrecreate();
        checkMap(params);
        p.setAppid((String) params.get("appid"));
        p.setMch_id((String) params.get("mch_id"));
        p.setSub_mch_id((String) params.get("sub_mch_id"));
        p.setDevice_info((String) params.get("device_info"));
        p.setNonce_str((String) params.get("nonce_str"));
//        p.setSign("(String)params.get("sign"))
        p.setBody((String) params.get("body"));
        p.setDetail(params.get("detail"));
        p.setAttach((String) params.get("attach"));
        p.setOut_trade_no((String) params.get("out_trade_no"));
        p.setFee_type((String) params.get("fee_type"));
        p.setTotal_fee((String) params.get("total_fee"));
        p.setSpbill_create_ip((String) params.get("spbill_create_ip"));
        p.setTime_start((String) params.get("time_start"));
        p.setTime_expire((String) params.get("time_expire"));
        p.setGoods_tag((String) params.get("goods_tag"));
        p.setNotify_url((String) params.get("notify_url"));
        p.setTrade_type((String) params.get("trade_type"));
        p.setProduct_id((String) params.get("product_id"));
        p.setLimit_pay((String) params.get("limit_pay"));
        p.setOpenid((String) params.get("openid"));
        p.setSub_openid((String) params.get("sub_openid"));

        WxRequestImpl wxRequest = new WxRequestImpl(WxPayConfig.QRPAY, p, key);
        String str = HttpClientUtil.doPostXML(wxRequest.getUrl(), wxRequest.getXml());
        WxResponseImpl wxResponse = new WxResponseImpl(str, key);
        Map<String, Object> map = wxResponse.getMap();
        return map;
    }

    /**
     * 刷卡支付
     *
     * @return
     * @throws ArgumentsException
     * @throws IOException
     * @throws ResponsException
     */
    public Map<String, Object> doBarPay(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException {
        String key = (String) params.get("key");
        if (StringUtils.isEmpty(key)) {
            throw new ArgumentsException("参数Map<String,Object> params中，必须包含 key.且不能为\"\" 或 null");
        }
        WxPayPrecreate p = new WxPayPrecreate();
        checkMap(params);
        p.setAppid((String) params.get("appid"));
        p.setMch_id((String) params.get("mch_id"));
        p.setSub_appid((String) params.get("sub_appid"));
        p.setSub_mch_id((String) params.get("sub_mch_id"));
        p.setDevice_info((String) params.get("device_info"));
        p.setNonce_str((String) params.get("nonce_str"));
//        p.setSign("(String)params.get("sign"))
        p.setBody((String) params.get("body"));
        p.setDetail(params.get("detail"));
        p.setAttach((String) params.get("attach"));
        p.setOut_trade_no((String) params.get("out_trade_no"));
        p.setTotal_fee((String) params.get("total_fee"));
        p.setFee_type((String) params.get("fee_type"));
        p.setSpbill_create_ip((String) params.get("spbill_create_ip"));
        p.setGoods_tag((String) params.get("goods_tag"));
        p.setLimit_pay((String) params.get("limit_pay"));
        p.setAuth_code((String) params.get("auth_code"));

        WxRequestImpl wxRequest = new WxRequestImpl(WxPayConfig.BARPAY, p, key);

        String str = HttpClientUtil.doPostXML(wxRequest.getUrl(), wxRequest.getXml());
        WxResponseImpl wxResponse = new WxResponseImpl(str, key);
        Map<String, Object> map = wxResponse.getMap();
        return map;
    }

    /**
     * 查询订单
     *
     * @return
     * @throws ArgumentsException
     * @throws IOException
     * @throws ResponsException
     */
    public Map<String, Object> doQuery(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException {
        String key = (String) params.get("key");
        if (StringUtils.isEmpty(key)) {
            throw new ArgumentsException("参数Map<String,Object> params中，必须包含 key.且不能为\"\" 或 null");
        }
        WxPayQuery p = new WxPayQuery();
        checkMap(params);
        p.setAppid((String) params.get("appid"));
        p.setMch_id((String) params.get("mch_id"));
        p.setSub_mch_id((String) params.get("sub_mch_id"));
        p.setTransaction_id((String) params.get("transaction_id"));
        p.setOut_trade_no((String) params.get("out_trade_no"));
        p.setNonce_str((String) params.get("nonce_str"));

        WxRequestImpl wxRequest = new WxRequestImpl(WxPayConfig.QRPAY, p, key);
        String str = HttpClientUtil.doPostXML(wxRequest.getUrl(), wxRequest.getXml());
        WxResponseImpl wxResponse = new WxResponseImpl(str, key);
        Map<String, Object> map = wxResponse.getMap();
        return map;
    }

    /**
     * 退款
     *
     * @return
     * @throws ArgumentsException
     * @throws IOException
     * @throws ResponsException
     */
    public Map<String, Object> doRefund(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException {
      /*  String key = (String) params.get("key");
        String appid = (String) params.get("appid");
        String mch_id = (String) params.get("mch_id");
        if(StringUtils.isEmpty(key)||StringUtils.isEmpty(appid)||StringUtils.isEmpty(mch_id)){
            throw new ArgumentsException("参数Map<String,Object> params中，必须包含 key、appid、mch_id .且不能为\"\" 或 null");
        }*/
        String key = (String) params.get("key");
        if (StringUtils.isEmpty(key)) {
            throw new ArgumentsException("参数Map<String,Object> params中，必须包含 key.且不能为\"\" 或 null");
        }
        WxPayRefund p = new WxPayRefund();
        checkMap(params);
        p.setAppid((String) params.get("appid"));
        p.setMch_id((String) params.get("mch_id"));
        p.setSub_appid((String) params.get("sub_appid"));
        p.setSub_mch_id((String) params.get("sub_mch_id"));
        p.setDevice_info((String) params.get("device_info"));
        p.setNonce_str((String) params.get("nonce_str"));
        p.setTransaction_id((String) params.get("transaction_id"));
        p.setOut_trade_no((String) params.get("out_trade_no"));
        p.setOut_refund_no((String) params.get("out_refund_no"));
        p.setTotal_fee((String) params.get("total_fee"));
        p.setRefund_fee((String) params.get("refund_fee"));
        p.setRefund_fee_type((String) params.get("refund_fee_type"));
        p.setOp_user_id((String) params.get("op_user_id"));

        WxRequestImpl wxRequest = new WxRequestImpl(WxPayConfig.REFUND, p, key);
        String str = HttpClientUtil.doPostXML(wxRequest.getUrl(), wxRequest.getXml());
        WxResponseImpl wxResponse = new WxResponseImpl(str, key);
        Map<String, Object> map = wxResponse.getMap();
        return map;
    }

    /**
     * 退款查询
     *
     * @return
     * @throws ArgumentsException
     * @throws IOException
     * @throws ResponsException
     */
    public Map<String, Object> doRefundQuery(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException {
        String key = (String) params.get("key");
        String appid = (String) params.get("appid");
        String mch_id = (String) params.get("mch_id");
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(appid) || StringUtils.isEmpty(mch_id)) {
            throw new ArgumentsException("参数Map<String,Object> params中，必须包含 key、appid、mch_id .且不能为\"\" 或 null");
        }
        WxPayQuery p = new WxPayQuery();
        checkMap(params);
        p.setAppid((String) params.get("appid"));
        p.setMch_id((String) params.get("mch_id"));
        p.setSub_appid((String) params.get("sub_appid"));
        p.setSub_mch_id((String) params.get("sub_mch_id"));
        p.setDevice_info((String) params.get("device_info"));
        p.setNonce_str((String) params.get("nonce_str"));
        p.setTransaction_id((String) params.get("transaction_id"));
        p.setOut_trade_no((String) params.get("out_trade_no"));
        p.setOut_refund_no((String) params.get("out_refund_no"));
        p.setRefund_id((String) params.get("refund_id`"));

        WxRequestImpl wxRequest = new WxRequestImpl(WxPayConfig.QUERY_REFUND, p, key);
        String str = HttpClientUtil.doPostXML(wxRequest.getUrl(), wxRequest.getXml());
        WxResponseImpl wxResponse = new WxResponseImpl(str, key);
        Map<String, Object> map = wxResponse.getMap();
        return map;
    }

    private void checkMap(Map<String, Object> map) throws ArgumentsException {
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            if (null != map.get(s) && (!"".equals(map.get(s)))) {
                if (map.get(s) instanceof Number) {
                    String tmps = String.valueOf(map.get(s));
                    if (tmps.contains(".")) {
                        throw new ArgumentsException("金额必须为大于0的整数（单位为：分）！传入的金额为：" + tmps + "");
                    } else {
                        map.put(s, tmps);
                    }
                }
            } else {
                Object sval = map.get(s);
                map.remove(s);
                throw new ArgumentsException(s + " 为null或为\"\"字符串。请检查参数是否正确！" + s + ":" + sval);
            }
        }
    }
}



