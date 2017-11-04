package com.qiaobei.hmp.test.dataImpl;

import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * Created by IntelliJ IDEA 2016.4
 * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * @author 凉生
 * Date 2016/5/24 0024.
 * Time 16:07.
 */


public class Pinyin {
    /**
     * 将汉字转换为全拼
     *
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {

        return PinyinHelper.convertToPinyinString(src, "");
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        return PinyinHelper.getShortPinyin(str);
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (byte aGBK : bGBK) {
            // System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(aGBK & 0xff)).append(" ");
        }
        return strBuf.toString();
    }

    //
    public static void main(String[] args) {
        System.out.println(getPinYinHeadChar("草泥马"));
        System.out.println(getPinYin("草泥马"));

    }




}

