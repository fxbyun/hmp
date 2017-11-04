package com.qiaobei.hmp.support;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.PropertiesLoader;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;

public class SMSUtil {

    private static Logger logger = LoggerFactory.getLogger(SMSUtil.class);
    private static PropertiesLoader propertiesLoader = new PropertiesLoader("classpath:/application.properties");
    private final static String URL = propertiesLoader.getProperty("rest_server");
    private final static String VERSION = propertiesLoader.getProperty("version");
    private static final String UTF8 = "utf-8";
    private static final String ACCEPT = "application/json";
    private static final String ACCOUNT_SID = "2031b02927f241895b796faa8e7b00bc";
    private static final String AUTH_TOKEN = "11cfdbcf0ebfd5681f1ef8424e8cb3a4";
    private static final String APP_ID = "92b6a277f9d642088e4589d6a3b61b02";
    public static final String TEMPLATE_ID = "17630";

    public static String sendSMS(String templateId, String phoneNo, String param) throws Exception {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String timestamp = DateUtils.date2Str(DateUtils.yyyymmddhhmmss);// 时间戳
        String signature = getSignature(ACCOUNT_SID, AUTH_TOKEN, timestamp);
        // 构造请求URL内容
        String url = getStringBuffer().append("/").append(VERSION)
                .append("/Accounts/").append(ACCOUNT_SID)
                .append("/Messages/templateSMS")
                .append("?sig=").append(signature).toString();
        TemplateSMS templateSMS = new TemplateSMS();
        templateSMS.setAppId(APP_ID);
        templateSMS.setTemplateId(templateId);
        templateSMS.setTo(phoneNo);
        templateSMS.setParam(param);
        String body = JSON.toJSONString(templateSMS);
        body = "{\"templateSMS\":" + body + "}";
        logger.info(body);
        HttpResponse response = postSMS(ACCEPT, ACCOUNT_SID, AUTH_TOKEN, timestamp, url, httpclient, body);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity, UTF8);
        }
        EntityUtils.consume(entity);
        httpclient.close();
        return result;
    }

    /**
     * MD5数字签名
     */
    private static String md5Digest(String src) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(src.getBytes(UTF8));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * BASE64编码
     */
    private static String base64Encoder(String src) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(src.getBytes(UTF8));
    }

    private static HttpResponse postSMS(String cType, String accountSid, String authToken, String timestamp,
                                        String url, CloseableHttpClient httpclient, String body) throws Exception {
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Accept", cType);
        httppost.setHeader("Content-Type", cType + ";charset=utf-8");
        String src = accountSid + ":" + timestamp;
        String auth = base64Encoder(src);
        httppost.setHeader("Authorization", auth);
        BasicHttpEntity requestBody = new BasicHttpEntity();
        requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
        requestBody.setContentLength(body.getBytes("UTF-8").length);
        httppost.setEntity(requestBody);
        // 执行客户端请求
        HttpResponse response = httpclient.execute(httppost);
        return response;
    }

    private static StringBuffer getStringBuffer() {
        StringBuffer sb = new StringBuffer("https://");
        sb.append(URL);
        return sb;
    }

    private static String getSignature(String accountSid, String authToken, String timestamp) throws Exception {
        String sig = accountSid + authToken + timestamp;
        String signature = md5Digest(sig);
        return signature;
    }
}
