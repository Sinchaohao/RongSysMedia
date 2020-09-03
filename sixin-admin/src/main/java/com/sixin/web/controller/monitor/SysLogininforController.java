package com.sixin.web.controller.monitor;

import java.util.List;
import com.sixin.framework.shiro.service.SysPasswordService;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysOperLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.system.domain.SysLogininfor;
import com.sixin.system.service.ISysLogininforService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统访问记录
 * 
 * @author sixin
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController
{
    private String prefix = "monitor/logininfor";

    @Autowired
    private ISysLogininforService logininforService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("monitor:logininfor:view")
    @GetMapping()
    public String logininfor()
    {
        return prefix + "/logininfor";
    }

    @RequiresPermissions("monitor:logininfor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysLogininfor logininfor)
    {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    @Log(title = "登陆日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:logininfor:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysLogininfor logininfor)
    {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        return util.exportExcel(list, "用户登陆日志");
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(logininforService.deleteLogininforByIds(ids));
    }
    
    @RequiresPermissions("monitor:logininfor:remove")
    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        logininforService.cleanLogininfor();
        return success();
    }

    @RequiresPermissions("monitor:logininfor:unlock")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @PostMapping("/unlock")
    @ResponseBody
    public AjaxResult unlock(String loginName)
    {
        passwordService.unlock(loginName);
        return success();
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        return util.importTemplateExcel("用户登录日志");
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        List<SysLogininfor> list = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        return toAjax(logininforService.batchInsertSysLogininfor(list));
    }

    /** @author qwerty
     * @description 导出√中的数据
     *
     * @param sfids
     * @return
     */
    @Log(title = "用户登录日志导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:logininfor:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportSysLoginforByIds(@RequestParam("sjids") List<String> sfids) {
        List<SysLogininfor> list = logininforService.selectSysLogininforListByids(sfids);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        return util.exportExcel(list, "SysLogininfor");
    }
}
