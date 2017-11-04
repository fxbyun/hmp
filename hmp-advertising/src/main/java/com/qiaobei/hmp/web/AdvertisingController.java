package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.support.FileUtilsServer;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.SMSUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/11 0011
 * Time 13:31
 */
@Controller
public class AdvertisingController extends ConstantsController {

    @Resource
    private AdvertingService advertingService;
    @Resource
    private DataFileService dataFileService;
    @Resource
    private FileUtilsServer fileUtilsServer;
    @Resource
    private PatientService patientService;
    @Resource
    private CardService cardService;
    @Resource
    private RegistrationService registrationService;
    @Resource
    private NationService nationService;


    @Resource
    private DoctorService doctorService;

    @ModelAttribute("genderMap")
    private EnumMap<Gender, String> genderMap(HttpServletRequest request) {
        return GENDER_MAP;
    }


    //TODO  广告终端 2016-8-3 15:22:21
    @RequestMapping("/advertising/adv")
    public String adv(Model model, HttpServletRequest httpServletRequest) {

        List<Adverting> advertings = advertingService.findAllByDoctorAndSystem(SecuritySupport.getDoctor(), new User
                (1L));


        //获取系统中间部分广告图片
        List<DataFile> dataFileList = dataFileService.findSysCenterImg(new User(1L));
        List<DataFile> dataFileListSysCenterImg = Lists.newArrayList(dataFileList);


        //获取底部 系统和医生设置的 照片 和视频 处理视频URL 获取视频ID  并且 把 图片加入到 dataFileList
        List<Adverting> advertingsFooterEnd = Lists.newArrayList();
        advertingService.findAllByDoctorAndSystem(SecuritySupport.getDoctor(), new User(1L)).
                stream().filter(adverting ->
                        adverting.getPosition() == Adverting.Position.DoctorFooterImg ||
                                adverting.getPosition() == Adverting.Position.SystemFooterImg
                //暂时屏蔽视频
//                        ||
//                        adverting.getPosition() == Adverting.Position.DoctorFooterVideo ||
//                        adverting.getPosition() == Adverting.Position.SystemFooterVideo
        ).collect(Collectors.toList()).forEach(adverting -> {
            adverting.setUrl(
                    adverting.getType() == Adverting.Type.video && adverting.getUrl() != null ?
                            adverting.getUrl().split("/")[adverting.getUrl().split("/").length - 1] : null);
            if (adverting.getType() == Adverting.Type.Pic) {
                DataFile dataFile = dataFileService.getDataFile(adverting.getId(), DataFile.Type.Adverting);
                if (dataFile != null) {
                    adverting.setName(dataFile.getFileName());
                    dataFileList.add(dataFile);
                }

            }

            advertingsFooterEnd.add(adverting);
        });

        //判断文件是否已经从数据库输出到网站
        dataFileList.forEach(dataFile -> outPutFileToDisk(dataFile, SecuritySupport.getDoctor(), httpServletRequest));

        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("advertings", advertings);
        model.addAttribute("sysCenterImg", dataFileListSysCenterImg);
        model.addAttribute("advertingsFooter", advertingsFooterEnd);
        model.addAttribute("picAndTextCount", advertingService.findAdvertingPicAndTextCount(SecuritySupport.getDoctor(), new User(1L)));

        //String ip = SecuritySupport.getSubject().getSession().getHost();
        return "/advertising/adv";
    }

    @RequestMapping("/advertising/slideU")
    public String slideU() {

        return "/advertising/slideU";
    }

    /**
     * 挂号页面
     */
    @RequestMapping("/advertising/registration")
    public String registration(@RequestParam(value = "doctorId", required = false) Long doctorId,
                               @RequestParam(value = "msg", required = false, defaultValue = "") String msg, Model
                                       model) {
        if ("undefined".equals(msg)) {
            msg = "";
        }
        Doctor doctor = doctorService.getDoctor(doctorId);
        model.addAttribute("msg", msg);
        model.addAttribute("doctor", doctor);
        return "/advertising/registration";
    }

