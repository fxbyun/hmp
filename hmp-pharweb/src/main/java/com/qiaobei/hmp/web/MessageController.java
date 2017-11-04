package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by teemoer@cntv.cn on 2016/6/30 0030.
 */
@Controller
public class MessageController {
    @Resource
    MsgMeassageService msgMeassageService;
    @Resource
    private MsgSendHistoryService msgSendHistoryService;
    @Resource
    private MsgSendHistoryDetailService msgSendHistoryDetailServicel;
    @Resource
    private MsgRechargeDetailService msgRechargeDetailService;
    @Resource
    private PatientService patientService;
    @Resource
    private EmrService emrService;
    @Resource
    private MsgMoneyService msgMoneyService;
    @Resource
    private TagService tagService;

    //TODO 短信发送及设置 2016-6-16 15:49:48
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(Model model) {
        model.addAttribute("msgInfo", msgSendHistoryService.getDoctorMsgInfo(SecuritySupport.getDoctor()));
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        return "message";
    }


    /**
     * @return 返回判断 用户是否有通知短信
     */
    @RequestMapping(value = "/getInformMessage")
    @ResponseBody
    public Result getInformMessage() {
        MsgMeassage msgMeassage = msgMeassageService.getMeassageByDoctor(SecuritySupport.getDoctor());
        if (msgMeassage == null) {
            return Result.fail();
        } else {
            return Result.ok(msgMeassage.getInfo());
        }


    }

    /**
     * @return 返回判断 用户是否有通知短信
     */
    @RequestMapping(value = "/delInformMessage")
    @ResponseBody
    public Result delInformMessage() {
        try {
            msgMeassageService.delMeassageByDoctor(SecuritySupport.getDoctor());
            return Result.ok();
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }

    }

    //TODO 短信充值和消费明细 2016-6-16 17:07:04
    @RequestMapping(value = "/msPage/msDetail", method = RequestMethod.GET)
    public String msDetail(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {

        return "/msPage/msDetail";
    }

    @RequestMapping(value = "/msPage/msDetailRechargeList", method = RequestMethod.GET)
    public String msDetailRechargeList(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {

        Pageable pageable = new PageRequest(page, 5, Sort.Direction.DESC, "id");
        Page<MsgRechargeDetail> msgRechargeDetailPage = msgRechargeDetailService.getPageByDoctor(pageable,
                SecuritySupport.getDoctor(), PayType.PAY);
        model.addAttribute("msgRechargeDetailPage", msgRechargeDetailPage);
        return "/msPage/msDetailRechargeList";
    }

    @RequestMapping(value = "/msPage/msDatailSendHistoryList", method = RequestMethod.GET)
    public String msDatailSendHistoryList(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                                  .PATTERN_DATE) Date startDate,
                                          @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                                  .PATTERN_DATE) Date endDate,
                                          Model model) {

        System.out.println(1);
        Pageable pageable = new PageRequest(page, 5, Sort.Direction.DESC, "id");
        Page<MsgSendHistory> msgSendHistoryPage;
        if (startDate != null) {
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            msgSendHistoryPage = msgSendHistoryService.findByDoctorAndCreateDateBetween(pageable, SecuritySupport
                    .getDoctor(), startDate, endDate);
        } else {
            Date date = new Date();
            date.setDate(1);
            date.setHours(0);
            date.setMinutes(0);
            Date endDate1 = DateUtils.getLastDayOfMonth(date);
            model.addAttribute("startDate", date);
            model.addAttribute("endDate", endDate1);

            msgSendHistoryPage = msgSendHistoryService.findByDoctorAndCreateDateBetween(pageable, SecuritySupport
                    .getDoctor(), date, endDate1);
            //msgSendHistoryPage = msgSendHistoryService.getListByDoctor(pageable, SecuritySupport.getDoctor());
        }


        model.addAttribute("msgSendHistoryPage", msgSendHistoryPage);
        return "/msPage/msDatailSendHistoryList";
    }

    //TODO 充值方式 2016-6-17 15:48:32
    @RequestMapping(value = "/msPage/msRecharge", method = RequestMethod.GET)

    public String msRecharge() {
        return "/msPage/msRecharge";
    }


    //TODO 回访短信模板 2016-6-28 15:57:04
    @RequestMapping(value = "/msPage/msTemplate", method = RequestMethod.GET)

    public String msTemplate() {
        return "/msPage/msTemplate";
    }

