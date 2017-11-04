package com.qiaobei.hmp.support;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/7 0007
 * Time 17:35
 */
public class SesionListenerMdzl extends SessionListenerAdapter {
    @Override
    public void onExpiration(Session session) {
        System.err.println("妈的炸了 session过期" + session.toString());
        super.onExpiration(session);
    }
}
