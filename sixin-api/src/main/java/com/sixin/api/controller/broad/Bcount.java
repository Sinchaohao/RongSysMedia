package com.sixin.api.controller.broad;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;

import com.sixin.broad.domain.Bindex;
import com.sixin.broad.service.IManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 张超 teavamc
 * @Description:应急广播的数据统计接口
 * @ClassName Count
 * @date 2019/1/26 10:31
 **/
@RestController
@RequestMapping("/api/bcount")
@CrossOrigin
@Api(value = "应急广播的数据统计接口")
public class Bcount {

    @Autowired
    private IManagementService managementService;

    /**
        * 返回首页需要的统计数据
        * @author 张超 teavamc
        * @date 2019/1/26
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */

    @CrossOrigin
    @PostMapping("/bindex")
    @ApiOperation(value = "返回首页需要的统计数据")
    public RongApiRes bindex(String aid){
        Bindex test=managementService.count(aid);
        return RongApiService.get_bean(test);
    }

}
