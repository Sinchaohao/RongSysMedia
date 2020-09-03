package com.sixin.api.controller.iot;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.iot.domain.SecurityData;
import com.sixin.iot.service.ISecurityDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:控制台物联网数据安防数据
 * @Date:
 */
@RestController
@RequestMapping("/api/sec")
@CrossOrigin
@Api(value = "根据时间和地区对安防数目的统计")
public class SecCount {
    @Autowired
    private ISecurityDataService securityDataService;

    @PostMapping("/installbytime")
    @ApiOperation(value = "根据安装时间和地区对安防数目的统计")
    public RongApiRes InstallByTime(String deptId) {
        List<SecurityData> installtime=securityDataService.InstallByTimeList(deptId);
        RongApiRes test = RongApiService.get_list(installtime);
        return test;
    }

    @PostMapping("/endbytime")
    @ApiOperation(value = "根据安装时间和地区对安防数目的统计")
    public RongApiRes EndByTime(String deptId) {
        List<SecurityData> endtime=securityDataService.EndByTime(deptId);
        RongApiRes test = RongApiService.get_list(endtime);
        return test;
    }

    @PostMapping("/isdelflag")
    @ApiOperation(value = "根据安装时间和地区对安防数目的统计")
    public RongApiRes IsDelflag(String deptId) {
        List<SecurityData> secStatus=securityDataService.isdelflag(deptId);
        RongApiRes test = RongApiService.get_list(secStatus);
        return test;
    }
}
