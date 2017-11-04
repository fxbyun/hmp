package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Adverting;
import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.User;
import com.qiaobei.hmp.modules.support.FileUtilsServer;
import com.qiaobei.hmp.modules.webSocket.WebSocketHandlerImpl;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.AdvertingService;
import com.qiaobei.hmp.service.DataFileService;
import com.qiaobei.hmp.support.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by 连晋 on 2016/9/8 0008. 10:15
 * By IDEA 2016.2.3 汉化: www.java.sx
 */
@Controller
public class AvingSettingController extends BaseController {

    private static EnumMap<Adverting.Type, String> typeMap = new EnumMap<>(Adverting.Type.class);

    static {
        typeMap.put(Adverting.Type.Text, "文本");
        typeMap.put(Adverting.Type.Pic, "图片");
//        typeMap.put(Adverting.Type.video, "视频");
    }

    @Resource
    WebSocketHandlerImpl webSocketHandler;
    @Resource
    private FileUtilsServer fileUtilsServer;
    @Resource
    private AdvertingService advertingService;
    @Resource
    private DataFileService dataFileService;

    @RequestMapping(value = "/advertSet")
    public String advertSet(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "0")
            int pageNo) {
        List<Sort.Order> orderList = Lists.newArrayList();
        orderList.add(new Sort.Order(Sort.Direction.ASC, "type"));
        orderList.add(new Sort.Order(Sort.Direction.ASC, "orderNo"));
        orderList.add(new Sort.Order(Sort.Direction.ASC, "createOn"));
        Sort sort = new Sort(orderList);
        Pageable pageable = new PageRequest(pageNo, 10, sort);

        Page<Adverting> advertingPage = advertingService.findPage(pageable, new User(SecuritySupport.getDoctor().getId()));
        Doctor doctor = SecuritySupport.getDoctor();
        advertingPage.getContent().forEach(adverting -> {
            DataFile dataFile = dataFileService.getDataFile(adverting.getId(), DataFile.Type.Adverting);
            if (dataFile != null) {
                adverting.setFile(dataFile);
                outPutFileToDisk(dataFile, doctor);
            }
        });

        model.addAttribute("typeMap", typeMap);
        model.addAttribute("page", advertingPage);
        model.addAttribute("doctor", doctor);
        return "advSet";
    }

    //新建广告位
    @RequestMapping(value = "/advertNewSet")
    public String advertNewSet(Model model,
                               @RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "msg", required = false) String msg
    ) {
        Adverting adverting = new Adverting();
        if (id != null) {
            adverting = advertingService.findById(id);
        }

        adverting.setFile(dataFileService.getDataFile(adverting.getId(), DataFile.Type.Adverting));

        model.addAttribute("typeMap", typeMap);
        model.addAttribute("adverting", adverting);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("msg", msg);
        return "advNewSet";
    }

    //新建广告位
    @RequestMapping(value = "/updateAdvingSetting")
    @ResponseBody
    public Result updateAdvingSetting(
    ) {
        webSocketHandler.CallAdvingAndNusurByDoctor(SecuritySupport.getDoctor(), "setting");
        return Result.ok();
    }
    //

    /**
     * 保存广告信息
     */
    @RequestMapping(value = "/adverting/add", method = RequestMethod.POST)
    public String save(@ModelAttribute Adverting adverting,
                       @RequestParam(value = "files", required = false) MultipartFile file, Model model) {
        model.addAttribute("typeMap", typeMap);
        if (adverting.getId() == null) {
            adverting.setCreateOn(new Date());
        } else {
            adverting.setCreateOn(advertingService.findById(adverting.getId()).getCreateOn());
        }
        if (adverting.getType() == Adverting.Type.Pic) {
            adverting.setPosition(Adverting.Position.DoctorFooterImg);
        } else {
            adverting.setPosition(Adverting.Position.DoctorTopText);
        }
        adverting.setStatus(Adverting.Status.Normal);
        Doctor doctor = SecuritySupport.getDoctor();
        adverting.setCreateBy(doctor.getName());
        adverting.setCreateById(doctor.getId());

        advertingService.save(adverting, file);
        model.addAttribute("adverting", adverting);
        model.addAttribute("msg", "广告保存成功。");

        DataFile dataFile = dataFileService.getDataFile(adverting.getId(), DataFile.Type.Adverting);
        if (dataFile != null) {
            adverting.setFile(dataFile);
            outPutFileToDisk(dataFile, doctor);
        }
        return "redirect:/advertNewSet?id=" + adverting.getId();
    }

    @RequestMapping(value = "/deleteAdving", method = RequestMethod.POST)
    @ResponseBody
    public Result deleetById(@RequestParam("id") Long id) {
        advertingService.deleteAdvert(new Adverting(id));
        return Result.ok();
    }


    /**
     * 判断文件是否已经从数据库输出到网站 fileDir/医生ID 目录 如果没有 即 马上 写到 fileDir/医生ID  下面去
     */
    private void outPutFileToDisk(DataFile dataFile, Doctor doctor) {
        String fileAubUrl = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(File.separatorChar +
                "fileDir" + File.separatorChar + doctor.getId());
        File file = new File(fileAubUrl + File.separatorChar + dataFile.getFileName());
        if (!file.exists()) {
            fileUtilsServer.saveFile(dataFile.getFileName(), dataFile.getContent(), fileAubUrl);
        }
    }
}
