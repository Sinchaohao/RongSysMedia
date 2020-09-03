package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.village.domain.Complaint;
import com.sixin.village.service.IComplaintService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * @program: lyb_RongSys
 * @description: 投诉咨询 信息操作处理
 * @author: Mr.Liu
 * @create: 2019-01-14 16:04
 **/
@Controller
@RequestMapping("/village/complaint")
public class ComplaintController extends BaseController {

    private String prefix = "village/Complaint";

    @Autowired
    private IComplaintService complaintService;

    @RequiresPermissions("village:complaint:view")
    @GetMapping()
    public String Complaintsmessages(){
        return prefix+"/complaint";
    }

    /**
     * 投诉咨询列表
     * @param complaintmessages
     * @return
     */
    @RequiresPermissions("village:complaint:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Complaint complaintmessages){
        startPage();
        List<Complaint> list = complaintService.selectComplaintsList(complaintmessages);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    /**
     * 新增保存投诉咨询
     */
    @RequiresPermissions("village:complaint:add")
    @Log(title = "投诉咨询", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Complaint complaint)
    {
        return toAjax(complaintService.insertcomplaintsmessages(complaint));
    }
    /**
     * 修改投诉咨询
     */
    @GetMapping("/edit/{fbid}")
    public String edit(@PathVariable("fbid") Integer fbid, ModelMap mmap)
    {
        Complaint complaint = complaintService.selectByfbid(fbid);
        mmap.put("complaint", complaint);
        return prefix + "/edit";
    }
    /**
     * 修改保存投诉咨询
     */
    @RequiresPermissions("village:complaint:edit")
    @Log(title = "投诉咨询", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Complaint complaint)
    {
        return toAjax(complaintService.updateComplaintsmessages(complaint));
    }
    /**
     * 删除投诉咨询
     */
    @RequiresPermissions("village:complaint:remove")
    @Log(title = "删除投诉咨询", businessType = BusinessType.DELETE)
    @PostMapping( "/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") String fbid)
    {
        return toAjax(complaintService.deleteComplaintsByids(fbid));
    }

}
