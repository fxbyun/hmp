package com.qiaobei.hmp.web;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.IaiIntoDetailService;
import com.qiaobei.hmp.modules.service.IaiIntoService;
import com.qiaobei.hmp.modules.service.SupplierService;
import com.qiaobei.hmp.modules.support.BeanUtils;
import com.qiaobei.hmp.modules.support.DecimalCalculate;
import com.qiaobei.hmp.modules.support.IaiIntoUtils;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.CompanyService;
import com.qiaobei.hmp.service.MedicinePrivateService;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/9/23 0023
 * Time 13:36
 */
@Controller
public class IaiIntoController extends ConstantsController {
    @Resource
    private IaiIntoService iaiIntoService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private SupplierService supplierService;

    @Resource
    private MedicinePrivateService medicinePrivateService;

    @Resource
    private CompanyService companyService;

    @Resource
    private IaiIntoDetailService iaiIntoDetailService;


    @ModelAttribute("doctor")
    private Doctor doctor(HttpServletRequest request) {
        return SecuritySupport.getDoctor();
    }

    //TODO 入货库记录
    @RequestMapping(value = "/validityManage/entryRecord")
    public String entryRecord(Model model,
                              @RequestParam(value = "goodsNo", required = false, defaultValue = "")
                                      String goodsNo,
                              @RequestParam(value = "pageNo", required = false, defaultValue = "0")
                                      int pageNo) {
        Pageable pageabl2e = new PageRequest(pageNo, 10, Sort.Direction.DESC, "id");
        Doctor doctor = SecuritySupport.getDoctor();
        //Page<IaiInto> iaiIntoPage = iaiIntoService.findIaiIntoPage(doctor, pageabl2e);
        Page<IaiInto> iaiIntoPage = iaiIntoService.findIaiIntoPageByGoodsNo(doctor, goodsNo, IaiInto.IaiIntoStatus.SAVE, IaiInto.IaiIntoType.STORAGE, pageabl2e);


        model.addAttribute("iaiIntoPage", iaiIntoPage);
        model.addAttribute("goodsNo", goodsNo);
        model.addAttribute("iaiIntoList", iaiIntoPage.getContent());
        return "/valManage/entryRecord";
    }

    @RequestMapping(value = "/validityManage/delIaiInto")
    @ResponseBody
    public Result delIaiInto(@RequestParam(value = "iaiIntoId") Long iaiIntoId) {

        iaiIntoService.delIaiInto(iaiIntoId);
        return Result.ok();
    }

    //判断该医生是否存在一张历史入库单
    @RequestMapping(value = "/validityManage/isHistory")
    @ResponseBody
    public Result isHistory() {
        Doctor doctor = SecuritySupport.getDoctor();
        Result result = new Result();
        List<IaiInto> iaiIntoList = iaiIntoService.findByCreateByAndIaiIntoTypeAndStatus(doctor.getId(),
                IaiInto.IaiIntoType.STORAGE, IaiInto.IaiIntoStatus.NOT_SAVE);
        if (CollectionUtils.isNotEmpty(iaiIntoList)) {
            result.setSuccess(true);
            result.setData(iaiIntoList.get(0).getUuid());
        } else {
            result.setSuccess(false);
        }
        return result;
    }


    //TODO 新增进货单 2016-9-19 16:00:10
    @RequestMapping(value = "/validityManage/saveValList")
    public String saveValList(Model model, @RequestParam("addUUID") String addUUID) {

        //拿到该医生的供应商
        Doctor doctor = SecuritySupport.getDoctor();
        List<Supplier> supplierList = supplierService.findByDoctor(doctor);
        model.addAttribute("supplierList", supplierList);
        model.addAttribute("doctor", doctor);


        //预先生成一个入库的id
        IaiInto iaiInto = iaiIntoService.findByUuid(addUUID);
        //新增
        if (iaiInto == null) {
            //先删之前的历史订单
            List<IaiInto> historyList = iaiIntoService.findByCreateByAndIaiIntoTypeAndStatus(doctor.getId(),
                    IaiInto.IaiIntoType.STORAGE, IaiInto.IaiIntoStatus.NOT_SAVE);
            if (CollectionUtils.isNotEmpty(historyList)) {
                historyList.forEach(iaiInto1 -> iaiIntoService.delIaiInto(iaiInto1.getId()));
            }

            iaiInto = new IaiInto();
            //设计生产RK号

            iaiInto.setGoodsNo(IaiIntoUtils.createRKGoodsNo(doctor.getId(), iaiIntoService.findLast(doctor.getId())));
            iaiInto.setCreateDate(new Date());
            iaiInto.setUuid(addUUID);
            iaiInto.setCreateBy(SecuritySupport.getDoctorId());
            iaiInto.setStatus(IaiInto.IaiIntoStatus.NOT_SAVE);
            iaiInto.setIaiIntoType(IaiInto.IaiIntoType.STORAGE);
            iaiIntoService.save(iaiInto);
        }

        model.addAttribute("iaiInto", iaiInto);


        return "/valManage/saveValList";
    }

