package com.qiaobei.hmp.schedule;

import com.qiaobei.hmp.support.DateUtils;
import com.qiaobei.hmp.support.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("scheduleProcess")
public class SchedulePreocess {

    private static Logger log = LoggerFactory.getLogger(SchedulePreocess.class);

    public void getAccessToken() {
        if (!WeixinUtil.IS_LOCAL) {
            log.info("Task start:" + DateUtils.date2Str(DateUtils.time_sdf));
            WeixinUtil.ACCESS_TOKEN_PHAR = WeixinUtil.getAccessToken(WeixinUtil.HAPPID, WeixinUtil.HAPPSECRET);
            WeixinUtil.ACCESS_TOKEN_OP = WeixinUtil.getAccessToken(WeixinUtil.PAPPID, WeixinUtil.PAPPSECRET);
            log.info("Task end");
        }
    }
}
