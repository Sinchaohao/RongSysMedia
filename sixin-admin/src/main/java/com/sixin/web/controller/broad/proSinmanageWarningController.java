package com.sixin.web.controller.broad;

import com.sixin.broad.domain.ProSinmanage;
import com.sixin.broad.service.IProListService;
import com.sixin.broad.service.IProSinmanageService;
import com.sixin.common.annotation.Log;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张超 teavamc
 * @Description:TODO
 * @ClassName proSinmanageWarningController
 * @date 2019/3/9 22:03
 **/
public class proSinmanageWarningController extends BaseController
{
    private String prefix = "broad/proSinmanageWarning";

    @Autowired
    private IProSinmanageService proSinmanageService;
    @Autowired
    private IProListService proListService;
    @Autowired
    private ISysUserService sysUserService;


    @RequiresPermissions("broad:proSinmanageWarning:view")
    @GetMapping()
    public String proSinmanageWarning()
    {
        return prefix + "/proSinmanageWarning";
    }

    /**
     * 查询节目播出单列表
     */
    @RequiresPermissions("broad:proSinmanageWarning:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        SysUser currentUser = ShiroUtils.getSysUser();//从session中获取当前登陆用户的userid
        long userid =  currentUser.getUserId();
        //int returnId = new Long(userid).intValue();
        int roleid = sysUserService.selectRoleid(userid);//通过所获取的userid去广播用户表中查询用户所属区域的Roleid
        List<ProSinmanage> list ;
        startPage();
        /*判断用户等级，若为超级管理员则可查看全部内容，否则只能查看自己的内容*/
        if(roleid != 1){
            list = proSinmanageService.selectProSinmanageListForWarning(userid);//通过所获取的Aid去查询用户所属区域对应的数据
        }else{
            list = proSinmanageService.selectProSinmanageListForWarning(userid);}
        return getDataTable(list);
    }

    /**
     * 打开节目单详情页
     */
    @GetMapping("/detail/{sjid}")
    public String detail(@PathVariable("sjid")String sjid,ModelMap mmap)
    {
        mmap.put("sjid",sjid);
        mmap.put("listBySjid",proListService.selectProListListByPid(sjid));
        return prefix + "/detail";
    }

    /**
     * 导出节目播出单列表
     */
    @RequiresPermissions("broad:proSinmanageWarning:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProSinmanage proSinmanage)
    {
        List<ProSinmanage> list = proSinmanageService.selectProSinmanageList(proSinmanage);
        ExcelUtil<ProSinmanage> util = new ExcelUtil<ProSinmanage>(ProSinmanage.class);
        return util.exportExcel(list, "proSinmanage");
    }

    /**
     * 新增节目播出单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存节目播出单
     */
    @RequiresPermissions("broad:proSinmanageWarning:add")
    @Log(title = "节目播出单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProSinmanage proSinmanage)
    {
        return toAjax(proSinmanageService.insertProSinmanage(proSinmanage));
    }

    /**
     * 修改节目播出单
     */
    @GetMapping("/edit/{sfid}")
    public String edit(@PathVariable("sfid") Integer sfid, ModelMap mmap)
    {
        ProSinmanage proSinmanage = proSinmanageService.selectProSinmanageById(sfid);
        mmap.put("proSinmanage", proSinmanage);
        return prefix + "/edit";
    }

    /**
     * 修改保存节目播出单
     */
    @RequiresPermissions("broad:proSinmanageWarning:edit")
    @Log(title = "节目播出单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProSinmanage proSinmanage)
    {
        return toAjax(proSinmanageService.updateProSinmanage(proSinmanage));
    }

    /**
     * 删除节目播出单
     */
    @RequiresPermissions("broad:proSinmanageWarning:remove")
    @Log(title = "节目播出单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(proSinmanageService.deleteProSinmanageByIds(ids));
    }

    @Log(title = "节目播出单", businessType = BusinessType.EXPORT)
    @RequiresPermissions("broad:proSinmanageWarn:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportProSinmanageByIds(@RequestParam("sjids") List<String> sfids) {
        List<ProSinmanage> list = proSinmanageService.selectProSinmanageListByids(sfids);
        ExcelUtil<ProSinmanage> util = new ExcelUtil<ProSinmanage>(ProSinmanage.class);
        return util.exportExcel(list, "proSinmanageWarn");
    }//导出√中的数据

}
