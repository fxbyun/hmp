package com.qiaobei.hmp.wxpay.orderBranch;

import java.util.Map;


public final class WxPayRefundResult implements _OrderBranchResult {
    private Map<String, Object> map = null;

    public void setResMap(Map<String, Object> map) {
        this.map = map;
    }

    public Object get(String key) {
        return null;
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    @Override
    public String toString() {
        if (null == map || map.size() <= 0) {
            return "not call setResMap(Map<String,Object> map)";
        }
        return map.toString();
    }
}
