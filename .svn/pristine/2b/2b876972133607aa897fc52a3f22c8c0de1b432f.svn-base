package com.qiaobei.hmp.wxpay.utils;

import java.net.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;


public class PayUtil {
    /**
     * 获取本机IP地址（内网）
     *
     * @return IP
     */
    public static String getLocalIp() {
        String ip = null;
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
                for (java.net.InterfaceAddress add : InterfaceAddress) {
                    InetAddress Ip = add.getAddress();
                    if (Ip != null && Ip instanceof Inet4Address) {
                        ip = Ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return ip;
    }

    /**
     * 生成由[A-Z,0-9]生成的随机字符串
     *
     * @param length 欲生成的字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(2);
            long result = 0;

            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
                default:
            }
        }
        return sb.toString();
    }
}
