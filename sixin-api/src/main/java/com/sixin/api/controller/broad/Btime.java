package com.sixin.api.controller.broad;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.service.IBroadTimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 根据时间和地区对广播数目的统计
 * @author 周博
 * @date 2019-01-15
 */
@RestController
@RequestMapping("/api/btime")
@CrossOrigin
@Api(value = "根据时间和地区对广播数目的统计")
public class Btime {
    @Autowired
    private IBroadTimeService broadTimeService;

    @CrossOrigin
    @PostMapping("/bt")
    @ApiOperation(value = "根据时间和地区对广播数目的统计")
    public RongApiRes selectList(String deptId) {
        List pre = broadTimeService.selectList(deptId);
        RongApiRes test = RongApiService.get_list(pre);
        return test;
    }

    @PostMapping("/bd")
    @ApiOperation(value = "根据时间和地区对广播数目的统计")
    public RongApiRes selectListByDate(String deptId) {
        List pre = broadTimeService.selectListByDate(deptId);
        RongApiRes test = RongApiService.get_list(pre);
        return test;
    }

    @PostMapping("/bds")
    @ApiOperation(value = "根据时间和地区对广播数目的统计")
    public RongApiRes selectListByScategory(String deptId) {
        List pre = broadTimeService.selectListByScategory(deptId);
        RongApiRes test = RongApiService.get_list(pre);
        return test;
    }

}
