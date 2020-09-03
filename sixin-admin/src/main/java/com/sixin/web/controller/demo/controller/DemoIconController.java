package com.sixin.web.controller.demo.controller;
/**
 * @function:图标相关
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-01-20 17：27
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 图标相关
 * 
 * @author sixin
 */
@Controller
@RequestMapping("/demo/icon")
public class DemoIconController
{
    private String prefix = "demo/icon";

    /**
     * FontAwesome图标
     */
    @GetMapping("/fontawesome")
    public String fontAwesome()
    {
        return prefix + "/fontawesome";
    }

    /**
     * Glyphicons图标
     */
    @GetMapping("/glyphicons")
    public String glyphicons()
    {
        return prefix + "/glyphicons";
    }
}
