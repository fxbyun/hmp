package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.service.RemotePermissionService;
import com.qiaobei.hmp.modules.service.RemoteRoleService;
import com.qiaobei.hmp.modules.service.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RemoteController extends BaseController {

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemoteRoleService remoteRoleService;

    @Autowired
    private RemotePermissionService remotePermissionService;


}
