package com.sixin.web.controller.village;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.PersonalDetail;
import com.sixin.village.service.IPersonalDetailService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;

/**
 * 个人详细 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Controller
@RequestMapping("/village/personalDetail")
public class PersonalDetailController extends BaseController
{
    private String prefix = "village/personalDetail";
	
	@Autowired
	private IPersonalDetailService personalDetailService;
	
	@RequiresPermissions("village:personalDetail:view")
	@GetMapping()
	public String personalDetail()
	{
	    return prefix + "/personalDetail";
	}
	
	/**
	 * 查询个人详细列表
	 */
	@RequiresPermissions("village:personalDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PersonalDetail personalDetail)
	{
		startPage();
        List<PersonalDetail> list = personalDetailService.selectPersonalDetailList(personalDetail);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出个人详细列表
	 */
	@RequiresPermissions("village:personalDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PersonalDetail personalDetail)
    {
    	List<PersonalDetail> list = personalDetailService.selectPersonalDetailList(personalDetail);
        ExcelUtil<PersonalDetail> util = new ExcelUtil<PersonalDetail>(PersonalDetail.class);
        return util.exportExcel(list, "personalDetail");
    }
	
	/**
	 * 新增个人详细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存个人详细
	 */
	@RequiresPermissions("village:personalDetail:add")
	@Log(title = "个人详细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PersonalDetail personalDetail)
	{		
		return toAjax(personalDetailService.insertPersonalDetail(personalDetail));
	}

	/**
	 * 修改个人详细
	 */
	@GetMapping("/edit/{pdid}")
	public String edit(@PathVariable("pdid") Integer pdid, ModelMap mmap)
	{
		PersonalDetail personalDetail = personalDetailService.selectPersonalDetailById(pdid);
		mmap.put("personalDetail", personalDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存个人详细
	 */
	@RequiresPermissions("village:personalDetail:edit")
	@Log(title = "个人详细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PersonalDetail personalDetail)
	{		
		return toAjax(personalDetailService.updatePersonalDetail(personalDetail));
	}
	
	/**
	 * 删除个人详细
	 */
	@RequiresPermissions("village:personalDetail:remove")
	@Log(title = "个人详细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(personalDetailService.deletePersonalDetailByIds(ids));
	}
	
}
