package com.sixin.api.controller.iot;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.iot.domain.Energy;
import com.sixin.iot.domain.EngPannel;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.service.IEngPannelService;
import com.sixin.iot.service.IEnvironlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description: 控制台物联网数据能源数据
 * @Date:
 */
@RestController
@RequestMapping("/api/engpannel")
@CrossOrigin
@Api(value = "能源数据 - 统计计数类接口")
public class EnergyCount {
    @Autowired
    private IEngPannelService engPannelService;

    @CrossOrigin
    @PostMapping("/data")
    @ApiOperation(value = "按照采集时间顺序统计近期能源数据")
    public RongApiRes energyCount(String addrid){
        List<EngPannel> energydata=engPannelService.selectEnergyListOnControl(addrid);
        RongApiRes test = RongApiService.get_list(energydata);
        return test;
    }
}
