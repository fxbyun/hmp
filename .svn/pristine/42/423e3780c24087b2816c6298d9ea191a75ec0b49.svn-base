package com.qiaobei.hmp.wxpay.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class WxSign {
    /**
     * 微信支付签名算法sign
     */
    public static String getSign(Map<String, Object> map, String key) {
        StringBuffer sb = new StringBuffer();
        List<String> keyList = new ArrayList<String>(map.keySet());
        Collections.sort(keyList);//对array排序
        String str = null;
        for (String s : keyList) {
            str = (String) map.get(s);
            if (StringUtils.isEmpty(str) || "sign".equals(s)) {
                continue;
            }
            sb.append(s + "=" + str + "&");
        }
        sb.append("key=" + key);
        String sign = DigestUtils.md5Hex(getContentBytes(sb.toString(), "utf-8"));
        return sign;
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

}
