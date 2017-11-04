package com.qiaobei.hmp.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.ImageWallService;
import com.qiaobei.hmp.modules.support.AddressUtils;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.modules.support.IPUtils;
import com.qiaobei.hmp.modules.support.StrUtils;
import com.qiaobei.hmp.modules.wxpay.WxNotifyImpl;
import com.qiaobei.hmp.modules.wxpay.payService.PayService;
import com.qiaobei.hmp.modules.wxpay.utils.WxConfig;
import com.qiaobei.hmp.modules.wxpay.utils.WxSign;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.Utils;
import com.qiaobei.hmp.support.WeixinUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.session.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/8/12.
 */
@Controller
public class OutpatientDepart extends BaseController {

    @Resource
    private DoctorService doctorService;

    @Resource
    private DataFileService dataFileService;

    @Resource
    private EmrService emrService;

    @Resource
    private EvaluateService evaluateService;

    @Resource
    private PatientService patientService;

    @Resource
    private AppointListService appointListService;

    @Resource
    private AppointPatientService appointPatientService;

    @Resource
    private AppointRewardService appointRewardService;

    @Resource
    private MobileUserService mobileUserService;

    @Resource
    private ImageWallService imageWallService;

    @Resource
    private RegistrationService registrationService;

    //TODO 门诊首页
    @RequestMapping(value = "/outpatient/outpatientDepart")
    public String outpatientDepart(@RequestParam(value = "doctorName", required = false) String doctorName,
                                   @RequestParam(value = "outName", required = false) String outName,
                                   @RequestParam(value = "province", required = false) String province,
                                   @RequestParam(value = "city", required = false) String city,
                                   @RequestParam(value = "area", required = false) String area,
                                   @RequestParam(value = "unknownCity", required = false) boolean unknownCity,
                                   Model model, HttpServletRequest request) {

        Page<Doctor> page = null;

        //这个方法还需加个参数，即医生的地址area
        page = doctorService.findPage(Utils.buildPageRequest(1, 10000), outName, doctorName, null, province, city, area);
        /*如果搜索地区时，有些医生没有填写省份、和城市*/
        if (unknownCity) {
            List<Doctor> unknownDocList = doctorService.findUnknowCity();
            page = new PageImpl<Doctor>(unknownDocList);
        }
        //获取所有医生的头像docHeadUrl<id,HeadUrl>
        Map<Long, String> docHeadUrl = new HashedMap();
        Map<Long, String> zhenSuoHeadMap = new HashMap<>();
        List<ImageWall> imageWallList = new ArrayList<>();
        List<Doctor> doctorList = page.getContent();
        for (int i = 0; i < doctorList.size(); i++) {
            //医生头像
            String headUlr = doctorService.findDoctorHeaderUrl(doctorList.get(i).getId());
            docHeadUrl.put(doctorList.get(i).getId(), headUlr);

            //诊所头像
            String zhenHeadUrl = null;
            ImageWall imageWall = imageWallService.findFirstImage(doctorList.get(i), ImageWall.ImageLevel.Cover);
            if (imageWall != null) {
                zhenHeadUrl = imageWall.getFileName();
                imageWallList.add(imageWall);
            }
            zhenSuoHeadMap.put(doctorList.get(i).getId(), zhenHeadUrl);
        }

        //查找最近看的诊所，根据根据手机号查找(//*)
        String mobile = SecuritySupport.getMobileUser().getMobile();
        //测试电话号（记得换掉）
        Doctor lastDoctor = doctorService.findLastGoByMobile(mobile);
        if (lastDoctor != null) {
            //判断该医生是否已经在Map中
            if (!docHeadUrl.containsKey(lastDoctor.getId())) {
                String lastDocHead = doctorService.findDoctorHeaderUrl(lastDoctor.getId());
                String zhensuoUrl = null;
                ImageWall lastWall = imageWallService.findFirstImage(lastDoctor, ImageWall.ImageLevel.Cover);
                if (lastWall != null) {
                    zhensuoUrl = lastWall.getFileName();
                    imageWallList.add(lastWall);
                }
                zhenSuoHeadMap.put(lastDoctor.getId(), zhensuoUrl);
                docHeadUrl.put(lastDoctor.getId(), lastDocHead);
            }
            model.addAttribute("lastDoctor", lastDoctor);
        }
        //将形象墙的图片写出来
        imageWallService.getImageUrlList(imageWallList);

        //获取访问者所在的ip地址
        Session session = SecuritySupport.getSubject().getSession();
        String cityName = (String) session.getAttribute("cityName");
        if (cityName == null || "".equals(cityName)) {

            try {
                //通过QQ获取地址
                String ipString = IPUtils.getIpString(request);
                String localIP = ipString;
                if (ipString.contains(",")) {
                    localIP = ipString.substring(0, ipString.indexOf(","));
                }

                cityName = IPUtils.getAddressByIP(localIP);
                if (cityName == null) {
                    //通过淘宝获取地址
                    cityName = AddressUtils.GetAddressByIp(localIP).get("city");
                }

            } catch (Exception e) {

                System.out.println(e.getMessage() + "获取地址失败！");
            }
        }
        System.out.println("-----当前城市-----" + cityName);
        session.setAttribute("cityName", cityName);
        model.addAttribute("cityName", cityName);

        String flag = null;
        if (doctorName != null) {
            flag = "doctorName";
        }
        model.addAttribute("flag", flag);
        //获取该医生的所有
        model.addAttribute("docHeadUrl", docHeadUrl);
        model.addAttribute("zhenSuoHeadMap", zhenSuoHeadMap);
        model.addAttribute("doctorPage", page);
        return "outpatient/outpatientDepart";
    }


