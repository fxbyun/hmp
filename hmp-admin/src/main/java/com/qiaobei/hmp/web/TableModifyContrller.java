package com.qiaobei.hmp.web;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.TableModify;
import com.qiaobei.hmp.modules.service.TableModifyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.EnumMap;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/28 0028
 * Time 18:02
 */
@Controller
public class TableModifyContrller {
    protected static final EnumMap<TableModify.TableModyfiType, String>
            tableModyfiTypeMap = Maps.newEnumMap(TableModify.TableModyfiType.class);

    static {
        tableModyfiTypeMap.put(TableModify.TableModyfiType.Add_Column, "增加列");
        tableModyfiTypeMap.put(TableModify.TableModyfiType.Edit_Column, "修改列");
        tableModyfiTypeMap.put(TableModify.TableModyfiType.Delete_Column, "删除列");
        tableModyfiTypeMap.put(TableModify.TableModyfiType.Add_Table, "增加表");
        tableModyfiTypeMap.put(TableModify.TableModyfiType.Edit_Table, "修改表");
        tableModyfiTypeMap.put(TableModify.TableModyfiType.Delete_Table, "删除表");
    }

    @Resource
    private TableModifyService tableModifyService;

    @ModelAttribute("tableModyfiTypeMap")
    private EnumMap<TableModify.TableModyfiType, String> tableModyfiTypeMap(HttpServletRequest request) {
        return tableModyfiTypeMap;
    }


    @RequestMapping("/table")
    public String tableList(Model model,
                            @RequestParam(value = "page", required = false, defaultValue = "0") int pageNow,
                            @RequestParam(value = "name", required = false) String tableName) {
        Page<TableModify> tableModifyPage =
                tableModifyService.findAll(tableName, new PageRequest(pageNow, 10));
        model.addAttribute("tablePage", tableModifyPage);
        return "/tableModify/index";
    }


    @RequestMapping(value = "/table/add", method = RequestMethod.GET)
    public String tableAdd(
            @ModelAttribute("tableModify") TableModify tableModify,
            @RequestParam(value = "id", required = false) Long id,
            Model model
    ) {
        if (id != null) {
            TableModify tableModify2 = tableModifyService.findById(id);
            model.addAttribute("tableModify", tableModify2);
            model.addAttribute("msg", "保存成功!");


        }
        return "/tableModify/tableAdd";
    }

    @RequestMapping(value = "/table/add", method = RequestMethod.POST)
    public String tableAddPost(
            @ModelAttribute("tableModify") TableModify tableModify,
            Model model
    ) {
        tableModify.setCreateDate(new Date());
        tableModify.setStatus(TableModify.TableModyfiType.Have_NOT_Be_Chang_On_Server);
        tableModifyService.save(tableModify);
        model.addAttribute("table", tableModify);
        return "redirect:/table/add?id=" + tableModify.getId();
    }
}
