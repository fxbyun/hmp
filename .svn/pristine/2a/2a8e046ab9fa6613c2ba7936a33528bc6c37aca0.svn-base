package com.qiaobei.hmp.modules.wxpay;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.wxpay.utils.WxSign;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 微信回调接口参数封装类
 * 若get方法没有包含其返回的参数。添加一个get方法即可
 */
public class WxNotifyImpl {
    private Map<String, Object> map = Maps.newHashMap();

    /**
     * @param request 微信接口回调方法的request
     * @throws DocumentException
     */
    public WxNotifyImpl(HttpServletRequest request) {
        super();
        this.map = getResultsMap(request);
    }

    public void close(HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            out.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg" +
                    "></xml>");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    /**
     * 是否完成支付
     *
     * @return
     */
    public boolean verfyPaySeccuss() {
        if ("SUCCESS".equals(this.getReturn_code())) {
            return true;
        }
        return false;
    }

    /**
     * 把XML封装成map 只针对没微信回调request参数中的不重复的xml
     *
     * @param request request
     * @return
     */
    private Map<String, Object> getResultsMap(HttpServletRequest request) {
        if (null == request) {
            return null;
        }
        String inputLine;
        StringBuffer xml = new StringBuffer();

        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                xml.append(inputLine);
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(xml)) {
            return map;
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(new String(xml).getBytes()));
            Element root = document.getRootElement();
            List<Element> childElements = root.elements();
            for (Element child : childElements) {
                map.put(child.getName(), child.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return map;

//        return getResultsMap(xml);
    }

    /**
     * 公众账号ID
     *
     * @return
     */
    public String getAppid() {
        if (this.map.get("appid") != null)
            return (String) this.map.get("appid");
        return null;
    }

    public String getBank_type() {
        if (this.map.get("bank_type") != null)
            return (String) this.map.get("bank_type");
        return null;
    }

    public String getCash_fee() {
        if (this.map.get("cash_fee") != null)
            return (String) this.map.get("cash_fee");
        return null;
    }

    /**
     * 设备号
     *
     * @return
     */
    public String getDevice_info() {
        if (this.map.get("device_info") != null)
            return (String) this.map.get("device_info");
        return null;
    }

    /**
     * 货币类型 CNY 人民币
     */
    public String getFee_type() {
        if (this.map.get("fee_type") != null)
            return (String) this.map.get("fee_type");
        return null;
    }

    public String getIs_subscribe() {
        if (this.map.get("is_subscribe") != null)
            return (String) this.map.get("is_subscribe");
        return null;
    }

    /**
     * 商户号
     *
     * @return
     */
    public String getMch_id() {
        if (this.map.get("mch_id") != null)
            return (String) this.map.get("mch_id");
        return null;
    }

    /**
     * 随机字符串
     *
     * @return
     */
    public String getNonce_str() {
        if (this.map.get("nonce_str") != null)
            return (String) this.map.get("nonce_str");
        return null;
    }

    /**
     * 用户标识
     *
     * @return
     */
    public String getOpenid() {
        if (this.map.get("openid") != null)
            return (String) this.map.get("openid");
        return null;
    }

    /**
     * 商户订单号
     *
     * @return
     */
    public String getOut_trade_no() {
        if (this.map.get("out_trade_no") != null)
            return (String) this.map.get("out_trade_no");
        return null;
    }

    /**
     * 业务结果
     *
     * @return
     */
    public String getResult_code() {
        if (this.map.get("result_code") != null)
            return (String) this.map.get("result_code");
        return null;
    }

    /**
     * 返回状态码
     *
     * @return
     */
    public String getReturn_code() {
        if (this.map.get("return_code") != null)
            return (String) this.map.get("return_code");
        return null;
    }

    /**
     * 签名
     *
     * @return
     */
    public String getSign() {
        if (this.map.get("sign") != null)
            return (String) this.map.get("sign");
        return null;
    }

    /**
     * 子商户号
     *
     * @return
     */
    public String getSub_mch_id() {
        if (this.map.get("sub_mch_id") != null)
            return (String) this.map.get("sub_mch_id");
        return null;
    }

    public String getTime_end() {
        if (this.map.get("time_end") != null)
            return (String) this.map.get("time_end");
        return null;
    }

    /**
     * 交易金额
     *
     * @return
     */
    public String getTotal_fee() {
        if (this.map.get("total_fee") != null)
            return (String) this.map.get("total_fee");
        return null;
    }

    /**
     * 交易类型
     *
     * @return
     */
    public String getTrade_type() {
        if (this.map.get("trade_type") != null)
            return (String) this.map.get("trade_type");
        return null;
    }

    public String getTransaction_id() {
        if (this.map.get("transaction_id") != null)
            return (String) this.map.get("transaction_id");
        return null;
    }

    /**
     * 验证签名是否正确
     *
     * @param map
     * @param key
     * @return
     */
    public boolean verfySign(Map<String, Object> map, String key) {
        String sign = (String) map.get("sign");
        if (sign != null && sign.equalsIgnoreCase(WxSign.getSign(map, key))) {
            return true;
        }
        return false;
    }

}
