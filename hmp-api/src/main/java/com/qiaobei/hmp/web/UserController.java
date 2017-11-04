package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.support.Result;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/4/10.
 */
@Api(value = "user-api", description = "有关于用户的CURD操作", position = 5)
@Controller
@RequestMapping("/swagger")
public class UserController {
    @ApiOperation(value = "注册", notes = "注册用户", position = 3)
    @ResponseBody
    @RequestMapping(value = { "/regist" }, method = RequestMethod.POST)
    public Result regist(@RequestBody Patient patient) {

        return new Result();
    }


}
