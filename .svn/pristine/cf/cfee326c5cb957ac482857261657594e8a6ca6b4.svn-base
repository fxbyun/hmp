package com.qiaobei.hmp.modules.support;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class AddressUtils
{



    /**
     *
     * @param IP
     * @return
     */
    public static Map<String,String> GetAddressByIp(String IP){
        Map<String,String> palaceMap = new HashedMap();
        try{
            String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip="+IP);
            //System.out.println(str);

            JSONObject obj = JSONObject.parseObject(str);
            JSONObject obj2 =  (JSONObject) obj.get("data");

            String code =  obj.get("code")+"";
            if(code.equals("0")){
                palaceMap.put("country",(String) obj2.get("country"));
                palaceMap.put("area",(String) obj2.get("area"));
                palaceMap.put("city",(String) obj2.get("city"));
                palaceMap.put("isp",(String) obj2.get("isp"));
                //代表获取成功
                palaceMap.put("status","0");
                palaceMap.put("message","地址获取成功！");

            }else{
                palaceMap.put("status","1");
                palaceMap.put("message","地址获取失败！");

            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("地址获取失败！");
        }
        return palaceMap;
    }

    public static String getJsonContent(String urlStr)
    {
        try
        {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url
                    .openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200)
            {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
    private static String ConvertStream2Json(InputStream inputStream)
    {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try
        {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
            {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static void main(String[] args) {
//        AddressUtils addressUtils = new AddressUtils();
//        Map<String,String> place=addressUtils.GetAddressByIp("210.21.232.38");
//        System.out.println(place.get("city"));
    }

}
