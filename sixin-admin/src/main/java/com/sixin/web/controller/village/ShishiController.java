package com.sixin.web.controller.village;

import java.util.List;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
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
import com.sixin.village.domain.Shishi;
import com.sixin.village.service.IShishiService;
import com.sixin.common.utils.poi.ExcelUtil;

/**
 * 实时政事 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
@Controller
@RequestMapping("/village/shishi")
public class ShishiController extends BaseController
{
    private String prefix = "village/shishi";
	
	@Autowired
	private IShishiService shishiService;
	
	@RequiresPermissions("village:shishi:view")
	@GetMapping()
	public String shishi()
	{
	    return prefix + "/shishi";
	}
	
	/**
	 * 查询实时政事列表
	 */
	@RequiresPermissions("village:shishi:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Shishi shishi)
	{
		startPage();
        List<Shishi> list = shishiService.selectShishiList(shishi);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出实时政事列表
	 */
	@RequiresPermissions("village:shishi:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Shishi shishi)
    {
    	List<Shishi> list = shishiService.selectShishiList(shishi);
        ExcelUtil<Shishi> util = new ExcelUtil<Shishi>(Shishi.class);
        return util.exportExcel(list, "shishi");
    }
	
	/**
	 * 新增实时政事
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存实时政事
	 */
	@RequiresPermissions("village:shishi:add")
	@Log(title = "实时政事", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Shishi shishi)
	{		
		return toAjax(shishiService.insertShishi(shishi));
	}

	/**
	 * 修改实时政事
	 */
	@GetMapping("/edit/{shiid}")
	public String edit(@PathVariable("shiid") Integer shiid, ModelMap mmap)
	{
		Shishi shishi = shishiService.selectShishiById(shiid);
		mmap.put("shishi", shishi);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存实时政事
	 */
	@RequiresPermissions("village:shishi:edit")
	@Log(title = "实时政事", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Shishi shishi)
	{		
		return toAjax(shishiService.updateShishi(shishi));
	}
	
	/**
	 * 删除实时政事
	 */
	@RequiresPermissions("village:shishi:remove")
	@Log(title = "实时政事", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(shishiService.deleteShishiByIds(ids));
	}
	
}
