package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.village.domain.Huodong;
import com.sixin.village.service.IHuodongService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @program: daiyuqi1999_RongSys
 * @description: 党员活动信息操作处理
 * @author: 戴誉琪
 * @create: 2019-01-14 16:04
 **/
@Controller
@RequestMapping("/village/huodong")
public class HuodongController extends BaseController {

    private String prefix = "village/huodong";

    @Autowired
    private IHuodongService huodongService;

    @RequiresPermissions("village:huodong:view")
    @GetMapping()
    public String Huodong(){
        return prefix+"/huodong";
    }

    /**
     * 公告信息列表
     * @param huodong
     * @return
     */
    //@RequiresPermissions("village:huodong:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Huodong huodong){
        startPage();
        List<Huodong> list = huodongService.selectHuodongList(huodong);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    /**
     * 新增保存党员活动信息
     */
    @RequiresPermissions("village:huodong:add")
    @Log(title = "公告信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Huodong huodong)
    {
        return toAjax(huodongService.inserthuodong(huodong));
    }
    /**
     * 修改党员活动信息
     */
    @GetMapping("/edit/{hdid}")
    public String edit(@PathVariable("hdid") Integer hdid, ModelMap mmap)
    {
        Huodong huodong = huodongService.selectByhdid(hdid);
        mmap.put("huodong", huodong);
        return prefix + "/edit";
    }
    /**
     * 修改保存党员活动
     */
    @RequiresPermissions("village:huodong:edit")
    @Log(title = "党员活动", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Huodong huodong)
    {
        return toAjax(huodongService.updateHuodong(huodong));
    }
    /**
     * 删除党员活动
     */
    @RequiresPermissions("village:huodong:remove")
    @Log(title = "删除党员活动", businessType = BusinessType.DELETE)
    @PostMapping( "/remove/{hsid}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("hdid") String hdid)
    {
        System.out.println("*******"+hdid);
        return toAjax(huodongService.deleteHuodongByIds(hdid));
    }
}
