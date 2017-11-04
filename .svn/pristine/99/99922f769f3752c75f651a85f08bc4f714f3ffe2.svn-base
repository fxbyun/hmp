package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.MedicineContainerService;
import com.qiaobei.hmp.modules.service.RetailMedicineService;
import com.qiaobei.hmp.modules.service.RetailService;
import com.qiaobei.hmp.modules.support.DecimalCalculate;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.PharSecuritySupport;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class RetailController extends ConstantsController {
    @Resource
    private RetailService retailService;

    @Resource
    private RetailMedicineService retailMedicineService;

    @Resource
    private PatientService patientService;

    @Resource
    private MedicineService medicineService;

    @Resource
    private CompanyService companyService;

    @Resource
    private MedicinePrivateService medPrivateService;

    @Resource
    private DoctorService doctorService;

    @Resource
    private MedicinePrivateService medicinePrivateService;

    @Resource
    private MedicineContainerService medicineContainerService;


    @ModelAttribute("medicineUnits")
    private EnumMap<Medicine.Unit, String> medicineUnits(HttpServletRequest request) {
        return MEDICINE_UNITS;
    }

    @ModelAttribute("genderMap")
    private EnumMap<Gender, String> genderMap(HttpServletRequest request) {
        return GENDER_MAP;
    }

    @ModelAttribute("loginUser")
    private Pharmacist loginUser(HttpServletRequest request) {
        return PharSecuritySupport.getPharmacist();
    }

    @ModelAttribute("todayRetailMoney")
    private Double getTodayRetailMoney(HttpServletRequest request) {
        Double todayRetailMoney = retailService.findByChargeTimeAndPharmacist(new Date(), PharSecuritySupport.getPharmacist()).stream().mapToDouble(retail -> retail.getRealCost()).sum();
        return DecimalCalculate.roundDown(todayRetailMoney, 2);
    }

    @RequestMapping(value = "/retail/getPatientInfo")
    @ResponseBody
    public Result getPatientInfo(@RequestParam(required = false) String udid,
                                 @RequestParam(required = false) String mobile) {
        Patient patientByUid = null;
        if (StringUtils.isNotEmpty(udid)) {
            List<Patient> patientList = patientService.getPatientListByUdid(udid);
            patientList = patientList.stream().filter(
                    patient -> StringUtils.isNotEmpty(
                            patient.getMobile())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(patientList)) {
                patientByUid = patientList.get(0);
            }
            if (patientByUid == null || patientByUid.getStatus() == StatusEntity.Status.Unactivated) {
                if (mobile == null || "".equals(mobile)) {
                    return Result.fail("该患者未绑卡,请先进行绑卡操作!");
                }
            } else {
                return Result.ok().setData(patientByUid);
            }
        }
        //输入手机号码
        if (StringUtils.isNotEmpty(mobile)) {
            List<Patient> ps = patientService.listPatientByMobile(mobile);
            if (ps != null && !ps.isEmpty()) {
                patientByUid = ps.get(0);
                return Result.ok().setData(patientByUid);
            } else {
                return Result.fail("该手机未绑卡！");
            }
        }
        return Result.fail();
    }


    //TODO  零售开单 2016-12-8 11:26:04
    @RequestMapping("/retail/RetailBilling")
    public String RetailBilling(Model model,
                                @RequestParam(value = "patientId", required = false) Long patientId
    ) {

        Patient patient = null;
        if (null != patientId) {
            patient = patientService.getPatientById(patientId);
        }
        //查找数据库中是否还有未保存的单子
        Retail retail;
        List<Retail> notSaveRetailList = retailService.findByRetailStatus(PharSecuritySupport.getDoctor(), Retail.Retail_Status.Not_Save);
        if (CollectionUtils.isNotEmpty(notSaveRetailList)) {
            retail = notSaveRetailList.get(0);
            //计算药品的合计
            retail.setAllMedCost(retailService.getAllMedPrice(retail));
            retailService.save(retail);
        } else {
            retail = retailService.createOne(patient, PharSecuritySupport.getPharmacist());
            retailService.save(retail);
        }

        //公司
        Map<Long, String> companyMap = companyService.findCompanyByRetailMedicines(retail.getRetailMedicineList());
        //药品有效期,库存等信息
        Map<Long, MedicineStock> stockMap = Maps.newHashMap();
        retail.getRetailMedicineList().forEach(rm ->
                stockMap.put(rm.getId(), medicineService.getMedicineStock(PharSecuritySupport.getDoctor(), rm.getMedicinePrivate()))
        );

        //药品的数量
        Map<Long, Double> totalNumMap = retailMedicineService.getTotalNumMap(retail.getRetailMedicineList());

        model.addAttribute("totalNumMap", totalNumMap);
        model.addAttribute("stockMap", stockMap);
        model.addAttribute("retail", retail);
        model.addAttribute("patient", retail.getPatient());
        model.addAttribute("companyMap", companyMap);
        model.addAttribute("genderMap", GENDER_MAP);
        model.addAttribute("retailMedicine", retail.getRetailMedicineList());
        return "/advertising/RetailBilling";
    }


    /**
     * 获取药品信息
     *
     * @param name
     * @param page
     * @param retailId
     * @param model
     * @return
     */
    @RequestMapping(value = "/retail/getMedTag")
    public String getMedTag(@RequestParam(value = "name") String name,
                            @RequestParam(value = "page") int page,
                            @RequestParam(value = "retailId") Long retailId,
                            @RequestParam(value = "barCode") String barCode,
                            Model model) {

        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        if (StringUtils.isNotEmpty(barCode)) {
            model.addAttribute("barCode", barCode);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, 20, sort);

        model.addAttribute("list", medicinePrivateService.findPrivateMedTag(doctorService.getPrimaryDoctor(PharSecuritySupport.getDoctor()), name, barCode, pageable));


        if (retailId != null) {
            model.addAttribute("retail", retailService.findById(retailId));
        } else {
            model.addAttribute("retail", null);
        }
        return "/valManage/sunPage/retailMedInfoTagPage";
    }

    //TODO  选择药品 2016-12-12 10:04:37
    @RequestMapping("/retail/retailMedShow")
    public String selectMedic(@RequestParam(value = "medId") Long medId,
                              @RequestParam(value = "retailId") Long retailId,
                              Model model) {
        Optional.ofNullable(medPrivateService.getMedPrivate(medId)).ifPresent(medicinePrivate -> {
            //药品有效期,库存等信息
            model.addAttribute("mp", medicinePrivate);
            MedicineStock stock = medicineService.getMedicineStock(PharSecuritySupport.getDoctor(), medicinePrivate);
            //之前没有保存到私有药品的barcode


            if (StringUtils.isEmpty(medicinePrivate.getBarCode()) && stock.getHaveManager() && StringUtils.isNotEmpty(stock.getBarCode())) {
                medicinePrivate.setBarCode(stock.getBarCode());
                medPrivateService.save(medicinePrivate);
            }
            model.addAttribute("stock", stock);
            model.addAttribute("retail", retailService.findById(retailId));
        });

        return "/advertising/bombBox/selectMedic";
    }


    /**添加药品
     * @param retailMedicine
     * @param medPrivateId
     * @param retailId
     * @return
     */
    @RequestMapping(value = "/retail/addMed")
    @ResponseBody
    public Result addMed(RetailMedicine retailMedicine,
                         @RequestParam(value = "medPrivateId") Long medPrivateId,
                         @RequestParam(value = "retailId") Long retailId) {

        Optional.ofNullable(retailService.findById(retailId)).ifPresent(retail -> {

            MedicinePrivate medPrivate = medPrivateService.getMedPrivate(medPrivateId);
            //设置零售药品的有效期
            Optional.ofNullable(medicineService.getMedicineStock(PharSecuritySupport.getDoctor(), medPrivate)).ifPresent(
                    medicineStock ->
                            retailMedicine.setValidityDate(medicineStock.getValidityDate())
            );
            medicineService.getMedicineStock(PharSecuritySupport.getDoctor(), medPrivate).getValidityDate();
            retailMedicine.setMedicinePrivate(medPrivate);
            retailMedicine.setCreateTime(new Date());
            retailMedicine.setStatus(RetailMedicine.Status.Not_Save);
            retailMedicine.setRetailPrice(medPrivate.getPrice());
            retailMedicine.setRetail(retail);
            retailMedicine.setTotalPrice(retailMedicineService.getTotalPrice(retailMedicine));

            retailMedicineService.save(retailMedicine);

        });


        return Result.ok();
    }

    /**删除药品
     * @param retailMeds
     * @return
     */
    @RequestMapping(value = "/retail/delRetailMed")
    @ResponseBody
    public Result delRetailMed(@RequestBody List<RetailMedicine> retailMeds) {
        if (CollectionUtils.isEmpty(retailMeds)) {
            return Result.ok("请选择药品");
        }
        retailMeds.forEach(retailMedicine ->
                retailMedicineService.delRetailMed(retailMedicine)
        );
        return Result.ok("删除成功！");
    }


    /**
     * @param retailId
     * @param model
     * @return
     */
    //TODO  费用明细 2016-12-12 15:01:21
    @RequestMapping("/retail/bombBox/retailChargeDetails")
    public String retailChargeDetails(@RequestParam(value = "retailId") Long retailId, Model model) {

        //药品总价格

        Optional.ofNullable(retailService.findById(retailId)).ifPresent(retail -> {

            Double totalPrice = retailService.getAllMedPrice(retail);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("retail", retail);
        });
        return "/advertising/bombBox/retailChargeDetails";
    }


    @RequestMapping(value = "/retail/submitPrice")
    @ResponseBody
    public Result submitPrice(@RequestParam(value = "retailId") Long retailId,
                              @RequestParam(value = "realPrice") Double realPrice) {

        boolean flag = Optional.ofNullable(retailService.findById(retailId)).map(retail -> {
            retail.setRealCost(realPrice);
            retail.setChargeStatus(Retail.Charge_Status.Charge);
            retail.setRetailStatus(Retail.Retail_Status.Save);
            retail.setChargeTime(new Date());
            retail.getRetailMedicineList().forEach(retMed -> {
                retMed.setStatus(RetailMedicine.Status.Save);
            });

            retailService.save(retail);


            //插入损耗表
            List<MedicineContainer> containerList = medicineContainerService.convertList(Optional.ofNullable(retail.getRetailMedicineList()).orElse(Lists.newArrayList()));
            medicineContainerService.insertLossDetail(containerList);


            return true;
        }).isPresent();

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

    //TODO  零售列表 2016-12-8 11:26:06
    @RequestMapping("/retail/RetailList")
    public String RetailList(@RequestParam(value = "orderId", required = false, defaultValue = "") String orderId,
                             @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                             Model model) {
        Page<Retail> retailPage = retailService.findAllPage(orderId, PharSecuritySupport.getDoctor(), new PageRequest(page, 10, Sort.Direction.DESC, "id"));

        model.addAttribute("orderId", orderId);
        model.addAttribute("retailPage", retailPage);
        return "/advertising/RetailList";
    }

    //TODO  零售订单详情 2016-12-8 12:04:21
    @RequestMapping("/retail/retailDetails")
    public String retailDetails(@RequestParam(value = "retailId") Long retailId,
                                Model model) {
        Optional.ofNullable(retailService.findById(retailId)).ifPresent(
                retail -> {
                    model.addAttribute("retail", retail);
                    //库存，公司信息
                    Map<Long, MedicineStock> stockMap = Maps.newHashMap();
                    retail.getRetailMedicineList().forEach(retMed ->
                            stockMap.put(retMed.getId(), medicineService.getMedicineStock(PharSecuritySupport.getDoctor(), retMed.getMedicinePrivate()))
                    );

                    //公司（备用）
                    Map<Long, String> companyMap = companyService.findCompanyByRetailMedicines(retail.getRetailMedicineList());
                    model.addAttribute("companyMap", companyMap);
                    model.addAttribute("stockMap", stockMap);
                    //患者信息
                    Optional.ofNullable(retail.getPatient()).ifPresent(
                            patient -> model.addAttribute("patient", patient)
                    );
                });
        return "/advertising/bombBox/retailDetails";
    }


    //TODO  开单关联患者 2016-12-12 11:49:33
    @RequestMapping("/retail/bombBox/registration")
    public String registration() {
        List<Retail> retailList = retailService.findByRetailStatus(PharSecuritySupport.getDoctor(), Retail.Retail_Status.Not_Save);
        if (CollectionUtils.isNotEmpty(retailList)) {
            retailService.delRetailList(retailList);
        }
        return "/advertising/bombBox/registration";
    }

    @RequestMapping(value = "/retail/noCard")
    @ResponseBody
    public Result noCard() {
        List<Retail> retailList = retailService.findByRetailStatus(PharSecuritySupport.getDoctor(), Retail.Retail_Status.Not_Save);
        if (CollectionUtils.isNotEmpty(retailList)) {
            retailService.delRetailList(retailList);
        }
        return Result.ok();
    }

    @RequestMapping(value = "/retail/updateMedPrice")
    @ResponseBody
    public Result updateMedPrice(@RequestParam(value = "medId") Long medId,
                                 @RequestParam(value = "medPrice") Double medPrice) {
        Boolean flag = Optional.ofNullable(medPrivateService.getMedPrivate(medId)).map(med -> {
            med.setPrice(medPrice);
            medPrivateService.save(med);
            return true;
        }).orElse(false);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }



    //TODO  拍摄照片 2016-12-19 16:31:11
    @RequestMapping("/retail/bombBox/takePhotos")
    public String takePhotos() {

        return "/advertising/bombBox/takePhotos";
    }

    //TODO  零售开单  修改药品 2017-2-10 14:49:26
    @RequestMapping("/retail/bombBox/editMedPrice")
    public String editMedPrice(@RequestParam(value = "medId") Long medId, Model model) {
        Optional.ofNullable(medPrivateService.getMedPrivate(medId)).ifPresent(mp -> {
            model.addAttribute("mp", mp);
        });
        return "/advertising/bombBox/editMedPrice";
    }

    @RequestMapping(value = "/retail/updateMed")
    @ResponseBody
    public Result functionName(@RequestParam(value = "price") Double price,
                               @RequestParam(value = "unit") Medicine.Unit unit,
                               @RequestParam(value = "medId") Long medId) {

        Boolean flag = Optional.ofNullable(medPrivateService.getMedPrivate(medId)).map(mp -> {
            mp.setPrice(price);
            mp.setUnit(unit);
            medPrivateService.save(mp);
            return true;
        }).orElse(false);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

}
