package com.qiaobei.hmp.modules.support;


/**
 * 生成各种订单号
 */
public class IaiIntoUtils {

    public static final String GoodsNo_RK = "RK";       //入库
    public static final String GoodsNo_ZN = "ZN";       //智能
    public static final String GoodsNo_SH = "SH";       //损耗

    public static String createRKGoodsNo(Long doctorId, Long iaiIntoId) {
        return GoodsNo_RK + createGoodsNO(doctorId, iaiIntoId);
    }

    public static String createZNGoodsNo(Long doctorId, Long iaiIntoId) {
        return GoodsNo_ZN + createGoodsNO(doctorId, iaiIntoId);
    }

    public static String createSHGoodsNo(Long doctorId, Long iaiLossId) {
        return GoodsNo_SH + createGoodsNO(doctorId, iaiLossId);
    }

    public static String createGoodsNO(Long doctorId, Long iaiId) {
        StringBuilder goodsNo = new StringBuilder();
        goodsNo.append(DateUtils.date2SStr());
        goodsNo.append(doctorId);
        long length = 10 - (doctorId + "").length();

        long num = iaiId;

        long lingNum = length - (num + "").length();
        String lingText = "";
        for (long i = 0; i < lingNum; i++) {
            lingText = lingText + "0";
        }
        goodsNo.append("-");
        goodsNo.append(lingText);
        goodsNo.append(iaiId);
        return goodsNo.toString();
    }


}
