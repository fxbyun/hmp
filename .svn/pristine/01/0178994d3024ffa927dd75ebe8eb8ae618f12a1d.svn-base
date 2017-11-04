package com.qiaobei.hmp.modules.support;

import org.springframework.web.context.ContextLoader;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA 2016.2
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 16/8/23
 * Time 上午11:34
 */
public class StaticFileFilter implements Filter {

    @Resource
    private FileUtilsServer fileUtilsServer;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("过滤器初始化!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String fileLocal = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(((HttpServletRequest)
                request).getRequestURI());
        System.out.println("文件绝对路径:" + fileLocal);
        File file = new File(fileLocal);
        if (!file.exists()) {
            System.out.println("文件还不存在  执行 数据库写入到磁盘!");
            httpServletRequest.getRequestDispatcher("/advertising/outImageToDisk").forward(request, response);

//            fileUtilsServer.saveFile(httpServletRequest.getParameter("fileName"),dataFileService.getImagByUrl(httpServletRequest.getParameter("fileName")))
        } else {
            chain.doFilter(request, response);
        }


        System.out.println("过滤器执行完毕!");
    }

    @Override
    public void destroy() {
//        System.out.println("过滤器销毁");

    }
}
