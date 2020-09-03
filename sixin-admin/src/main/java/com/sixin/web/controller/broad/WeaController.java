package com.sixin.web.controller.broad;

import java.util.List;

import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
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
import com.sixin.broad.domain.Wea;
import com.sixin.broad.service.IWeaService;

/**
 * 天气公告 信息操作处理
 * 
 * @author 张超
 * @date 2019-01-14
 */
@Controller
@RequestMapping("/broad/wea")
public class WeaController extends BaseController
{
    private String prefix = "broad/wea";
	
	@Autowired
	private IWeaService weaService;
	
	@RequiresPermissions("broad:wea:view")
	@GetMapping()
	public String wea()
	{
	    return prefix + "/wea";
	}
	
	/**
	 * 查询天气公告列表
	 */
	@RequiresPermissions("broad:wea:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Wea wea)
	{
		startPage();
        List<Wea> list = weaService.selectWeaList(wea);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出天气公告列表
	 */
	@RequiresPermissions("broad:wea:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Wea wea)
    {
    	List<Wea> list = weaService.selectWeaList(wea);
        ExcelUtil<Wea> util = new ExcelUtil<Wea>(Wea.class);
        return util.exportExcel(list, "wea");
    }
	
	/**
	 * 新增天气公告
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存天气公告
	 */
	@RequiresPermissions("broad:wea:add")
	@Log(title = "新增天气公告", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Wea wea)
	{		
		return toAjax(weaService.insertWea(wea));
	}

	/**
	 * 修改天气公告
	 */
	@GetMapping("/edit/{wid}")
	public String edit(@PathVariable("wid") Integer wid, ModelMap mmap)
	{
		Wea wea = weaService.selectWeaById(wid);
		mmap.put("wea", wea);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存天气公告
	 */
	@RequiresPermissions("broad:wea:edit")
	@Log(title = "修改天气公告", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Wea wea)
	{		
		return toAjax(weaService.updateWea(wea));
	}
	
	/**
	 * 删除天气公告
	 */
	@RequiresPermissions("broad:wea:remove")
	@Log(title = "删除天气公告", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(weaService.deleteWeaByIds(ids));
	}
	
}
