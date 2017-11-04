package com.qiaobei.hmp.modules.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by IntelliJ IDEA 2016.4
 * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 *
 * @author 凉生
 *         Date 2017/2/7 0007.
 *         Time 11:13.
 */
public class ApplicationContextPostListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        SpringContextHolder.setApplicationContext(applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
