package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Advert;
import com.qiaobei.hmp.modules.entity.Advert;
import com.qiaobei.hmp.modules.entity.User;
import com.qiaobei.hmp.service.AdvertService;
import com.qiaobei.hmp.service.AdvertService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.EnumMap;
import java.util.List;

@Controller
@RequestMapping(value = "/advert/")
public class AdvertController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AdvertController.class);

    @Autowired
    private AdvertService advertService;

    private static EnumMap<Advert.Position, String> positionMap = new EnumMap<Advert.Position, String>(Advert
            .Position.class);
    private static EnumMap<Advert.Type, String> typeMap = new EnumMap<Advert.Type, String>(Advert.Type.class);
    static {
        positionMap.put(Advert.Position.Print, "打印页面");
        positionMap.put(Advert.Position.Home, "坐诊页面");
        typeMap.put(Advert.Type.Text, "文本");
        typeMap.put(Advert.Type.Pic, "图片");
    }

    /**
     * 广告列表页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String advertList(Model model) {
        Page<Advert> page = advertService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE));
        model.addAttribute("page", page);
        model.addAttribute("positionMap", positionMap);
        model.addAttribute("typeMap", typeMap);
        return "advert/list";
    }

    /**
     * 广告查询
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        Page<Advert> page = advertService.findPage(Utils.buildPageRequest(pageNo, Constants.PAGZ_SIZE));
        model.addAttribute("page", page);
        model.addAttribute("positionMap", positionMap);
        model.addAttribute("typeMap", typeMap);
        return "Advert/list";
    }

    /**
     * 新建广告页
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("advert", new Advert());
        model.addAttribute("positionMap", positionMap);
        model.addAttribute("typeMap", typeMap);
        return "advert/add";
    }

    /**
     * 广告信息修改
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("advert", advertService.findById(id));
        model.addAttribute("typeMap", typeMap);
        model.addAttribute("positionMap", positionMap);
        return "advert/add";
    }

    /**
     * 删除广告
     */
    @RequestMapping(value = "del/{id}", method = RequestMethod.GET)
    public String del(@PathVariable Long id, Model model) {
        advertService.deleteAdvert(advertService.findById(id));
        return "redirect:/advert/list";
    }

    /**
     * 保存广告信息
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String save(@ModelAttribute Advert advert,
                       @RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        model.addAttribute("positionMap", positionMap);
        model.addAttribute("typeMap", typeMap);
        if (advert.getId() == null) {
            if (advert.getType().equals(Advert.Type.Text) &&
                    advertService.getAdvertByPosition(advert.getPosition()) != null) {
                model.addAttribute("error", "该位置已设置广告！");
                return "advert/add";
            }
            advert.setStatus(Advert.Status.Normal);
            advert.setCreateOn(new Date());
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            advert.setCreateBy(user.getName());
            advert.setCreateById(user.getId());
        }
        advertService.save(advert, file);
        model.addAttribute("advert", advert);
        model.addAttribute("msg", "广告保存成功。");
        return "advert/add";
    }

    @ModelAttribute
    public void getAdvert(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("advert", advertService.findById(id));
        }
    }
}
