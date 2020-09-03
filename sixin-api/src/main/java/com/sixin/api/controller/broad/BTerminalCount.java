package com.sixin.api.controller.broad;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.service.ITerminalCountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ASUS on 2019/3/21.
 * @author 陈霞
 * 统计终端维护量
 */
@RestController
@RequestMapping("/api/terminal")
public class BTerminalCount {
    @Autowired
    private ITerminalCountService iTerminalCountService;

    @CrossOrigin
    @PostMapping("/bt")
    @ApiOperation(value = "根据时间统计终端维护量")
    public RongApiRes getAllTerminal(String deptId){
        List pre = iTerminalCountService.getAllTerminal(deptId);
        RongApiRes test = RongApiService.get_list(pre);
        return test;
    }

}
