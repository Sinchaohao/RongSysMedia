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
import com.sixin.village.domain.Wuser;
import com.sixin.village.service.IWuserService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;

/**
 * 用户基本 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
@Controller
@RequestMapping("/village/wuser")
public class WuserController extends BaseController
{
    private String prefix = "village/wuser";
	
	@Autowired
	private IWuserService wuserService;
	
	@RequiresPermissions("village:wuser:view")
	@GetMapping()
	public String wuser()
	{
	    return prefix + "/wuser";
	}
	
	/**
	 * 查询用户基本列表
	 */
	@RequiresPermissions("village:wuser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Wuser wuser)
	{
		startPage();
        List<Wuser> list = wuserService.selectWuserList(wuser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户基本列表
	 */
	@RequiresPermissions("village:wuser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Wuser wuser)
    {
    	List<Wuser> list = wuserService.selectWuserList(wuser);
        ExcelUtil<Wuser> util = new ExcelUtil<Wuser>(Wuser.class);
        return util.exportExcel(list, "wuser");
    }
	
	/**
	 * 新增用户基本
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户基本
	 */
	@RequiresPermissions("village:wuser:add")
	@Log(title = "用户基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Wuser wuser)
	{		
		return toAjax(wuserService.insertWuser(wuser));
	}

	/**
	 * 修改用户基本
	 */
	@GetMapping("/edit/{uid}")
	public String edit(@PathVariable("uid") Integer uid, ModelMap mmap)
	{
		Wuser wuser = wuserService.selectWuserById(uid);
		mmap.put("wuser", wuser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户基本
	 */
	@RequiresPermissions("village:wuser:edit")
	@Log(title = "用户基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Wuser wuser)
	{		
		return toAjax(wuserService.updateWuser(wuser));
	}
	
	/**
	 * 删除用户基本
	 */
	@RequiresPermissions("village:wuser:remove")
	@Log(title = "用户基本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(wuserService.deleteWuserByIds(ids));
	}
	
}
