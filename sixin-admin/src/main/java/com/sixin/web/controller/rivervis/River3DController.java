package com.sixin.web.controller.rivervis;

import com.sixin.framework.web.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/river3d")
public class River3DController extends BaseController
{
    private String prefix = "rivervis/sanwei";

    @RequestMapping("/scene1")
    @RequiresPermissions("river3d:scene1:view")
    @GetMapping()
    public String envData()
    {
        return prefix + "/unity1";
    }
}
