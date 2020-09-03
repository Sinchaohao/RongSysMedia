package com.sixin.web.controller.broad;

import java.util.List;
import java.util.Map;

import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.system.domain.SysDept;
import com.sixin.system.domain.SysRole;
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
import com.sixin.broad.domain.Area;
import com.sixin.broad.service.IAreaService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
/**
 * 终端地域 信息操作处理
 *
 * @author 张超
 * @date 2019-01-17
 */
@Controller
@RequestMapping("/broad/area")
public class AreaController extends BaseController
{
    private String prefix = "broad/area";

	@Autowired
	private IAreaService areaService;

	@RequiresPermissions("broad:area:view")
	@GetMapping()
	public String area()
	{
	    return prefix + "/area";
	}

	/**
	 * 查询终端地域列表
	 */
	@RequiresPermissions("broad:area:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Area area)
	{
		startPage();
        List<Area> list = areaService.selectAreaList(area);
		return getDataTable(list);
	}


	/**
	 * 导出终端地域列表
	 */
	@RequiresPermissions("broad:area:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Area area)
    {
    	List<Area> list = areaService.selectAreaList(area);
        ExcelUtil<Area> util = new ExcelUtil<Area>(Area.class);
        return util.exportExcel(list, "area");
    }

	/**
	 * 新增终端地域
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 新增保存终端地域
	 */
	@RequiresPermissions("broad:area:add")
	@Log(title = "终端地域", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Area area)
	{
		return toAjax(areaService.insertArea(area));
	}

	/**
	 * 修改终端地域
	 */
	@GetMapping("/edit/{aid}")
	public String edit(@PathVariable("aid") long aid, ModelMap mmap)
	{
		Area area = areaService.selectAreaById(aid);
		mmap.put("area", area);
	    return prefix + "/edit";
	}

	/**
	 * 修改保存终端地域
	 */
	@RequiresPermissions("broad:area:edit")
	@Log(title = "终端地域", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Area area)
	{
		return toAjax(areaService.updateArea(area));
	}

	/**
	 * 删除终端地域
	 */
	@RequiresPermissions("broad:area:remove")
	@Log(title = "终端地域", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(long[] ids)
	{
		return toAjax(areaService.deleteAreaByIds(ids));
	}

	/**
	 * 选择部门树
	 */
	@GetMapping("/selectAreaTree/{AreaId}")
	public String selectAreaTree(@PathVariable("AreaId") long AreaId, ModelMap mmap)
	{
		mmap.put("area", areaService.selectAreaById(AreaId));
		return prefix + "/tree";
	}

	/**
	 * 加载部门列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData()
	{
		List<Map<String, Object>> tree = areaService.selectAreaTree(new Area());
		return tree;
	}


}
