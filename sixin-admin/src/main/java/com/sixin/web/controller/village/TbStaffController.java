package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.village.domain.TbStaff;
import com.sixin.village.domain.VillagerInfo;
import com.sixin.village.service.ITbStaffService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 工作员 信息操作处理
 *
 * @author 张鸿权
 * @date 2019-11-03
 */
@Controller
@RequestMapping("/village/tbStaff")
public class TbStaffController extends BaseController
{
	private String prefix = "village/tbStaff";

	@Autowired
	private ITbStaffService tbStaffService;

	@RequiresPermissions("village:tbStaff:view")
	@GetMapping()
	public String tbStaff()
	{
		return prefix + "/tbStaff";
	}

	/**
	 * 查询工作员列表
	 */
	@RequiresPermissions("village:tbStaff:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TbStaff tbStaff)
	{
		startPage();
		List<TbStaff> list = tbStaffService.selectTbStaffList(tbStaff);
		return getDataTable(list);
	}


	/**
	 * 导出工作员列表
	 */
	@RequiresPermissions("village:tbStaff:export")
	@PostMapping("/exportbysingle")
	@ResponseBody
	public AjaxResult export( @RequestParam("rows") List<String> rows)//TbStaff tbStaff
	{
		//List<TbStaff> list = tbStaffService.selectTbStaffList(tbStaff);
		List<TbStaff> list = tbStaffService.selectTbStaffByIds(rows);
		ExcelUtil<TbStaff> util = new ExcelUtil<TbStaff>(TbStaff.class);
		return util.exportExcel(list, "tbStaff");
	}

	@RequiresPermissions("village:tbStaff:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export( TbStaff tbStaff)//TbStaff tbStaff
	{
		List<TbStaff> list = tbStaffService.selectTbStaffList(tbStaff);
		//List<TbStaff> list = tbStaffService.selectTbStaffByIds(rows);
		ExcelUtil<TbStaff> util = new ExcelUtil<TbStaff>(TbStaff.class);
		return util.exportExcel(list, "tbStaff");
	}


	@Log(title = "村民", businessType = BusinessType.UPDATE)
	@RequiresPermissions("village:tbStaff:edit")
	@PostMapping("/changeStatus")
	@ResponseBody
	public AjaxResult changeStatus(TbStaff tbStaff )
	{
		return toAjax(tbStaffService.updateTbStaff(tbStaff));
	}
	/**
	 * 新增工作员
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存工作员
	 */
	@RequiresPermissions("village:tbStaff:add")
	@Log(title = "工作员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TbStaff tbStaff)
	{
		return toAjax(tbStaffService.insertTbStaff(tbStaff));
	}

	/**
	 * 修改工作员
	 */
	@GetMapping("/edit/{stid}")
	public String edit(@PathVariable("stid") String stid, ModelMap mmap)
	{
		TbStaff tbStaff = tbStaffService.selectTbStaffById(stid);
		mmap.put("tbStaff", tbStaff);
		return prefix + "/edit";
	}

	/**
	 * 修改保存工作员
	 */
	@RequiresPermissions("village:tbStaff:edit")
	@Log(title = "工作员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbStaff tbStaff)
	{
		return toAjax(tbStaffService.updateTbStaff(tbStaff));
	}

	/**
	 * 删除工作员
	 */
	@RequiresPermissions("village:tbStaff:remove")
	@Log(title = "工作员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(tbStaffService.deleteTbStaffByIds(ids));
	}


	/**
	 * 导入数据
	 */
	@PostMapping("/importData")
	@ResponseBody
	public com.sixin.common.base.AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<TbStaff> util = new ExcelUtil<TbStaff>(TbStaff.class);
		List<TbStaff> userList = util.importExcel(file.getInputStream());
		String message = importUser(userList, updateSupport);
		return com.sixin.common.base.AjaxResult.success(message);
	}

	/**
	 * 导入用户数据
	 *
	 * @param userList        用户数据列表
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
	 * @return 结果
	 */
	public String importUser(List<TbStaff> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (TbStaff user : userList) {
			try {
				tbStaffService.insertTbStaff(user);
				successNum++;
				successMsg.append("<br/>" + successNum + "用户 " + user.getUsername() + " 导入成功");
			} catch (Exception e) {
				String msg = user.getUsername() + " 导入失败：";
				failureMsg.append(msg + e.getMessage());
			}
		}
		return successMsg.toString();
	}

	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<TbStaff> util = new ExcelUtil<TbStaff>(TbStaff.class);
		return util.importTemplateExcel("用户数据");
	}

}
