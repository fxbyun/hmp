package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.RetailMedicineService;
import com.qiaobei.hmp.modules.service.RetailService;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.modules.support.DecimalCalculate;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.AppointRewardService;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.service.PharmacistService;
import com.qiaobei.hmp.support.DateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/9/19
 * Time 13:35
 */
@Controller
public class FinancialStatisticsController extends ConstantsController {
    @Resource
    DoctorService doctorService;
    @Resource
    EmrService emrService;
    @Resource
    AppointRewardService appointRewardService;
    @Resource
    PharmacistService pharmacistService;
    @Resource
    RetailService retailService;
    @Resource
    RetailMedicineService retailMedicineService;

    //TODO  财务统计 2016-11-23 11:21:47
    @RequestMapping(value = "/financial")
    public String dailyOoperation(Model model,
                                  @RequestParam(value = "startDate", required = false) Date startDate,
                                  @RequestParam(value = "endDate", required = false) Date endDate
    ) {
        //医生信息
        Doctor doctor = SecuritySupport.getDoctor();
        List<Doctor> doctorList = Lists.newArrayList(doctor);
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            doctorList.addAll(doctorService.findSubDoctor(doctor));
        }
        model.addAttribute("doctorList", doctorList);
        model.addAttribute("doctor", doctor);
        startDate = DateUtils.getDayStartTime(startDate);
        endDate = DateUtils.getDayEndMaxTime(endDate);
        //病历信息
        List<Emr> emrList = emrService.findAllByDoctorListAndTime(doctorList, startDate, endDate);
        model.addAttribute("emrList", emrList);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        //总收入
        Double countCost = emrList.stream().mapToDouble(Emr::getRealCost).sum();
        //保留两位,其余的小数去掉
        model.addAttribute("countCost", DecimalCalculate.roundDown(countCost, 2));
        //挂起金额
        Double HANG_UP = emrList.stream().filter(
                emr -> emr.getStatus() == StatusEntity.Status.HANG_UP
        ).mapToDouble(emr -> emr.getCost() - emr.getRealCost()).sum();
        model.addAttribute("HANG_UP", DecimalCalculate.roundDown(HANG_UP, 2));
        //处方中药价格
        final Double[] chinaPriceCount = {0D};
        //处方西药价格
        final Double[] westernPriceCount = {0D};
        //处方中医理疗
        final Double[] chineseTherapyPriceCount = {0D};
        final Double[] emrExtCostPriceCount = {0D};
        final Double[] jianChajianYanPriceCount = {0D};

        emrList.forEach(
                emr -> {
                    //如果是收费才去计算费用
                    if (emr.getStatus() != null && emr.getStatus() != StatusEntity.Status.HANG_UP && emr.getStatus() != Emr.Status.Normal) {
                        emr.getEmrMedicineList().forEach(
                                emrMedicine -> {

                                    if (emrMedicine.getStatus() != null &&
                                            emrMedicine.getStatus() != StatusEntity.Status.Normal &&
                                            emrMedicine.getStatus() != StatusEntity.Status.Have_Dispensing_Back &&
                                            emrMedicine.getStatus() != StatusEntity.Status.HANG_UP) {
                                        if (emrMedicine.getQty() == null)
                                            emrMedicine.setQty(1D);
                                        if (emrMedicine.getCopies() == null)
                                            emrMedicine.setCopies(1D);
                                        if (emrMedicine.getUnitPrice() == null)
                                            emrMedicine.setUnitPrice(0D);
                                        if (emrMedicine.getMedicineType() == Medicine.Type.Chinese) {
                                            chinaPriceCount[0] += (
                                                    emrMedicine.getQty() *
                                                            emrMedicine.getCopies() *
                                                            emrMedicine.getUnitPrice() *
                                                            emr.getChineseQty()
                                            );
                                        } else if (emrMedicine.getMedicineType() == Medicine.Type.Western) {
                                            westernPriceCount[0] += (
                                                    emrMedicine.getQty() *
                                                            emrMedicine.getCopies() *
                                                            emrMedicine.getUnitPrice() / emrMedicine.getRate()
                                            );
                                        } else if (emrMedicine.getMedicineType() == Medicine.Type.ChineseTherapy) {
                                            chineseTherapyPriceCount[0] += (
                                                    emrMedicine.getQty() *
                                                            emrMedicine.getCopies() * emrMedicine.getUnitPrice()
                                            );
                                        }
                                    }
                                }
                        );
                    }
                    //如果状态是收费才统计
                    emr.getEmrExtCostList().forEach(emrExtCost -> {
                        if (emrExtCost.getStatus() != StatusEntity.Status.Normal &&
                                emrExtCost.getStatus() != StatusEntity.Status.Have_Dispensing_Back &&
                                emrExtCost.getStatus() != StatusEntity.Status.HANG_UP &&
                                emrExtCost.getStatus() != null
                                ) {
                            emrExtCostPriceCount[0] += emrExtCost.getPrice();
                        }
                    });
                    //如果状态收费才统计
                    emr.getEmrJClassAdviceDicts().forEach(emrJClassAdviceDict -> {
                        if (emrJClassAdviceDict.getStatus() != StatusEntity.Status.Normal &&
                                emrJClassAdviceDict.getStatus() != StatusEntity.Status.Have_Dispensing_Back &&
                                emrJClassAdviceDict.getStatus() != StatusEntity.Status.HANG_UP &&
                                emrJClassAdviceDict.getStatus() != null
                                ) {
                            //有些情况价格为空
                            if (null == emrJClassAdviceDict.getPrice()) {
                                emrJClassAdviceDict.setPrice(0D);
                            }
                            jianChajianYanPriceCount[0] += emrJClassAdviceDict.getPrice();
                        }
                    });
                }
        );