    // TODO: 保存进货单 2016/10/14 0014
    @RequestMapping(value = "/validityManage/saveIaiInto")
    @ResponseBody
    public Result saveIaiInto(IaiInto iaiInto, Long supplierId) {
        Supplier supplier = supplierService.findById(supplierId);
        IaiInto iai = iaiIntoService.findById(iaiInto.getId());
        iai.setSupplier(supplier);
        iai.setCreateDate(iaiInto.getCreateDate());
        iai.setGoodsNo(iaiInto.getGoodsNo());
        iai.setWhoCreate(iaiInto.getWhoCreate());
        iai.setIaiIntoType(IaiInto.IaiIntoType.STORAGE);
        iai.setStatus(IaiInto.IaiIntoStatus.SAVE);
        List<IaiIntoDetail> details = iaiIntoDetailService.findByIaiIntoId(iaiInto.getId());
        Double totalMoney = 0.00;
        if (CollectionUtils.isEmpty(details)) {
            iai.setTotalMoney(0.00);
        } else {
            for (IaiIntoDetail detail : details) {
                //计算订单总额
                totalMoney = totalMoney + detail.getBayingPrice() * detail.getTotalNumber();
                //将每个药品状态设置成保存
                detail.setStatus(IaiIntoDetail.DetailStatus.SAVE);
                iaiIntoDetailService.save(detail);
            }
        }
        iai.setTotalMoney(totalMoney);
        iai.setStatus(IaiInto.IaiIntoStatus.SAVE);
        iaiIntoService.save(iai);
        return Result.ok();
    }


    //TODO 弹出编辑药品弹框 2016-9-20 11:05:24
    @RequestMapping(value = "/validityManage/bombBox/saveMedicine")
    public String saveMedicine(Model model,
                               @RequestParam("medId") Long id,
                               @RequestParam(value = "fromHtml", required = false) String fromHtml,
                               @RequestParam("iaiIntoId") Long iaiIntoId,
                               @RequestParam(value = "iaiDetailId", required = false) Long iaiDetailId
    ) {

        Medicine medicine = medicineService.getMedicine(id);
        List<MedicinePrivate> medPrivateList = medicinePrivateService.findByDoctorAndMedicine(SecuritySupport.getDoctor(), medicine);
        if (CollectionUtils.isNotEmpty(medPrivateList)) {
            model.addAttribute("med", medPrivateList.get(0).getMedicine());
            model.addAttribute("medPrivate", medPrivateList.get(0));
        } else {
            model.addAttribute("med", medicine);
        }

        model.addAttribute("medicineUnits", MEDICINE_UNITS);


        model.addAttribute("iaiInto", iaiIntoService.findById(iaiIntoId));

        if (iaiDetailId != null) {
            IaiIntoDetail detail = iaiIntoDetailService.findById(iaiDetailId);
            model.addAttribute("stockNum", DecimalCalculate.roundDown(iaiIntoDetailService.getStockNum(detail), 2));
            model.addAttribute("detail", detail);
        }

        model.addAttribute("fromHtml", fromHtml);

        //跳转到入库页面编辑药品
        if ("RK_HTML".equals(fromHtml)) {
            return "valManage/bombBox/incomeWindow";
        }

        //跳转到
        if ("replenishment".equals(fromHtml)) {
            return "valManage/bombBox/saveReplenishMedicine";
        }


        return "valManage/bombBox/saveMedicine";
    }

