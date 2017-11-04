package com.qiaobei.hmp.web;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Company;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.IaiInto;
import com.qiaobei.hmp.modules.entity.IaiIntoDetail;
import com.qiaobei.hmp.modules.service.IaiIntoDetailService;
import com.qiaobei.hmp.modules.service.IaiIntoService;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.modules.support.DecimalCalculate;
import com.qiaobei.hmp.modules.support.IaiIntoUtils;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.CompanyService;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/9/19
 * Time 14:47
 */
@Controller
public class ValidityManageController extends ConstantsController {

    @Resource
    private IaiIntoDetailService iaiIntoDetailService;

    @Resource
    private IaiIntoService iaiIntoService;

    @Resource
    private CompanyService companyService;

    @Resource
    private DoctorService doctorService;


    //TODO 库存管理
    @RequestMapping(value = "/validityManage")
    public String validityManage(Model model,
                                 @RequestParam(value = "medName", required = false) String medName,
                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        Doctor doctor = SecuritySupport.getDoctor();
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        Page<IaiIntoDetail> detailPage = iaiIntoDetailService.findAllPage(doctor, medName, IaiIntoDetail.DetailStatus.SAVE, IaiIntoDetail.DetailType.STORAGE, pageable);
        List<IaiIntoDetail> iaiDetails = detailPage.getContent();

        Map<Long, String> companyMap = Maps.newHashMap();
        Map<Long, String> expireMap = Maps.newHashMap();

        //每一个药品的库存量
        Map<Long, Double> StockNumMap = Maps.newHashMap();

        Map<Long, Boolean> lackMap = Maps.newHashMap();
        for (IaiIntoDetail detail : iaiDetails) {
            //公司
            if (null == detail.getCompanyId()) {
                System.out.println("药品厂家出现null的情况:" + ":" + detail.getId() + ":" + detail.getMedicine().getName());
            }
            Company company = companyService.getCompany(Optional.ofNullable(detail.getCompanyId()).orElse(-1L));
            if (company != null) {
                companyMap.put(detail.getId(), company.getName());
            } else {
                companyMap.put(detail.getId(), null);
            }
            //过期
            Date valid = detail.getValidityDate();
            if (valid == null) {
                valid = new Date();
            }
            int day = DateUtils.diffDay(new Date(), valid);
            int month = DateUtils.diffMonth(new Date(), detail.getValidityDate());
            if (day < 0) {
                expireMap.put(detail.getId(), "Expire");
            } else if (month >= 0 && month <= 3) {
                expireMap.put(detail.getId(), "fastExpire");

            } else {
                expireMap.put(detail.getId(), "notExpire");
            }

            //药品的库存量
            Double stockNum = iaiIntoDetailService.getStockNum(detail);
            StockNumMap.put(detail.getId(), DecimalCalculate.roundDown(stockNum, 2));

            //需补充数量
            if (detail.getWarnLine() != null && detail.getWarnLine() > stockNum) {
                lackMap.put(detail.getId(), true);
            } else {
                lackMap.put(detail.getId(), false);
            }

            //药品的库存量
            //StockNumMap.put(detail.getId(),iaiIntoDetailService.getStockNum(detail));

        }


        model.addAttribute("detailPage", detailPage);
        model.addAttribute("details", iaiDetails);
        model.addAttribute("companyMap", companyMap);
        model.addAttribute("expireMap", expireMap);
        model.addAttribute("lackMap", lackMap);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("medName", medName);
        model.addAttribute("StockNumMap", StockNumMap);
        return "validityManage";
    }


    //TODO 智能订单 2016-9-20 14:24:22
    @RequestMapping(value = "/validityManage/repOrders")
    public String repOrders(Model model,
                            @RequestParam(value = "goodsNo", required = false, defaultValue = "")
                                    String goodsNo,
                            @RequestParam(value = "page", required = false, defaultValue = "0")
                                    int page) {
        Doctor doctor = SecuritySupport.getDoctor();
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        Page<IaiInto> IaiIntoRepPage = iaiIntoService.findIaiIntoPageByGoodsNo(doctor, goodsNo, IaiInto.IaiIntoStatus.SAVE, IaiInto.IaiIntoType.REPLENISH, pageable);

        model.addAttribute("IaiIntoRepPage", IaiIntoRepPage);
        model.addAttribute("goodsNo", goodsNo);
        model.addAttribute("iaiIntoRepList", IaiIntoRepPage.getContent());


        return "valManage/repOrders";
    }

