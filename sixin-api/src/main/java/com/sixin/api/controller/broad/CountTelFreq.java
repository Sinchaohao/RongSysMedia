package com.sixin.api.controller.broad;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.service.ITextmessagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张超 teavamc
 * @Description:统计电话的频率与时间
 * @ClassName CountTelFreq
 * @date 2019/3/22 21:15
 **/
@RestController
@RequestMapping("/api/counttel")
@CrossOrigin
@Api(value = "统计电话的频率与时间")
public class CountTelFreq {

    @Autowired
    private ITextmessagesService textmessagesService;

    @CrossOrigin
    @GetMapping("/freq")
    @ApiOperation(value = "统计电话的频率与时间")
    public RongApiRes selectList() {
        return RongApiService.get_list(textmessagesService.selectTypeNum());
    }


}
