package com.qiaobei.hmp.modules.support;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class IPUtils {
    /**
     * 获取所在内网所有IP
     * @param
     * @throws InterruptedException
     */
    public static void gainAllIp() throws InterruptedException {
        final List<String> innerIp = Collections.synchronizedList(new ArrayList<String>());
        final CyclicBarrier barrier = new CyclicBarrier(255, new IpShow(innerIp));
        String hostAddress = getLocalIP();
        int pos = hostAddress.lastIndexOf(".");
        String wd = hostAddress.substring(0, pos + 1);
        for (int i = 1; i <= 255; i++) {
            String ip = wd + i;
            PingIpThread thread = new IPUtils.PingIpThread(ip, barrier, innerIp);
            thread.start();
        }
    }

    public static String  getLocalIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCanonicalHostName() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getIPFromDomainName(String domainName) {
        try {
            return InetAddress.getByName(domainName).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDomainNameFromIP(String ip) {
        try {
            return InetAddress.getByAddress(getIPBytes(ip)).getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] getIPBytes(String ip) {
        byte[] ipBytes = new byte[4];
        String[] ipStr = ip.split("[.]");

        for (int i = 0; i < 4; i++) {
            int m = Integer.parseInt(ipStr[i]);
            byte b= (byte)(m & 0xff);
            ipBytes[i] = b;
        }
        return ipBytes;
    }

    /**获得请求的ip地址
     * @param request
     * @return
     */
    public static String getIpString2(HttpServletRequest request){
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    /**多级反向代理的话,获得ip地址
     * @param request
     * @return
     */
    public static String  getIpString(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**ip.qq.com根据ip地址获取地址
     * @return
     */
    public static String getAddressByIP(String strIP) {
        String province=null;
        String city=null;
        try {
            URL url = new URL("http://ip.qq.com/cgi-bin/searchip?searchip1=" + strIP);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            strIP = result.substring(result.indexOf("该IP所在地为："));
            strIP = strIP.substring(strIP.indexOf("：") + 1);
//            province = strIP.substring(6, strIP.indexOf("省"));
            int shen = strIP.indexOf("省");
            int shi = strIP.indexOf("市");
            city = strIP.substring(shen + 1, shi);
        } catch (IOException e) {
            return null;
        }
        return city;
    }

    public static void main(String[] args) throws InterruptedException {
    }

    public static class IpShow implements Runnable {
        private List<String> innerIp;

        public IpShow(List<String> innerIp) {
            this.innerIp = innerIp;
        }

        @Override
        public void run() {
            for (String ip : innerIp) {
                System.out.println(ip);
            }
        }
    }

    public static class PingIpThread extends Thread {
        private String ip;
        private CyclicBarrier barrier;
        private List<String> list;

        public PingIpThread(String ip, CyclicBarrier barrier, List<String> list) {
            this.ip = ip;
            this.barrier = barrier;
            this.list = list;
        }

        public void run() {
            try {
                if (InetAddress.getByName(ip).isReachable(5000))
                    list.add(ip);
                barrier.await();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//          } catch (InterruptedException e) {
//              e.printStackTrace();
//          }
            catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
