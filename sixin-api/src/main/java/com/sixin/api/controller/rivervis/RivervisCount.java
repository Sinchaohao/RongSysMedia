package com.sixin.api.controller.rivervis;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.rivervis.domain.Riverbytl;
import com.sixin.rivervis.service.IEnvDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 张超 teavamc
 * @Description:山洪预警 - 统计计数类接口
 * @ClassName RivervisCount
 * @date 2019/3/10 13:35
 **/
@RestController
@RequestMapping("/api/rivervis")
@CrossOrigin
@Api(value = "山洪预警 - 统计计数类接口")
public class RivervisCount {

    @Autowired
    private IEnvDataService envDataService;

    /**
        * 按照时间顺序返回前二十条
        * @author 张超 teavamc
        * @date 2019/3/10
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @CrossOrigin
    @GetMapping("/commentCount")
    @ApiOperation(value = "按照时间顺序返回前二十条")
    public RongApiRes commentCount(){
        return RongApiService.get_list(envDataService.selectEnvDataListLimit());
    }

    /**
     * 统计所有山洪数据
     * @author 张超 teavamc
     * @date 2019/3/10
     * @param []
     * @return com.sixin.api.domain.RongApiRes
     */
    @CrossOrigin
    @GetMapping("/countall")
    @ApiOperation(value = "统计所有山洪数据")
    public RongApiRes countall(){
        return RongApiService.get_bean(envDataService.countall());
    }

    @CrossOrigin
    @GetMapping("/envbytl")
    @ApiOperation(value = "根据时间和查询条数统计环境数据")
    public RongApiRes envbytl(Riverbytl riverbytl){
        return RongApiService.get_list(envDataService.selectEnvDataListbytl(riverbytl));
    }

}
