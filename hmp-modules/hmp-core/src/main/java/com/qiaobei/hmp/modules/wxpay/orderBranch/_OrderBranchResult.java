package com.qiaobei.hmp.modules.wxpay.orderBranch;

import java.util.Map;


public interface _OrderBranchResult {
    public void setResMap(Map<String, Object> map);

    public Object get(String key);

    public Map<String, Object> getMap();

}
