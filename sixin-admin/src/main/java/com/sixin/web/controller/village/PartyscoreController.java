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
import com.sixin.village.domain.Partyscore;
import com.sixin.village.service.IPartyscoreService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;

/**
 * 党员自评 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
@Controller
@RequestMapping("/village/partyscore")
public class PartyscoreController extends BaseController
{
    private String prefix = "village/partyscore";
	
	@Autowired
	private IPartyscoreService partyscoreService;
	
	@RequiresPermissions("village:partyscore:view")
	@GetMapping()
	public String partyscore()
	{
	    return prefix + "/partyscore";
	}
	
	/**
	 * 查询党员自评列表
	 */
	@RequiresPermissions("village:partyscore:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Partyscore partyscore)
	{
		startPage();
        List<Partyscore> list = partyscoreService.selectPartyscoreList(partyscore);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出党员自评列表
	 */
	@RequiresPermissions("village:partyscore:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Partyscore partyscore)
    {
    	List<Partyscore> list = partyscoreService.selectPartyscoreList(partyscore);
        ExcelUtil<Partyscore> util = new ExcelUtil<Partyscore>(Partyscore.class);
        return util.exportExcel(list, "partyscore");
    }
	
	/**
	 * 新增党员自评
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存党员自评
	 */
	@RequiresPermissions("village:partyscore:add")
	@Log(title = "党员自评", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Partyscore partyscore)
	{		
		return toAjax(partyscoreService.insertPartyscore(partyscore));
	}

	/**
	 * 修改党员自评
	 */
	@GetMapping("/edit/{sfid}")
	public String edit(@PathVariable("sfid") Integer sfid, ModelMap mmap)
	{
		Partyscore partyscore = partyscoreService.selectPartyscoreById(sfid);
		mmap.put("partyscore", partyscore);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存党员自评
	 */
	@RequiresPermissions("village:partyscore:edit")
	@Log(title = "党员自评", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Partyscore partyscore)
	{		
		return toAjax(partyscoreService.updatePartyscore(partyscore));
	}
	
	/**
	 * 删除党员自评
	 */
	@RequiresPermissions("village:partyscore:remove")
	@Log(title = "党员自评", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partyscoreService.deletePartyscoreByIds(ids));
	}
	
}
