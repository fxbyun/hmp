package com.qiaobei.hmp.web;

import com.qiaobei.hmp.service.UserLoginService;
import com.qiaobei.hmp.support.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
public class LoginLogController extends BaseController {

    @Resource
    private UserLoginService userLoginService;

    /**
     * 登陆信息列表
     */
    @RequestMapping("/loginList.html")
    public String loginList() {
        return "admin/loginList";
    }

    /**
     * 登陆信息查询
     */
    @RequestMapping("/queryLoginLog.html")
    @ResponseBody
    public Object queryLoginLog(ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        String pageNo = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        String sort = request.getParameter("sidx");
        String order = request.getParameter("sord");
//        PageRequest page = Utils.buildPageRequest(Integer.parseInt(pageNo),
//                Integer.parseInt(pageSize), sort, order);
//        Page<UserLogin> data =  userLoginService.findPage(page,searchParams);
        //有内嵌对象时需要toJSON
//        return JSON.toJSON(Common.switchPage(data));
        return null;
    }
}
