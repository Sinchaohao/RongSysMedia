package com.sixin.web.controller.system;

import com.sixin.framework.web.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/system/media")
@Controller
public class SysMediaController extends BaseController {
    private String prefix = "system/media";
//    @RequiresPermissions("system:media:view")
//    @GetMapping()
//    public String user()
//    {
//        return prefix + "/media";
//    }

    private String rtmpAddress = "rtmp://114.67.88.76:1936/live";
    @RequiresPermissions("system:media:view")
    @GetMapping()
    public String mediaview(ModelMap mmap){
        //传给前台流媒体地址 传给 /streamlive 页面
        mmap.put("rtmpAddress",rtmpAddress);
        return prefix + "/media";
    }
}
