package com.qiaobei.hmp.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * Created by Administrator on 2016/12/8 0008.
 */
public class SessionListenerMobileUser extends SessionListenerAdapter {
    @Override
    public void onExpiration(Session session) {
        System.err.println("手机用户session过期:" + session.toString());
        super.onExpiration(session);
    }
}