    // TODO 绑定健康卡 2016-8-31 16:32:18
    @RequestMapping("/advertising/patientInfo")
    public String patientInfo(@RequestParam("cardNo") String cardNo,
                              @RequestParam("doctorId") Long doctorId,
                              Model model) {
        Card card = cardService.getCardByNo(cardNo);
        Patient patient = patientService.getPatientByUid(cardNo);
        if (card != null) {
            if (patient == null)
                patient = patientService.savePatient4Card(card);
            if (patient != null) {
                Doctor d = SecuritySupport.getDoctor();
                if (patient.getProvinceNo() == null && d != null) {
                    patient.setProvinceNo(d.getProvinceNo());
                    patient.setProvince(d.getProvince());
                    patient.setCityNo(d.getCityNo());
                    patient.setCity(d.getCity());
                    patient.setAreaNo(d.getAreaNo());
                    patient.setArea(d.getArea());
                }
                model.addAttribute("patient", patient);
                model.addAttribute("doctorId", doctorId);
                model.addAttribute("provinceList", nationService.listNation(0));
                model.addAttribute("cityList", nationService.listNation(patient.getProvinceNo()));
                model.addAttribute("areaList", nationService.listNation(patient.getCityNo()));
            }
        }
        return "/advertising/patientInfo";
    }


    /**
     * 进行挂号操作
     */
    @RequestMapping(value = "/advertising/registration/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam(required = false) String udid,
                       @RequestParam(required = false) String mobile,
                       @RequestParam(value = "doctorId") Long doctorId) {
        Patient patientByUid = patientService.getPatientByUdid(udid);
        if (patientByUid == null || patientByUid.getStatus() == StatusEntity.Status.Unactivated) {
            if (mobile == null || "".equals(mobile)) {
                if (udid != null) {
                    Card card = cardService.getCardByUdid(udid);
                    if (card == null) {
                        return Result.fail("无效卡号!");
                    } else {
                        return Result.fail("该患者未绑卡,请先进行绑卡操作!").setData(card);
                    }
                }

            }
        }

        Patient patient = null;
        Registration reg = new Registration();
        if (StringUtils.isNotEmpty(udid)) {
            patient = patientService.getPatientByUdid(udid);
            if (patient == null) {
                Card card = cardService.getCardByUdid(udid);
                if (card == null) {
                    return Result.fail("无效卡！");
                } else {
                    reg.setPatientName(card.getCardNo());
                    reg.setPatientUid(card.getCardNo());
                }
            } else {
                reg.setPatientName(patient.getName());
                reg.setPatientUid(patient.getUid());
            }
        }
        if (StringUtils.isNotEmpty(mobile)) {
            //List<Patient> ps = patientService.listPatientByMobile(mobile);
            patient = patientService.getPatientByUid(mobile);
            if (patient != null) {
                reg.setPatientName(patient.getName());
                reg.setPatientUid(patient.getUid());
            } else {
                Card card = cardService.getCardByNo(mobile);
                if (card == null) {
                    return Result.fail("无效卡号!");
                } else {
                    return Result.fail("该患者未绑卡,请先进行绑卡操作!").setData(card);
                }
            }
        }
        //Doctor doctor = SecuritySupport.getDoctor();
        Doctor doctor = doctorService.getDoctor(doctorId);
        //判断是否已经挂号
        //getRegistrationByPatientUidAndNameAndDocto
        List<Registration> registrationSa = registrationService.getByPatientAndDoctor(
                patient, doctor);
        if (registrationSa != null && registrationSa.size() > 0) {
            return Result.ok("您已经挂号了！");
        }

        reg.setDoctorId(doctor.getId());
        reg.setDoctorName(doctor.getName());
        reg.setCreateOn(new Date());
        reg.setStatus(Registration.Status.Normal);
        reg.setQueueStatus(Registration.QueueStatus.NotVisit);
        reg.setSequence(0);


        //判断该医生是否是主治医生
        Doctor bossDoctor;
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            bossDoctor = doctorService.getDoctor(doctor.getPrimaryDoctorId());
        } else {
            bossDoctor = doctor;
        }

        List<Doctor> subDocList = doctorService.findSubDoctor(bossDoctor);
        List<Long> doctorIdList = Lists.newArrayList();
        subDocList.add(bossDoctor);
        subDocList.forEach(doctor1 -> doctorIdList.add(doctor1.getId()));

