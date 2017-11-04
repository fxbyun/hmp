package com.qiaobei.hmp.web;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.service.DoctorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/account/")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private static Map<String, String> allStatus = Maps.newHashMap();

    static {
        allStatus.put("enabled", "有效");
        allStatus.put("disabled", "无效");
    }

    @Resource
    private DoctorService doctorService;

    // 特别设定多个ReuireRoles之间为Or关系，而不是默认的And.
//	@RequiresRoles(value = { "Admin", "Doctor" }, logical = Logical.OR)
    @RequestMapping(value = "")
    public String list(Model model, ServletRequest request) {

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        Page<Doctor> users = doctorService.findPage(null, searchParams);
        model.addAttribute("page", users);
        model.addAttribute("allStatus", allStatus);
        return "account/userList";
    }

    @RequiresRoles("Admin")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("user", doctorService.getUser(id));
        model.addAttribute("allStatus", allStatus);
//		model.addAttribute("allRoles", doctorService.getAllRole());
        return "account/userForm";
    }

    /**
     * 演示自行绑定表单中的checkBox roleList到对象中.
     */
    @RequiresPermissions("user:edit")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("user") Doctor doctor,
                         @RequestParam(value = "roleList") List<Long> checkedRoleList, RedirectAttributes
                                     redirectAttributes) {

        // bind roleList
//		user.getRoleList().clear();
//		for (Long roleId : checkedRoleList) {
//			Role role = new Role(roleId);
//			user.getRoleList().add(role);
//		}

        doctorService.saveDoctor(doctor, null, null);

        redirectAttributes.addFlashAttribute("message", "保存用户成功");
        return "redirect:/account/users";
    }

    @RequestMapping(value = "checkAccount")
    @ResponseBody
    public String checkAccount(@RequestParam("oldAccount") String oldAccount,
                               @RequestParam("account") String account) {
        if (account.equals(oldAccount)) {
            return "true";
        } else if (doctorService.getDoctorByMobile(account) == null) {
            return "true";
        }
        return "false";
    }

    /**
     * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
     * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("user", doctorService.getDoctor(id));
        }
    }

    /**
     * 不自动绑定对象中的roleList属性，另行处理。
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("roleList");
    }
}
