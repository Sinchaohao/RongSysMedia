package com.sixin.api.controller.iot;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.iot.domain.Water;
import com.sixin.iot.service.IWaterpannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:控制台物联网数据水质数据
 * @Date:
 */
@RestController
@RequestMapping("/api/waterpannel")
@CrossOrigin
@Api(value = "土壤数据 - 统计计数类接口")
public class WaterCount {
    @Autowired
    private IWaterpannelService waterpannelService;

    @CrossOrigin
    @PostMapping("/data")
    @ApiOperation(value = "按照时间顺序统计近期土壤数据")
    public RongApiRes WaterData(String deptId){
        List<Water> waterdata=waterpannelService.WaterData(deptId);
        RongApiRes test = RongApiService.get_list(waterdata);
        return test;
    }

    @CrossOrigin
    @PostMapping("/installcount")
    @ApiOperation(value = "按照时间顺序统计近期土壤数据")
    public RongApiRes Installcount(String deptId){
        List<Water> installcount=waterpannelService.ImeiInstallcount(deptId);
        RongApiRes test = RongApiService.get_list(installcount);
        return test;
    }
}
