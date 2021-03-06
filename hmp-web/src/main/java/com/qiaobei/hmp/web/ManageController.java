package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.ImageWallService;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.*;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/4/15
 * Time 13:39
 */
@Controller
public class ManageController extends ConstantsController {
    @Resource
    private DataFileService dataFileService;
    @Resource
    private DoctorService doctorService;
    @Resource
    private PharmacistService pharmacistService;
    @Resource
    private EmrService emrService;
    @Resource
    private NationService nationService;

    @Resource
    private ImageWallService imageWallService;

    @ModelAttribute("genderMap")
    private EnumMap<Gender, String> genderMap(HttpServletRequest request) {
        return GENDER_MAP;
    }

    @ModelAttribute("medicineUnits")
    private EnumMap<Medicine.Unit, String> medicineUnits(HttpServletRequest request) {
        return MEDICINE_UNITS;
    }

    @ModelAttribute("medicineNeedAlonePrintLists")
    private List<String> medicineNeedAlonePrint(HttpServletRequest request) {
        return MEDICINE_USE_MODE__NEED_ALONE_PRINT_LIST;
    }

    /**
     * 门诊管理页
     */
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage(Model model) {
        DateTime dt = new DateTime();
        int month = dt.getMonthOfYear();
        Long doctorId = SecuritySupport.getDoctorId();
        //患者总数
        model.addAttribute("opCount", emrService.getPatientCount(doctorId));
        //当月患者数
        model.addAttribute("opCountForMonth", emrService.getPatientCountByCurrentMonth(doctorId));
        //当前月
        model.addAttribute("currentMonth", month);
        Doctor doctor = doctorService.getDoctor(SecuritySupport.getDoctorId());
        model.addAttribute("doctor", doctor);
        model.addAttribute("isExt", LoadStaticFile.Config_Addr.contains(doctor.getProvince()));
        model.addAttribute("months", getMonths());
        model.addAttribute("month", LocalDate.now().withDayOfMonth(1).toDate());

        //
        List<Doctor> subDoctorList = doctorService.findSubDoctor(SecuritySupport.getDoctor());
        if (SecuritySupport.getDoctor().getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            subDoctorList.add(SecuritySupport.getDoctor());
        }
        model.addAttribute("subDoctorList", subDoctorList);


        return "manage";
    }

    private List<Date> getMonths() {
        List<Date> months = Lists.newArrayList();
        LocalDate curr = LocalDate.now().withDayOfMonth(1);
        for (int i = 11; i >= 0; i--) {
            months.add(curr.plusMonths(-i).toDate());
        }
        return Collections.unmodifiableList(months);
    }


    /**
     * 接诊数统计数据
     */
    @RequestMapping(value = "/diagnosisStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Result diagnosisStatistics(@RequestParam(value = "doctorId", required = false) Long doctorId) {

        if (doctorId != null && doctorId == 0) {
            doctorId = null;
        }
        List<Doctor> doctorList = doctorService.getDoctorOrSubDoctor(doctorId);

        List<Statistics> list1 = emrService.findDiagnosisStatisticsData(new PageRequest(0, 12), doctorList);


        return Result.ok(reverse(list1));
    }