    /**
     * 诊所首页
     *
     * @param doctorName
     * @param outName
     * @param province
     * @param city
     * @param area
     * @param unknownCity
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/outpatient/clinicIndex")
    public String clinicIndex(@RequestParam(value = "doctorName", required = false) String doctorName,
                              @RequestParam(value = "outName", required = false) String outName,
                              @RequestParam(value = "province", required = false) String province,
                              @RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "area", required = false) String area,
                              @RequestParam(value = "unknownCity", required = false) boolean unknownCity,
                              HttpServletRequest request, Model model) {

        //得到开启预约功能的所有主治医生
        Page<Doctor> page = doctorService.findAllClinicBossPage(Utils.buildPageRequest(1, 10000), outName, doctorName, null, province, city, area);

        /*如果搜索地区时，有些医生没有填写省份、和城市,搜索出来的医生结果*/
        if (unknownCity) {
            List<Doctor> unknownDocList = doctorService.findUnknowCity();
            //只留主治医生的
            page = new PageImpl<Doctor>(
                    unknownDocList.stream().filter(
                            doctor -> doctor.getDoctorType() != Doctor.Doctor_Type.Sub_Doctor
                    ).collect(Collectors.toList())
            );
        }

        //诊所的头像
        Map<Long, String> zhenSuoHeadMap = new HashMap<>();
        //参数,用于数据库中写出形象墙
        List<ImageWall> imageWallList = new ArrayList<>();
        List<Doctor> doctorList = page.getContent();
        //得到每个诊所的头像
        doctorList.forEach(doctor -> {
            zhenSuoHeadMap.put(doctor.getId(), null);
            Optional.ofNullable(
                    //找到该医生的诊所头像
                    imageWallService.findFirstImage(doctor, ImageWall.ImageLevel.Cover)
            ).ifPresent(imageWall -> {
                imageWallList.add(imageWall);
                zhenSuoHeadMap.put(doctor.getId(), imageWall.getFileName());
            });
        });

