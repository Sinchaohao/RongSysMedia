package com.sixin.api.controller.broad;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.service.IManagementService;
import com.sixin.broad.service.ITermapService;
import com.sixin.framework.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 应急广播系统的终端接口
 *
 * @author 张超
 */
@RestController
@RequestMapping("/api/gps")
@CrossOrigin
@Api(value = "应急广播模块 - 终端设备GPS坐标API类")
public class Gps extends BaseController
{
    @Autowired
    private IManagementService managementService;
    @Autowired
    private ITermapService termapService;
    /**
     * 随机查询100台终端的地理位置
     * @author 张超
     * @return RongApiRes
     */
    @GetMapping("/random")
    @CrossOrigin
    @ApiOperation(value = "随机查询100台终端的地理位置")
    public RongApiRes searchrandom()
    {
        return RongApiService.get_list(managementService.selectManagementByRandom());
    }

    /**
     * 查询全部的终端地理位置
     * @author 张超
     * @return RongApiRes
     */
    @GetMapping("/all")
    @CrossOrigin
    @ApiOperation(value = "查询全部的终端地理位置")
    public RongApiRes searchall()
    {
        return RongApiService.get_list(managementService.selectManagementAll());
    }


    @GetMapping("/iotAll")
    @CrossOrigin
    @ApiOperation(value = "查询全部的终端地理位置")
    public RongApiRes searchiotAll()
    {
        return RongApiService.get_list(termapService.getAllMapInfoForIot());
    }

}