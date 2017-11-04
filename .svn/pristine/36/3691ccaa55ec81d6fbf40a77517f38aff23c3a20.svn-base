package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgRechargeDetail;
import com.qiaobei.hmp.modules.entity.PayType;
import com.qiaobei.hmp.service.MsgRechargeDetailService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.DateFilter;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Date;

/**
 * Created by Administrator on 2016/7/26 0026.
 */

@Controller
public class RechargeController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MsgRechargeDetailService msgRechargeDetailService;

    /**
     * 充值记录列表页
     */
    @RequestMapping(value = "recharge/list", method = RequestMethod.GET)
    public String list(Model model) {
        Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "updateDate");
        Page<MsgRechargeDetail> page = msgRechargeDetailService.findPage(pageable, null, null, null);
        model.addAttribute("page", page);
        return "recharge/list";
    }

    /**
     * 充值记录查询
     */
    @RequestMapping(value = "recharge/query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                        @RequestParam(required = false) String doctorName,
                        @RequestParam(required = false) Double addMoney,
                        @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                        @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate,
                        Model model) {
        DateFilter dateFilter = null;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        }

        Pageable pageable = new PageRequest(pageNo, 10, Sort.Direction.DESC, "updateDate");

        Page<MsgRechargeDetail> page = msgRechargeDetailService.findPage(pageable,
                doctorName, addMoney, dateFilter);
        model.addAttribute("page", page);
        model.addAttribute("doctorName", doctorName);
        model.addAttribute("addMoney", addMoney);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "recharge/list";
    }
}