        //查找最近看的诊所，根据根据手机号查找(//*)
        if (SecuritySupport.getMobileUser() == null) {
            return "login";
        }
        String mobile = SecuritySupport.getMobileUser().getMobile();
        if (null != mobile) {
            SecuritySupport.getSubject().getSession().setAttribute("userMobile", mobile);
        } else {
            mobile = SecuritySupport.getSubject().getSession().getAttribute("userMobile").toString();
        }
        Optional.ofNullable(doctorService.findLastGoByMobile(mobile)).ifPresent(doctor -> {
            //是否主治医生
            if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
                doctor = doctorService.getDoctor(doctor.getPrimaryDoctorId());
            }
            //前端展示最近拜访的医生
            model.addAttribute("lastDoctor", doctor);
            //将最近拜访的诊所的形象墙取出
            if (!zhenSuoHeadMap.containsKey(doctor.getId())) {
                zhenSuoHeadMap.put(doctor.getId(),
                        Optional.ofNullable(imageWallService.findFirstImage(doctor, ImageWall.ImageLevel.Cover)).map(
                                imageWall -> {
                                    imageWallList.add(imageWall);
                                    return imageWall.getFileName();
                                }
                        ).orElse(null)
                );
            }

        });
        //将形象墙的图片写出来
        imageWallService.getImageUrlList(imageWallList);
        //获取定位的位置
        /*Optional.ofNullable(
                SecuritySupport.getSubject().getSession().getAttribute("city")).ifPresent(
                place -> {
                    model.addAttribute("cityName", place.toString());
                });*/

        //微信获取地址
        /*Map<String, String> jssdkMap = LocationService.JSSDKMap(SecuritySupport.getAttribute("jsapi_ticket").toString());
        //保存jsapi_ticket，jsapi_ticket有效期是2个小时
        if(null!=jssdkMap.get("jsapi_ticket")){
            SecuritySupport.setAttribute("jsapi_ticket",jssdkMap.get("jsapi_ticket"),Long.valueOf(7200*1000));
        }*/
        //获取该医生的所有
        model.addAttribute("zhenSuoHeadMap", zhenSuoHeadMap);
        model.addAttribute("doctorPage", page);
        //model.addAttribute("jssdkMap", jssdkMap);
        return "outpatient/clinicIndex";
    }

    /**
     * 主页医生列表
     *
     * @param doctorName
     * @param outName
     * @param province
     * @param city
     * @param area
     * @param unknownCity
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "outpatient/doctorIndex")
    public String doctorIndex(@RequestParam(value = "doctorName", required = false) String doctorName,
                              @RequestParam(value = "outName", required = false) String outName,
                              @RequestParam(value = "province", required = false) String province,
                              @RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "area", required = false) String area,
                              @RequestParam(value = "unknownCity", required = false) boolean unknownCity,
                              HttpServletRequest request, Model model) {

        //查找所有的医生
        Page<Doctor> page = doctorService.findPage(
                Utils.buildPageRequest(1, 10000), outName, doctorName, null, province, city, area);

        /*如果搜索地区时，有些医生没有填写省份、和城市,搜索出来的医生结果*/
        if (unknownCity) {
            List<Doctor> unknownDocList = doctorService.findUnknowCity();
            page = new PageImpl<Doctor>(unknownDocList);
        }

        //医生的头像
        Map<Long, String> docHeadUrl = new HashMap<>();
        List<Doctor> doctorList = page.getContent();
        //得到每个医生的头像
        doctorList.forEach(doctor ->
                docHeadUrl.put(doctor.getId(),
                        Optional.ofNullable(
                                //找到该医生头像
                                doctorService.findDoctorHeaderUrl(doctor.getId())
                        ).orElse(null)
                )
        );

        //查找最近看的诊所，根据根据手机号查找(//*)
        String mobile = SecuritySupport.getMobileUser().getMobile();
        if (null != mobile) {
            SecuritySupport.getSubject().getSession().setAttribute("userMobile", mobile);
        } else {
            mobile = SecuritySupport.getSubject().getSession().getAttribute("userMobile").toString();
        }
        //最近拜访医生是否存在于头像Map中
        Optional.ofNullable(doctorService.findLastGoByMobile(mobile)).ifPresent(doctor -> {
            model.addAttribute("lastDoctor", doctor);
            if (!docHeadUrl.containsKey(doctor.getId())) {
                docHeadUrl.put(doctor.getId(),
                        Optional.ofNullable(doctorService.findDoctorHeaderUrl(doctor.getId())).orElse(null)
                );

            }
        });

        //获取定位的位置
        Optional.ofNullable(
                SecuritySupport.getSubject().getSession().getAttribute("city")).ifPresent(
                place -> {
                    model.addAttribute("cityName", place.toString());
                });

        //获取该医生的所有
        model.addAttribute("docHeadUrl", docHeadUrl);
        model.addAttribute("doctorPage", page);
        return "outpatient/doctorIndex";
    }


    @RequestMapping(value = "outpatient/saveLocation")
    @ResponseBody
    public Result saveLocation(@RequestParam String province,
                               @RequestParam String city) {
        SecuritySupport.getSubject().getSession().setAttribute("city", city);
        System.out.println("手机上获取到的定位:" + province + city);
        //设置
        Optional.ofNullable(SecuritySupport.getMobileUser()).ifPresent(
                mobileUser -> {
                    mobileUser.setLocationPlace(province + "-" + city);
                    mobileUserService.save(mobileUser);
                    System.out.println("是否保存了用户定位信息:" + mobileUser.getLocationPlace());
                });
        return Result.ok();
    }


    /**
     * 门诊-定位页
     *
     * @return
     */
    @RequestMapping(value = "/outpatient/outLocation")
    public String outLocation(Model model) {

        List<ProvinceDoctor> provinceDocList = doctorService.findProvinceDocCount();
        //广东省各个市区的医生人数
        List<ProvinceDoctor> guangDong_docList = doctorService.findCityDocCount("广东省");

        model.addAttribute("provinceDocList", provinceDocList);
        model.addAttribute("guangDong_docList", guangDong_docList);
        Session session = SecuritySupport.getSubject().getSession();
        String cityName = (String) session.getAttribute("cityName");
        System.out.println("==当前城市==" + cityName);
        model.addAttribute("cityName", cityName);
        return "outpatient/outLocation";
    }

    //TODO 门诊-详情页 2016-8-13 15:35:41
    @RequestMapping(value = "/outpatient/outLocationDetail")
    public String outLocationDetail(@RequestParam("doctorId") Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctor(doctorId);

        //平均分
        Map<Doctor, Double> averageMap = Maps.newHashMap();
        //平均人数
        Map<Doctor, Double> averPerNumsMap = Maps.newHashMap();
        //头像
        Map<Doctor, String> headUrlMap = Maps.newHashMap();
        //所有医生的信息
        List<Doctor> allClinicDoctorList = Lists.newArrayList();
        //查找该诊所的所有医生
        doctorService.findClinicAllDoctor(doctor).forEach(doc -> {
            allClinicDoctorList.add(doc);
            //医生头像
            headUrlMap.put(doc, doctorService.findDoctorHeaderUrl(doc.getId()));
            //平均分和平均人数
            Long emrCount = emrService.getEmrCount(doc.getId());

            Calendar start = Calendar.getInstance();
            start.setTime(DateUtils.getDate());

            Calendar now = Calendar.getInstance();
            if (null == doc.getVerifyOn()) {
                now.setTime(doc.getCreateOn());
            } else {
                now.setTime(doc.getVerifyOn());
            }

            int monNums = DateUtils.dateDiff('d', start, now) / 30;
            Double averPerNums = doctorService.getAverage(Integer.parseInt(emrCount + ""), (long) monNums);
            Double average = doctorService.getAverage(doc.getIntegration(), emrCount);

            averPerNumsMap.put(doc, averPerNums);
            averageMap.put(doc, average);

        });
        //头像门诊(只有主治医生才有门诊头像)
        Doctor clinicDoctor;
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            //获得主治医生
            clinicDoctor = doctorService.getDoctor(doctor.getPrimaryDoctorId());
        } else {
            clinicDoctor = doctor;
        }
        ImageWall imageWall = imageWallService.findFirstImage(clinicDoctor, ImageWall.ImageLevel.Cover);
        model.addAttribute("imageWall", imageWall);

        //医生排序(主治普通医生排前面,开启预约挂号排前面)
        allClinicDoctorList.sort((doc1, doc2) -> {
            if (doc1.getAppointStatus() == Doctor.DoctorAppointStatus.Open && (doc2.getAppointStatus() == Doctor.DoctorAppointStatus.Close || doc2.getAppointStatus() == null)) {
                return -1;
            } else {
                return 0;
            }
        });

        allClinicDoctorList.sort((doc1, doc2) -> {
            if (doc1.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss || doc1.getDoctorType() == Doctor.Doctor_Type.Common_Doctor || doc1.getDoctorType() == null) {
                return -1;
            } else {
                return 0;
            }
        });


        model.addAttribute("averageMap", averageMap);
        model.addAttribute("averPerNumsMap", averPerNumsMap);
        model.addAttribute("allClinicDoctorList", allClinicDoctorList);
        model.addAttribute("headUrlMap", headUrlMap);
        model.addAttribute("clinicDoctor", clinicDoctor);
        return "outpatient/outLocationDetail";
    }

    //TODO 门诊-预约页面 2016-8-15 10:15:34
    @RequestMapping(value = "/outpatient/outAppointment")
    public String outAppointment(@RequestParam("doctorId") Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        String headUrl = doctorService.findDoctorHeaderUrl(doctorId);
        //该医生的患者数量
        Long emrCount = emrService.getEmrCount(doctor.getId());
//        doctor.getVerifyBy();

        Calendar start = Calendar.getInstance();
        start.setTime(DateUtils.getDate());

        Calendar now = Calendar.getInstance();
        if (null == doctor.getVerifyOn()) {
            if (doctor.getCreateOn() != null) {
                now.setTime(doctor.getCreateOn());
            } else {
                now.setTime(new Date());
            }

        } else {
            now.setTime(doctor.getVerifyOn());
        }

        int monNums = DateUtils.dateDiff('d', start, now) / 30;
        Double averPerNums = doctorService.getAverage(Integer.parseInt(emrCount + ""), (long) monNums);
        Double average = doctorService.getAverage(doctor.getIntegration(), emrCount);

        //评价的条数
        List<Evaluate> evaluateList = evaluateService.findEvaluateByELE(doctor.getId());
        //获得当天的日期和星期
        Date nowDate = new Date();
        String weekOfDate = DateUtils.getWeekOfDate(new Date());

        //获得后三天的日期和星期
        Map<Date, String> dateAndWeekForThree = new LinkedHashMap();
        for (int i = 0; i < 3; i++) {
            //得到明天的日期
            Date tempDate = DateUtils.getTomorrowDate(new Date(), i);
            //String dateString = DateUtils.date_sdf.format(tempDate);
            //得到明天的星期几
            String tomorrowStr = DateUtils.getTomorrowWeek(new Date(), i);

            dateAndWeekForThree.put(tempDate, tomorrowStr);
        }

        //得到后三天可预约的列表
        int count = 1;
        for (Date day : dateAndWeekForThree.keySet()) {
            List<AppointList> appointLists = appointListService.findByDate(day, doctor);

            if (count == 1) {
                if (appointLists != null) {
                    //手机端如果预约的时间是已经过去的时间,那么就不显示出来
                    Iterator<AppointList> iter = appointLists.iterator();
                    while (iter.hasNext()) {
                        AppointList appointList = iter.next();
                        boolean flag = DateUtils.compareTime(appointList.getEndTime(), new Date());
                        if (flag) {
                            iter.remove();
                        }
                    }

                }
                model.addAttribute("appointLists_today", appointLists);
            }

            if (count == 2) {
                model.addAttribute("appointLists_tomorrow", appointLists);
            }

            if (count == 3) {
                model.addAttribute("appointLists_afterTomorrow", appointLists);
            }

            count++;
        }

        //获取诊所头像


        //该医生的平均分
        model.addAttribute("average", average);
        model.addAttribute("averPerNums", averPerNums);
        model.addAttribute("doctor", doctor);
        model.addAttribute("headUrl", headUrl);
        model.addAttribute("evaluateList", evaluateList);
        model.addAttribute("nowDate", nowDate);
        model.addAttribute("weekOfDate", weekOfDate);
        model.addAttribute("dateAndWeekForThree", dateAndWeekForThree);
        return "outpatient/outAppointment";
    }

    //TODO 医生就诊时间段页面 2016-8-17 10:30:35
    @RequestMapping(value = "/outpatient/outTreatmentTime")
    public String outTreatmentTime() {

        return "outpatient/outTreatmentTime";
    }

    @RequestMapping(value = "/outpatient/outReservationInfo")
    public String outReservationInfo(@RequestParam(value = "doctorId") Long doctorId,
                                     @RequestParam(value = "appListId") Long appListId,
                                     @RequestParam(value = "patientId", required = false) Long patientId,
                                     Model model) {

        //得到当前登录的用户id和手机号
        MobileUser currentUser = SecuritySupport.getMobileUser();
        //开发测试用的账号
        //String mobile = "18207554327";

        List<Patient> patientList = patientService.listPatientByMobile(currentUser.getMobile());
        model.addAttribute("patientList", patientList);

        Doctor doctor = doctorService.getDoctor(doctorId);
        AppointList appointList = appointListService.findById(appListId);
        if (patientId != null) {
            Patient patient = patientService.getPatientById(patientId);

            model.addAttribute("patientEdit", patient);
        }

        model.addAttribute("doctor", doctor);
        model.addAttribute("appointList", appointList);


        return "outpatient/outReservationInfo";
    }

    /**
     * 用户预约操作
     *
     * @param patientId
     * @param appointListId
     * @param model
     * @return
     */
    @RequestMapping(value = "/outpatient/outReward")
    @ResponseBody
    public Result outReward(@RequestParam(value = "patientId") Long patientId, @RequestParam(value = "appointListId") Long appointListId, Model model) {
        String errorInfo = null;
        //进行预约之前，应该判断用户是否是用微信登录
        /*if (SecuritySupport.getSubject().getSession().getAttribute("openid") == null) {
            errorInfo = "请用微信进行预约挂号！";
            return Result.fail(errorInfo);
        }*/


        Patient patient = patientService.getPatientById(patientId);
        AppointList appointList = appointListService.findById(appointListId);

        //判断是否两个小时前为同一个病人预约过
        List<AppointReward> hasRewardList = appointRewardService.findTwoHourBefore(appointList.getDoctor().getId(), patientId, appointList.getDate());
        if (CollectionUtils.isNotEmpty(hasRewardList)) {
            for (AppointReward reward : hasRewardList) {
                AppointList list = reward.getAppointPatient().getAppointList();
                Date compareDate = DateUtils.getDayAfterHous(list.getStartTime(), 2, DateUtils.DATA_UTLIST_TYPE_DO.ADD);
                long millis = DateUtils.compareTimeGetMillis(appointList.getStartTime(), compareDate);
                if (millis > 0 && millis < 14400000) {
                    errorInfo = "该时间段两个小时前后，该病人已预约过！";
                    return Result.fail(errorInfo);
                }
            }


        }


        //这里最好加个同步
        AppointPatient appointPatient = appointPatientService.findMinIdByListId(appointListId);

        if (appointPatient == null) {
            errorInfo = "改时间段的预约号已经被预约完了，请重新选择！";
            //model.addAttribute("errorInfo",errorInfo);
            return Result.fail(errorInfo);
        }
        appointPatient.setPatient(patient);
        appointPatient.setAppointList(appointList);
        appointPatient.setAppointStatus(AppointPatient.Status.Distributed);
        appointPatient.setDate(new Date());
        appointPatientService.saveAppointPatient(appointPatient);

        //时间段人数加1
        appointList.setPeopleNum(appointList.getPeopleNum() + 1);
        appointListService.save(appointList);

        //预约成功的插入排队表中
        Registration registration = new Registration();
        registration.setDoctorId(appointList.getDoctor().getId());
        registration.setAppointPatientId(appointPatient.getId());
        registration.setCallStatus(Registration.CallStatus.DO_NOT_CALL);
        registration.setCompleteOn(appointList.getEndTime());
        registration.setCreateOn(appointList.getStartTime());
        registration.setDoctorName(appointList.getDoctor().getName());

        registration.setPatientId(patient.getId());
        registration.setPatientName(patient.getName());
        registration.setPatientUid(patient.getUid());
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(patient.getUid())) {
            registration.setHaveBindingCard(Registration.RegistrationTypeEnum.YES);
        } else {
            registration.setHaveBindingCard(Registration.RegistrationTypeEnum.NO);
        }
        registration.setRegistrationType(Registration.RegistrationTypeEnum.WECHAT);
        registration.setSequence(0);

        //排队顺序
        registration.setNoNumber(registrationService.getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum.WECHAT));

        registration.setDoctorDeptName(appointList.getDoctor().getDeptName());
        registrationService.save(registration);


        return Result.ok(appointPatient.getId());
    }


    /**
     * 跳转到打赏页面时，预先生成打赏单
     *
     * @param appPatientId
     * @param model
     * @return
     */
    @RequestMapping(value = "/wxtest/toReward/test", method = RequestMethod.POST)
    public String toReward(@RequestParam(value = "appPatientId") Long appPatientId, Model model) {

        AppointPatient appointPatient = appointPatientService.findById(appPatientId);
        //生成一个预约订单
        AppointReward appointReward = new AppointReward();

        MobileUser currentUser = SecuritySupport.getMobileUser();

        if (currentUser == null) {
            System.out.println("====当前用户为空=====");
        }

        appointReward.setMobileUser(SecuritySupport.getMobileUser());
        appointReward.setMobile(SecuritySupport.getMobileUser().getMobile());
        appointReward.setStatus(AppointReward.RewardStatus.NOT_PAID);
        appointReward.setAppointPatient(appointPatient);
        appointReward.setDoctor(appointPatient.getAppointList().getDoctor());
        appointReward.setOrderId(StrUtils.getRandomString(30));
        appointReward.setPayDate(new Date());
        appointReward.setAppointDate(appointPatient.getAppointList().getStartTime());

        Patient patient = appointPatient.getPatient();
        if (patient != null) {
            appointReward.setPatientId(patient.getId());
            appointReward.setPatientName(patient.getName());
        }


        appointRewardService.save(appointReward);
        model.addAttribute("doctor", appointReward.getDoctor());
        model.addAttribute("appointReward", appointReward);


        return "outpatient/outReward";
    }

    /**
     * 构建支付用的数据
     *
     * @param money
     * @param rewardId
     * @param model
     * @return
     */
    @RequestMapping(value = "/outpatient/getPayDate")
    @ResponseBody
    public Result getPayDate(@RequestParam(value = "money") double money,
                             @RequestParam(value = "rewardId") Long rewardId,
                             Model model) {
        String openId = (String) SecuritySupport.getSubject().getSession().getAttribute("openid");
        MobileUser user = SecuritySupport.getMobileUser();
        if (user == null) {
            System.err.println("==用户为空==");
        }
        if (openId != null) {
            if (!openId.equals(user.getOpenId())) {
                System.out.println("{OutpatientDepart:toReward}OpenId不相等！");
                return Result.fail("支付后台出错！程序员正在抢救，抱歉！");
            }
        }
        //获得打赏号
        AppointReward appointReward = appointRewardService.getById(rewardId);


        Map map = null;
        if (!StringUtils.isEmpty(openId)) {
            if (money > 0) {
                DecimalFormat df = new DecimalFormat(".00");
                money = Double.parseDouble(df.format(money));
                System.out.println(money);
            }
            map = PayService.wxWebPay((long) (money * 100), appointReward.getOrderId(), openId);
        }
        System.err.println("--------------老子是单号：" + appointReward.getOrderId());
        if (map == null) {
            System.out.println("{OutpatientDepart:getPayDate}map为空，支付数据map生成失败！");
            return Result.fail("支付数据出错，请尝试重新进入页面！");
        }


        Map<String, Object> WXPayDate = new HashedMap();
        WXPayDate.put("appId", WxConfig.APP_ID);
        WXPayDate.put("timeStamp", Long.valueOf((new Date().getTime() / 1000)).toString());
        WXPayDate.put("nonceStr", map.get("nonce_str"));
        WXPayDate.put("package", "prepay_id=" + map.get("prepay_id"));
        WXPayDate.put("signType", "MD5");

        WXPayDate.put("paySign", WxSign.getSign(WXPayDate, WxConfig.KEY));

        return Result.ok(WXPayDate);
    }


    /**
     * 测试用的方法
     *
     * @param accessToken
     * @param openid
     * @param scope
     * @return
     */
    @RequestMapping(value = "/outpatient/toPayTest")
    public String toPayTest(@RequestParam(value = "access_token", required = false) String accessToken,
                            @RequestParam(value = "openid", required = false) String openid,
                            @RequestParam(value = "scope", required = false) String scope) {
        //Map<String, Object> payMap = PayService.wxWebPay(new Long(1), StrUtils.getRandomString(10));
        System.out.println(accessToken);
        System.out.println(openid);
        System.out.println(scope);

        return "redirect:/";
    }


    /**
     * 返回一个该城市医生数量
     *
     * @param city
     * @param model
     * @return
     */
    @RequestMapping(value = "/outpatient/outLocation/city")
    @ResponseBody
    public Result toLocalCity(@RequestParam(value = "city") String city, Model model) {
        String errorInfo = "";
        if (city == null || city.equals("")) {
            model.addAttribute("errorInfo", errorInfo);
            return Result.fail("城市为空");
        }
        /*if(city.equals("未知地区")){
            return
        }*/
        //广东省各个市区的医生人数
        List<ProvinceDoctor> cityDocList = doctorService.findCityDocCount(city);
        String cityListStr = JSON.toJSONString(cityDocList);
        Result result = new Result();
        result.setData(cityListStr);
        result.setSuccess(true);
        return result;

    }


