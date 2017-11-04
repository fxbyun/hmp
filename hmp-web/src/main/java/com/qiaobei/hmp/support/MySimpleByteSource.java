package com.qiaobei.hmp.support;

import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA 2016.4
 * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 *
 * @author 凉生
 *         Date 2017/2/7 0007.
 *         Time 11:00.
 */
public class MySimpleByteSource extends SimpleByteSource implements Serializable {

    private static final long serialVersionUID = 6200411788492161577L;

    public MySimpleByteSource(byte[] bytes) {
        super(bytes);
    }
}
