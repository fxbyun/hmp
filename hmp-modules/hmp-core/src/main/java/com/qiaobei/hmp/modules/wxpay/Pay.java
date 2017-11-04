package com.qiaobei.hmp.modules.wxpay;


import com.qiaobei.hmp.modules.wxpay.excption.ArgumentsException;
import com.qiaobei.hmp.modules.wxpay.excption.ResponsException;

import java.io.IOException;
import java.util.Map;


public interface Pay {
    public Map<String, Object> doQrPay(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException;

    public Map<String, Object> doWebPay(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException;


    public Map<String, Object> doBarPay(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException;

    public Map<String, Object> doQuery(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException;

    public Map<String, Object> doRefund(Map<String, Object> params) throws ArgumentsException, IOException,
            ResponsException;
}
