package com.qiaobei.hmp.modules.wxpay;


import com.qiaobei.hmp.modules.wxpay.excption.ResponsException;
import com.qiaobei.hmp.modules.wxpay.orderBranch._OrderBranchResult;
import com.qiaobei.hmp.modules.wxpay.utils.WxSign;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxResponseImpl {
    private _OrderBranchResult orderBranchResult = null;
    private boolean isInstance = false;
    private Map<String, Object> map = new HashMap<String, Object>();


    /**
     * 创建实例后通过getMap()拿返回的数据
     *
     * @param xml
     * @throws ResponsException
     */
    public WxResponseImpl(String xml, String key) throws ResponsException {
        super();
        this.buildResultsMap(xml);
        if ("SUCCESS".equals(this.map.get("return_code"))) {
            if (!verfySign(key)) {
                //签名错误
                throw new ResponsException("微信返回封装类，签名验证错误。");
            }
        }
    }

    /**
     * 验证签名是否正确
     *
     * @param key
     * @return
     */
    private boolean verfySign(String key) {
        String sign = (String) this.map.get("sign");
        String resSign = WxSign.getSign(this.map, key);
        return sign != null && sign.equalsIgnoreCase(resSign);
    }


    /**
     * 把xml封装成map
     * 只针对没有重复节点的xml 字符串
     *
     * @param xml
     * @return
     */
    private void buildResultsMap(String xml) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (null != document) {
            Element root = document.getRootElement();
            List<Element> childElements = root.elements();
            for (Element child : childElements) {
                this.map.put(child.getName(), child.getStringValue());
            }
        }
        return;
    }


    public Map<String, Object> getMap() {
        return this.map;
    }

}
