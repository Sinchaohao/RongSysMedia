package com.sixin.web.controller.system;

import com.sixin.common.annotation.Log;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.enums.BusinessType;
import com.sixin.system.domain.SysDept;
import com.sixin.system.domain.SysPhone;
import com.sixin.system.service.ISysDeptService;
import com.sixin.system.service.ISysPhoneService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Controller
@RequestMapping("/system/phonemanage")
public class SysPhoneController extends BaseController {
    private String prefix="system/phonemanage";

    @Autowired
    private ISysPhoneService sysPhoneService;

    @Autowired
    private ISysDeptService deptService;
    @RequiresPermissions("system:phone:view")
    @GetMapping()
    public String phone(){
        return prefix + "/phone";
    }


    @RequiresPermissions("system:phone:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPhone phone)
    {
        startPage();
        List<SysPhone> list =sysPhoneService.selectPhonelist(phone);
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
    @RequiresPermissions("system:phone:add")
    @Log(title = "授权号码管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysPhone phone)
    {

        return toAjax(sysPhoneService.insertPhone(phone));
    }

    /**
     * 删除授权号码
     */
    @RequiresPermissions("system:phone:remove")
    @Log(title = "授权号码管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
            return toAjax(sysPhoneService.deletePhone(ids));
    }

    /**
     * 修改授权号码信息
     */
    @GetMapping("/edit/{pid}")
    public String edit(@PathVariable("pid")Long pid, ModelMap mmap)
    {
        mmap.put("phone", sysPhoneService.selectPhoneById(pid));
        return prefix + "/edit";
    }

    /**
     * 修改保存授权号码信息
     */
    @RequiresPermissions("system:phone:edit")
    @Log(title = "授权手机管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysPhone phone)
    {
        return toAjax(sysPhoneService.updatePhone(phone));
    }
}
