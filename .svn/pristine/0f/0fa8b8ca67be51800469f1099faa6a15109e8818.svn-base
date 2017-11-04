package com.qiaobei.hmp.schedule;

import com.qiaobei.hmp.redis.WxCache;
import com.qiaobei.hmp.redis.WxInfo;
import com.qiaobei.hmp.support.DateUtils;
import com.qiaobei.hmp.support.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("scheduleProcess")
public class SchedulePreocess {
    private static Logger log = LoggerFactory.getLogger(SchedulePreocess.class);
    private final WxCache wxCache;

    @Autowired
    public SchedulePreocess(WxCache wxCache) {
        this.wxCache = wxCache;
    }

    public void getAccessToken() {
        if (!WeixinUtil.IS_LOCAL) {
            log.info("Task start:" + DateUtils.date2Str(DateUtils.time_sdf));
            //从redis获取微信token
            WxInfo wxInfo = wxCache.load("wxToken");
            if (wxInfo == null) {
                log.info("wxToken 是 空的 开始获取token");
                wxInfo = new WxInfo();
                wxInfo.setACCESS_TOKEN_PHAR(WeixinUtil.getAccessToken(WeixinUtil.HAPPID, WeixinUtil.HAPPSECRET));
                wxInfo.setACCESS_TOKEN_OP(WeixinUtil.getAccessToken(WeixinUtil.PAPPID, WeixinUtil.PAPPSECRET));
                //有效期为 100分钟
                wxCache.addOrUpdate("wxToken", wxInfo, 90);
                System.out.println("wxToken 获取成功:" + wxInfo.toString());
            }
            WeixinUtil.ACCESS_TOKEN_PHAR = wxInfo.getACCESS_TOKEN_PHAR();
            WeixinUtil.ACCESS_TOKEN_OP = wxInfo.getACCESS_TOKEN_OP();
//            WeixinUtil.ACCESS_TOKEN_PHAR = WeixinUtil.getAccessToken(WeixinUtil.HAPPID, WeixinUtil.HAPPSECRET);
//            WeixinUtil.ACCESS_TOKEN_OP = WeixinUtil.getAccessToken(WeixinUtil.PAPPID, WeixinUtil.PAPPSECRET);
            log.info("Task end");
        } else {
            log.info("当前为本地项目,不获取微信token!");
        }
    }
}
