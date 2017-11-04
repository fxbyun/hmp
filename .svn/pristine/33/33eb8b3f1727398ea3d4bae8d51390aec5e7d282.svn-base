package com.qiaobei.hmp.support;

import com.qiaobei.hmp.modules.support.LoadJs;
import com.qiaobei.hmp.security.SecuritySupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.2
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 16/8/23
 * Time 下午2:11
 */
public class LoadStaticFile extends TagSupport {

    private static final long serialVersionUID = 5890051580169700711L;
    public static List<String> Config_Addr = new ArrayList<>(Arrays.asList("江苏省",
            "上海市",
            "浙江省",
            "安徽省",
            "福建省",
            "山东省"));
    private String url;
    private String type;
    private String ctx;
    private boolean needChengStyle = false;

    @Override
    public int doStartTag() throws JspException {
        if (needChengStyle) {
            try {
                if (Config_Addr.contains(SecuritySupport.getDoctor().getProvince()))
                    url = url.replace("green.css", "blue.css");
            } catch (Exception e) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) pageContext.getRequest();
                System.out.println(httpServletRequest.getRequestURI() + " 用户未登录,无法判断所属css为文件,默认为green.css");
            }

        }
        String urlFile = LoadJs.load(
                url,
                type,
                ctx);
        JspWriter jspWriter = pageContext.getOut();
        try {
            jspWriter.print(urlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.doStartTag();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCtx(String ctx) {
        this.ctx = ctx;
    }

    public void setNeedChengStyle(boolean needChengStyle) {
        this.needChengStyle = needChengStyle;
    }
}
