package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.service.ErrorLogService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.utils.Collections3;

import java.util.Date;
import java.util.List;

@Controller
public class SuggestController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SuggestController.class);

    @Autowired
    private SuggestService suggestService;
    @Autowired
    private DiagnosisTagService diagnosisTagService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MsgMoneyService msgMoneyService;
    @Autowired
    private ErrorLogService errorLogService;

    /**
     * 建议列表页
     */
    @RequestMapping(value = "/suggest/list", method = RequestMethod.GET)
    public String SuggestList(Model model) {
        Page<Suggest> page = suggestService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null, null);
        model.addAttribute("page", page);
        return "suggest/list";
    }

    /**
     * 建议查询
     */
    @RequestMapping(value = "/suggest/query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false) String tagName,
                        @RequestParam(required = false) String content, Model model) {
        Page<Suggest> page = suggestService.findPage(Utils.buildPageRequest(pageNo, Constants.PAGZ_SIZE), content,
                tagName);
        model.addAttribute("page", page);
        model.addAttribute("content", content);
        model.addAttribute("tagName", tagName);
        return "suggest/list";
    }

    /**
     * 新建建议页
     */
    @RequestMapping(value = "/suggest/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("suggest", new Suggest());
        return "suggest/add";
    }

    @RequestMapping(value = "/fragment/suggest/diagnosisTags", method = RequestMethod.GET)
    public String diagnosisTags(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "name", required = false) String name, Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGZ_SIZE, Sort.Direction.DESC, "frequency");
        Page<DiagnosisTag> diagnosisPage = diagnosisTagService.findPage(pageable, name);
        model.addAttribute("diagnosisPage", diagnosisPage);
        return "fragment/diagnosisSelect";
    }

    /**
     * 建议信息修改
     */
    @RequestMapping(value = "/suggest/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("suggest", suggestService.findById(id));
        return "suggest/add";
    }

    /**
     * 删除建议
     */
    @RequestMapping(value = "/suggest/del/{id}", method = RequestMethod.GET)
    public String del(@PathVariable Long id, Model model) {
        suggestService.deleteSuggest(suggestService.findById(id));
        return "redirect:/suggest/list";
    }

    /**
     * 保存建议信息
     */
    @RequestMapping(value = "/suggest/add", method = RequestMethod.POST)
    public String save(@ModelAttribute Suggest suggest,
                       @RequestParam(value = "diagnosisTagIds", required = false) List<Long> diagnosisTagIds, Model
                               model) {
        if (!Collections3.isEmpty(diagnosisTagIds)) {
            if (suggest.getId() == null) {
                for (int i = 0; i < diagnosisTagIds.size(); i++) {
                    Long tagId = diagnosisTagIds.get(i);
                    DiagnosisTag tag = diagnosisTagService.getTag(tagId);
                    Suggest s = new Suggest();
                    s.setContent(suggest.getContent());
                    s.setTagId(tagId);
                    s.setTagName(tag.getName());
                    User user = (User) SecurityUtils.getSubject().getPrincipal();
                    s.setCreateBy(user.getName());
                    s.setCreateById(user.getId());
                    s.setCreateOn(new Date());
                    suggestService.save(s);
                }
            } else {
                Long tagId = diagnosisTagIds.get(0);
                DiagnosisTag tag = diagnosisTagService.getTag(tagId);
                suggest.setTagId(tagId);
                suggest.setTagName(tag.getName());
                suggestService.save(suggest);
            }
        } else {
            model.addAttribute("error", "请选择诊断结果！");
            return "suggest/add";
        }
        model.addAttribute("suggest", suggest);
        model.addAttribute("msg", "建议保存成功。");
        return "suggest/add";
    }

    @ModelAttribute
    public void getSuggest(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("suggest", suggestService.findById(id));
        }
    }


    @RequestMapping("/doctor/recharegPage")
    public String goToRecharegPage(@RequestParam("id") Long doctorId, Model model) {
        model.addAttribute("doctor", doctorService.getDoctor(doctorId));
        return "/fragment/doctorRecharge";
    }

    @RequestMapping("/doctor/recharegSave")
    public String updateMonery(Model model,
                               @RequestParam("monery") Double monery,
                               @RequestParam("doctorId") Long doctorId) {
        MsgMoney msgMoney = msgMoneyService.getByDoctor(new Doctor(doctorId));
        if (msgMoney != null) {
            msgMoney.setDeposit(monery);
        } else {
            msgMoney = new MsgMoney();
            msgMoney.setDeposit(monery);
            msgMoney.setDoctor(new Doctor(doctorId));
        }
        ErrorLog errorLog = new ErrorLog();
        errorLog.setDoctor(new Doctor(doctorId));
        errorLog.setCreateTime(new Date());
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        errorLog.setErrorInfo("操作员" + user.getName());
        errorLog.setErrorTitle("后台修改医生余额为:" + monery);
        errorLog.setErrorUrl("后台Admin");
        errorLog.setType("后台金额修改");

        errorLogService.save(errorLog);
        msgMoneyService.updateOrSave(msgMoney);
        model.addAttribute("msg", "修改成功!");
        model.addAttribute("doctor", doctorService.getDoctor(doctorId));
        return "/fragment/doctorRecharge";
    }
}
