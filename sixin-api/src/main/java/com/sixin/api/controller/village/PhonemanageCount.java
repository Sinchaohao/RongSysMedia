package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.village.domain.Phonemanage;
import com.sixin.village.service.IPhonemanageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权号码表管理信息 控制层
 *
 * @author 薛莎莎
 * @date 2019-11-24
 */
@RestController
@RequestMapping("/api/phonemanage")
@CrossOrigin
@Api(value = "授权号码表管理信息")
public class PhonemanageCount extends BaseController {
    @Autowired
    private IPhonemanageService phonemanageService;
    @CrossOrigin
    @GetMapping("/list")
    @ApiOperation(value = "查询授权号码表管理信息列表")
    public RongApiRes list(Phonemanage phonemanage)
    {
        return RongApiService.get_list(phonemanageService.selectPhonemanageList(phonemanage));
    }
}
