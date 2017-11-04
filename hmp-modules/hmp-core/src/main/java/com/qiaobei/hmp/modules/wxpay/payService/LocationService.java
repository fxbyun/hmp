package com.qiaobei.hmp.modules.wxpay.payService;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.support.WeixinUtil;
import com.qiaobei.hmp.modules.wxpay.utils.WxSign;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/1/12 0012.
 */
public class LocationService {

    public static Map<String, String> JSSDKMap(String jsapi_ticket) {
        Map<String, String> JSSDMap = Maps.newHashMap();
        //获取access_token
        String access_token = WeixinUtil.getAccessToken(WeixinUtil.HAPPID, WeixinUtil.HAPPSECRET);
        System.out.println("access_token=" + access_token);

        if (null == jsapi_ticket) {
            //获取jsapi_ticket有效期7200秒
            jsapi_ticket = WeixinUtil.getTicket(access_token);
            System.out.println("jsapi_ticket=" + jsapi_ticket);
        }


        //随机字符串
        String noncestr = UUID.randomUUID().toString();

        //时间戳
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);

        //当前网页的URL
        String url = "http://mp.weixin.qq.com";

        String str = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + noncestr +
                "&timestamp=" + timestamp +
                "&url=" + url;

        //sha1加密
        String signature = WxSign.SHA1(str);
        System.out.println("noncestr=" + noncestr);
        System.out.println("timestamp=" + timestamp);
        System.out.println("signature=" + signature);

        JSSDMap.put("noncestr", noncestr);
        JSSDMap.put("timestamp", timestamp);
        JSSDMap.put("signature", signature);
        JSSDMap.put("appId", WeixinUtil.HAPPID);
        JSSDMap.put("jsapi_ticket", jsapi_ticket);
        return JSSDMap;
    }
}
