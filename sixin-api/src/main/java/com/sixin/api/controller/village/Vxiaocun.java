package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.village.domain.*;
import com.sixin.village.service.IVareaService;
import com.sixin.village.service.IVillagefamilyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/xiaocun")
@CrossOrigin
@Api(value = "村务模块 - 小村家事")
public class Vxiaocun extends BaseController {
    @Autowired
    private IVillagefamilyService VillagefamilyService;
    @Autowired
    private IVareaService vareaService;

    //小村家事 调用业务逻辑处理接口方法，并封装返回值
    @GetMapping("/vilfamily_all")
    @CrossOrigin
    @ApiOperation(value = "返回小村家事项目")
    public RongApiRes searchProAll (Villagefamily villagefamily)
    {
        return RongApiService.get_list(VillagefamilyService.selectvillagefamilylist(villagefamily));
    }

}
