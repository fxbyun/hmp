package com.qiaobei.hmp.support;

import com.qiaobei.hmp.modules.support.LoadJs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA 2016.2
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 16/8/23
 * Time 下午2:11
 */
public class LoadStaticFile extends TagSupport {

    private String url;
    private String type;
    private String ctx;

    @Override
    public int doStartTag() throws JspException {

        String urlFile = LoadJs.load(
                url,
                type, ctx);

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
}
