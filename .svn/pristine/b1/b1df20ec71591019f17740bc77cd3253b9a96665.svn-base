package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.exception.NotCurrentUserException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;

/**
 * 控制器支持类
 *
 * @author Andy Du
 * @version 2016-4-14
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 参数绑定异常
     */
    @ExceptionHandler({BindException.class})
    public String bindException() {
        return "error/500";
    }

    /**
     * 授权登录异常
     */
    @ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {
        return "error/403";
    }

    /**
     * 将所有传递进来的String进行HTML编码，防止XSS攻击
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }

            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
        });
    }

    /**
     * 跳转到到登录页面(jsp接口调用)
     *
     * @return
     * @date
     * @author
     */
    public ModelAndView toLoginView() {
        System.out.println("当前用户已失效，请重新登录！");
        return new ModelAndView("/login");
    }

    public ModelAndView to500View() {
        return new ModelAndView("error/500");
    }

    /**
     * 基于@ExceptionHandler异常处理
     */
    @ExceptionHandler
    public ModelAndView exp(HttpServletRequest request, Exception ex) {

        request.setAttribute("ex", ex);

        // 根据不同错误转向不同页面
        if (ex instanceof NotCurrentUserException) {
            System.out.println("异常:" + ex);
            return to500View();
        } else if (ex instanceof NullPointerException) {
            System.out.println("异常:" + ex);
            return to500View();
        } else {
            System.out.println("异常:" + ex);
            return to500View();
        }
    }

}
