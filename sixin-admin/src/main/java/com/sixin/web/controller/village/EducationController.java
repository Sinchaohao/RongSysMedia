package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.village.domain.Education;
import com.sixin.village.service.IEducationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @program: dyq_RongSys
 * @description: 党员在线学习 信息操作处理
 * @author: 戴誉琪
 * @create: 2019-01-18 13:04
 **/
@Controller
@RequestMapping("/village/education")
public class EducationController extends BaseController {

    private String prefix = "village/education";

    @Autowired
    private IEducationService educationService;

    @RequiresPermissions("village:education:view")
    @GetMapping()
    public String Education(){
        return prefix+"/education";
    }

    /**
     *党员在线学习列表
     * @param education
     * @return
     */
    //@RequiresPermissions("village:education:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Education education){
        startPage();
        List<Education> list = educationService.selectEducationList(education);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    /**
     * 新增保存党员在线学习
     */
    @RequiresPermissions("village:education:add")
    @Log(title = "党员在线学习", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Education education)
    {
        return toAjax(educationService.inserteducation(education));
    }
    /**
     * 修改党员在线学习
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer eid, ModelMap mmap)
    {
        Education education = educationService.selectByeid(eid);
        mmap.put("education", education);
        return prefix + "/edit";
    }
    /**
     * 修改保存政策
     */
    @RequiresPermissions("village:education:edit")
    @Log(title = "党员在线学习", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Education education)
    {
        return toAjax(educationService.updateEducation(education));
    }
    /**
     * 删除党员在线学习课程
     */
    @RequiresPermissions("village:education:remove")
    @Log(title = "删除党员在线学习课程", businessType = BusinessType.DELETE)
    @PostMapping( "/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") String eid)
    {
        System.out.println("*******"+eid);
        return toAjax(educationService.deleteEducationByids(eid));
    }
}