    /**
     * 进入智能补货的详细药品页
     */
    @RequestMapping(value = "/validityManage/goIaiIntoDetailRep")
    public String goIaiIntoDetailRep(Model model,
                                     @RequestParam(value = "iaiIntoId") Long iaiIntoId,
                                     @RequestParam(value = "page", required = false, defaultValue = "0")
                                             int page) {
        IaiInto iaiInto = iaiIntoService.findById(iaiIntoId);
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        Page<IaiIntoDetail> detailPage = iaiIntoDetailService.findByIaiIntoPage(iaiInto, pageable);
        List<IaiIntoDetail> details = detailPage.getContent();
        Map<Long, String> companyMap = companyService.findCompanyByDetails(details);
        model.addAttribute("iaiInto", iaiInto);
        model.addAttribute("details", details);
        model.addAttribute("companyMap", companyMap);
        model.addAttribute("detailPage", detailPage);
        return "valManage/replenishment";
    }


    //TODO 智能补货 2016-9-20 14:24:22
    @RequestMapping(value = "/validityManage/replenishment")
    public String replenishment(Model model,
                                @RequestParam(value = "uuid", required = false, defaultValue = "0") String uuid,
                                @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        Doctor doctor = SecuritySupport.getDoctor();
        //必须确保是主账号的id
        if (doctor.getDoctorType() != Doctor.Doctor_Type.Clinic_Boss && null != doctor.getPrimaryDoctorId()) {
            doctor = doctorService.getDoctor(doctor.getPrimaryDoctorId());
        }
        IaiInto iaiInto = iaiIntoService.findByUuid(uuid);
        //需补药品数量
        Map<IaiIntoDetail, Double> needMap = Maps.newHashMap();
        if (iaiInto == null) {
            //找到该医生的智能补货单（类型：智能补货，未保存）
            List<IaiInto> iaiIntoList = iaiIntoService.findByCreateByAndIaiIntoTypeAndStatus(doctor.getId(),
                    IaiInto.IaiIntoType.REPLENISH, IaiInto.IaiIntoStatus.NOT_SAVE);
            //删除之前未保存的智能订单
            if (CollectionUtils.isNotEmpty(iaiIntoList)) {
                for (IaiInto anIaiIntoList : iaiIntoList) {
                    iaiIntoService.delIaiInto(anIaiIntoList.getId());
                }

            }
            //创建一个临时的
            IaiInto iaiIntoTemp = new IaiInto();
            iaiIntoTemp.setIaiIntoType(IaiInto.IaiIntoType.REPLENISH);
            iaiIntoTemp.setStatus(IaiInto.IaiIntoStatus.NOT_SAVE);
            iaiIntoTemp.setCreateBy(doctor.getId());
            iaiIntoTemp.setCreateDate(new Date());

            iaiIntoTemp.setGoodsNo(IaiIntoUtils.createZNGoodsNo(doctor.getId(), iaiIntoService.findLast(doctor.getId())));
            iaiIntoTemp.setUuid(uuid);
            iaiIntoService.save(iaiIntoTemp);

            iaiIntoDetailService.findByDoctorHasSaveAndStorage(doctor).forEach(detail -> {
                Double remainNum = iaiIntoDetailService.getStockNum(detail);
                //当剩余库存小于警戒线时
                if (null == detail.getWarnLine()) {
                    detail.setWarnLine(0L);
                }
                if (remainNum < detail.getWarnLine().doubleValue()) {
                    IaiIntoDetail detailTemp = IaiIntoDetail.copyIaiIntoDetail(detail);
                    detailTemp.setIaiInto(iaiIntoTemp);
                    detailTemp.setStatus(IaiIntoDetail.DetailStatus.NOT_SAVE);
                    detailTemp.setDetailType(IaiIntoDetail.DetailType.REPLENISH);
                    //临时的库存保存数量
                    detailTemp.setIncomeQuantity(detail.getTotalNumber() - remainNum);
                    detailTemp.setTotalNumber(detail.getTotalNumber() - remainNum);
                    iaiIntoDetailService.save(detailTemp);
                    needMap.put(detailTemp, detail.getTotalNumber() - remainNum);
                }
            });

            iaiInto = iaiIntoTemp;
        }
        model.addAttribute("iaiInto", iaiInto);
        model.addAttribute("needMap", needMap);
        //找到该智能补货单对应的药品
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        Page<IaiIntoDetail> detailPage = iaiIntoDetailService.findByIaiIntoPage(iaiInto, pageable);
        List<IaiIntoDetail> details = detailPage.getContent();
        Map<Long, String> companyMap = companyService.findCompanyByDetails(details);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("detailPage", detailPage);
        model.addAttribute("details", details);
        model.addAttribute("companyMap", companyMap);

        return "valManage/replenishment";
    }

