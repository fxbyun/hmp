package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.service.JClassAdviceDictService;
import com.qiaobei.hmp.support.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
@Controller
public class DataInputTest {
    @Resource
    private JClassAdviceDictService jClassAdviceDictService;

    @RequestMapping(value = "/JClassAdviceTest")
    @ResponseBody
    public Result JClassAdviceTest() {
        jClassAdviceDictService.setAdviceHelpCode();
        return Result.ok();
    }
}