//    @RequestMapping(value = "/wx/callWebBack")
//    public String payCallBack(HttpServletRequest request){
//
//        Map<String , String> requestMap = request.getParameterMap();
//
//        return "redirect:/outpatient/outpatientDepart";
//    }

    /**
     * 微信一进来就获得的getOpenId
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "/wxtest/getOpenId")
    public String bind(@RequestParam("code") String code, Model model) {
        String url = WeixinUtil.GET_OPEN_ID_URL.replaceAll("APPID", WeixinUtil.PAPPID).
                replaceAll("SECRET", WeixinUtil.PAPPSECRET).replaceAll("CODE", code);
        JSONObject json = WeixinUtil.httpRequest(url, "GET", null);
        String openId = json.getString("openid");
        System.err.println("=====================" + openId);
        SecuritySupport.getSubject().getSession().setAttribute("openid", openId);

        return "redirect:/logon";
    }

    /**
     * 微信支付结果回调地址
     */
    @RequestMapping(value = "/wxtest/wx/callWebBack")
    @ResponseBody
    public Object weixinNotify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println("微信回调开始！！！！");
        BufferedOutputStream out;
        String resXml;
        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        WxNotifyImpl wxNotify;
        String key;


        AppointReward appointReward = null;

        //try {
        wxNotify = new WxNotifyImpl(request);
        Map<String, Object> resmap = wxNotify.getMap();//拿到所有返回结果的map
        if (null == resmap || resmap.size() <= 0 || wxNotify.getOut_trade_no() == null) {
            return "fail";
        }
        //error 这里需要根据订单号去找
        appointReward = appointRewardService.getByOrderId((String) resmap.get("out_trade_no"));
        if (appointReward == null) {
            System.out.println("没有该订单号");
            return "fail";
        }

        if (appointReward.getStatus() == AppointReward.RewardStatus.SUCCESS) {
            System.out.println("该订单号已经支付！");
            return "fail";
        }

        //是否需要判断两个订单号是否一致
        if (!wxNotify.getOut_trade_no().equals(appointReward.getOrderId())) {
            System.out.println("订单号不一致");
            return "fail";
        }

        key = WxConfig.KEY;
        boolean signFlag = wxNotify.verfySign(resmap, key);
        if (signFlag) {
            if (wxNotify.verfyPaySeccuss()) {
                System.out.println("支付成功了呢！");
                appointReward.setStatus(AppointReward.RewardStatus.SUCCESS);
                //appointReward.setOrderId();
                appointReward.setPayDate(new Date());
                appointReward.setMoney(Double.parseDouble(wxNotify.getTotal_fee()) / 100);
                appointRewardService.save(appointReward);

                //通知微信 回调成功
                try {
                    out = new BufferedOutputStream(response.getOutputStream());
                    out.write(resXml.getBytes());
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "fail";
                }


            } else {
                System.out.println("接口通知支付失败，进行重新查询订单");
            }
        } else {
            System.out.println("签名不正确");
        }

        return resXml;
    }


    /**
     * 健康建档
     *
     * @param patient
     * @param doctorId
     * @param appointListId
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/outpatient/savePatient", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("patient") Patient patient,
                       @RequestParam(value = "doctorId") Long doctorId,
                       @RequestParam(value = "appointListId") Long appointListId,
                       RedirectAttributes redirectAttributes) {

        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("msg", "患者信息成功保存");
        String url = null;
        if (doctorId != null) {
            url = "/outpatient/outReservationInfo?doctorId=" + doctorId + "&appListId=" + appointListId + "&patientId=" + patient.getId();
        }
        return "redirect:" + url;
    }

    //TODO 新增页面-门诊详情 2016-9-29 14:40:46
    @RequestMapping(value = "/outpatient/outClinicDetail")
    public String outClinicDetail(@RequestParam(value = "doctorId") Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        //这句代码的执行速度好慢啊！
        List<ImageWall> imageWallList = imageWallService.findByDoctorId(doctor);

        //诊所头像
        String clinicHeadUrl = null;
        List<String> imageUrlList = null;
        if (CollectionUtils.isNotEmpty(imageWallList)) {
            imageUrlList = imageWallService.getImageUrlList(imageWallList);
            clinicHeadUrl = imageWallList.get(0).getFileName();

        }

        model.addAttribute("doctor", doctor);
        model.addAttribute("imageUrlList", imageUrlList);
        model.addAttribute("clinicHeadUrl", clinicHeadUrl);
        return "outpatient/outClinicDetail";
    }

}
