package com.sixin.web.controller.demo.controller;
/**
 * @function:模态窗口
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-01-20 17:18
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo/modal")
public class DemoDialogController
{
    private String prefix = "demo/modal";

    /**
     * 模态窗口
     */
    @GetMapping("/dialog")
    public String dialog()
    {
        return prefix + "/dialog";
    }

    /**
     * 弹层组件
     */
    @GetMapping("/layer")
    public String layer()
    {
        return prefix + "/layer";
    }

    /**
     * 表单
     */
    @GetMapping("/form")
    public String form()
    {
        return prefix + "/form";
    }

    /**
     * 表格
     */
    @GetMapping("/table")
    public String table()
    {
        return prefix + "/table";
    }

    /**
     * 表格check
     */
    @GetMapping("/check")
    public String check()
    {
        return prefix + "/table/check";
    }

    /**
     * 表格radio
     */
    @GetMapping("/radio")
    public String radio()
    {
        return prefix + "/table/radio";
    }

    /**
     * 表格回传父窗体
     */
    @GetMapping("/parent")
    public String parent()
    {
        return prefix + "/table/parent";
    }
}
