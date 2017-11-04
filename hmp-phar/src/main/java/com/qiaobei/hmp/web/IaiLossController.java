package com.qiaobei.hmp.web;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.IaiIntoDetailService;
import com.qiaobei.hmp.modules.service.IaiIntoService;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.modules.service.IaiLossService;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.modules.support.IaiIntoUtils;
import com.qiaobei.hmp.service.CompanyService;
import com.qiaobei.hmp.service.MedicinePrivateService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/adv")
public class IaiLossController extends ConstantsController {

    @Resource
    private IaiLossService iaiLossService;

    @Resource
    private IaiLossDetailService iaiLossDetailService;

    @Resource
    private IaiIntoService iaiIntoService;

    @Resource
    private IaiIntoDetailService iaiIntoDetailService;

    @Resource
    private CompanyService companyService;

    @Resource
    private MedicinePrivateService medPrivateService;

    @ModelAttribute("loginUser")
    private Pharmacist loginUser(HttpServletRequest request) {
        return PharSecuritySupport.getPharmacist();
    }

    //TODO 损耗记录 2016-9-20 14:24:22
    @RequestMapping(value = "/validityManage/lossRecord")
    public String lossRecord(Model model,
                             @RequestParam(value = "lossNo", required = false, defaultValue = "")
                                     String lossNo,
                             @RequestParam(value = "page", required = false, defaultValue = "0")
                                     int page) {
        Doctor doctor = PharSecuritySupport.getDoctor();
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        Page<IaiLoss> lossPage = iaiLossService.findLossPageByLossNo(doctor, lossNo,
                IaiLoss.LossStatus.NOT_SAVE, pageable);

        model.addAttribute("lossPage", lossPage);
        model.addAttribute("lossNo", lossNo);
        model.addAttribute("lossList", lossPage.getContent());
        model.addAttribute("loginUser", PharSecuritySupport.getPharmacist());
        return "valManage/lossRecord";
    }

    /*@RequestMapping(value = "/IaiLossManage/showLoss")
    public String showLoss(Model model,@RequestParam(value = "iaiLossId") Long iaiLossId){
        IaiLoss iaiLoss = iaiLossService.findById(iaiLossId);
        Pageable pageable = new PageRequest(page,10, Sort.Direction.DESC,"id");
        iaiLossDetailService.findByIaiLossAndStatus(iaiLoss, IaiLossDetail.DetailStatus.SAVE);
        List<IaiLossDetail> lossDetails= iaiLoss.getIaiLossDetailList();
        Map<Long,String> companyMap = companyService.findCompanyByLossDetails(lossDetails);
        model.addAttribute("medicineUnits",MEDICINE_UNITS);
        model.addAttribute("", "");
        return "/";
    }*/





