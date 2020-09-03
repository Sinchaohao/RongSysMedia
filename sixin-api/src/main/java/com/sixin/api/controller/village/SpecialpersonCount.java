package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.village.domain.Specialperson;
import com.sixin.village.service.ISpecialpersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 特殊人员监控表信息 控制层
 *
 * @author 薛莎莎
 * @date 2019-12-15
 */
@RestController
@RequestMapping("/api/specialperson")
@CrossOrigin
@Api(value = "特殊人员监控表管理信息")
public class SpecialpersonCount extends BaseController {
    @Autowired
    private ISpecialpersonService specialpersonService;
    @CrossOrigin
    @GetMapping("/list")
    @ApiOperation(value = "查询特殊人员监控表管理信息列表")
    public RongApiRes list(Specialperson specialperson){
        return RongApiService.get_list(specialpersonService.selectSpecialpersonList(specialperson));
    }
}
