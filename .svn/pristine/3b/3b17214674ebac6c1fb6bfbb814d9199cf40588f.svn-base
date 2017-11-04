package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Supplier;
import com.qiaobei.hmp.modules.service.SupplierService;
import com.qiaobei.hmp.support.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 供应商
 */
@Controller
public class SupplierController extends BaseController {

    @Resource
    private SupplierService supplierService;


    @RequestMapping(value = "/supplier/delSupplier")
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

}
