package com.sixin.web.controller.system;

import com.sixin.framework.web.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/system/smslive")
@Controller
public class SysSmsliveController extends BaseController {
    private String prefix = "system/smslive";
//system/smslive

    @RequiresPermissions("system:smslive:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/smslive";
    }
}