    //TODO 添加损耗单 2016-9-20 14:24:22
    @RequestMapping(value = "/IaiLossManage/saveIaiLoss")
    public String saveIaiLoss(Model model, @RequestParam(value = "uuid") String uuid,
                              @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        Doctor doctor = PharSecuritySupport.getDoctor();
        //判断是否是第一次点击进来的
        IaiLoss iaiLoss = iaiLossService.findByUuid(uuid);


        if (iaiLoss == null) {

            //找到这个医生的所有未保存的损耗单并删除掉
            List<IaiLoss> notSaveLoss = iaiLossService.findByCreateIdAndStatus(doctor.getId(), IaiLoss.LossStatus.NOT_SAVE);
            if (CollectionUtils.isNotEmpty(notSaveLoss)) {
                iaiLossService.delLossList(notSaveLoss);
            }


            iaiLoss = new IaiLoss();
            iaiLoss.setUuid(uuid);
            iaiLoss.setCreateId(doctor.getId());
            iaiLoss.setLossNo(IaiIntoUtils.createSHGoodsNo(doctor.getId(), iaiLossService.findLast(doctor.getId())));
            iaiLoss.setStatus(IaiLoss.LossStatus.NOT_SAVE);
            iaiLossService.save(iaiLoss);

            //将入库单中的已过期的药品复制一份加进来
            List<IaiIntoDetail> iaiDetailList = iaiIntoDetailService.findExpireDetail(doctor.getId(), "%%");
            if (CollectionUtils.isNotEmpty(iaiDetailList)) {
                for (IaiIntoDetail detail : iaiDetailList) {
                    IaiLossDetail lossDetail = IaiLossDetail.copyIaiLossDetail(detail);
                    lossDetail.setTotalNumber(detail.getTotalNumber());
                    lossDetail.setDetailStatus(IaiLossDetail.DetailStatus.NOT_SAVE);
                    lossDetail.setLossType(IaiLossDetail.IaiLossType.ACTIVE);
                    lossDetail.setStatus(StatusEntity.Status.Normal);
                    lossDetail.setIaiLoss(iaiLoss);
                    iaiLossDetailService.save(lossDetail);
                }
            }


        }
        model.addAttribute("iaiLoss", iaiLoss);
        //找到该iaiLoss损耗表对应的药品
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        Page<IaiLossDetail> lossDetailPage;
        if (iaiLoss.getStatus() == IaiLoss.LossStatus.SAVE) {
            lossDetailPage = iaiLossDetailService.findByIaiLossAndStatus(iaiLoss, IaiLossDetail.DetailStatus.SAVE, pageable);
        } else {
            lossDetailPage = iaiLossDetailService.findByIaiLossAndStatus(iaiLoss, IaiLossDetail.DetailStatus.NOT_SAVE, pageable);
        }

        List<IaiLossDetail> lossDetails = lossDetailPage.getContent();
        Map<Long, String> companyMap = companyService.findCompanyByLossDetails(lossDetails);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("lossDetailPage", lossDetailPage);
        model.addAttribute("lossDetails", lossDetails);
        model.addAttribute("companyMap", companyMap);
        model.addAttribute("now", new Date());
        model.addAttribute("loginUser", PharSecuritySupport.getPharmacist());
        return "valManage/saveLossList";
    }


