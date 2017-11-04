package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.DateFilter;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Created by yanbin on 11/4/15.
 */
@Controller
public class ManageController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(ManageController.class);
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
    private MsgSendHistoryService msgSendHistoryService;
    @Resource
    private PatientService patientService;

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
        model.addAttribute("months", getMonths());
        model.addAttribute("month", LocalDate.now().withDayOfMonth(1).toDate());
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

    @RequestMapping(value = "/diagnosisStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Result diagnosisStatistics() {
        List<Statistics> list = emrService.findDiagnosisStatisticsData(new PageRequest(0, 12), SecuritySupport
                .getDoctorId());
        return Result.ok(reverse(list));
    }

    private List<Statistics> reverse(List<Statistics> list) {
        List<Statistics> newlist = new ArrayList<Statistics>();
        for (int i = list.size() - 1; i >= 0; i--) {
            newlist.add(list.get(i));
        }
        return newlist;
    }

    @RequestMapping(value = "/diseaseStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Result diseaseStatistics(@RequestParam(required = false) @DateTimeFormat(pattern = Constants
            .PATTERN_MONTH) Date month) {
        if (month == null) {
            month = LocalDate.now().withDayOfMonth(1).toDate();
        }
        List<Statistics> list = emrService.findDiseaseStatisticsData(new PageRequest(0, 10), SecuritySupport
                .getDoctorId(), month);
        return Result.ok(list);
    }

    @RequestMapping(value = "/chineseStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Result chineseStatistics(@RequestParam(required = false) @DateTimeFormat(pattern = Constants
            .PATTERN_MONTH) Date month) {
        if (month == null) {
            month = LocalDate.now().withDayOfMonth(1).toDate();
        }
        List<Statistics> list = emrService.findChineseStatisticsData(new PageRequest(0, 12), SecuritySupport
                .getDoctorId(), month);
        return Result.ok(list);
    }

    @RequestMapping(value = "/westernStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Result westernStatistics(@RequestParam(required = false) @DateTimeFormat(pattern = Constants
            .PATTERN_MONTH) Date month) {
        if (month == null) {
            month = LocalDate.now().withDayOfMonth(1).toDate();
        }
        List<Statistics> list = emrService.findWesternStatisticsData(new PageRequest(0, 12), SecuritySupport
                .getDoctorId(), month);
        return Result.ok(list);
    }

    /**
     * 财富页
     */
    @RequestMapping(value = "/wealth", method = RequestMethod.GET)
    public String wealth(Model model) {
        return "wealth";
    }

    /**
     * 信息设置页
     */
    @RequestMapping(value = "/infro", method = RequestMethod.GET)
    public String infro(HttpServletRequest request, Model model) {
        Doctor doctor = doctorService.getDoctor(SecuritySupport.getDoctorId());
        DataFile permit = dataFileService.getDataFile(doctor.getId(), DataFile.Type.Avatar);
        String portraitFile = null;
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
                portraitFile = fileName;
            } catch (IOException e) {
            }
        }
        Long emrCount = emrService.getEmrCount(SecuritySupport.getDoctorId());
        model.addAttribute("average", getAverage(doctor.getIntegration(), emrCount));
        model.addAttribute("doctor", doctor);
        model.addAttribute("portraitFile", portraitFile);
        model.addAttribute("pharmacist", pharmacistService.getPharmacistByDoctor(doctor.getId()));
        return "infroSet";
    }

    private double getAverage(Integer integration, Long emrCount) {
        if (integration == null || emrCount == null) {
            return 0;
        }
        BigDecimal integrationValue = new BigDecimal(integration);
        BigDecimal count = new BigDecimal(emrCount);
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
            }
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
                            @RequestParam(required = false) MultipartFile portraitFile, Model model) {
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
        doctorService.saveDoctor(doctor, businessFile, portraitFile);
        SecuritySupport.getDoctor().setPrintType(doctor.getPrintType());
        SecuritySupport.getDoctor().setAutoSend(doctor.getAutoSend());
        SecuritySupport.getDoctor().setAutoSendDay(doctor.getAutoSendDay());
        SecuritySupport.getDoctor().setMobile(doctor.getMobile());
        SecuritySupport.getDoctor().setPrintModel(doctor.getPrintModel());
        return "redirect:/infro";
    }

    private boolean checkFileType(String fileName) {
        String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if ("jpg".equals(fileEnd)) {
            return true;
        }
        return false;
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
    public String newPhar(Model model) {
        String account = Utils.random(8);
        account = checkAccount(account);
        String pwd = account.substring(2);
        Pharmacist phar = new Pharmacist();
        phar.setAccount(account);
        byte[] salt = Digests.generateSalt(Constants.PASSWORD_SALT_SIZE);
        phar.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(pwd.getBytes(), salt, Constants.PASSWORD_HASH_INTERATIONS);
        phar.setPassword(Encodes.encodeHex(hashPassword));
        phar.setDoctorId(SecuritySupport.getDoctorId());
        pharmacistService.savePharmacist(phar);
        return "redirect:/infro";
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
    public String unbind(@PathVariable Long id, Model model) {
        pharmacistService.delPharmacist(id);
        return "redirect:/infro";
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
        return "admissDetail";
    }

    @RequestMapping(value = "/admissDetail/query", method = RequestMethod.POST)
    public String admissDetailQuery(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                                    @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate,
                                    Model model) {
        DateFilter dateFilter;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        } else {
            dateFilter = DateFilter.withStartPlusMonths(-1);
        }
        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(page, Constants.DIATELS_PAGE_SIZE, Sort.Direction.DESC, "id");
        model.addAttribute("detailsPage", emrService.findDiseaseDiatels(pageable, SecuritySupport.getDoctorId(),
                dateFilter));
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
        return "westernDetail";
    }

    @RequestMapping(value = "/westernDetail/query", method = RequestMethod.POST)
    public String westernDetailQuery(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                                     @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate,
                                     Model model) {
        DateFilter dateFilter;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        } else {
            dateFilter = DateFilter.withStartPlusMonths(-1);
        }
        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(page, Constants.DIATELS_PAGE_SIZE, Sort.Direction.DESC, "id");
        model.addAttribute("detailsPage", emrService.findWesternDiatels(pageable, SecuritySupport.getDoctorId(),
                dateFilter));
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
        return "chineseDetail";
    }

    @RequestMapping(value = "/chineseDetail/query", method = RequestMethod.POST)
    public String chineseDetailQuery(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                                     @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate,
                                     Model model) {
        DateFilter dateFilter;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        } else {
            dateFilter = DateFilter.withStartPlusMonths(-1);
        }
        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(page, Constants.DIATELS_PAGE_SIZE, Sort.Direction.DESC, "id");
        model.addAttribute("detailsPage", emrService.findChineseDiatels(pageable, SecuritySupport.getDoctorId(),
                dateFilter));
        return "chineseDetail";
    }

    @RequestMapping(value = "/fragment/doctor/updatePwd", method = RequestMethod.GET)
    public String updatePwd() {
        return "fragment/updatePwd";
    }

    @RequestMapping(value = "/fragment/doctor/updatePwd", method = RequestMethod.POST)
    public String savePwd(@RequestParam String oldPwd,
                          @RequestParam String newPwd, Model model) {
        Doctor doctor = (Doctor) SecuritySupport.getDoctor();
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
}
