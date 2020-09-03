package com.sixin.web.controller.broad;

import com.sixin.broad.domain.Terminaladmin;
import com.sixin.broad.service.ITerminaladminService;
import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 授权号码管理 信息操作处理
 *
 * @date 2020-02-21
 */
@Controller
@RequestMapping("/broad/terminaladmin")
public class TerminaladminController extends BaseController {
    private String prefix = "broad/terminaladmin";

    @Autowired
    private ITerminaladminService terminaladminService;

    @RequiresPermissions("broad:terminaladmin:view")
    @GetMapping()
    public String terminaladmin() {
        return prefix + "/terminaladmin";
    }

    /**
     * 查询授权号码管理列表
     */
    @RequiresPermissions("broad:terminaladmin:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Terminaladmin terminaladmin) {
        startPage();
        List<Terminaladmin> list = terminaladminService.selectTerminaladminList(terminaladmin);
        return getDataTable(list);
    }


    /**
     * 导出授权号码管理列表
     */
    @RequiresPermissions("broad:terminaladmin:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Terminaladmin terminaladmin) {
        List<Terminaladmin> list = terminaladminService.selectTerminaladminList(terminaladmin);
        ExcelUtil<Terminaladmin> util = new ExcelUtil<Terminaladmin>(Terminaladmin. class);
        return util.exportExcel(list, "Terminaladmin");
    }

    /**
     * 新增授权号码管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存授权号码管理
     */
    @RequiresPermissions("broad:terminaladmin:add")
    @Log(title = "授权号码管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Terminaladmin terminaladmin) {
        return toAjax(terminaladminService.insertTerminaladmin(terminaladmin));
    }

    /**
     * 修改授权号码管理
     */
    @GetMapping("/edit/{pid}")
    public String edit(@PathVariable("pid") Long pid, ModelMap mmap) {
        Terminaladmin terminaladmin =terminaladminService.selectTerminaladminById(pid);
        mmap.put("terminaladmin", terminaladmin);
        return prefix + "/edit";
    }

    /**
     * 修改保存授权号码管理
     */
    @RequiresPermissions("broad:terminaladmin:edit")
    @Log(title = "授权号码管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Terminaladmin terminaladmin) {
        return toAjax(terminaladminService.updateTerminaladmin(terminaladmin));
    }

    /**
     * 删除授权号码管理
     */
    @RequiresPermissions("broad:terminaladmin:remove")
    @Log(title = "授权号码管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(terminaladminService.deleteTerminaladminByIds(ids));
    }

}
