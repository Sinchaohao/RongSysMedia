package com.sixin.web.controller.demo.controller;
/**
 * @function:报表
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-01-20 17：30
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo/report")
public class DemoReportController
{
    private String prefix = "demo/report";

    /**
     * 百度ECharts
     */
    @GetMapping("/echarts")
    public String echarts()
    {
        return prefix + "/echarts";
    }

    /**
     * 图表插件
     */
    @GetMapping("/peity")
    public String peity()
    {
        return prefix + "/peity";
    }

    /**
     * 线状图插件
     */
    @GetMapping("/sparkline")
    public String sparkline()
    {
        return prefix + "/sparkline";
    }

    /**
     * 图表组合
     */
    @GetMapping("/metrics")
    public String metrics()
    {
        return prefix + "/metrics";
    }
}
