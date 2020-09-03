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
import com.sixin.village.domain.Guide;
import com.sixin.village.service.IGuideService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;

/**
 * 常用办事指南 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
@Controller
@RequestMapping("/village/guide")
public class GuideController extends BaseController
{
    private String prefix = "village/guide";
	
	@Autowired
	private IGuideService guideService;
	
	@RequiresPermissions("village:guide:view")
	@GetMapping()
	public String guide()
	{
	    return prefix + "/guide";
	}
	
	/**
	 * 查询常用办事指南列表
	 */
	@RequiresPermissions("village:guide:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Guide guide)
	{
		startPage();
        List<Guide> list = guideService.selectGuideList(guide);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出常用办事指南列表
	 */
	@RequiresPermissions("village:guide:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Guide guide)
    {
    	List<Guide> list = guideService.selectGuideList(guide);
        ExcelUtil<Guide> util = new ExcelUtil<Guide>(Guide.class);
        return util.exportExcel(list, "guide");
    }
	
	/**
	 * 新增常用办事指南
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	/**
	 * 新增保存常用办事指南
	 */
	@RequiresPermissions("village:guide:add")
	@Log(title = "常用办事指南", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Guide guide)
	{		
		return toAjax(guideService.insertGuide(guide));
	}

	/**
	 * 修改常用办事指南
	 */
	@GetMapping("/edit/{guid}")
	public String edit(@PathVariable("guid") Integer guid, ModelMap mmap)
	{
		Guide guide = guideService.selectGuideById(guid);
		mmap.put("guide", guide);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存常用办事指南
	 */
	@RequiresPermissions("village:guide:edit")
	@Log(title = "常用办事指南", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Guide guide)
	{		
		return toAjax(guideService.updateGuide(guide));
	}
	
	/**
	 * 删除常用办事指南
	 */
	@RequiresPermissions("village:guide:remove")
	@Log(title = "常用办事指南", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(guideService.deleteGuideByIds(ids));
	}
	
}
