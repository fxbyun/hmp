package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Adverting;
import com.qiaobei.hmp.modules.entity.User;
import com.qiaobei.hmp.service.AdvertingService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Utils;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

@Controller
@RequestMapping(value = "/adverting/")
public class AdvertingController extends BaseController {

    @Resource
    private AdvertingService advertingService;

    private static EnumMap<Adverting.Position, String> positionMap = new EnumMap<>(Adverting
            .Position.class);
    private static EnumMap<Adverting.Type, String> typeMap = new EnumMap<>(Adverting.Type.class);

    static {
        positionMap.put(Adverting.Position.SystemTopText, "系统顶部公告");
        positionMap.put(Adverting.Position.SystemCenterImg, "系统中间图片");
        positionMap.put(Adverting.Position.SystemFooterImg, "系统下面图片");
        positionMap.put(Adverting.Position.SystemFooterVideo, "系统下面视频");
        positionMap.put(Adverting.Position.DoctorTopText, "医生公告");
        positionMap.put(Adverting.Position.DoctorFooterImg, "医生图片");
        positionMap.put(Adverting.Position.DoctorFooterVideo, "医生视频");
        typeMap.put(Adverting.Type.Text, "文本");
        typeMap.put(Adverting.Type.Pic, "图片");
        typeMap.put(Adverting.Type.video, "视频");
    }

    /**
     * 广告列表页
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    public String advertList(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "0")
            int pageNo) {
        if (pageNo != 0) {
            pageNo -= 1;
        }
        List<Sort.Order> orderList = Lists.newArrayList();
        orderList.add(new Sort.Order(Sort.Direction.ASC, "position"));
        orderList.add(new Sort.Order(Sort.Direction.ASC, "type"));
        Sort sort = new Sort(orderList);
        Pageable pageable = new PageRequest(pageNo, 10, sort);
        Page<Adverting> page = advertingService.findPage(pageable, (User) SecurityUtils.getSubject().getPrincipal());
        model.addAttribute("page", page);
        model.addAttribute("positionMap", positionMap);
        model.addAttribute("typeMap", typeMap);
        return "/adverting/list";
    }

    /**
     * 广告查询
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        Page<Adverting> page = advertingService.findPage(Utils.buildPageRequest(pageNo, Constants.PAGZ_SIZE));
        model.addAttribute("page", page);
        model.addAttribute("positionMap", positionMap);
        model.addAttribute("typeMap", typeMap);
        return "/adverting/list";
    }

    /**
     * 新建广告页
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("adverting", new Adverting());
        model.addAttribute("positionMap", positionMap);
        model.addAttribute("typeMap", typeMap);
        return "/adverting/add";
    }

    /**
     * 广告信息修改
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("adverting", advertingService.findById(id));
        model.addAttribute("typeMap", typeMap);
        model.addAttribute("positionMap", positionMap);
        return "/adverting/add";
    }

    /**
     * 删除广告
     */
    @RequestMapping(value = "del/{id}", method = RequestMethod.GET)
    public String del(@PathVariable Long id, Model model) {
        advertingService.deleteAdvert(advertingService.findById(id));
        return "redirect:/adverting/list";
    }

    /**
     * 保存广告信息
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String save(@ModelAttribute Adverting adverting,
                       @RequestParam(value = "file2", required = false) MultipartFile file, Model model) {
        model.addAttribute("positionMap", positionMap);
        model.addAttribute("typeMap", typeMap);
        if (adverting.getId() == null) {
            //if (adverting.getType().equals(Adverting.Type.Text) &&
            //        advertingService.getAdvertByPosition(adverting.getPosition()) != null) {
            //    model.addAttribute("error", "该位置已设置广告！");
            //    return "/adverting/add";
            //}
            adverting.setStatus(Adverting.Status.Normal);
            adverting.setCreateOn(new Date());
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            adverting.setCreateBy(user.getName());
            adverting.setCreateById(user.getId());
        }
        advertingService.save(adverting, file);
        model.addAttribute("adverting", adverting);
        model.addAttribute("msg", "广告保存成功。");
        return "/adverting/add";
    }

    @ModelAttribute
    public void getAdverting(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("adverting", advertingService.findById(id));
        }
    }
}
