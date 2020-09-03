package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.village.service.IPartyaffairsService;
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
import com.sixin.village.domain.Partyaffairs;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;

/**
 * 党务公开 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
@Controller
@RequestMapping("/village/partyaffairs")
public class PartyaffairsController extends BaseController
{
    private String prefix = "village/partyaffairs";
	
	@Autowired
	private IPartyaffairsService partyaffairsService;
	
	@RequiresPermissions("village:partyaffairs:view")
	@GetMapping()
	public String partyaffairs()
	{
	    return prefix + "/partyaffairs";
	}
	
	/**
	 * 查询党务公开列表
	 */
	@RequiresPermissions("village:partyaffairs:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Partyaffairs partyaffairs)
	{
		startPage();
        List<Partyaffairs> list = partyaffairsService.selectPartyaffairsList(partyaffairs);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出党务公开列表
	 */
	@RequiresPermissions("village:partyaffairs:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Partyaffairs partyaffairs)
    {
    	List<Partyaffairs> list = partyaffairsService.selectPartyaffairsList(partyaffairs);
        ExcelUtil<Partyaffairs> util = new ExcelUtil<Partyaffairs>(Partyaffairs.class);
        return util.exportExcel(list, "partyaffairs");
    }
	
	/**
	 * 新增党务公开
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存党务公开
	 */
	@RequiresPermissions("village:partyaffairs:add")
	@Log(title = "党务公开", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Partyaffairs partyaffairs)
	{		
		return toAjax(partyaffairsService.insertPartyaffairs(partyaffairs));
	}

	/**
	 * 修改党务公开
	 */
	@GetMapping("/edit/{dwid}")
	public String edit(@PathVariable("dwid") Integer dwid, ModelMap mmap)
	{
		Partyaffairs partyaffairs = partyaffairsService.selectPartyaffairsById(dwid);
		mmap.put("partyaffairs", partyaffairs);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存党务公开
	 */
	@RequiresPermissions("village:partyaffairs:edit")
	@Log(title = "党务公开", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Partyaffairs partyaffairs)
	{		
		return toAjax(partyaffairsService.updatePartyaffairs(partyaffairs));
	}
	
	/**
	 * 删除党务公开
	 */
	@RequiresPermissions("village:partyaffairs:remove")
	@Log(title = "党务公开", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partyaffairsService.deletePartyaffairsByIds(ids));
	}
	
}
