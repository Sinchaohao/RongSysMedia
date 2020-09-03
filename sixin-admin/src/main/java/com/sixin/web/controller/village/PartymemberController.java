package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.village.domain.Partymember;
import com.sixin.village.domain.Partynew;
import com.sixin.village.service.IPartymemberService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 暮云党员 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
@Controller
@RequestMapping("/village/partymember")
public class PartymemberController extends BaseController
{
    private String prefix = "village/partymember";
	
	@Autowired
	private IPartymemberService partymemberService;
	
	@RequiresPermissions("village:partymember:view")
	@GetMapping()
	public String partymember()
	{
	    return prefix + "/partymember";
	}
	
	/**
	 * 查询暮云党员列表
	 */
	@RequiresPermissions("village:partymember:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Partymember partymember)
	{
		startPage();
        List<Partymember> list = partymemberService.selectPartymemberList(partymember);
		return getDataTable(list);
	}

	
	
	/**
	 * 导出暮云党员列表
	 */
	@RequiresPermissions("village:partymember:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Partymember partymember)
    {
    	List<Partymember> list = partymemberService.selectPartymemberList(partymember);
        ExcelUtil<Partymember> util = new ExcelUtil<Partymember>(Partymember.class);
        return util.exportExcel(list, "partymember");
    }

    /**
     * @Author TanXY
     * @Description
     * @Date 2020/4/18 18:57
     * @Param [rows]
     * @return com.sixin.common.base.AjaxResult
     */
	@RequiresPermissions("village:partymenber:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Partymember> list = partymemberService.selectPartymemberByIds(rows);
		ExcelUtil<Partymember> util = new ExcelUtil<>(Partymember.class);
		return util.exportExcel(list, "Partymember");
	}

	
	/**
	 * 新增暮云党员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存暮云党员
	 */
	@RequiresPermissions("village:partymember:add")
	@Log(title = "暮云党员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Partymember partymember)
	{		
		return toAjax(partymemberService.insertPartymember(partymember));
	}

	/**
	 * 修改暮云党员
	 */
	@GetMapping("/edit/{pid}")
	public String edit(@PathVariable("pid") Integer pid, ModelMap mmap)
	{
		Partymember partymember = partymemberService.selectPartymemberById(pid);
		mmap.put("partymember", partymember);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存暮云党员
	 */
	@RequiresPermissions("village:partymember:edit")
	@Log(title = "暮云党员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Partymember partymember)
	{		
		return toAjax(partymemberService.updatePartymember(partymember));
	}
	
	/**
	 * 删除暮云党员
	 */
	@RequiresPermissions("village:partymember:remove")
	@Log(title = "暮云党员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partymemberService.deletePartymemberByIds(ids));
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate()
	{
		ExcelUtil<Partymember> util = new ExcelUtil<Partymember>(Partymember.class);
		return util.importTemplateExcel("用户数据");
	}

	/**
	 * 导入数据
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
	{
		ExcelUtil<Partymember> util = new ExcelUtil<Partymember>(Partymember.class);
		List<Partymember> partymemberList = util.importExcel(file.getInputStream());
		String message = importUser(partymemberList, updateSupport);
		return AjaxResult.success(message);
	}

	/**
	 * 导入用户数据
	 *
	 * @param partymemberList 用户数据列表
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
	 * @return 结果
	 */
	public String importUser(List<Partymember> partymemberList, Boolean isUpdateSupport)
	{
		if (StringUtils.isNull(partymemberList) || partymemberList.size() == 0)
		{
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Partymember  partymember: partymemberList)
		{
			try{
				partymemberService.insertPartymember(partymember);
				successNum++;
				successMsg.append("<br/>" + successNum + "用户 " + partymember.getUname() + " 导入成功");
			}
			catch (Exception e)
			{
				String msg = partymember.getUname() + " 导入失败：";
				failureMsg.append(msg + e.getMessage());
			}
		}
		return successMsg.toString();
	}




	/**
	 * 打开项目详情页
	 */
	@GetMapping("/detail/{pid}")
	public String detail(@PathVariable("pid")Integer pid,ModelMap mmap)
	{
		mmap.put("listByid",partymemberService.selectPartymemberById(pid));
		return prefix + "/detail";
	}
}
