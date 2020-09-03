package com.sixin.web.controller.iot;
/**
 * 周宇滔
 * 气象信息
 */

import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.service.IMetService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/iot/metpannel")
public class metpannelController extends BaseController {
    private String prefix="iot/metpannel";


    @Autowired
    private IMetService metService;

    @RequiresPermissions("iot:metpannel:view")
    @GetMapping()
    public String metpannel(){
        return prefix + "/metpannel";
    }



    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Environl environl)
    {
        startPage();
        List<Environl> list = metService.selectMet(environl);
        return getDataTable(list);
    }

    /**
     * 新增气象信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存气象信息
     */
    @RequiresPermissions("iot:metpannel:add")
    @Log(title = "气象信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Environl environl)
    {
        return toAjax(metService.insertMetData(environl));
    }

    /**
     * 修改气象信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Environl environl =metService.selectMetDataById(id);
        mmap.put("metweatherdata", environl);
        return prefix + "/edit";
    }

    /**
     * 修改保存气象信息
     */
    @RequiresPermissions("iot:metpannel:edit")
    @Log(title = "气象信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Environl environl)
    {
        return toAjax(metService.updateMetData(environl));
    }

    /**
     * 删除气象信息
     */
    @RequiresPermissions("iot:metpannel:remove")
    @Log(title = "气象信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(metService.deleteMetDataByIds(ids));
    }

}
