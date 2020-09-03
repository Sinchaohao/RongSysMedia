package com.sixin.web.controller.broad;

import com.sixin.broad.domain.MaintainApply;
import com.sixin.broad.service.IMaintainApplyService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cx
 *
 *
 * @Description 申请维护记录 控制层
 */
@Controller
@RequestMapping("/broad/maintainApply")
public class MaintainApplyController extends BaseController {
    private String prefix = "broad/maintainApply";

    @Autowired
    private IMaintainApplyService iMaintainApplyService;

    @Autowired
    private ISysUserService iSysUserService;

    @GetMapping()
    public String maintainApply()
    {
        return prefix + "/maintainApply";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MaintainApply maintainApply) {
        SysUser currentUser = ShiroUtils.getSysUser();  //从session中获取当前登陆用户的userid
        Long userid = currentUser.getUserId();
        //int returnid = new Long(userid).intValue();
        int roleid = iSysUserService.selectRoleid(userid); //通过所获取的userid去广播用户表中查询用户所属区域的Roleid
        if (roleid == 1)
        {
            startPage();
            List<MaintainApply> list = iMaintainApplyService.selectMaintainApplyList(maintainApply);
            return getDataTable(list);
        }else
        {
            maintainApply.setUid(userid);
            startPage();
            List<MaintainApply> list = iMaintainApplyService.selectMaintainApplyList(maintainApply);
            return getDataTable(list);
        }
    }
    @GetMapping("/add")
    public String addMaintainApply(ModelMap modelMap){
        SysUser currentUser = ShiroUtils.getSysUser();
        String username =  currentUser.getUserName();
		String phone =  currentUser.getPhonenumber();
        Long userid =  currentUser.getUserId();
        String aid = String.valueOf(iSysUserService.selectAid(userid));
        modelMap.put("username", username);
		modelMap.put("userphone", phone);
        return prefix + "/add";
    }

//    @RequiresPermissions("broad:maintain:maintainapply")
    @Log(title = "申请维护记录保存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MaintainApply maintainApply){
        return toAjax(iMaintainApplyService.insertMaintainApply(maintainApply));
    }

    @PostMapping("/remove")
    @Log(title = "申请维护记录删除",businessType = BusinessType.DELETE)
    @RequiresPermissions("broad:maintainApply:remove")
    @ResponseBody
    public AjaxResult removeMaintainApply(String ids)
    {
         return toAjax(iMaintainApplyService.deleteMaintainApplyById(ids));
    }

    @GetMapping("/detail/{maid}")
    @Log(title = "申请维护记录详细")
    public String detail(@PathVariable("maid") String maid,ModelMap mmp)
    {
        mmp.put("listById",iMaintainApplyService.selectMaintainApplyById(maid));
        return prefix + "/detail";
    }

    /**
     * 修改终端维护记录
     */
    @GetMapping("/edit/{maid}")
    public String edit(@PathVariable("maid") String maid, ModelMap mmap)
    {
        MaintainApply maintainApply = iMaintainApplyService.selectMaintainApplyById(maid);
        mmap.put("maintainApply", maintainApply);
        return prefix + "/edit";
    }

    /**
     * 修改保存终端维护记录
     */
    @RequiresPermissions("broad:maintain:edit")
    @Log(title = "申请维护记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MaintainApply maintainApply)
    {
        return toAjax(iMaintainApplyService.updateMaintainApply(maintainApply));
    }

    /*
    * 导出Excel表
    * */
    /**
     * 导出终端维护记录列表
     */
    @RequiresPermissions("broad:maintainApply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaintainApply maintainApply)
    {
        List<MaintainApply> list = iMaintainApplyService.selectMaintainApplyList(maintainApply);
        ExcelUtil<MaintainApply> util = new ExcelUtil<MaintainApply>(MaintainApply.class);
        return util.exportExcel(list, "maintainApply");
    }
}
