package com.qiaobei.hmp.web;


import com.qiaobei.hmp.support.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "user-api", description = "有关于用户的CURD操作")
@Controller
@RequestMapping(value = "/users") // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {


    @ApiOperation(value = "注册", notes = "注册用户", position = 3)
    @ResponseBody
    @RequestMapping(value = {"/regist"}, method = RequestMethod.POST)
    public Result regist() {

        return Result.ok();
    }


    @RequestMapping(value = "/swagger")
    public String swaggerIndex(Model model) {

        model.addAttribute("", "");
        return "/swaggerApi";
    }

}