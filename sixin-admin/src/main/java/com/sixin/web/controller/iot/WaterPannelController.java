package com.sixin.web.controller.iot;

import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.iot.domain.Water;
import com.sixin.iot.service.IWaterpannelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/iot/waterpannel")
public class WaterPannelController extends BaseController {
    private String prefix = "iot/waterpannel";

    @Autowired
    private IWaterpannelService waterpannelService;
    //private WaterpannelServiceImpl waterpannelServiceImpl;

    @GetMapping()
    public   String water() {
        return prefix + "/waterpannel";
    }

    /**
     * 导出水质监测列表
     */
    /**
     * Water列表
     * @param water
     * @return
     */
    @RequiresPermissions("iot:waterpannel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Water water){
        startPage();
        List<Water> list = waterpannelService.selectWaterpannelList(water);
        return getDataTable(list);
    }
}