    private List<Statistics> reverse(List<Statistics> list) {
        List<Statistics> newlist = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            newlist.add(list.get(i));
        }
        return newlist;
    }

    @RequestMapping(value = "/diseaseStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Result diseaseStatistics(@RequestParam(required = false) @DateTimeFormat(pattern = Constants
            .PATTERN_MONTH) Date month, @RequestParam(value = "doctorId", required = false) Long doctorId) {
        if (month == null) {
            month = LocalDate.now().withDayOfMonth(1).toDate();
        }
        List<Long> doctorList = Lists.newArrayList();

        if (doctorId != null && doctorId == 0) {
            doctorId = null;
        }

        doctorService.getDoctorOrSubDoctor(doctorId).forEach(doctor -> doctorList.add(doctor.getId()));

        /*List<Statistics> list = emrService.findDiseaseStatisticsData(new PageRequest(0, 10), SecuritySupport
                .getDoctorId(), month);*/

        List<Statistics> list = emrService.findDiseaseStatisticsData(new PageRequest(0, 10), doctorList, month);

        return Result.ok(list);
    }

    @RequestMapping(value = "/chineseStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Result chineseStatistics(@RequestParam(required = false) @DateTimeFormat(pattern = Constants
            .PATTERN_MONTH) Date month, @RequestParam(value = "doctorId", required = false) Long doctorId) {
        if (month == null) {
            month = LocalDate.now().withDayOfMonth(1).toDate();
        }

        if (doctorId != null && doctorId == 0) {
            doctorId = null;
        }

        List<Long> doctorList = Lists.newArrayList();

        doctorService.getDoctorOrSubDoctor(doctorId).forEach(doctor -> doctorList.add(doctor.getId()));

        /*List<Statistics> list = emrService.findChineseStatisticsData(new PageRequest(0, 12), SecuritySupport
                .getDoctorId(), month);*/
        List<Statistics> list = emrService.findChineseStatisticsData(new PageRequest(0, 12), doctorList, month);


        return Result.ok(list);
    }

    @RequestMapping(value = "/westernStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Result westernStatistics(@RequestParam(required = false) @DateTimeFormat(pattern = Constants
            .PATTERN_MONTH) Date month, @RequestParam(value = "doctorId", required = false) Long doctorId) {
        if (month == null) {
            month = LocalDate.now().withDayOfMonth(1).toDate();
        }

        if (doctorId != null && doctorId == 0) {
            doctorId = null;
        }


        List<Long> doctorList = Lists.newArrayList();

        doctorService.getDoctorOrSubDoctor(doctorId).forEach(doctor -> doctorList.add(doctor.getId()));

        /*List<Statistics> list = emrService.findWesternStatisticsData(new PageRequest(0, 12), SecuritySupport
                .getDoctorId(), month);*/

        List<Statistics> list = emrService.findWesternStatisticsData(new PageRequest(0, 12), doctorList, month);

        return Result.ok(list);
    }

    /**
     * 财富页
     */
    @RequestMapping(value = "/wealth", method = RequestMethod.GET)
    public String wealth() {
        return "wealth";
    }

    /**
     * 信息设置页
     */
    @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
    @RequestMapping(value = "/infro", method = RequestMethod.GET)
    public String infro(HttpServletRequest request, @RequestParam(value = "CSMobile", required = false) Boolean CSMobile, Model model) {
        Doctor doctor = doctorService.getDoctor(SecuritySupport.getDoctorId());
        if (CSMobile != null && CSMobile) {
            String msg = "本次修改手机号为" + SecuritySupport.getDoctor().getMobile() + "下次登录账号为当前手机号，请在信息设置及时修改密码，以免忘记。";
            model.addAttribute("msg", msg);
        }
        DataFile permit = dataFileService.getDataFile(doctor.getId(), DataFile.Type.Avatar);
        String portraitFile = null;
        if (permit != null) {
            byte[] content = permit.getContent();
            String fileName = permit.getFileName();
//            ServletContext sc = request.getSession().getServletContext();
            ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
            String dir = sc.getRealPath("/temp");
            try {
                File f = new File(dir + "/" + fileName);
                if (f.exists()) {
                    f.delete();
                }
                FileUtils.writeByteArrayToFile(f, content);
                portraitFile = fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //诊所的形象墙
        List<ImageWall> imageWallList = imageWallService.findByDoctorId(doctor);
        if (CollectionUtils.isNotEmpty(imageWallList)) {
            List<String> imageUrlList = imageWallService.getImageUrlList(imageWallList);
            model.addAttribute("imageUrlList", imageUrlList);
        } else {
            model.addAttribute("imageUrlList", null);
        }


        Long emrCount = emrService.getEmrCount(SecuritySupport.getDoctorId());
        model.addAttribute("average", getAverage(doctor.getIntegration(), emrCount));
        model.addAttribute("doctor", doctor);
        model.addAttribute("portraitFile", portraitFile);
        List<Pharmacist> pharmacists = pharmacistService.getPharmacistByDoctor(doctor.getId());
        model.addAttribute("pharmacist", pharmacists.stream().filter(pharmacist -> pharmacist.getPersonType() != Pharmacist.PersonType.Nurse).collect(Collectors.toList()));
        model.addAttribute("nurseList", pharmacists.stream().filter(pharmacist -> pharmacist.getPersonType() == Pharmacist.PersonType.Nurse).collect(Collectors.toList()));
        return "infroSet";
    }

    private double getAverage(Integer integration, Long emrCount) {
        if (integration == null || emrCount == null) {
            return 0;
        }
        BigDecimal integrationValue = new BigDecimal(integration);
        BigDecimal count = new BigDecimal(emrCount);
        if(count.doubleValue()==0){
            return 0;
        }
        BigDecimal average = integrationValue.divide(count, 1, BigDecimal.ROUND_HALF_UP);
        return average.doubleValue();
    }

    /**
     * 修改信息页
     */
    @RequestMapping(value = "/infroChange/{id}", method = RequestMethod.GET)
    public String infroChange(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        Doctor doctor = doctorService.getDoctor(id);
        model.addAttribute("doctor", doctor);
        DataFile permit = dataFileService.getDataFile(id, DataFile.Type.License);
        String businessFileName = null;
        if (permit != null) {
            byte[] content = permit.getContent();
            String fileName = permit.getFileName();
            ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
            String dir = sc.getRealPath("/temp");
            try {
                File f = new File(dir + "/" + fileName);
                if (f.exists()) {
                    f.delete();
                }
                FileUtils.writeByteArrayToFile(f, content);
                businessFileName = fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("businessFileName", businessFileName);
        DataFile avatar = dataFileService.getDataFile(id, DataFile.Type.Avatar);
        String portraitFile = null;
        if (avatar != null) {
            byte[] content = avatar.getContent();
            String fileName = avatar.getFileName();
            ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
            String dir = sc.getRealPath("/temp");
            try {
                File f = new File(dir + "/" + fileName);
                if (f.exists()) {
                    f.delete();
                }
                FileUtils.writeByteArrayToFile(f, content);
                portraitFile = fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //诊所的形象墙
        List<ImageWall> imageWallList = imageWallService.findByDoctorId(doctor);
        if (CollectionUtils.isNotEmpty(imageWallList)) {
            model.addAttribute("imageWallList", imageWallList);
        } else {
            model.addAttribute("imageUrlList", null);
        }


        model.addAttribute("portraitFile", portraitFile);
        model.addAttribute("provinceList", nationService.listNation(0));
        model.addAttribute("cityList", nationService.listNation(doctor.getProvinceNo()));
        model.addAttribute("areaList", nationService.listNation(doctor.getCityNo()));
        return "infroChange";
    }

    /**
     * 信息保存
     */
    @RequestMapping(value = "/infro/save", method = RequestMethod.POST)
    public String infroSave(@ModelAttribute Doctor doctor,
                            @RequestParam(required = false) MultipartFile businessFile,
                            @RequestParam(required = false) MultipartFile portraitFile,
                            @RequestParam(value = "imageWallFiles", required = false) List<MultipartFile> imageWallFiles,
                            Model model) {
        if (businessFile != null && !businessFile.isEmpty()) {
            if (businessFile.getSize() > 2097152) {
                model.addAttribute("error", "营业执照文件应不大于2MB！");
                return "infroChange";
            }
            if (!checkFileType(businessFile.getOriginalFilename())) {
                model.addAttribute("error", "营业执照文件请使用jpg格式的图片！");
                return "infroChange";
            }
        }


        if (portraitFile != null && !portraitFile.isEmpty()) {
            if (portraitFile.getSize() > 1048576) {
                model.addAttribute("error", "头像文件应不大于1MB！");
                return "infroChange";
            }
            if (!checkFileType(portraitFile.getOriginalFilename())) {
                model.addAttribute("error", "头像文件请使用jpg格式的图片！");
                return "infroChange";
            }
        }

        for (int i = 0; i < imageWallFiles.size(); i++) {
            if (imageWallFiles.get(i).getSize() > 2097152) {
                int index = i + 1;
                model.addAttribute("error", "形象墙的第" + index + "张图片的应不大于2MB");
                return "infroChange";
            }
        }

        boolean CSMobile = false;
        if (SecuritySupport.getDoctor().getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            if (!SecuritySupport.getDoctor().getMobile().equals(doctor.getMobile())) {
                CSMobile = true;
            }
        }

        doctorService.saveDoctor(doctor, businessFile, portraitFile);
        doctorService.saveImageWall(imageWallFiles, doctor);

        SecuritySupport.getDoctor().setPrintType(doctor.getPrintType());
        SecuritySupport.getDoctor().setAutoSend(doctor.getAutoSend());
        SecuritySupport.getDoctor().setAutoSendDay(doctor.getAutoSendDay());
        SecuritySupport.getDoctor().setMobile(doctor.getMobile());
        SecuritySupport.getDoctor().setPrintModel(doctor.getPrintModel());
        SecuritySupport.getDoctor().setAutoSendActivateMsg(doctor.getAutoSendActivateMsg());
        SecuritySupport.getDoctor().setProvince(doctor.getProvince());
        SecuritySupport.getDoctor().setProvinceNo(doctor.getProvinceNo());
        SecuritySupport.getDoctor().setDeptName(doctor.getDeptName());
        doctorService.saveDoctor(doctor);
        return "redirect:/infro?CSMobile=" + CSMobile;

    }

    private boolean checkFileType(String fileName) {
        String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return "jpg".equals(fileEnd);
    }

    @ModelAttribute
    public void getDoctor(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("doctor", doctorService.getDoctor(id));
        }
    }

    /**
     * 添加药剂师帐号
     */
    @RequestMapping(value = "/manage/new", method = RequestMethod.GET)
    public String newPhar(@RequestParam(value = "type", required = false, defaultValue = "Wecath_dispenser") Pharmacist.PersonType type
    ) {
        Doctor doctor = SecuritySupport.getDoctor();
        if (type != Pharmacist.PersonType.Sub_Doctor) {
            String account = Utils.random(8);
            account = checkAccount(account);
            String pwd = account.substring(2);
            Pharmacist phar = new Pharmacist();
            phar.setAccount(account);
            byte[] salt = Digests.generateSalt(Constants.PASSWORD_SALT_SIZE);
            phar.setSalt(Encodes.encodeHex(salt));
            byte[] hashPassword = Digests.sha1(pwd.getBytes(), salt, Constants.PASSWORD_HASH_INTERATIONS);
            phar.setPassword(Encodes.encodeHex(hashPassword));
            phar.setDoctorId(doctor.getId());
            phar.setPersonType(type);
            pharmacistService.savePharmacist(phar);
            if (type == Pharmacist.PersonType.Nurse) {
                return "redirect:/employee?personType=Nurse";
            }
        } else {
            Doctor newDcotor = new Doctor();
            newDcotor.setName("未设置");
            newDcotor.setArea(doctor.getArea());
            newDcotor.setAreaNo(doctor.getAreaNo());
            newDcotor.setDoctorType(Doctor.Doctor_Type.Sub_Doctor);
            newDcotor.setPrimaryDoctorId(doctor.getId());
            newDcotor.setAutoSend(doctor.getAutoSend());
            newDcotor.setStatus(doctor.getStatus());
            String account = Utils.random(8);
            newDcotor.setMobile(account);
            newDcotor.setVerifyOn(new Date());
            newDcotor.setPlainPassword(account.substring(2));
            newDcotor.setAutoSendActivateMsg(doctor.getAutoSendActivateMsg());
            newDcotor.setAutoSendDay(doctor.getAutoSendDay());
            newDcotor.setBusinessAddr(doctor.getBusinessAddr());
            newDcotor.setPrintModel(doctor.getPrintModel());
            newDcotor.setPrintInfo(doctor.getPrintInfo());
            newDcotor.setNeedAlonePrinTypeStrings(doctor.getNeedAlonePrinTypeStrings());
            newDcotor.setOutpatientService(doctor.getOutpatientService());
            newDcotor.setProvinceNo(doctor.getProvinceNo());
            newDcotor.setCreateOn(new Date());
            newDcotor.setCityNo(doctor.getCityNo());
            newDcotor.setLegal(doctor.getLegal());
            newDcotor.setLegalCard(doctor.getLegalCard());
            newDcotor.setClinicInfo(doctor.getClinicInfo());
            newDcotor.setBusinessLicense(doctor.getBusinessLicense());
            doctorService.saveDoctor(newDcotor, null, null);
            return "redirect:/employee?personType=Sub_Doctor";

        }
        return "redirect:/employee";
    }

    private String checkAccount(String account) {
        Pharmacist phar = pharmacistService.getPharmacistByAccount(account);
        if (phar != null) {
            account = checkAccount(Utils.random(8));
        }
        return account;
    }

    /**
     * 删除药剂师帐号
     */
    @RequestMapping(value = "/manage/unbind/{id}", method = RequestMethod.GET)
    public String unbind(
            @PathVariable Long id,
            @RequestParam(value = "personType", required = false) Pharmacist.PersonType personType) {

        if (personType == Pharmacist.PersonType.Sub_Doctor) {
            Doctor doctor = doctorService.getDoctor(id);
            if (doctor.getStatus() == StatusEntity.Status.Locked) {
                doctor.setStatus(StatusEntity.Status.Normal);
            } else {
                doctor.setStatus(StatusEntity.Status.Locked);
            }

            doctorService.saveDoctor(doctor);
            return "redirect:/employee?personType=Sub_Doctor";
        } else {
            pharmacistService.delPharmacist(id);
        }

        if (personType == Pharmacist.PersonType.Nurse) {
            return "redirect:/employee?personType=Nurse";
        }
        return "redirect:/employee";
    }

    /**
     * 病症分析 -- 详细列表
     */
    @RequestMapping(value = "/admissDetail", method = RequestMethod.GET)
    public String admissDetail(Model model) {

        DateFilter dateFilter = DateFilter.withStartPlusMonths(-1);
        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(0, Constants.DIATELS_PAGE_SIZE, Sort.Direction.DESC, "id");
        model.addAttribute("detailsPage", emrService.findDiseaseDiatels(pageable, SecuritySupport.getDoctorId(),
                dateFilter));
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        List<Doctor> subDoctorList = doctorService.findSubDoctor(SecuritySupport.getDoctor());
        model.addAttribute("subDoctorList", subDoctorList);

        return "admissDetail";
    }

    @RequestMapping(value = "/admissDetail/query", method = RequestMethod.POST)
    public String admissDetailQuery(@RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                                    @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate,
                                    @RequestParam(value = "doctorId", required = false) Long doctorId,
                                    @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                    Model model) {
        DateFilter dateFilter;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        } else {
            dateFilter = DateFilter.withStartPlusMonths(-1);
        }
        model.addAttribute("dateFilter", dateFilter);


        //该医生管理的医生账号
        List<Doctor> subDoctorList = doctorService.findSubDoctor(SecuritySupport.getDoctor());
        model.addAttribute("subDoctorList", subDoctorList);
        //当点击全部的时候，找出所有的子医生
        List<Long> doctorList = Lists.newArrayList();
        Pageable pageable = new PageRequest(page, Constants.DIATELS_PAGE_SIZE, Sort.Direction.DESC, "id");

        Page<Statistics> detailsPage;

        if (doctorId != null && doctorId == 0) {
            doctorService.getDoctorOrSubDoctor(null).forEach(doctor -> doctorList.add(doctor.getId()));
            detailsPage = emrService.findDiseaseDiatels(pageable, doctorList, dateFilter);
        } else {
            detailsPage = emrService.findDiseaseDiatels(pageable, doctorId, dateFilter);
        }
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("detailsPage", detailsPage);
        return "admissDetail";
    }

    /**
     * 西药房 -- 详细列表
     */
    @RequestMapping(value = "/westernDetail", method = RequestMethod.GET)
    public String westernDetail(Model model) {
        DateFilter dateFilter = DateFilter.withStartPlusMonths(-1);
        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(0, Constants.DIATELS_PAGE_SIZE, Sort.Direction.DESC, "id");
        model.addAttribute("detailsPage", emrService.findWesternDiatels(pageable, SecuritySupport.getDoctorId(),
                dateFilter));

        List<Doctor> subDoctorList = doctorService.findSubDoctor(SecuritySupport.getDoctor());
        model.addAttribute("subDoctorList", subDoctorList);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        return "westernDetail";
    }

    @RequestMapping(value = "/westernDetail/query", method = RequestMethod.POST)
    public String westernDetailQuery(@RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                                     @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate,
                                     @RequestParam(value = "doctorId", required = false) Long doctorId,
                                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                     Model model) {
        DateFilter dateFilter;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        } else {
            dateFilter = DateFilter.withStartPlusMonths(-1);
        }
        model.addAttribute("dateFilter", dateFilter);
        //该医生管理的医生账号
        List<Doctor> subDoctorList = doctorService.findSubDoctor(SecuritySupport.getDoctor());
        model.addAttribute("subDoctorList", subDoctorList);
        //当点击全部的时候，找出所有的子医生
        List<Long> doctorList = Lists.newArrayList();
        Pageable pageable = new PageRequest(page, Constants.DIATELS_PAGE_SIZE, Sort.Direction.DESC, "id");


        Page<Statistics> detailsPage;

        if (doctorId != null && doctorId == 0) {
            doctorService.getDoctorOrSubDoctor(null).forEach(doctor -> doctorList.add(doctor.getId()));
            detailsPage = emrService.findWesternDiatels(pageable, doctorList, dateFilter);


        } else {
            detailsPage = emrService.findWesternDiatels(pageable, doctorId, dateFilter);
        }

        model.addAttribute("detailsPage", detailsPage);
        return "westernDetail";
    }

    /**
     * 中药房 -- 详细列表
     */
    @RequestMapping(value = "/chineseDetail", method = RequestMethod.GET)
    public String chineseDetail(Model model) {
        DateFilter dateFilter = DateFilter.withStartPlusMonths(-1);
        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(0, Constants.DIATELS_PAGE_SIZE, Sort.Direction.DESC, "id");
        model.addAttribute("detailsPage", emrService.findChineseDiatels(pageable, SecuritySupport.getDoctorId(),
                dateFilter));
        List<Doctor> subDoctorList = doctorService.findSubDoctor(SecuritySupport.getDoctor());
        model.addAttribute("subDoctorList", subDoctorList);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        return "chineseDetail";
    }

    @RequestMapping(value = "/chineseDetail/query", method = RequestMethod.POST)
    public String chineseDetailQuery(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                     @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                                     @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate,
                                     @RequestParam(value = "doctorId", required = false) Long doctorId,
                                     Model model) {
        DateFilter dateFilter;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        } else {
            dateFilter = DateFilter.withStartPlusMonths(-1);
        }
        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(page, Constants.DIATELS_PAGE_SIZE, Sort.Direction.DESC, "id");
        //该医生管理的医生账号
        List<Doctor> subDoctorList = doctorService.findSubDoctor(SecuritySupport.getDoctor());
        model.addAttribute("subDoctorList", subDoctorList);
        //当点击全部的时候，找出所有的子医生
        List<Long> doctorList = Lists.newArrayList();
        Page<Statistics> detailsPage;
        if (doctorId != null && doctorId == 0) {
            doctorService.getDoctorOrSubDoctor(null).forEach(doctor -> doctorList.add(doctor.getId()));
            detailsPage = emrService.findChineseDiatels(pageable, doctorList, dateFilter);
        } else {
            detailsPage = emrService.findChineseDiatels(pageable, doctorId, dateFilter);
        }

        model.addAttribute("detailsPage", detailsPage);

        return "chineseDetail";
    }

    @RequestMapping(value = "/fragment/doctor/updatePwd", method = RequestMethod.GET)
    public String updatePwd() {
        return "fragment/updatePwd";
    }

    @RequestMapping(value = "/fragment/doctor/updatePwd", method = RequestMethod.POST)
    public String savePwd(@RequestParam String oldPwd,
                          @RequestParam String newPwd, Model model) {
        Doctor doctor = doctorService.getDoctor(SecuritySupport.getDoctor().getId());
        String salt = doctor.getSalt();
        if (!StringUtils.equals(Utils.encodePwd(oldPwd, salt), doctor.getPassword())) {
            model.addAttribute("error", "旧密码错误！");
        } else {
            doctor.setPassword(Utils.encodePwd(newPwd, salt));
            doctorService.saveDoctor(doctor, null, null);
            model.addAttribute("msg", "密码修改成功。");
        }
        return "fragment/updatePwd";
    }

    @RequestMapping(value = "/fragment/doctor/saveImageWall", method = RequestMethod.POST)
    @ResponseBody
    public Result saveImageWall(@RequestParam(value = "imageWallFiles", required = false) MultipartFile imageWallFiles) {
//        System.out.println(imageWallFiles.getSize() + "=====" + imageWallFiles.getOriginalFilename());
        if (imageWallFiles == null || imageWallFiles.getSize() == 0) {
            return Result.fail("文件为空！");
        }
        Doctor doctor = SecuritySupport.getDoctor();
        if (doctor == null) {
            System.out.println("医生为空，登录失效");
            return Result.fail("医生登录已失效，请重新登录！");
        }
        List<ImageWall> imageList = imageWallService.findByDoctorId(doctor);

        Long newImageId;
        //判断是否是第一张
        if (CollectionUtils.isEmpty(imageList)) {
            newImageId = doctorService.saveImageWall(imageWallFiles, doctor, ImageWall.ImageLevel.Cover);
        } else {
            newImageId = doctorService.saveImageWall(imageWallFiles, doctor, ImageWall.ImageLevel.NotCover);
        }
        return Result.ok(newImageId);
    }

    @RequestMapping(value = "/fragment/doctor/delImageWall")
    @ResponseBody
    public Result delImageWall(@RequestParam(value = "imageId") Long imageId) {

        Doctor doctor = SecuritySupport.getDoctor();

        if (doctor == null) {
            System.out.println("医生为空！");
            return Result.fail("医生登录失效，请重新登录！");
        }

        ImageWall image = imageWallService.findById(imageId);
        //如果删除的是封面图片的话
        if (image.getLevel() == ImageWall.ImageLevel.Cover) {
            //让最前面的图片设成封面
            ImageWall coverImage = imageWallService.findOrderImageId(doctor.getId());
            coverImage.setLevel(ImageWall.ImageLevel.Cover);
            imageWallService.save(coverImage);
        }

        boolean flag = imageWallService.deleteImageWall(imageId);
        if (!flag) {
            return Result.fail("图片已被删除！");
        }

        return Result.ok("图片删除成功！");
    }


    // TODO  员工管理 2016-10-26 16:19:08
    @RequestMapping(value = "/employee")
    public String employee(Model model,
                           @RequestParam(value = "personType", required = false, defaultValue = "Wecath_dispenser") Pharmacist.PersonType personType) {

        Doctor doctor = SecuritySupport.getDoctor();
        List<Pharmacist> pharmacists = pharmacistService.getPharmacistByDoctor(doctor.getId());
        if (personType == Pharmacist.PersonType.Wecath_dispenser) {
            model.addAttribute("list", pharmacists.stream().filter(pharmacist -> pharmacist.getPersonType() != Pharmacist.PersonType.Nurse).collect(Collectors.toList()));
        } else if (personType == Pharmacist.PersonType.Nurse) {
            model.addAttribute("list", pharmacists.stream().filter(pharmacist -> pharmacist.getPersonType() == Pharmacist.PersonType.Nurse).collect(Collectors.toList()));
        } else if (personType == Pharmacist.PersonType.Sub_Doctor && doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            model.addAttribute("list", doctorService.findSubDoctor(doctor));
        }

        model.addAttribute("personType", personType);
        model.addAttribute("doctor", doctor);
        return "employee";
    }

    @RequestMapping(value = "/setThisIdAsChiefNurse")
    @ResponseBody
    public Result setThisIdAsChiefNurse(
            @RequestParam("id") Long id,
            @RequestParam("status") Pharmacist.IsChiefNurse status
    ) {
        Pharmacist pharmacist = pharmacistService.getPharmacistById(id);
        pharmacist.setIsChiefNurse(status);
        pharmacistService.savePharmacist(pharmacist);
        return Result.ok();
    }


    @RequestMapping(value = "/initPassword", method = RequestMethod.POST)
    @ResponseBody
    public Result initPassword(@RequestParam(value = "id") Long id,
                               @RequestParam(value = "type") Doctor.Doctor_Type Sub_Doctor) {
        if (Sub_Doctor == Doctor.Doctor_Type.Sub_Doctor) {
            Doctor doctor = doctorService.getDoctor(id);
            String Mobile = doctor.getMobile();
            int n = 6;
            String pwd = Mobile.substring(Mobile.length() - n, Mobile.length());
            String salt = doctor.getSalt();
            doctor.setPassword(Utils.encodePwd(pwd, salt));
            doctorService.saveDoctor(doctor, null, null);
            System.out.println("==修改成功=");
            return Result.ok();
        } else {
            System.out.println("不是子医生");
            return Result.fail();
        }
    }

    /**
     * 根据传递的ID 和类型进行修改 子帐号或者 药剂师和护士
     */
    @RequestMapping(value = "/updateNameByTypeAndId", method = RequestMethod.POST)
    @ResponseBody
    public Result updateNameByTypeAndId(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("type") Pharmacist.PersonType personType
    ) {

        //如果不是子帐号
        if (personType != Pharmacist.PersonType.Sub_Doctor) {
            Pharmacist pharmacist = pharmacistService.getPharmacistById(id);
            pharmacist.setName(name);
            pharmacistService.update(pharmacist);
        } else {
            Doctor doctor = doctorService.getDoctor(id);
            doctor.setName(name);
            doctorService.saveDoctor(doctor);
        }

        return Result.ok();
    }


}