    //TODO  库存管理 药品入库保存 2016-10-13 16:54:05
    @RequestMapping(value = "/validityManage/bombBox/addIaiIntoDetail")
    @ResponseBody
    public Result editMedicine(IaiIntoDetail iaiDetail, @RequestParam("MedicineName") String MedicineName,
                               @RequestParam("medAddress") String medAddress,
                               @RequestParam("medAddressId") Long medAddressId,
                               @RequestParam("standard") String standard,
                               @RequestParam(value = "medicineUnit", required = false) Medicine.Unit medicineUnit,
                               @RequestParam(value = "price", required = false) Double price,
                               @RequestParam(value = "warnLine", required = false) Long warnLine,
                               @RequestParam("iaiIntoId") Long iaiIntoId,
                               @RequestParam("medicineId") Long medicineId,
                               @RequestParam(value = "detailId", required = false) Long detailId
    ) {
        //找到iaiInto
        IaiInto iaiInto = iaiIntoService.findById(iaiIntoId);
        if (iaiInto == null) {
            System.out.println("没有该入库表");
            return Result.ok("没有该入库表！");
        }

        IaiIntoDetail iaiIntoDetail;
        //判断是编辑还是新增
        if (detailId != null) {
            iaiIntoDetail = iaiIntoDetailService.findById(detailId);

        } else {
            iaiIntoDetail = new IaiIntoDetail();

        }

        iaiIntoDetail.setBarCode(iaiDetail.getBarCode());
        iaiIntoDetail.setBayingPrice(iaiDetail.getBayingPrice());
        iaiIntoDetail.setCompanyId(medAddressId);
        if (iaiDetail.getTotalNumber() != null) {
            iaiIntoDetail.setTotalNumber(iaiDetail.getTotalNumber());

        }
        iaiIntoDetail.setIncomeQuantity(iaiDetail.getTotalNumber());


        if (iaiDetail.getValidityDate() != null) {
            iaiIntoDetail.setValidityDate(iaiDetail.getValidityDate());
        }


        iaiIntoDetail.setWarnLine(warnLine);
        //设置药品时未保存的(根据订单状态来设置)
        if (iaiInto.getStatus() == IaiInto.IaiIntoStatus.NOT_SAVE) {
            iaiIntoDetail.setStatus(IaiIntoDetail.DetailStatus.NOT_SAVE);
        } else {
            iaiIntoDetail.setStatus(IaiIntoDetail.DetailStatus.SAVE);
        }
        //iaiIntoDetail.setStatus(IaiIntoDetail.DetailStatus.NOT_SAVE);
        if (iaiInto.getIaiIntoType() == IaiInto.IaiIntoType.REPLENISH) {
            //设置药品时入库类型的
            iaiIntoDetail.setDetailType(IaiIntoDetail.DetailType.REPLENISH);
        } else {
            //设置药品时入库类型的
            iaiIntoDetail.setDetailType(IaiIntoDetail.DetailType.STORAGE);
        }

        iaiIntoDetail.setIaiInto(iaiInto);
        //判断私有库中是否存在该药品
        Doctor doctor = SecuritySupport.getDoctor();
        MedicinePrivate medPrivate = null;
        if (CollectionUtils.isNotEmpty(medicinePrivateService.getMedPriByMedIdList(medicineId, doctor))) {
            medPrivate = medicinePrivateService.getMedPriByMedIdList(medicineId, doctor).get(0);
        }
        if (medPrivate == null) {
            medPrivate = new MedicinePrivate();
            medPrivate = medPrivate.medToMedPriveate(medicineService.getMedicine(medicineId));
            medPrivate.setDoctor(doctor);
        }
        //药厂,有些药厂是为null
        if (medAddressId == null) {
            medAddressId = -1L;
        }
        Company company = companyService.getCompany(medAddressId);


        medPrivate.setDefaultCompany(company);
        medPrivate.setDefaultCompanyName(company.getName());

        medPrivate.setStandard(standard);
        //设定入库单不会改变私有药品的统计单位属性
        medPrivate.setUnit(medicineUnit);
        medPrivate.setPrice(price);
        //设置该私有药品为库存所管理
        medPrivate.setHaveManager(MedicinePrivate.HaveManager.YES);
        //设置该私有药品的条码
        if (StringUtils.isNotEmpty(iaiDetail.getBarCode())) {
            medPrivate.setBarCode(iaiDetail.getBarCode());
        }
        medicinePrivateService.save(medPrivate);

        iaiIntoDetail.setMedicine(medPrivate);
        iaiIntoDetailService.save(iaiIntoDetail);

        return Result.ok("药品入库成功！");
    }

