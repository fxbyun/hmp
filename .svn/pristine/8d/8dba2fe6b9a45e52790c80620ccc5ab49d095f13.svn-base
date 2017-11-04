package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.User;
import com.qiaobei.hmp.service.UserService;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "pwd", method = RequestMethod.GET)
    public String updatePwd() {
        return "updatePwd";
    }

    @RequestMapping(value = "pwd/save", method = RequestMethod.POST)
    public String updatePwd(@RequestParam String oldPwd,
                            @RequestParam String newPwd,
                            Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String salt = user.getSalt();
        if (!StringUtils.equals(Utils.encodePwd(oldPwd, salt), user.getPassword())) {
            model.addAttribute("error", "旧密码错误！");
        } else {
            user.setPassword(Utils.encodePwd(newPwd, salt));
            userService.saveUser(user);
            model.addAttribute("msg", "密码修改成功。");
        }
        return "updatePwd";
    }
}
