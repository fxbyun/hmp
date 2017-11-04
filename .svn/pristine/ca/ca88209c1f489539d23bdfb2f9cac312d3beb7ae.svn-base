package com.qiaobei.hmp.web;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Card;
import com.qiaobei.hmp.modules.entity.ConversionApply;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.service.CardService;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.EnumMap;

@Controller
@RequestMapping(value = "/card/")
public class CardController extends BaseController {

    private static final EnumMap<Card.Status, String> CARD_STATUS = Maps.newEnumMap(Card.Status.class);
    private static Logger logger = LoggerFactory.getLogger(CardController.class);

    static {
        CARD_STATUS.put(ConversionApply.Status.Normal, "未使用");
        CARD_STATUS.put(ConversionApply.Status.Used, "已使用");
    }

    @Autowired
    private CardService cardService;
    @Autowired
    private PatientService patientService;

    /**
     * 卡列表页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String cardList(Model model) {
        Page<Card> page = cardService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null, null, null);
        model.addAttribute("page", page);
        model.addAttribute("cardStatus", CARD_STATUS);
        return "card/list";
    }
    /**
     * 卡列表页
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String cardList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(required = false) String cardNo,
                           @RequestParam(required = false) Card.Status status,
                           @RequestParam(required = false) String udid, Model model) {
        Page<Card> page = cardService.findPage(Utils.buildPageRequest(pageNo, Constants.PAGZ_SIZE), cardNo, udid, status);
        model.addAttribute("page", page);
        Long total = cardService.count();
        Long used = patientService.count();
        model.addAttribute("total", total);
        model.addAttribute("used", used);
        model.addAttribute("cardStatus", CARD_STATUS);
        model.addAttribute("cardNo", cardNo);
        model.addAttribute("udid", udid);
        model.addAttribute("status", status);
        return "card/list";
    }

    /**
     * 添加卡页
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String cardAdd(Model model) {
        model.addAttribute("card", new Card());
        return "card/add";
    }

    /**
     * 卡详情页
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String cardView(@PathVariable Long id, Model model) {
        Card card = cardService.getCard(id);
        Patient p = null;
        if (card.getStatus() == Card.Status.Used) {
            p = patientService.getPatientByUid(card.getCardNo());
        }
        model.addAttribute("card", card);
        model.addAttribute("patient", p);
        return "card/view";
    }

    /**
     * 删除卡
     */
    @RequestMapping(value = "del/{id}", method = RequestMethod.GET)
    public String cardDel(@PathVariable Long id) {
        cardService.delCard(id);
        return "redirect:/card/list";
    }

    /**
     * 保存卡
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String cardSave(@Valid @ModelAttribute Card card, Model model) {
        try {
            if (StringUtils.isEmpty(card.getCardNo()) ||
                    StringUtils.isEmpty(card.getUdid())) {
                model.addAttribute("error", "请输入完整信息！");
            } else {
                Long no = Long.parseLong(card.getCardNo());
                cardService.saveCard(card);
                card.setCardNo(no + 1 + "");
                model.addAttribute("msg", "卡信息保存成功。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "卡号或识别码已存在！");
        }
        model.addAttribute("card", card);
        return "card/add";
    }
}