    @RequestMapping(value = "/validityManage/delDetail")
    @ResponseBody
    public Result delDetail(@RequestParam("detailId") Long detailId) {
        iaiIntoDetailService.delIaiDetail(detailId);
        return Result.ok();
    }


    //TODO 补货订单详情 2016-10-9 11:44:47
    @RequestMapping(value = "/validityManage/bombBox/orderDetails")
    public String orderDetails() {
        return "valManage/bombBox/orderDetails";
    }

    //TODO 管理供应商 2016-10-9 11:44:47
    @RequestMapping(value = "/validityManage/bombBox/manageSupplier")
    public String manageSupplier(Model model) {
        Doctor doctor = SecuritySupport.getDoctor();
        List<Supplier> supplierList = supplierService.findByDoctor(doctor);
        model.addAttribute("supplierList", supplierList);
        return "valManage/bombBox/editSupplier";
    }

    //TODO 弹出编辑供应商窗口 2016-10-9 11:44:47
    @RequestMapping(value = "/validityManage/bombBox/editSupplier")
    public String editSupplier(Model model, Long supplierId) {
        Supplier supplier = supplierService.findById(supplierId);
        model.addAttribute("supplier", supplier);
        return "valManage/bombBox/supplierForm";
    }

    //TODO 弹出供应商窗口 2016-10-9 11:44:47
    @RequestMapping(value = "/validityManage/bombBox/addSupplier")
    public String addSupplier(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "valManage/bombBox/supplierForm";
    }

    @RequestMapping(value = "/validityManage/bombBox/saveSupplier")
    @ResponseBody
    public Result saveSupplier(@RequestParam("name") String supplierName,
                               @RequestParam(value = "id", required = false) Long id) {
        Doctor doctor = SecuritySupport.getDoctor();
        Supplier supplier;
        if (id != null) {
            supplier = supplierService.findById(id);
        } else {
            supplier = new Supplier();
            supplier.setDoctor(doctor);
        }
        supplier.setName(supplierName);
        supplierService.save(supplier);

        return Result.ok();
    }


    @RequestMapping(value = "/validityManage/bombBox/delSupplier")
    @ResponseBody
    public Result delSupplier(@RequestParam("supplierId") Long supplierId) {

        Supplier supplier = supplierService.findById(supplierId);
        if (supplier != null) {
            supplierService.delSupplier(supplierId);
            return Result.ok("供应商成功删除了！");
        } else {
            return Result.fail("没有找到该供应商！");
        }

    }


