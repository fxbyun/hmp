package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.modules.entity.User;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.SMSUtil;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.utils.PropertiesLoader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.EnumMap;

@Controller
@RequestMapping(value = "/doctor/")
public class DoctorController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(DoctorController.class);
    private static PropertiesLoader propertiesLoader = new PropertiesLoader("classpath:/application.properties");
    private static EnumMap<Gender, String> genderMap = new EnumMap<Gender, String>(Gender.class);

    static {
        genderMap.put(Gender.Male, "男");
        genderMap.put(Gender.Female, "女");
    }

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private NationService nationService;
    @Autowired
    private EmrService emrService;
    @Autowired
    private DataFileService dataFileService;
    @Autowired
    private UserService userService;

    /**
     * 医生列表页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        Page<Doctor> page = doctorService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null, null, null);
        model.addAttribute("page", page);
        return "doctor/list";
    }

    /**
     * 医生查询
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false) String doctorName,
                        @RequestParam(required = false) String outName,
                        @RequestParam(required = false) Doctor.Status status, Model model) {
        Page<Doctor> page = doctorService.findPage(Utils.buildPageRequest(pageNo, Constants.PAGZ_SIZE), outName,
                doctorName, status);
        model.addAttribute("page", page);
        model.addAttribute("doctorName", doctorName);
        model.addAttribute("outName", outName);
        model.addAttribute("status", status);
        return "doctor/list";
    }

    /**
     * 医生详情页
     */
    @RequestMapping("view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctor(id);
        Boolean isAdminUser = userService.isSupervisor(userService.getCurrentUser());
        //是否是子医生
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            model.addAttribute("isSubDoctor", true);
        } else {
            model.addAttribute("isSubDoctor", false);
        }
        model.addAttribute("isAdminUser", isAdminUser);
        model.addAttribute("doctor", doctor);
        model.addAttribute("patientCount", emrService.getPatientCountByDoctor(id));
        return "doctor/view";
    }

    /**
     * 审核通过
     */
    @RequestMapping("pass/{id}")
    @ResponseBody
    public Result pass(@PathVariable Long id) {
        Doctor d = doctorService.getDoctor(id);
        d.setStatus(Doctor.Status.Normal);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        d.setVerifyBy(user.getName());
        d.setVerifyById(user.getId());
        d.setVerifyOn(new Date());
        doctorService.saveDoctor(d);
        //发短信
        try {
            SMSUtil.sendSMS(SMSUtil.TEMPLATE_ID, d.getMobile(), "通过");
        } catch (Exception e) {
            logger.info("SendSMS error:" + e.getMessage());
        }
        return Result.ok("审核通过完成。");
    }

    /**
     * 审核不通过
     */
    @RequestMapping("nopass/{id}")
    @ResponseBody
    public Result nopass(@PathVariable Long id) {
        Doctor d = doctorService.getDoctor(id);
        d.setStatus(Doctor.Status.Disabled);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        d.setVerifyBy(user.getName());
        d.setVerifyById(user.getId());
        d.setVerifyOn(new Date());
        doctorService.saveDoctor(d);
        //发短信
        try {
            SMSUtil.sendSMS(SMSUtil.TEMPLATE_ID, d.getMobile(), "不通过");
        } catch (Exception e) {
            logger.info("SendSMS error:" + e.getMessage());
        }
        return Result.ok("审核不通过完成。");
    }

    /**
     * 查看营业执照
     */
    @RequestMapping("viewPermit/{id}")
    @ResponseBody
    public Result viewPermit(@PathVariable Long id, HttpServletRequest request) {
        DataFile file = dataFileService.getDataFile(id, DataFile.Type.License);
        if (file != null) {
            byte[] content = file.getContent();
            String fileName = file.getFileName();
            ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
            String dir = sc.getRealPath("/temp");
            try {
                File f = new File(dir + "/" + fileName);
                if (f.exists()) {
                    f.delete();
                }
                FileUtils.writeByteArrayToFile(f, content);
            } catch (IOException e) {
                return Result.fail(e.getMessage());
            }
            return Result.ok(fileName);
        } else {
            return Result.fail("没有找到营业执照文件！");
        }
    }

    /**
     * 重置患者密码
     */
    @RequestMapping(value = "retPwd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result retPwd(@PathVariable Long id) {
        Doctor d = doctorService.getDoctor(id);
        String defaultPwd = Utils.encodePwd(StringUtils.right(d.getMobile(), 6), d.getSalt());
        d.setPassword(defaultPwd);
        doctorService.saveDoctor(d);
        return Result.ok();
    }

    /**
     * 医生编辑页
     */
    @RequestMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctor(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("provinceList", nationService.listNation(0));
        model.addAttribute("cityList", nationService.listNation(doctor.getProvinceNo()));
        model.addAttribute("areaList", nationService.listNation(doctor.getCityNo()));
        return "doctor/edit";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute Doctor doctor, Model model) {
        try {
            doctorService.saveDoctor(doctor);
            model.addAttribute("msg", "医生信息保存成功。");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("doctor", doctor);
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("provinceList", nationService.listNation(0));
        model.addAttribute("cityList", nationService.listNation(doctor.getProvinceNo()));
        model.addAttribute("areaList", nationService.listNation(doctor.getCityNo()));
        return "doctor/edit";
    }

    @ModelAttribute
    public void getDoctor(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("doctor", doctorService.getDoctor(id));
        }
    }
    /*判断文件的类型*/
    private boolean checkFileType(String fileName) {
        String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (Constants.fileType.contains(fileEnd)) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "uploadPermit", method = RequestMethod.POST)
    public String uploadPermit(HttpServletRequest request,
                               @RequestParam(required = true) Long doctorId,
                               @RequestParam(required = false) MultipartFile businessFile, Model model) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        String msg = "上传成功！";
        if (doctor == null) {
            msg = "该医生在数据库中不存在！";
            model.addAttribute("msg", msg);
        }
        if (businessFile != null && !businessFile.isEmpty()) {
            if (businessFile.getSize() > Constants.maxFileSize) {
                msg = "营业执照文件应不大于2MB！";
                model.addAttribute("msg", msg);
            }
            if (!checkFileType(businessFile.getOriginalFilename())) {
                msg = "营业执照文件请使用jpg格式的图片！";
                model.addAttribute("msg", msg);
            }
        }
        model.addAttribute("msg", msg);
        model.addAttribute("doctor", doctor);
        model.addAttribute("patientCount", emrService.getPatientCountByDoctor(doctorId));
        doctorService.saveDoctorPermit(request, doctor, businessFile);
        return "doctor/view";
    }

    @RequestMapping(value = "delSubDoctor")
    @ResponseBody
    public Result delSubDoctor(@RequestParam("doctorId") Long doctorId) {
        Boolean isSuccess = doctorService.delSubDoctor(doctorId);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

}