        BigDecimal westernPriceCountBig = new BigDecimal(westernPriceCount[0]);
        BigDecimal chinaPriceCountBig = new BigDecimal(chinaPriceCount[0]);
        model.addAttribute("chinaPriceCount", chinaPriceCountBig.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("westernPriceCount", westernPriceCountBig.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("chineseTherapyPriceCount", chineseTherapyPriceCount[0]);
        model.addAttribute("jianChaYanPriceCount", jianChajianYanPriceCount[0]);
        model.addAttribute("emrExtCostPriceCount", emrExtCostPriceCount[0]);

        //查找该医生的患者
        //List<Emr> docHasPatientsList = emrService.findAllByDoctorListAndTime(doctorList, DateUtils.str2Date("1990-01-01",DateUtils.date_sdf), new Date());
        //当天新增用户
        final Date final_start_time = startDate;
        Map<Patient, List<Emr>> newPatientEmrMap = emrList.stream().filter(emr ->
                emr.getPatient().getCreateOn().getTime()
                        >= final_start_time.getTime()
        ).collect(Collectors.groupingBy(Emr::getPatient));
        model.addAttribute("newPatientEmrMap", newPatientEmrMap);
        //老患者人数
        Map<Patient, List<Emr>> oldPatientEmrMap = emrList.stream().filter(emr ->
                emr.getPatient().getCreateOn().getTime()
                        < final_start_time.getTime()
        ).collect(Collectors.groupingBy(Emr::getPatient));
        model.addAttribute("oldPatientEmrMap", oldPatientEmrMap);

        //按照doctor 为 emr 分组
        Map<Doctor, List<Emr>> emrDoctorMap = emrList.stream().collect(Collectors.groupingBy(Emr::getDoctor));
        model.addAttribute("emrDoctorMap", emrDoctorMap);

        //每个doctor 的处方笺 总额
        Map<Doctor, Double> doctorDoubleMap = Maps.newConcurrentMap();
        emrDoctorMap.forEach((doctor1, emrListMap) -> doctorDoubleMap.put(doctor1, DecimalCalculate.roundDown(emrListMap.stream().mapToDouble(Emr::getRealCost).sum(), 2)));
        model.addAttribute("doctorDoubleMap", doctorDoubleMap);
        //预约人数
        List<AppointReward> appointRewardList = appointRewardService.findByDoctorAndTimeAndStatus(doctorList, startDate, endDate);
        model.addAttribute("appointRewardList", appointRewardList);

        //收银员按照名字分组的emrList
        Map<String, List<Emr>> shouYinNameListMap = emrList.stream().collect(Collectors.groupingBy(Emr::getCashierName));
        Map<String, Double> shouYinPriceMap = Maps.newConcurrentMap();
        shouYinNameListMap.forEach((name, emrListByName) -> {
            final Double[] tmpPrice = {0D};
            emrListByName.forEach(emr -> {
                if (emr.getStatus() != StatusEntity.Status.Normal && emr.getStatus() != StatusEntity.Status.HANG_UP && !"无收银".equals(emr.getCashierName()))
                    tmpPrice[0] += emr.getRealCost();
                if ("无收银".equals(emr.getCashierName())) {
                    tmpPrice[0] += emr.getCost();
                }
            });
            shouYinPriceMap.put(name, new BigDecimal(tmpPrice[0]).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
        });
        model.addAttribute("shouYinPriceMap", shouYinPriceMap);

        //零售药品
        Map<String, Double> retailMap = Maps.newHashMap();
        List<Retail> retailList = retailService.findByRetailStatusToday(doctorService.getPrimaryDoctor(doctor), Retail.Retail_Status.Save, (new Date()));
        //零售总金额
        Double retAllPrice = retailService.getRetailListPriceSum(retailList);
        model.addAttribute("retAllPrice", retAllPrice);

        //零售中药的总金额
        Double retChPrice = retailService.getRetailListChineseMedPrice(retailList);
        model.addAttribute("retChPrice", retChPrice);
        //零售西药的总金额
        Double retWePrice = retailService.getRetailListWesternMedPrice(retailList);
        model.addAttribute("retWePrice", retWePrice);

        return "financialStatistics";
    }

    //TODO  日患者明细统计 2016-11-23 11:21:47
    @RequestMapping(value = "/statisics/patientDetail")
    public String patientDetail(Model model,
                                @RequestParam(value = "startDate", required = false) Date startDate,
                                @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                @RequestParam(value = "endDate", required = false) Date endDate,
                                @RequestParam(value = "patientName", required = false) String patientName,
                                @RequestParam(value = "shouYinId", required = false) Long shouYinId,
                                @RequestParam(value = "doctorId", required = false) Long doctorId) {
        //医生信息
        Doctor doctor = SecuritySupport.getDoctor();
        List<Doctor> doctorList = Lists.newArrayList(doctor);
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            doctorList.addAll(doctorService.findSubDoctor(doctor));
        }
        //用于医生搜索
        List<Doctor> searchDoctorList = Lists.newArrayList();
        if (null == doctorId) {
            searchDoctorList = doctorList;
        } else {
            searchDoctorList.clear();
            searchDoctorList.add(doctorService.getDoctor(doctorId));
        }

        model.addAttribute("doctorList", doctorList);
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("shouYinId", shouYinId);
        model.addAttribute("patientName", patientName);
        if (startDate == null) {
            startDate = DateUtils.getDayStartTime();
        }
        if (endDate == null) {
            endDate = DateUtils.getDayEndMaxTime();
        } else {
            endDate = DateUtils.getDayEndMaxTime(endDate);
        }
        //病历信息
        List<Emr> emrList = emrService.findAllByDoctorListAndTimeAndShouYinId(searchDoctorList, startDate, endDate, patientName, shouYinId);
        model.addAttribute("emrList", emrList);
        //找到该诊所的所有护士
        Map<Long, String> shouYinMap = pharmacistService.findAllCashier(doctor).stream().collect(
                Collectors.toMap((IdEntity::getId), (Pharmacist::getName))
        );
//        shouYinMap.remove("无收银");
        shouYinMap.forEach((aLong, s) -> {
            if ("无收银".equals(s)) {
                shouYinMap.remove(aLong);
            }
        });
        model.addAttribute("shouYinMap", shouYinMap);

        DateFilter dateFilter = new DateFilter();
        dateFilter.setStartDate(startDate);
        dateFilter.setEndDate(endDate);
        Page<Emr> emrPage = emrService.pageEmr(new PageRequest(page, size), searchDoctorList, patientName, dateFilter, shouYinId);
        model.addAttribute("emrPage", emrPage);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        //应收总额
        Double countCost = emrList.stream().mapToDouble(Emr::getCost).sum();
        model.addAttribute("countCost", DecimalCalculate.roundDown(countCost, 2));
        //实收总额
        Double countRealCost = emrList.stream().mapToDouble(Emr::getRealCost).sum();
        System.out.println(countRealCost);
        model.addAttribute("countRealCost", DecimalCalculate.roundDown(countRealCost, 2));

        //挂起金额
        Double HANG_UP = emrList.stream().filter(
                emr -> emr.getStatus() == StatusEntity.Status.HANG_UP
        ).mapToDouble(emr -> emr.getCost() - emr.getRealCost()).sum();
        model.addAttribute("HANG_UP", DecimalCalculate.roundDown(HANG_UP, 2));

        //无收银
        Double NO_Baby = emrList.stream().filter(
                emr -> emr.getStatus() == StatusEntity.Status.Normal
        ).mapToDouble(Emr::getCost).sum();
        model.addAttribute("NO_Baby", DecimalCalculate.roundDown(NO_Baby, 2));

        //收银人
        model.addAttribute("cashierMap", pharmacistService.getCashierMapByEmrList(emrList));

        return "patientDetail";
    }
}
