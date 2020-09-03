package com.sixin.api.controller.broad;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.service.IBuserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广播面板中对广播数目的统计
 *
 * @author 周博
 * @date 2019-03-21
 */

@RestController
@RequestMapping("/api/bp")
@CrossOrigin
@Api(value = "广播面板中对广播数目的统计")
public class Bpuser {
    @Autowired
    private IBuserService buserService;

    @CrossOrigin
    @GetMapping("/bu")
    @ApiOperation(value = "根据用户对广播数目的统计")
    public RongApiRes selectList() {
        List pre = buserService.selectProgramsByUser();
        RongApiRes test = RongApiService.get_list(pre);
        return test;
    }

    @CrossOrigin
    @PostMapping("/bl")
    @ApiOperation(value = "根据时长对广播数目的统计")
    public RongApiRes selectProgramsByLenth(String deptId) {
        List pre = buserService.selectProgramsByLenth(deptId);
        RongApiRes test = RongApiService.get_list(pre);
        return test;
    }

    @CrossOrigin
    @PostMapping("/bip")
    @ApiOperation(value = "根据是否公共对广播数目的统计")
    public RongApiRes selectProgramsIfPublic(String deptId) {
        List pre = buserService.selectProgramsIfPublic(deptId);
        RongApiRes test = RongApiService.get_list(pre);
        return test;
    }

    @CrossOrigin
    @PostMapping("/ptp")
    @ApiOperation(value = "广播查询记录")
    public RongApiRes selectProgramsPtp(String deptId) {
        List pre = buserService.selectProgramsPtp(deptId);
        RongApiRes test = RongApiService.get_list(pre);
        return test;
    }

}