    //TODO 添加收件人 2016-6-21 12:15:56
    @RequestMapping(value = "/msPage/msAddPeople", method = {RequestMethod.GET, RequestMethod.POST})
    public String msAddPeople(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                              @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                              @RequestParam(value = "patient", required = false) String patientName,
                              @RequestParam(value = "addType", required = false) String addType,
                              @RequestParam(value = "ageTopsString", required = false) String ageTopsString,
                              @RequestParam(value = "ageFlooerString", required = false) String ageFlooerString,
                              @RequestParam(value = "diagonsisName", required = false) String diagonsisName,
                              @RequestParam(value = "genderSex", required = false) Gender genderSex,
                              @RequestParam(value = "checkItem", required = false) List<Long> checkItemList,
                              @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date
                                      startDate,
                              @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date
                                      endDate,
                              @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date
                                      ageTops,
                              @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date
                                      ageFlooer,
                              Model model,
                              HttpSession session) {
        Map<Long, Patient> map = (Map<Long, Patient>) session.getAttribute("chackPatient");
        if (null == map) {
            map = Maps.newConcurrentMap();
        }
        if (null != checkItemList) {
            for (Long s : checkItemList) {
                map.put(s, new Patient(s));
            }
        }
        DateFilter dateFilter = null;
        DateFilter dateAge = null;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        }
        if (null != ageTops && null != ageFlooer) {
            dateAge = new DateFilter(ageFlooer, ageTops);
        }
        if (",".equals(patientName)) {
            patientName = null;
        }

        Page<Emr> patientListEntities = emrService.findCountByPatient(new PageRequest(page, size),
                SecuritySupport.getDoctor(),
                patientName,
                dateFilter,
                dateAge,
                genderSex,
                diagonsisName);

        if (addType != null && "add".equals(addType) && patientListEntities.getTotalElements() > 0) {
            //map.clear();

            Page<Emr> patientListAllEntities = emrService.findCountByPatient(new PageRequest(0, (int)
                            patientListEntities
                                    .getTotalElements()),
                    SecuritySupport.getDoctor(),
                    patientName,
                    dateFilter,
                    dateAge,
                    genderSex,
                    diagonsisName);

            for (Emr emr : patientListAllEntities.getContent()) {
                map.put(emr.getPatient().getId(), emr.getPatient());
            }
        }
        if (addType != null && "del".equals(addType)) {

            map.clear();
        }


        session.setAttribute("diagnosisTagList", tagService.getAllDiagnosisTag());
        session.setAttribute("chackPatient", map);
        model.addAttribute("chackPatient2", map);
        model.addAttribute("patientPage", patientListEntities);
        model.addAttribute("patientName", patientName);
        model.addAttribute("diagonsisName", diagonsisName);
        model.addAttribute("startDate", startDate);
        model.addAttribute("addType", addType);
        model.addAttribute("endDate", endDate);
        model.addAttribute("ageTopsString", ageTopsString);
        model.addAttribute("ageFlooerString", ageFlooerString);
        model.addAttribute("genderSex", genderSex);

