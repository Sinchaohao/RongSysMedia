package com.sixin.api.controller.iot;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.service.IEnvironlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description: 控制台物联网数据土壤数据
 * @Date:
 */
@RestController
@RequestMapping("/api/soilpannel")
@CrossOrigin
@Api(value = "土壤数据 - 统计计数类接口")
public class SoilCount {
    @Autowired
    private IEnvironlService environlService;

    @CrossOrigin
    @PostMapping("/data")
    @ApiOperation(value = "按照时间顺序统计近期土壤数据")
    public RongApiRes soilCount(String deptId){
        List<Environl> soildata=environlService.selectEnvironlListOnControl(deptId);
        RongApiRes test = RongApiService.get_list(soildata);
        return test;
    }
}
