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
import com.sixin.village.domain.Filemanager;
import com.sixin.village.service.IFilemanagerService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.common.page.TableDataInfo;

/**
 * 文件管理 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
@Controller
@RequestMapping("/village/filemanager")
public class FilemanagerController extends BaseController
{
    private String prefix = "village/filemanager";
	
	@Autowired
	private IFilemanagerService filemanagerService;
	
	@RequiresPermissions("village:filemanager:view")
	@GetMapping()
	public String filemanager()
	{
	    return prefix + "/filemanager";
	}
	
	/**
	 * 查询文件管理列表
	 */
	@RequiresPermissions("village:filemanager:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Filemanager filemanager)
	{
		startPage();
        List<Filemanager> list = filemanagerService.selectFilemanagerList(filemanager);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出文件管理列表
	 */
	@RequiresPermissions("village:filemanager:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Filemanager filemanager)
    {
    	List<Filemanager> list = filemanagerService.selectFilemanagerList(filemanager);
        ExcelUtil<Filemanager> util = new ExcelUtil<Filemanager>(Filemanager.class);
        return util.exportExcel(list, "filemanager");
    }
	
	/**
	 * 新增文件管理
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存文件管理
	 */
	@RequiresPermissions("village:filemanager:add")
	@Log(title = "文件管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Filemanager filemanager)
	{		
		return toAjax(filemanagerService.insertFilemanager(filemanager));
	}

	/**
	 * 修改文件管理
	 */
	@GetMapping("/edit/{fileid}")
	public String edit(@PathVariable("fileid") Integer fileid, ModelMap mmap)
	{
		Filemanager filemanager = filemanagerService.selectFilemanagerById(fileid);
		mmap.put("filemanager", filemanager);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存文件管理
	 */
	@RequiresPermissions("village:filemanager:edit")
	@Log(title = "文件管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Filemanager filemanager)
	{		
		return toAjax(filemanagerService.updateFilemanager(filemanager));
	}
	
	/**
	 * 删除文件管理
	 */
	@RequiresPermissions("village:filemanager:remove")
	@Log(title = "文件管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(filemanagerService.deleteFilemanagerByIds(ids));
	}
	
}