    @RequestMapping(value = "/validityManage/replenishment/saveReplenish")
    @ResponseBody
    public Result saveReplenish(@RequestParam(value = "iaiIntoId") Long iaiIntoId) {
        IaiInto iaiIntoRep = iaiIntoService.findById(iaiIntoId);
        iaiIntoRep.setStatus(IaiInto.IaiIntoStatus.SAVE);
        Double totalMoney = 0.00;

        List<IaiIntoDetail> detailRepList = iaiIntoRep.getIntoDetailList();
        if (CollectionUtils.isEmpty(detailRepList)) {
            iaiIntoRep.setTotalMoney(0.00);
        } else {
            for (IaiIntoDetail detail : detailRepList) {
                //计算订单总额
                //totalMoney = totalMoney + detail.getBayingPrice() * detail.getTotalNumber();
                //将每个药品状态设置成保存
                detail.setStatus(IaiIntoDetail.DetailStatus.SAVE);
                iaiIntoDetailService.save(detail);
            }
        }
        //保存智能补货单
        iaiIntoRep.setTotalMoney(totalMoney);
        iaiIntoRep.setCreateDate(new Date());
        iaiIntoService.save(iaiIntoRep);

        return Result.ok();
    }


    //TODO 过期药品提醒 2016-9-20 16:45:06
    @RequestMapping(value = "/validityManage/overdueMedicine")
    public String overdueMedicine(Model model, @RequestParam(value = "medName", required = false) String medName,
                                  @RequestParam(value = "page", required = false, defaultValue = "0") int page
    ) {
        Doctor doctor = SecuritySupport.getDoctor();
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        if (medName == null) {
            medName = "";
        }
        //过期的
        Page<IaiIntoDetail> expirePage = iaiIntoDetailService.findExpireDetail(doctor.getId(), "%" + medName + "%", pageable);
        List<IaiIntoDetail> expires = expirePage.getContent();
        Map<Long, String> expireCompanyMap = companyService.findCompanyByDetails(expires);

        //快过期
        Page<IaiIntoDetail> fastExpirePage = iaiIntoDetailService.findFastExpireDetail(doctor.getId(), "%" + medName + "%", pageable);
        List<IaiIntoDetail> fastExpires = fastExpirePage.getContent();
        Map<Long, String> fastExpireCompanyMap = companyService.findCompanyByDetails(fastExpires);

        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("medName", medName);
        model.addAttribute("expirePage", expirePage);
        model.addAttribute("fastExpirePage", fastExpirePage);
        model.addAttribute("expireCompanyMap", expireCompanyMap);
        model.addAttribute("fastExpireCompanyMap", fastExpireCompanyMap);
        model.addAttribute("expires", expires);
        model.addAttribute("fastExpires", fastExpires);

        //过期各个药品的库存
        model.addAttribute("fastStockMap", iaiIntoDetailService.getStockNumMap(fastExpires));
        //快过期的各个药品的库存
        model.addAttribute("expireStockMap", iaiIntoDetailService.getStockNumMap(expires));

        return "valManage/overdueMedicine";
    }

}
