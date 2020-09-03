package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.village.domain.Memorial;
import com.sixin.village.domain.Partymember;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.Partyfupin;
import com.sixin.village.service.IPartyfupinService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 扶贫工作 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
@Controller
@RequestMapping("/village/partyfupin")
public class PartyfupinController extends BaseController
{
    private String prefix = "village/partyfupin";
	
	@Autowired
	private IPartyfupinService partyfupinService;
	
	@RequiresPermissions("village:partyfupin:view")
	@GetMapping()
	public String partyfupin()
	{
	    return prefix + "/partyfupin";
	}
	
	/**
	 * 查询扶贫工作列表
	 */
	@RequiresPermissions("village:partyfupin:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Partyfupin partyfupin)
	{
		startPage();
        List<Partyfupin> list = partyfupinService.selectPartyfupinList(partyfupin);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出扶贫工作列表
	 */
	@RequiresPermissions("village:partyfupin:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Partyfupin partyfupin)
    {
    	List<Partyfupin> list = partyfupinService.selectPartyfupinList(partyfupin);
        ExcelUtil<Partyfupin> util = new ExcelUtil<Partyfupin>(Partyfupin.class);
        return util.exportExcel(list, "partyfupin");
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
		List<Partyfupin> list = partyfupinService.selectFupinByIds(rows);
		ExcelUtil<Partyfupin> util = new ExcelUtil<>(Partyfupin.class);
		return util.exportExcel(list, "Partyfupin");
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Partyfupin> util = new ExcelUtil<>(Partyfupin.class);
		return util.importTemplateExcel("扶贫工作");
	}

	/**
	 * @Author TanXY
	 * @Description 导入数据
	 * @Date 2020/4/17 22:21
	 * @Param [file, updateSupport]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<Partyfupin> util = new ExcelUtil<Partyfupin>(Partyfupin.class);
		List<Partyfupin> userList = util.importExcel(file.getInputStream());
		String message = importUser(userList, updateSupport);
		return AjaxResult.success(message);
	}

	/**
	 * 导入用户数据
	 *
	 * @param userList        用户数据列表
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
	 * @return 结果
	 */
	public String importUser(List<Partyfupin> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Partyfupin user : userList) {
			try {
				partyfupinService.insertPartyfupin(user);
				successNum++;
				successMsg.append("<br/>" + successNum + "导入成功");
			} catch (Exception e) {
				String msg = "导入失败：";
				failureMsg.append(msg + e.getMessage());
			}
		}
		return successMsg.toString();
	}

	
	/**
	 * 新增扶贫工作
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存扶贫工作
	 */
	@RequiresPermissions("village:partyfupin:add")
	@Log(title = "扶贫工作", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Partyfupin partyfupin)
	{		
		return toAjax(partyfupinService.insertPartyfupin(partyfupin));
	}

	/**
	 * 修改扶贫工作
	 */
	@GetMapping("/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, ModelMap mmap)
	{
		Partyfupin partyfupin = partyfupinService.selectPartyfupinById(mid);
		mmap.put("partyfupin", partyfupin);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存扶贫工作
	 */
	@RequiresPermissions("village:partyfupin:edit")
	@Log(title = "扶贫工作", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Partyfupin partyfupin)
	{		
		return toAjax(partyfupinService.updatePartyfupin(partyfupin));
	}
	
	/**
	 * 删除扶贫工作
	 */
	@RequiresPermissions("village:partyfupin:remove")
	@Log(title = "扶贫工作", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partyfupinService.deletePartyfupinByIds(ids));
	}
	
}