    /**显示过期的药品
     * @param model
     * @param //page
     * @param //name
     * @param //iaiLossId
     * @return
     */
    @RequestMapping(value = "/IaiLossManage/bombBox/expireMedList")
    public String expireMedList(Model model,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "iaiLossId", required = false) Long iaiLossId) {

        Doctor doctor = PharSecuritySupport.getDoctor();

        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }

        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        Page<MedicinePrivate> expireMedPage = iaiIntoDetailService.findLossMedTag(doctor.getId(), name, pageable);

        model.addAttribute("list", expireMedPage);


        if (iaiLossId != null) {
            model.addAttribute("loss", iaiLossService.findById(iaiLossId));
        } else {
            model.addAttribute("loss", null);
        }
        model.addAttribute("loginUser", PharSecuritySupport.getPharmacist());

        return "valManage/sunPage/expireMedTagPage";
    }

    /** 损耗药品的选择
     * @param model
     * @param medPrivateId
     * @param iaiLossId
     * @return
     */
    @RequestMapping(value = "/IaiLossManage/bombBox/selectMedTag")
    public String selectMedTag(Model model, @RequestParam(value = "medPrivateId") Long medPrivateId,
                               @RequestParam(value = "iaiLossId") Long iaiLossId) {
        MedicinePrivate medPrivate = medPrivateService.getMedPrivate(medPrivateId);
        List<IaiIntoDetail> iaiDetailList = iaiIntoDetailService.findByMedicine(medPrivate);

        Collections.sort(iaiDetailList, (o1, o2) -> {
            Long flag = DateUtils.compareTimeGetMillis(o1.getValidityDate(), o2.getValidityDate());
            if (flag > 0) {
                return -1;
            } else if (flag == 0) {
                return 0;
            } else {
                return 1;
            }
        });

        Map<Long, String> companyMap = Maps.newHashMap();
        Iterator<IaiIntoDetail> iter = iaiDetailList.iterator();
        while (iter.hasNext()) {
            IaiIntoDetail temp = iter.next();
            Company com = companyService.getCompany(temp.getCompanyId());
            if (!companyMap.containsKey(com.getId())) {
                companyMap.put(com.getId(), com.getName());
            }
        }

        //找到第一个药厂的有效列表
        Long firstCompanyId = null;
        for (Map.Entry<Long, String> entry : companyMap.entrySet()) {
            firstCompanyId = entry.getKey();
            if (firstCompanyId != null && firstCompanyId != -1) {
                break;
            }
        }
        List<IaiIntoDetail> firstIaiDetails = iaiIntoDetailService.findByCompanyIdAndMedicine(firstCompanyId, medPrivate);

        model.addAttribute("iaiLoss", iaiLossService.findById(iaiLossId));
        model.addAttribute("details", firstIaiDetails);
        model.addAttribute("companyMap", companyMap);
        model.addAttribute("medPrivate", medPrivate);
        model.addAttribute("iaiDetailList", iaiDetailList);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("loginUser", PharSecuritySupport.getPharmacist());

        return "/valManage/bombBox/saveExpireMedicine";
    }

    @RequestMapping(value = "/IaiLossManage/bombBox/selectCompany")
    @ResponseBody
    public Result selectCompany(@RequestParam(value = "medPrivateId") Long medPrivateId,
                                @RequestParam(value = "companyId") Long companyId) {
        MedicinePrivate medPrivate = medPrivateService.getMedPrivate(medPrivateId);

        List<IaiIntoDetail> details = iaiIntoDetailService.findByCompanyIdAndMedicine(companyId, medPrivate);
        Collections.sort(details, (o1, o2) -> {
            Long flag = DateUtils.compareTimeGetMillis(o1.getValidityDate(), o2.getValidityDate());
            if (flag > 0) {
                return -1;
            } else if (flag == 0) {
                return 0;
            } else {
                return 1;
            }
        });

        Iterator<IaiIntoDetail> iter = details.iterator();
        StringBuilder html = new StringBuilder();
        for (int i = 0; i < details.size(); i++) {
            IaiIntoDetail detail = details.get(i);
            String detailId = "'" + detail.getId() + "'";
            String date = detail.getValidityDate().toString().substring(0, detail.getValidityDate().toString().indexOf(" "));

            if (i == 0) {
                html.append("<option value=" + detailId + "  selected='selected'>" + date + "</option>");
            } else {
                html.append("<option value=" + detailId + ">" + date + "</option>");
            }
        }

        System.out.println("=============");
        System.out.println(html.toString());
        Result result = new Result();
        result.setData(html.toString());
        result.setSuccess(true);
        return result;
    }


    @RequestMapping(value = "/validityManage/delIaiLoss")
    @ResponseBody
    public Result delIaiLoss(@RequestParam(value = "lossId") Long lossId) {

        iaiLossService.delIaiLoss(lossId);

        return Result.ok();
    }


    @RequestMapping(value = "/IaiLossManage/bombBox/saveMedTag")
    @ResponseBody
    public Result saveMedTag(@RequestParam(value = "detailId") Long detailId,
                             @RequestParam(value = "lossId") Long lossId,
                             @RequestParam(value = "lossNumber") Double lossNumber) {
        IaiIntoDetail detail = iaiIntoDetailService.findById(detailId);
        IaiLoss loss = iaiLossService.findById(lossId);
        //iaiDetail转lossDetail
        IaiLossDetail lossDetail = IaiLossDetail.copyIaiLossDetail(detail);


        //设置损耗的数量
        lossDetail.setTotalNumber(lossNumber);
        if (loss.getStatus() == IaiLoss.LossStatus.SAVE) {
            lossDetail.setDetailStatus(IaiLossDetail.DetailStatus.SAVE);
        } else {
            lossDetail.setDetailStatus(IaiLossDetail.DetailStatus.NOT_SAVE);

        }
        lossDetail.setLossType(IaiLossDetail.IaiLossType.ACTIVE);
        lossDetail.setStatus(StatusEntity.Status.Normal);
        lossDetail.setIaiLoss(loss);
        iaiLossDetailService.save(lossDetail);

        return Result.ok();
    }

    @RequestMapping(value = "/IaiLossManage/bombBox/selectLossItem")
    public String selectLossItem(Model model, @RequestParam(value = "lossDetailId") Long lossDetailId,
                                 @RequestParam(value = "medPrivateId") Long medPrivateId,
                                 @RequestParam(value = "lossId") Long lossId) {
        IaiLossDetail lossDetail = iaiLossDetailService.findById(lossDetailId);
        IaiLoss loss = iaiLossService.findById(lossId);
        MedicinePrivate medPrivate = medPrivateService.getMedPrivate(medPrivateId);
        Company company = companyService.getCompany(lossDetail.getCompanyId());


        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("lossDetail", lossDetail);
        model.addAttribute("loss", loss);
        model.addAttribute("medPrivate", medPrivate);
        model.addAttribute("company", company);
        model.addAttribute("loginUser", PharSecuritySupport.getPharmacist());
        return "/valManage/bombBox/selectLossDetail";
    }

    @RequestMapping(value = "/IaiLossManage/bombBox/editLossItem")
    @ResponseBody
    public Result editLossItem(@RequestParam(value = "lossDetailId") Long lossDetailId,
                               @RequestParam(value = "lossNumber") Double lossNumber) {
        IaiLossDetail lossDetail = iaiLossDetailService.findById(lossDetailId);
        lossDetail.setTotalNumber(lossNumber);
        iaiLossDetailService.save(lossDetail);
        return Result.ok();
    }

    @RequestMapping(value = "/IaiLossManage/bombBox/delLossItem")
    @ResponseBody
    public Result delLossItem(@RequestParam(value = "lossDetailId") Long lossDetailId) {

        iaiLossDetailService.delIaiLossDetail(lossDetailId);

        return Result.ok();
    }

    @RequestMapping(value = "/IaiLossManage/saveLoss")
    @ResponseBody
    public Result saveLoss(@RequestParam(value = "createDate") Date createDate,
                           @RequestParam(value = "whoCreate") String whoCreate,
                           @RequestParam(value = "remark") String remark,
                           @RequestParam(value = "iaiLossId") Long lossId) {
        //设置损耗单
        IaiLoss loss = iaiLossService.findById(lossId);
        loss.setCreateDate(createDate);
        loss.setWhoCreate(whoCreate);
        loss.setRemark(remark);
        loss.setStatus(IaiLoss.LossStatus.SAVE);
        Double allMoney = new Double(0.00);
        //设置损耗单的药品
        List<IaiLossDetail> lossDetailList = loss.getIaiLossDetailList();
        Iterator<IaiLossDetail> iter = lossDetailList.iterator();
        while (iter.hasNext()) {
            IaiLossDetail lossDetail = iter.next();
            lossDetail.setDetailStatus(IaiLossDetail.DetailStatus.SAVE);
            allMoney = allMoney + Optional.ofNullable(lossDetail.getTotalNumber()).orElse(0.00D) * Optional.ofNullable(lossDetail.getBayingPrice()).orElse(0.00D);
            iaiLossDetailService.save(lossDetail);

            //找到入库单，减去损耗单数量
            /*if(lossDetail.getIaiIntoDetailId()!=null){
                IaiIntoDetail detail = iaiIntoDetailService.findById(lossDetail.getIaiIntoDetailId());
                //损耗单的药品时正常的
                //#FangXB
                if(detail!=null){
                    detail.setTotalNumber(detail.getTotalNumber()-lossDetail.getTotalNumber());
                    iaiIntoDetailService.save(detail);
                }

            }*/

        }
        //保存loss
        loss.setTotalMoney(allMoney);
        iaiLossService.save(loss);

        return Result.ok();
    }



    
}
