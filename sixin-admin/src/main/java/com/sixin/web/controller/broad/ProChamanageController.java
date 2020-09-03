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
import com.sixin.broad.domain.ProChamanage;
import com.sixin.broad.service.IProChamanageService;

/**
 * 节目转播管理 信息操作处理
 * 
 * @author 张超
 * @date 2019-03-02
 */
@Controller
@RequestMapping("/broad/proChamanage")
public class ProChamanageController extends BaseController
{
    private String prefix = "broad/proChamanage";
	
	@Autowired
	private IProChamanageService proChamanageService;
	
	@RequiresPermissions("broad:proChamanage:view")
	@GetMapping()
	public String proChamanage()
	{
	    return prefix + "/proChamanage";
	}
	
	/**
	 * 查询节目转播管理列表
	 */
	@RequiresPermissions("broad:proChamanage:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProChamanage proChamanage)
	{
		startPage();
        List<ProChamanage> list = proChamanageService.selectProChamanageList(proChamanage);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出节目转播管理列表
	 */
	@RequiresPermissions("broad:proChamanage:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProChamanage proChamanage)
    {
    	List<ProChamanage> list = proChamanageService.selectProChamanageList(proChamanage);
        ExcelUtil<ProChamanage> util = new ExcelUtil<ProChamanage>(ProChamanage.class);
        return util.exportExcel(list, "proChamanage");
    }
	
	/**
	 * 新增节目转播管理
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存节目转播管理
	 */
	@RequiresPermissions("broad:proChamanage:add")
	@Log(title = "新增节目转播管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ProChamanage proChamanage)
	{		
		return toAjax(proChamanageService.insertProChamanage(proChamanage));
	}

	/**
	 * 修改节目转播管理
	 */
	@GetMapping("/edit/{cid}")
	public String edit(@PathVariable("cid") Integer cid, ModelMap mmap)
	{
		ProChamanage proChamanage = proChamanageService.selectProChamanageById(cid);
		mmap.put("proChamanage", proChamanage);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存节目转播管理
	 */
	@RequiresPermissions("broad:proChamanage:edit")
	@Log(title = "修改节目转播管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ProChamanage proChamanage)
	{		
		return toAjax(proChamanageService.updateProChamanage(proChamanage));
	}
	
	/**
	 * 删除节目转播管理
	 */
	@RequiresPermissions("broad:proChamanage:remove")
	@Log(title = "删除节目转播管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(proChamanageService.deleteProChamanageByIds(ids));
	}
	
}
