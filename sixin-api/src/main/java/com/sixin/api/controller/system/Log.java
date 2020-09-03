package com.sixin.api.controller.system;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.system.service.ISysLogininforService;
import com.sixin.system.service.ISysOperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张超 teavamc
 * @Description: 一些系统记录的接口
 * @ClassName Log
 * @date 2019/1/31 16:02
 **/
@RequestMapping("/api/sys_log")
@RestController
@Api(value = "一些系统记录的接口")
@CrossOrigin
public class Log {

    @Autowired
    private ISysLogininforService logininforService;

    @Autowired
    private ISysOperLogService sysOperLogService;

    /**
        * 此接口能够展示最近的系统登录记录信息
        * @author 张超 teavamc
        * @date 2019/1/31
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @GetMapping(value = "/l_log")
    @CrossOrigin
    @ApiOperation(value = "此接口能够展示最近的系统登录记录信息")
    public RongApiRes login_log(){
        return RongApiService.get_list(logininforService.selectLoginLogforIndex());
    }


    /**
     * 此接口能够展示最近的操作记录信息
     * @author 张超 teavamc
     * @date 2019/1/31
     * @param []
     * @return com.sixin.api.domain.RongApiRes
     */
    @GetMapping(value = "/O_log")
    @CrossOrigin
    @ApiOperation(value = "此接口能够展示最近的操作记录信息")
    public RongApiRes oper_log(){
        return RongApiService.get_list(sysOperLogService.selectOperLogforIndex());
    }



    /**
     * 获取最近一个月操作次数最多的五个人名单
     * @author 张超 teavamc
     * @date 2019/1/31
     * @param []
     * @return com.sixin.api.domain.RongApiRes
     */
    @GetMapping(value = "/CountOperLogMonth")
    @CrossOrigin
    @ApiOperation(value = "获取最近一个月操作次数最多的五个人名单")
    public RongApiRes CountLogDescForMonth(){
        return RongApiService.get_list(sysOperLogService.CountLogDescForMonth());
    }

    /**
     * 获取最近一个月内登陆次数最多的名单
     * @author 张超 teavamc
     * @date 2019/1/31
     * @param []
     * @return com.sixin.api.domain.RongApiRes
     */
    @GetMapping(value = "/CountLoLogDescMonth")
    @CrossOrigin
    @ApiOperation(value = "获取最近一个月内登陆次数最多最多的五个人名单")
    public RongApiRes CountLoLogDescMonth(){
        return RongApiService.get_list(logininforService.CountLoLogDescMonth());
    }

    /**
     * 获取前五条热门登陆地点
     * @author 张超 teavamc
     * @date 2019/1/31
     * @param []
     * @return com.sixin.api.domain.RongApiRes
     */
    @GetMapping(value = "/CountHotLocal")
    @CrossOrigin
    @ApiOperation(value = "获取前五条热门登陆地点")
    public RongApiRes CountHotLocal(){
        return RongApiService.get_list(logininforService.CountHotLocal());
    }

    /**
     * 获取所有登陆地点
     * @author 张超 teavamc
     * @date 2019/1/31
     * @param []
     * @return com.sixin.api.domain.RongApiRes
     */
    @GetMapping(value = "/CountLocal")
    @CrossOrigin
    @ApiOperation(value = "获取所有登陆地点")
    public RongApiRes CountLocal(){
        return RongApiService.get_list(logininforService.CountLocal());
    }

    /**
     * 获取最近创建的三个用户
     * @author 张超 teavamc
     * @date 2019/1/31
     * @param []
     * @return com.sixin.api.domain.RongApiRes
     */
    @GetMapping(value = "/recentCreatUser")
    @CrossOrigin
    @ApiOperation(value = "获取最近创建的三个用户")
    public RongApiRes recentCreatUser(){
        return RongApiService.get_list(logininforService.recentCreatUser());
    }

}
