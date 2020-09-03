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
 * @Description: 控制台物联网数据气象数据
 * @Date:
 */
@RestController
@RequestMapping("/api/metpannel")
@CrossOrigin
@Api(value = "环境数据 - 统计计数类接口")
public class MetCount {
    @Autowired
    private IEnvironlService environlService;

    @CrossOrigin
    @PostMapping("/data")
    @ApiOperation(value = "按照时间顺序统计近期气象数据")
    public RongApiRes metCount(String deptId){
        List<Environl> metdata=environlService.selectEnvironlListOnControl(deptId);
        RongApiRes test = RongApiService.get_list(metdata);
        return test;
    }
}
