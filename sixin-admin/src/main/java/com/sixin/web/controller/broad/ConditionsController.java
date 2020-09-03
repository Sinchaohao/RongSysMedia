package com.sixin.web.controller.broad;

import java.util.List;

import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
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
import com.sixin.broad.domain.Conditions;
import com.sixin.broad.service.IConditionsService;
import com.sixin.common.page.TableDataInfo;

/**
 * 设备报 信息操作处理
 * 
 * @author 张超
 * @date 2019-01-15
 */
@Controller
@RequestMapping("/broad/conditions")
public class ConditionsController extends BaseController
{
    private String prefix = "broad/conditions";
	
	@Autowired
	private IConditionsService conditionsService;
	
	@RequiresPermissions("broad:conditions:view")
	@GetMapping()
	public String conditions()
	{
	    return prefix + "/conditions";
	}
	
	/**
	 * 查询设备报列表
	 */
	@RequiresPermissions("broad:conditions:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Conditions conditions)
	{
		startPage();
        List<Conditions> list = conditionsService.selectConditionsList(conditions);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出设备报列表
	 */
	@RequiresPermissions("broad:conditions:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Conditions conditions)
    {
    	List<Conditions> list = conditionsService.selectConditionsList(conditions);
        ExcelUtil<Conditions> util = new ExcelUtil<Conditions>(Conditions.class);
        return util.exportExcel(list, "conditions");
    }
	
	/**
	 * 新增设备报
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备报
	 */
	@RequiresPermissions("broad:conditions:add")
	@Log(title = "设备报", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Conditions conditions)
	{		
		return toAjax(conditionsService.insertConditions(conditions));
	}

	/**
	 * 修改设备报
	 */
	@GetMapping("/edit/{tid}")
	public String edit(@PathVariable("tid") String tid, ModelMap mmap)
	{
		Conditions conditions = conditionsService.selectConditionsById(tid);
		mmap.put("conditions", conditions);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备报
	 */
	@RequiresPermissions("broad:conditions:edit")
	@Log(title = "设备报", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Conditions conditions)
	{		
		return toAjax(conditionsService.updateConditions(conditions));
	}
	
	/**
	 * 删除设备报
	 */
	@RequiresPermissions("broad:conditions:remove")
	@Log(title = "设备报", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(conditionsService.deleteConditionsByIds(ids));
	}
	
}