        //排队的顺序号码
        String queueNo = registrationService.getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum.LOCALE, doctorIdList);
        //reg.setNoNumber(registrationService.getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum.LOCALE));
        reg.setNoNumber(queueNo);
        //reg.setNoNumber(registrationService.getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum.LOCALE));
        registrationService.save(reg);
        return Result.ok("挂号成功！");
    }


    /**
     * 绑卡操作保存
     */
    @RequestMapping(value = "/advertising/patient/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("patient") Patient patient, @RequestParam(value = "doctorId", required = false) Long doctorId,
                       RedirectAttributes redirectAttributes) {
        if (patient.getStatus() == StatusEntity.Status.Unactivated && !"否".equals(SecuritySupport.getDoctor()
                .getAutoSendActivateMsg())) {
            try {
                SMSUtil.sendSMS(SMSUtil.ACTIVE_TEMPLATE, patient.getMobile(), SecuritySupport.getDoctor()
                        .getOutpatientService() + "," + patient.getUid());
            } catch (Exception e) {
                logger.error("激活短信发送失败：" + e.getMessage());
            }
        }
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("msg", "信息成功保存");
        redirectAttributes.addFlashAttribute("udid", patient.getUdid());
        redirectAttributes.addFlashAttribute("doctorId", doctorId);
        return "redirect:/advertising/patientInfo?cardNo=" + patient.getUid() + "&doctorId=" + doctorId;
    }


    /**
     * 轮询 获取排队 信息
     */
    @RequestMapping(value = "/advertising/getAdvings", method = RequestMethod.POST)
    @ResponseBody
    public Result getAdvingStatus() {

        List<Registration> registrationList = registrationService.getRegistrationList(SecuritySupport.getDoctor().getId(), Registration.Status.Normal, Registration.QueueStatus.NotVisit);
        Registration oneReg = registrationService.getCallNameByDoctor(SecuritySupport.getDoctor());
        if (StringUtils.isNotEmpty(oneReg.getPatientName())) {
            registrationService.setRegistrationCallStatusNot(SecuritySupport.getDoctor(), oneReg.getPatientName());
            if (!registrationList.stream().map(Registration::getPatientName).collect(Collectors.toList()).contains(oneReg.getPatientName())) {
                oneReg.setPatientName("");
                oneReg.setDoctorDeptName("");
            }
        }
        return new Result().setSuccess(true).setData(registrationList.size()).setMsg(oneReg.getPatientName()).setDeptName(oneReg.getDoctorDeptName());
    }

    /**
     * 轮询 获取排队 信息列表
     */
    @RequestMapping(value = "/advertising/getAdvingListInfo", method = RequestMethod.POST)
    @ResponseBody
    public List<Registration> getAdvingListInfo() {
        return registrationService.getRegistrationList(SecuritySupport.getDoctor().getId(), Registration.Status.Normal, Registration.QueueStatus.NotVisit);
        //return registrationService.getRegistrationList(SecuritySupport.getDoctor().getId(), Registration.Status.Normal);
    }


    @RequestMapping(value = "/advertising/getPicCount")
    @ResponseBody
    public Result getPicCount() {
        Long count = advertingService.findAdvertingPicAndTextCount(SecuritySupport.getDoctor(), new User(1L));
        return new Result().setSuccess(true).setData(count);
    }

    /**
     * 判断文件是否已经从数据库输出到网站 fileDir/医生ID 目录 如果没有 即 马上 写到 fileDir/医生ID  下面去
     */
    private void outPutFileToDisk(DataFile dataFile, Doctor doctor, HttpServletRequest httpServletRequest) {
        String fileAubUrl = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(File.separatorChar +
                "fileDir" + File.separatorChar + doctor.getId());
        if (dataFile != null) {
            File file = new File(fileAubUrl + File.separatorChar + dataFile.getFileName());
            if (!file.exists()) {
                fileUtilsServer.saveFile(dataFile.getFileName(), dataFile.getContent(), fileAubUrl);
            }
        }

    }


    /**
     * 天气级联
     */
    @RequestMapping(value = "/anon/getNations/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getNations(@PathVariable("parentId") Integer parentId) {
        return Result.ok(nationService.listNation(parentId));
    }

    @RequestMapping(value = "/advertising/changeDoctor")
    public String changeDoctor(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        Doctor doctor = SecuritySupport.getDoctor();
        System.out.println(doctor.getName());

        Page<Doctor> doctorPage = doctorService.findDocAndSubDoctorPage(doctor.getId(), new PageRequest(page, 3, Sort.Direction.ASC, "id"));
        HashedMap docHeadUrl = new HashedMap();

        doctorPage.getContent().forEach(doctor1 -> {
            //医生头像
            String headUlr = doctorService.findDoctorHeaderUrl(doctor.getId());
            docHeadUrl.put(doctor.getId(), headUlr);
        });
        System.out.println(doctorPage.getTotalPages());
        model.addAttribute("page", doctorPage);
        model.addAttribute("doctorList", doctorPage.getContent());
        model.addAttribute("docHeadUrl", docHeadUrl);


        return "/advertising/changeDoctor";
    }

}
