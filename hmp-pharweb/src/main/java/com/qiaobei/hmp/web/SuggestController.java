package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Suggest;
import com.qiaobei.hmp.service.SuggestService;
import com.qiaobei.hmp.support.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SuggestController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SuggestController.class);

    @Autowired
    private SuggestService suggestService;

    @RequestMapping(value = "/suggest", method = RequestMethod.GET)
    @ResponseBody
    public Result getSuggest(@RequestParam String name, Model model) {
        List<Suggest> suggests = suggestService.findByTagName(name);
        Result result = Result.ok();
        result.setData(suggests);
        return result;
    }

}
