package com.sixin.api.controller.broad;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.domain.ProSinmanage;
import com.sixin.broad.service.IManagementService;
import com.sixin.broad.service.IProSinmanageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prosin")
@CrossOrigin
@Api(value = "应急广播模块 - 节目播出单")
public class Prosin {
    @Autowired
    private IProSinmanageService proSinmanageService ;

    @GetMapping("/warn")
    @CrossOrigin
    @ApiOperation(value = "查询紧急节目播出单列表")
    public RongApiRes selectProSinmanageListForWarning(Long userid)
    {
        return RongApiService.get_list(proSinmanageService.selectProSinmanageListForWarning(userid));
    }

    @GetMapping("/all")
    @CrossOrigin
    @ApiOperation(value = "查询节目播出单列表")
    public RongApiRes selectProSinmanageList(ProSinmanage proSinmanage)
    {
        return RongApiService.get_list(proSinmanageService.selectProSinmanageList(proSinmanage));
    }

    @GetMapping("/proOne")
    @CrossOrigin
    @ApiOperation(value = "查询节目播出单列表")
    public RongApiRes selectProSinmanagebyoneday()
    {
        return RongApiService.get_list(proSinmanageService.selectProSinmanagebyoneday());
    }

}
