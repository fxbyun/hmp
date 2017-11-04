package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.service.NoticeService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.EnumMap;

@Controller
@RequestMapping(value = "/notice/")
public class NoticeController {

    private static Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    private static EnumMap<Notice.Type, String> typeMap = new EnumMap<Notice.Type, String>(Notice.Type.class);
    static {
        typeMap.put(Notice.Type.System, "系统通知");
        typeMap.put(Notice.Type.User, "用户消息");
    }

    /**
     * 通知列表页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String noticeList(Model model) {
        Page<Notice> page = noticeService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null);
        model.addAttribute("page", page);
        model.addAttribute("typeMap", typeMap);
        return "notice/list";
    }

    /**
     * 通知查询
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false) String subject, Model model) {
        Page<Notice> page = noticeService.findPage(Utils.buildPageRequest(pageNo, Constants.PAGZ_SIZE), subject);
        model.addAttribute("page", page);
        model.addAttribute("subject", subject);
        model.addAttribute("typeMap", typeMap);
        return "notice/list";
    }

    /**
     * 发通知页
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("notice", new Notice());
        return "notice/add";
    }

    /**
     * 通知修改
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("notice", noticeService.findById(id));
        return "notice/add";
    }

    /**
     * 发通知页
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String save(@ModelAttribute Notice notice, Model model) {
        if (StringUtils.isEmpty(notice.getSubject()) ||
                StringUtils.isEmpty(notice.getContent())) {
            model.addAttribute("error", "标题和内容不能为空！");
            return "notice/add";
        }
        notice.setStatus(Notice.Status.Normal);
        notice.setCreateOn(new Date());
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        notice.setCreateBy(user.getName());
        notice.setCreateById(user.getId());
        notice.setType(Notice.Type.System);
        noticeService.save(notice);
        model.addAttribute("notice", notice);
        model.addAttribute("msg", "通知保存成功。");
        return "notice/add";
    }

    @ModelAttribute
    public void getNotice(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("notice", noticeService.findById(id));
        }
    }
}
