package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.ErrorLog;
import com.qiaobei.hmp.modules.entity.ErrorLogService;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.LogService;
import com.qiaobei.hmp.support.JsErrorLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.Date;
import java.util.Formatter;
import java.util.Map;

@Controller
public class LogController extends BaseController {

    @Resource
    private LogService logService;
    @Resource
    private ErrorLogService errorLogService;

    /**
     * 日志列表1
     */
    @RequestMapping("/pub/errorList")
    public String logList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        Page<ErrorLog> errorLogPage = errorLogService.getErrorLogByPage(pageable);
        model.addAttribute("errorLogPage", errorLogPage);
        model.addAttribute("nowPage", page + 1);
        return "/error/logList";
    }

    /**
     * 日志查询
     */
    @RequestMapping("/queryLog.html")
    @ResponseBody
    public Object queryLog(ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        String pageNo = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        String sort = request.getParameter("sidx");
        String order = request.getParameter("sord");
//        PageRequest page = Utils.buildPageRequest(Integer.parseInt(pageNo),
//                Integer.parseInt(pageSize), sort, order);
//        Page<SystemLog> data =  logService.findPage(page,searchParams);
//        return Common.switchPage(data);
        return null;
    }


    @RequestMapping(value = "/common/log/js/error", method = RequestMethod.POST)
    public ResponseEntity<String> jsError(JsErrorLog jsErrorLog) {
        final Formatter formatter = new Formatter();
        formatter.format("\nerrorMessage: %s\n", jsErrorLog.getErrorMessage());
        formatter.format("scriptURI: %s\n", jsErrorLog.getScriptURI());
        formatter.format("lineNumber: %s\n", jsErrorLog.getLineNumber());
        formatter.format("columnNumber: %s\n", jsErrorLog.getColumnNumber());
        logger.error(formatter.toString());

        Doctor doctor = SecuritySupport.getDoctor();
        ErrorLog errorLog = new ErrorLog();
        errorLog.setDoctor(doctor);
        errorLog.setCreateTime(new Date());
        errorLog.setErrorInfo(jsErrorLog.getErrorMessage());
        errorLog.setType("前台错误");
        errorLog.setErrorUrl(jsErrorLog.getScriptURI());
        errorLog.setErrorTitle(jsErrorLog.getErrorMessage().split("at")[0]);

        errorLogService.save(errorLog);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/common/log/java/error", method = RequestMethod.POST)
    public ResponseEntity<String> javaError(@RequestParam(value = "errorHappyUrl", required = false) String
                                                        errorHappyUrl,
                                            @RequestParam(value = "errorTitle", required = false) String errorTitle,
                                            @RequestParam(value = "errorStack", required = false) String errorStack) {

        Doctor doctor = SecuritySupport.getDoctor();
        ErrorLog errorLog = new ErrorLog();
        errorLog.setDoctor(doctor);
        errorLog.setCreateTime(new Date());
        errorLog.setErrorInfo(errorStack);
        errorLog.setType("后台错误");
        errorLog.setErrorUrl(errorHappyUrl);
        errorLog.setErrorTitle(errorTitle);

        errorLogService.save(errorLog);

        logger.error(errorHappyUrl + "/n" + errorTitle + "/n" + errorStack);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
