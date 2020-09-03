package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.village.domain.Partyactive;
import com.sixin.village.service.IPartyactiveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 党员活动 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
@Controller
@RequestMapping("/village/partyactive")
public class PartyactiveController extends BaseController
{
    private String prefix = "village/partyactive";
	
	@Autowired
	private IPartyactiveService partyactiveService;

	@RequiresPermissions("village:partyactive:view")
	@GetMapping()
	public String partyactive()
	{
	    return prefix + "/partyactive";
	}
	
	/**
	 * 查询党员活动列表
	 */
	@RequiresPermissions("village:partyactive:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Partyactive partyactive)
	{
		startPage();
        List<Partyactive> list = partyactiveService.selectPartyactiveList(partyactive);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出党员活动列表
	 */
	@RequiresPermissions("village:partyactive:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Partyactive partyactive)
    {
    	List<Partyactive> list = partyactiveService.selectPartyactiveList(partyactive);
        ExcelUtil<Partyactive> util = new ExcelUtil<Partyactive>(Partyactive.class);
        return util.exportExcel(list, "partyactive");
    }
	
	/**
	 * 新增党员活动
	 */
	@GetMapping("/add")
	public String add()
	{

		return prefix + "/add";
	}
	
	/**
	 * 新增保存党员活动
	 */
	@RequiresPermissions("village:partyactive:add")
	@Log(title = "党员活动", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Partyactive partyactive)
	{




		return toAjax(partyactiveService.insertPartyactive(partyactive));
		//return toAjax(1);
	}

	/**
	 * 修改党员活动
	 */
	@GetMapping("/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, ModelMap mmap)
	{
		Partyactive partyactive = partyactiveService.selectPartyactiveById(mid);
		mmap.put("partyactive", partyactive);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存党员活动
	 */
	@RequiresPermissions("village:partyactive:edit")
	@Log(title = "党员活动", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Partyactive partyactive)
	{		
		return toAjax(partyactiveService.updatePartyactive(partyactive));
	}
	
	/**
	 * 删除党员活动
	 */
	@RequiresPermissions("village:partyactive:remove")
	@Log(title = "党员活动", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partyactiveService.deletePartyactiveByIds(ids));
	}
	
}
