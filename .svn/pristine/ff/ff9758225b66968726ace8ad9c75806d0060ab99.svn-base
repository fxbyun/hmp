package com.qiaobei.hmp.modules.support;

import org.springframework.web.context.ContextLoader;

import java.io.File;

/**
 * Created by IntelliJ IDEA 2016.2
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 16/8/23
 * Time 下午12:19
 * 根据传递的URL  自动加上文件 version=最后一次修改时间
 */
public class LoadJs {

    /**
     * @param fileUrl 需要引入的JS的相对路径
     * @param type    文件类型   js 或者 css
     * @return 返回   "<script src='{fileUrl?version=fileLastModifyTime}' type='text/javascript'></script>"
     */
    public static String load(String fileUrl, String type, String ctx) {
        String versionString = ctx + fileUrl +  //获取传递进来的URL
                getFileModifedTime(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/") +
                        fileUrl);  //获取 文件的最后一次修改时间
        if ("css".equals(type)) {
            //组装URL为  CSS引入
            return "<link href='needRep' type='text/css' rel='stylesheet' />".replace("needRep", versionString);
        }
        //组装URL为  js引入
        return "<script src='needRep' type='text/javascript'></script>".replace("needRep", versionString);
    }


    private static String getFileModifedTime(String url) {
        File file = new File(url);
        Long modifiedTime = 0L;
        if (file.exists() && file.isFile()) {
            modifiedTime = file.length();
        }
        return "?version=" + String.valueOf(modifiedTime);
    }

}
