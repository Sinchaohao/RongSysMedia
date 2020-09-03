package com.sixin.web.controller.village;

import java.util.List;

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
import com.sixin.village.domain.Varea;
import com.sixin.village.service.IVareaService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;

/**
 * 地域 信息操作处理
 * 
 * @author 张超
 * @date 2019-01-20
 */
@Controller
@RequestMapping("/village/varea")
public class VareaController extends BaseController
{
    private String prefix = "village/varea";
	
	@Autowired
	private IVareaService vareaService;
	
	@RequiresPermissions("village:varea:view")
	@GetMapping()
	public String varea()
	{
	    return prefix + "/varea";
	}
	
	/**
	 * 查询地域列表
	 */
	@RequiresPermissions("village:varea:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Varea varea)
	{
		startPage();
        List<Varea> list = vareaService.selectVareaList(varea);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出地域列表
	 */
	@RequiresPermissions("village:varea:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Varea varea)
    {
    	List<Varea> list = vareaService.selectVareaList(varea);
        ExcelUtil<Varea> util = new ExcelUtil<Varea>(Varea.class);
        return util.exportExcel(list, "varea");
    }
	
	/**
	 * 新增地域
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存地域
	 */
	@RequiresPermissions("village:varea:add")
	@Log(title = "地域", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Varea varea)
	{		
		return toAjax(vareaService.insertVarea(varea));
	}

	/**
	 * 修改地域
	 */
	@GetMapping("/edit/{aid}")
	public String edit(@PathVariable("aid") String aid, ModelMap mmap)
	{
		Varea varea = vareaService.selectVareaById(aid);
		mmap.put("varea", varea);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存地域
	 */
	@RequiresPermissions("village:varea:edit")
	@Log(title = "地域", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Varea varea)
	{		
		return toAjax(vareaService.updateVarea(varea));
	}
	
	/**
	 * 删除地域
	 */
	@RequiresPermissions("village:varea:remove")
	@Log(title = "地域", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vareaService.deleteVareaByIds(ids));
	}
	
}
