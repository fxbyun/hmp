package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.NoticeItem;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.NoticeItemService;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class NoticeItemController extends BaseController {
    @Resource
    private NoticeItemService noticeItemService;

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String notice(Model model, HttpServletRequest request) {
        model.addAttribute("page", noticeItemService.findPage(Utils.buildPageRequest(1), SecuritySupport.getDoctorId
                ()));
        request.getSession().setAttribute("noReadCount", noticeItemService.noReadCount(SecuritySupport.getDoctorId(),
                NoticeItem.Status.Normal));
        return "notice";
    }

    @RequestMapping(value = "/notice/{pageNo}", method = RequestMethod.GET)
    public String notice(@PathVariable Integer pageNo, Model model) {
        model.addAttribute("page", noticeItemService.findPage(Utils.buildPageRequest(pageNo), SecuritySupport
                .getDoctorId()));
        return "notice";
    }

    @RequestMapping(value = "/notice/item/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result change(@PathVariable Long id, HttpServletRequest request) {
        NoticeItem item = noticeItemService.getNoticeItem(id);
        if (NoticeItem.Status.Normal.equals(item.getStatus())) {
            item.setStatus(NoticeItem.Status.Readed);
            item.setReadOn(new Date());
            noticeItemService.save(item);
            Long noReadCount = noticeItemService.noReadCount(SecuritySupport.getDoctorId(), NoticeItem.Status.Normal);
            request.getSession().setAttribute("noReadCount", noReadCount);
            return Result.ok(noReadCount);
        }
        return Result.ok();
    }

}
