package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.domain.Organization;
import com.sixin.broad.service.IOrganizationService;
import com.sixin.village.domain.Energyshow;
import com.sixin.village.service.IEnergyshowService;
import com.sixin.village.service.IWeatherdataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 村务宝典接口 控制层
 * 物联网管理
 * @author hfz
 * @date 2020-01-04
 */
@RestController
@RequestMapping("/api/iotmanagement")
@CrossOrigin
@Api(value = "村务宝典 - 物联网管理API类")
public class Iotmanagement {
    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private IEnergyshowService energyshowService;
    @Autowired
    private IWeatherdataService weatherdataService;


    @GetMapping("/terminalschange")
    @CrossOrigin
    @ApiOperation(value = "应急广播参数修改列表")
    public RongApiRes searchterminalslist(Organization organization)
    {
        return RongApiService.get_list(organizationService.selectOrganizationList(organization));
    }

    /**
     * 能源管理终端采集信息 控制层
     *
     * @author 薛莎莎
     * @date 2019-11-24
     */
    @GetMapping("/energyshow")
    @CrossOrigin
    @ApiOperation(value = "获取终端能源监测列表及监测参数修改")
    public RongApiRes list(Energyshow energyshow)
    {
        return RongApiService.get_list(energyshowService.selectEnergyshowListByAll(energyshow));
    }


    /**
     * 查询天气信息采集列表
     */
    @CrossOrigin
    @GetMapping("/weatherdatalist")
    @ApiOperation(value = "查询天气信息采集列表")
    public RongApiRes list(String userid)
    {

        return RongApiService.get_list(weatherdataService.selectWeatherdataList(userid));
    }
}