        return "/msPage/msAddPeople";
    }


    @RequestMapping("/msPage/editThisPatient")
    @ResponseBody
    public Result removeCheaPatient(@RequestParam("ids") Long[] ids,
                                    @RequestParam("type") String type,
                                    HttpSession session) {
        Map<Long, Patient> map = (Map<Long, Patient>) session.getAttribute("chackPatient");
        if (map == null)
            map = Maps.newHashMap();
        if ("add".equals(type)) {
            for (Long id : ids) {
                map.put(id, new Patient(id));
            }
        } else {
            for (Long id : ids) {
                map.remove(id);
            }
        }
        session.setAttribute("chackPatient", map);
        return Result.ok();
    }

    @RequestMapping("/msPage/GetPatientInfo")
    @ResponseBody
    public List<Patient> GetPatientInfo(HttpSession session, @RequestParam(value = "type", required = false) String
            type) {
        if ("del".equals(type)) {
            session.setAttribute("chackPatient", null);
            return Lists.newArrayList();
        }
        Map<Long, Patient> map = (Map<Long, Patient>) session.getAttribute("chackPatient");
        session.setAttribute("chackPatient", null);
        List patienList = Lists.newArrayList();
        if (null != map && map.size() > 0) {
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Patient patient = patientService.getPatientById((Long) iterator.next());
                patienList.add(new Patient(patient.getId(), patient.getName(), patient.getMobile()));
            }
            //
            return patienList;
        } else {
            return patienList;
        }

    }


    //从选择发信人页面记录发送的选择条件 比如 几岁到几岁
    @RequestMapping("/msPage/addSendPatienInfo")
    @ResponseBody
    public Result addSendPatienInfo(@RequestParam("patientIdList") List<Long> patientIdList,
                                    @RequestParam("mobList") List<String> mobList,
                                    @RequestParam(value = "ageTop", required = false) String ageTop,
                                    @RequestParam(value = "ageFor", required = false) String ageFor,
                                    @RequestParam(value = "diagonsisName", required = false) String diagonsisName,
                                    @RequestParam(value = "sex", required = false) String sex,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE)
                                            Date txtStartDate,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE)
                                            Date txtEndDate,
                                    @RequestParam(value = "msgContext", required = true) String msgContext,
                                    @RequestParam(value = "title", required = true) String title,
                                    HttpSession session) {
        if (patientIdList != null && patientIdList.size() > 0) {
            //System.out.println(msgContext.length());
            int sendOneSize = (int) Math.ceil(Double.valueOf(msgContext.length()) /
                    (msgContext.length() <= 70 ? 70 : 67));
            session.setAttribute("patientIdListMsg", patientIdList);
            session.setAttribute("mobListMsg", mobList);
            session.setAttribute("titleMsg", title);
            session.setAttribute("msgContextMsg", msgContext.replace("退订回复TD", "").replace("[易佳诊]", ""));
            session.setAttribute("msgContextMsgOnePatientSendSize", sendOneSize);
            session.setAttribute("ageTopMsg", (ageTop == null || "".equals(ageTop) ? "全部" : (ageTop + "岁")));
            session.setAttribute("ageForMsg", (ageFor == null || "".equals(ageFor) ? "全部" : (ageFor + "岁")));
            session.setAttribute("diagonsisNameMsg", (diagonsisName == null || "".equals(diagonsisName) ? "全部" :
                    diagonsisName));
            if ("Female".equals(sex)) {
                sex = "女";
            } else if ("Male".equals(sex)) {
                sex = "男";
            } else {

                sex = "全部";
            }
            session.setAttribute("sexMsg", sex);
            session.setAttribute("txtStartDateMsg", (txtStartDate == null ? "全部" : txtStartDate));
            session.setAttribute("txtEndDateMsg", (txtEndDate == null ? "全部" : txtEndDate));
            session.setAttribute("useMoneryMsg", Constants.MSG_PRICE * 100 * patientIdList.size()
                    * sendOneSize / 100);
            session.setAttribute("msgPrice", Constants.MSG_PRICE);
            return Result.ok();
        } else {
            return Result.fail("请增加发件人!");
        }


    }

    @RequestMapping("/msPage/clearSessionMsgInfo")
    @ResponseBody
    public Map clearSessionMsgInfo(HttpSession session) {
        session.setAttribute("patientIdListMsg", null);
        session.setAttribute("mobListMsg", null);
        session.setAttribute("msgContextMsg", null);
        session.setAttribute("msgContextMsgOnePatientSendSize", null);
        session.setAttribute("ageTopMsg", null);
        session.setAttribute("ageForMsg", null);
        session.setAttribute("diagonsisNameMsg", null);
        session.setAttribute("sexMsg", null);
        session.setAttribute("txtStartDateMsg", null);
        session.setAttribute("txtEndDateMsg", null);
        session.setAttribute("useMoneryMsg", null);
        session.setAttribute("msgPrice", null);
        Map<?, ?> map = msgSendHistoryService.getDoctorMsgInfo(SecuritySupport.getDoctor());
        return map;
    }


    //TODO 确认发送 2016-6-21 16:54:40
    @RequestMapping(value = "/msPage/msSend", method = RequestMethod.GET)
    public String msSend(Model model, HttpSession session) {
        Double useMon = 0D;
        if (session.getAttribute("useMoneryMsg") != null) {
            useMon = (double) session.getAttribute("useMoneryMsg");
        }
        MsgMoney msgMoney = msgMoneyService.getByDoctor(SecuritySupport.getDoctor());
        if (null != msgMoney) {
            if (!(msgMoney.getDeposit() >= useMon)) {
                model.addAttribute("needRecharge", true);
            } else {
                model.addAttribute("needRecharge", false);
            }
            return "/msPage/msSend";
        }
        model.addAttribute("needRecharge", true);
        return "/msPage/msSend";
    }

    @RequestMapping(value = "/msPage/sendMsg", method = RequestMethod.POST)
    @ResponseBody
    public Result sendMsg(HttpSession session) {
        Double useMon = (double) session.getAttribute("useMoneryMsg");
        MsgMoney msgMoney = msgMoneyService.getByDoctor(SecuritySupport.getDoctor());
        if (null != msgMoney) {
            if (!(msgMoney.getDeposit() >= useMon)) {
                return Result.fail("您余额不足,请先充值!");
            }
        } else {
            return Result.fail("您余额不足,请先充值!");
        }

        List<Long> patientIdList = (List<Long>) session.getAttribute("patientIdListMsg");
        List<String> mobListMsg = (List<String>) session.getAttribute("mobListMsg");
        String msgContextMsg = (String) session.getAttribute("msgContextMsg");
        String titleMsg = (String) session.getAttribute("titleMsg");
        //每个人需要发送多少条
        int sendOneSize = (int) session.getAttribute("msgContextMsgOnePatientSendSize");

        MsgSendHistory msgSendHistory = new MsgSendHistory();
        msgSendHistory.setContext(msgContextMsg);
        msgSendHistory.setCreateDate(new Date());
        msgSendHistory.setMsgType(MsgSendHistory.SendType.SELF);
        msgSendHistory.setDoctor(SecuritySupport.getDoctor());
        msgSendHistory.setTitle(titleMsg);
        msgSendHistory.setSendSize(patientIdList.size() * sendOneSize);
        msgSendHistory.setMsgSendHistoryDetail(Lists.<MsgSendHistoryDetail>newArrayList());
        msgSendHistoryService.updateOrSave(msgSendHistory);

        int successSize = patientIdList.size();
        for (int i = 0; i < patientIdList.size(); i++) {
            MsgSendHistoryDetail msgSendHistoryDetail = new MsgSendHistoryDetail();
            msgSendHistoryDetail.setPatient(new Patient(patientIdList.get(i)));
            msgSendHistoryDetail.setMsgSendHistory(msgSendHistory);
            try {
                //TODO 暂时屏蔽
                SMSUtil.sendSMS(SMSUtil.CUSTOMIZE_ALONE_TEMLATE, mobListMsg.get(i), msgContextMsg);
                msgSendHistoryDetail.setSendStatus(MsgSendHistoryDetail.SendStatus.Success);
            } catch (Exception e) {
                --successSize;
                msgSendHistoryDetail.setSendStatus(MsgSendHistoryDetail.SendStatus.Failure);
                e.printStackTrace();
                System.err.println("用户ID为:" + patientIdList.get(i) + "---手机号为:" + mobListMsg.get(i) + " 发送失败!");
            } finally {
                msgSendHistoryDetailServicel.updateOrSave(msgSendHistoryDetail);
                msgSendHistory.getMsgSendHistoryDetail().add(msgSendHistoryDetail);
            }
        }

        double useMonrys = Constants.MSG_PRICE * 100 * successSize
                * sendOneSize / 100;
        //根据成功的发送数量进行扣钱操作
        msgSendHistory.setUseMoney(useMonrys);
        msgSendHistory.setSuccessSize(successSize);
        msgMoney.setDeposit(msgMoney.getDeposit() - useMonrys);
        msgSendHistoryService.updateOrSave(msgSendHistory);
        msgMoneyService.updateOrSave(msgMoney);

        return Result.ok();
    }


    @RequestMapping("/msPage/getMsgSendList")
    public String getMsgSendListPage(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        model.addAttribute("msgSendHistoryListPage", msgSendHistoryService.getListByDoctorAndSendType(pageable,
                SecuritySupport
                        .getDoctor(), MsgSendHistory.SendType.SELF));
        return "/msPage/sendList";
    }

    @RequestMapping("/msPage/loadSmgSendHosDetil")
    public String loadSmgSendHosDetil(@RequestParam(value = "id", required = true) Long id,
                                      @RequestParam(value = "name", required = false) String name
            , Model model) {
        Pageable pageable = new PageRequest(0, 999, Sort.Direction.DESC, "id");
        model.addAttribute("msgSendHistoryTableListPage", msgSendHistoryDetailServicel.getListByMSHAndName(pageable,
                name, id));
        return "/msPage/sendTableSun";
    }

    @RequestMapping("/msPage/addAllRetToSeletOfDel")
    @ResponseBody
    public Result addAllRetToSeletOfDel(HttpSession httpSession, @RequestParam("type") String type) {

        if ("add".equals(type)) {
            httpSession.setAttribute("chackPatient", httpSession.getAttribute("allChackPatient"));
        } else {
            httpSession.setAttribute("chackPatient", Maps.newHashMap());
        }

        return Result.ok();
    }
}
