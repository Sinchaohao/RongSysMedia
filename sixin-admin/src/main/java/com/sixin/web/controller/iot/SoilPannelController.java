package com.sixin.web.controller.iot;

import com.sixin.framework.web.base.BaseController;
import com.sixin.common.page.TableDataInfo;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.service.ISoilPannelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Controller
@RequestMapping("/iot/soilpannel")
public class SoilPannelController extends BaseController {
    private String prefix = "iot/soilpannel";
    @Autowired
    private ISoilPannelService soilPannelService;


    @RequiresPermissions("iot:soilpannel:view")
    @GetMapping()
    public String soilpannel(){
        return prefix + "/soilpannel";
    }



    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Environl environl)
    {
        startPage();
        List<Environl> list = soilPannelService.selectSoilList(environl);
        return getDataTable(list);
    }

}
