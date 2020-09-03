package com.sixin.web.controller.iot;


import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.EngPannel;
import com.sixin.iot.service.IEngPannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Controller
@RequestMapping("/iot/engpannel")
public class EngPannelController extends BaseController {
    private String prefix = "iot/engpannel";

    @Autowired
    private IEngPannelService engPannelService;
    @GetMapping()
    public String metpannel(){
        return prefix + "/engpannel";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EngPannel engPannel){
        startPage();
        List<EngPannel> list=engPannelService.selectEnergyList(engPannel);
        return getDataTable(list);
    }

}