    /**
     * 编辑药品信息
     */
    @RequestMapping(value = "/validityManage/bombBox/getMedInfo")
    public String getMedList(@RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "iaiIntoId", required = false) Long iaiIntoId,
                             Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, 20, sort);
        model.addAttribute("list", medicineService.listDoctorMedTag(pageable, SecuritySupport.getDoctor(), null, name,
                null));


        if (iaiIntoId != null) {
            model.addAttribute("iai", iaiIntoService.findById(iaiIntoId));
        } else {
            model.addAttribute("iai", null);
        }


        return "valManage/sunPage/medInfoTagPage";
    }

    /**
     * 编辑选择智能药品
     */
    @RequestMapping(value = "/validityManage/bombBox/getReplenishMedList")
    public String getReplenishMedList(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "iaiIntoId", required = false) Long iaiIntoId,
                                      Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, 20, sort);
        model.addAttribute("list", medicineService.listDoctorMedTag(pageable, SecuritySupport.getDoctor(), null, name,
                null));


        if (iaiIntoId != null) {
            model.addAttribute("iai", iaiIntoService.findById(iaiIntoId));
        } else {
            model.addAttribute("iai", null);
        }


        return "valManage/sunPage/replenishMedTagPage";
    }


    @RequestMapping(value = "/fragment/validityManage/showIaiDetails")
    public String showIaiDetails(Model model,
                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                 @RequestParam("iaiIntoId") Long iaiIntoId) {
        IaiInto iaiInto = iaiIntoService.findById(iaiIntoId);

        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");

        Page<IaiIntoDetail> result = iaiIntoDetailService.findByIaiIntoPage(iaiInto, pageable);

        List<IaiIntoDetail> iaiDetails = result.getContent();
        Map<Long, String> companyMap = Maps.newHashMap();
        for (IaiIntoDetail detail : iaiDetails) {
            Company company;
            if (detail.getCompanyId() == null) {
                company = null;
            } else {
                company = companyService.getCompany(detail.getCompanyId());
            }
            if (company != null) {
                companyMap.put(detail.getId(), company.getName());
            } else {
                companyMap.put(detail.getId(), null);
            }
        }

        model.addAttribute("page", result);
        model.addAttribute("iaiDetails", iaiDetails);
        model.addAttribute("iaiInto", iaiInto);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("companyMap", companyMap);
        return "/fragment/saveValListPage";
    }

    /*============================库存管理开始===================================*/
    @RequestMapping(value = "/validityManage/showDoctorIaiInto")
    public String showDoctorIaiInto(Model model,
                                    @RequestParam(value = "medName", required = false, defaultValue = "")
                                            String medName,
                                    @RequestParam(value = "pageNo", required = false, defaultValue = "0")
                                            int pageNo
    ) {
        Doctor doctor = SecuritySupport.getDoctor();
        Pageable pageable = new PageRequest(pageNo, 10, Sort.Direction.DESC, "id");
        Page<IaiIntoDetail> detailPage = iaiIntoDetailService.findAllPage(doctor, medName, IaiIntoDetail.DetailStatus.SAVE, IaiIntoDetail.DetailType.STORAGE, pageable);

        List<IaiIntoDetail> detailList = detailPage.getContent();


        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("detailPage", detailPage);
        model.addAttribute("detailList", detailList);
        return "/validityManage";
    }


    //补货订单详情列表
    @RequestMapping(value = "/validityManage/bombBox/showReplenish")
    public String function(Model model,
                           @RequestParam(value = "page", required = false, defaultValue = "0")
                                   int page) {
        Doctor doctor = SecuritySupport.getDoctor();
        Pageable pageable = new PageRequest(page, 5, Sort.Direction.DESC, "id");
        Page<IaiInto> replenishPage = iaiIntoService.findByCreateByAndIaiIntoTypeAndStatus(doctor.getId(),
                IaiInto.IaiIntoType.REPLENISH, IaiInto.IaiIntoStatus.SAVE, pageable);

        List<IaiInto> replenishList = replenishPage.getContent();
        model.addAttribute("replenishPage", replenishPage);
        model.addAttribute("replenishList", replenishList);

        return "/valManage/bombBox/orderDetails";
    }

    @RequestMapping(value = "/validityManage/bombBox/loadReplenish")
    @ResponseBody
    public Result loadReplenish(@RequestParam(value = "iaiIntoId") Long iaiIntoId,
                                @RequestParam(value = "replenishId") Long replenishId) {
        IaiInto iaiInto = iaiIntoService.findById(iaiIntoId);
        IaiInto replenish = iaiIntoService.findById(replenishId);
        if (iaiInto != null && replenish != null) {
            List<IaiIntoDetail> details = replenish.getIntoDetailList();
            for (IaiIntoDetail repDetail : details) {
                IaiIntoDetail temp = new IaiIntoDetail();
                //如果为保存状态才复制
                if (repDetail.getStatus() == IaiIntoDetail.DetailStatus.SAVE) {
                    try {
                        BeanUtils.applyIf(temp, repDetail);
                    } catch (Exception e) {
                        System.out.println("{IaiIntoController：loadReplenish}" + e.getMessage());
                    }
                    temp.setId(null);
                    temp.setIaiInto(iaiInto);
                    temp.setStatus(IaiIntoDetail.DetailStatus.NOT_SAVE);
                    temp.setDetailType(IaiIntoDetail.DetailType.STORAGE);
                    iaiIntoDetailService.save(temp);
                }


            }
        } else {
            return Result.fail("数据出错：iaiIntoId和replenishId其中一个为空");
        }

        return Result.ok();
    }


}
