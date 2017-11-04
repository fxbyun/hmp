package com.qiaobei.hmp.support;

import java.io.File;

/**
 * Created by teemoer@cntv.cn on 2016/6/17 0017.
 */
public class WebCacheAutoVersion {
    public static String AutoVersion(String path, String root, String fileName) {
        File file = new File(path, fileName);
        if (file.exists()) {
            return root + fileName + "?ver=" + file.lastModified();
        } else {
            return root + fileName + "?ver=1";
        }

    }
}
