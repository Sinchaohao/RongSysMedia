package com.sixin.web.controller.system;

import com.sixin.common.annotation.Log;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.enums.BusinessType;
import com.sixin.system.domain.SysPerson;
import com.sixin.system.service.ISysPersonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Controller
@RequestMapping("/system/sperson")
public class SysPersonController extends BaseController {
    private String prefix = "system/sperson";

    @Autowired
    private ISysPersonService sysPersonService;
    @RequiresPermissions("system:sperson:view")
    @GetMapping()
    public String sperson(){
        return prefix + "/sperson";
    }

    @RequiresPermissions("system:sperson:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPerson person)
    {
        startPage();
        List<SysPerson> list =sysPersonService.selectPersonlist(person);
        return getDataTable(list);
    }


    /**
     * 新增授权号码
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存授权号码
     */
    @RequiresPermissions("system:sperson:add")
    @Log(title = "特殊人员管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysPerson person)
    {

        return toAjax(sysPersonService.insertPerson(person));
    }

    /**
     * 修改特殊人员信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        mmap.put("person", sysPersonService.selectPersonById(id));
        return prefix + "/edit";
    }

    /**
     * 修改保存特殊人员信息
     */
    @RequiresPermissions("system:sperson:edit")
    @Log(title = "特殊人员信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysPerson person)
    {
        return toAjax(sysPersonService.updatePerson(person));
    }

    /**
     * 删除特殊人员信息
     */
    @RequiresPermissions("system:sperson:remove")
    @Log(title = "特殊人员信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysPersonService.deletePersonById(ids));
    }


}
