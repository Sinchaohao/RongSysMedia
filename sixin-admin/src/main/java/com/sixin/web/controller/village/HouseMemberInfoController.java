package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.village.domain.HouseMemberInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.HouseMemberInfo;
import com.sixin.village.service.IHouseMemberInfoService;
import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 村户 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Controller
@RequestMapping("/village/houseMemberInfo")
public class HouseMemberInfoController extends BaseController
{
    private String prefix = "village/houseMemberInfo";
	
	@Autowired
	private IHouseMemberInfoService houseMemberInfoService;
	
	@RequiresPermissions("village:houseMemberInfo:view")
	@GetMapping()
	public String houseMemberInfo()
	{
	    return prefix + "/houseMemberInfo";
	}
	
	/**
	 * 查询村户列表
	 */
	@RequiresPermissions("village:houseMemberInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HouseMemberInfo houseMemberInfo)
	{
		startPage();
        List<HouseMemberInfo> list = houseMemberInfoService.selectHouseMemberInfoList(houseMemberInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出村户列表
	 */
	@RequiresPermissions("village:houseMemberInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HouseMemberInfo houseMemberInfo)
    {
    	List<HouseMemberInfo> list = houseMemberInfoService.selectHouseMemberInfoList(houseMemberInfo);
        ExcelUtil<HouseMemberInfo> util = new ExcelUtil<HouseMemberInfo>(HouseMemberInfo.class);
        return util.exportExcel(list, "houseMemberInfo");
    }

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 21:54
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:houseMemberInfo:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<HouseMemberInfo> list = houseMemberInfoService.selectHouseMemberInfoByIds(rows);
		ExcelUtil<HouseMemberInfo> util = new ExcelUtil<>(HouseMemberInfo.class);
		return util.exportExcel(list, "HouseMemberInfo");
	}

	/**
	 * @Author TanXY
	 * @Description 导入数据
	 * @Date 2020/4/17 21:54
	 * @Param [file, updateSupport]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<HouseMemberInfo> util = new ExcelUtil<>(HouseMemberInfo.class);
		List<HouseMemberInfo> userList = util.importExcel(file.getInputStream());
		String message = importUser(userList, updateSupport);
		return AjaxResult.success(message);
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<HouseMemberInfo> util = new ExcelUtil<>(HouseMemberInfo.class);
		return util.importTemplateExcel("村户管理");
	}

	/**
	 * 导入用户数据
	 *
	 * @param userList        用户数据列表
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
	 * @return 结果
	 */
	public String importUser(List<HouseMemberInfo> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (HouseMemberInfo user : userList) {
			try {
				houseMemberInfoService.insertHouseMemberInfo(user);
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
	 * 新增村户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存村户
	 */
	@RequiresPermissions("village:houseMemberInfo:add")
	@Log(title = "村户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HouseMemberInfo houseMemberInfo)
	{		
		return toAjax(houseMemberInfoService.insertHouseMemberInfo(houseMemberInfo));
	}

	/**
	 * 修改村户
	 */
	@GetMapping("/edit/{hmid}")
	public String edit(@PathVariable("hmid") Integer hmid, ModelMap mmap)
	{
		HouseMemberInfo houseMemberInfo = houseMemberInfoService.selectHouseMemberInfoById(hmid);
		mmap.put("houseMemberInfo", houseMemberInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存村户
	 */
	@RequiresPermissions("village:houseMemberInfo:edit")
	@Log(title = "村户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HouseMemberInfo houseMemberInfo)
	{		
		return toAjax(houseMemberInfoService.updateHouseMemberInfo(houseMemberInfo));
	}
	
	/**
	 * 删除村户
	 */
	@RequiresPermissions("village:houseMemberInfo:remove")
	@Log(title = "村户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(houseMemberInfoService.deleteHouseMemberInfoByIds(ids));
	}
	
}
