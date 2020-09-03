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
import com.sixin.broad.domain.Ledshow;
import com.sixin.broad.service.ILedshowService;

/**
 * led显示 信息操作处理
 * 
 * @author 张超
 * @date 2019-01-14
 */
@Controller
@RequestMapping("/broad/ledshow")
public class LedshowController extends BaseController
{
    private String prefix = "broad/ledshow";
	
	@Autowired
	private ILedshowService ledshowService;
	
	@RequiresPermissions("broad:ledshow:view")
	@GetMapping()
	public String ledshow()
	{
	    return prefix + "/ledshow";
	}
	
	/**
	 * 查询led显示列表
	 */
	@RequiresPermissions("broad:ledshow:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Ledshow ledshow)
	{
		startPage();
        List<Ledshow> list = ledshowService.selectLedshowList(ledshow);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出led显示列表
	 */
	@RequiresPermissions("broad:ledshow:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Ledshow ledshow)
    {
    	List<Ledshow> list = ledshowService.selectLedshowList(ledshow);
        ExcelUtil<Ledshow> util = new ExcelUtil<Ledshow>(Ledshow.class);
        return util.exportExcel(list, "ledshow");
    }
	
	/**
	 * 新增led显示
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存led显示
	 */
	@RequiresPermissions("broad:ledshow:add")
	@Log(title = "新增led显示公告", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Ledshow ledshow)
	{		
		return toAjax(ledshowService.insertLedshow(ledshow));
	}

	/**
	 * 修改led显示
	 */
	@GetMapping("/edit/{lsid}")
	public String edit(@PathVariable("lsid") Integer lsid, ModelMap mmap)
	{
		Ledshow ledshow = ledshowService.selectLedshowById(lsid);
		mmap.put("ledshow", ledshow);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存led显示
	 */
	@RequiresPermissions("broad:ledshow:edit")
	@Log(title = "修改led显示", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Ledshow ledshow)
	{		
		return toAjax(ledshowService.updateLedshow(ledshow));
	}
	
	/**
	 * 删除led显示
	 */
	@RequiresPermissions("broad:ledshow:remove")
	@Log(title = "删除led显示", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(ledshowService.deleteLedshowByIds(ids));
	}
	
}
