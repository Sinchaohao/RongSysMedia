package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.village.domain.PlowlandInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.PlowlandInfo;
import com.sixin.village.service.IPlowlandInfoService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 耕地 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Controller
@RequestMapping("/village/plowlandInfo")
public class PlowlandInfoController extends BaseController
{
    private String prefix = "village/plowlandInfo";
	
	@Autowired
	private IPlowlandInfoService plowlandInfoService;
	
	@RequiresPermissions("village:plowlandInfo:view")
	@GetMapping()
	public String plowlandInfo()
	{
	    return prefix + "/plowlandInfo";
	}
	
	/**
	 * 查询耕地列表
	 */
	@RequiresPermissions("village:plowlandInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PlowlandInfo plowlandInfo)
	{
		startPage();
        List<PlowlandInfo> list = plowlandInfoService.selectPlowlandInfoList(plowlandInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出耕地列表
	 */
	@RequiresPermissions("village:plowlandInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlowlandInfo plowlandInfo)
    {
    	List<PlowlandInfo> list = plowlandInfoService.selectPlowlandInfoList(plowlandInfo);
        ExcelUtil<PlowlandInfo> util = new ExcelUtil<PlowlandInfo>(PlowlandInfo.class);
        return util.exportExcel(list, "plowlandInfo");
    }

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<PlowlandInfo> util = new ExcelUtil<>(PlowlandInfo.class);
		return util.importTemplateExcel("耕地管理");
	}
	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 14:28
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:plowlandInfo:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<PlowlandInfo> list = plowlandInfoService.selectPlowlandInfoByIds(rows);
		ExcelUtil<PlowlandInfo> util = new ExcelUtil<>(PlowlandInfo.class);
		return util.exportExcel(list, "PlowlandInfo");
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
		ExcelUtil<PlowlandInfo> util = new ExcelUtil<>(PlowlandInfo.class);
		List<PlowlandInfo> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<PlowlandInfo> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (PlowlandInfo user : userList) {
			try {
				plowlandInfoService.insertPlowlandInfo(user);
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
	 * 新增耕地
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存耕地
	 */
	@RequiresPermissions("village:plowlandInfo:add")
	@Log(title = "耕地", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PlowlandInfo plowlandInfo)
	{		
		return toAjax(plowlandInfoService.insertPlowlandInfo(plowlandInfo));
	}

	/**
	 * 修改耕地
	 */
	@GetMapping("/edit/{pid}")
	public String edit(@PathVariable("pid") Integer pid, ModelMap mmap)
	{
		PlowlandInfo plowlandInfo = plowlandInfoService.selectPlowlandInfoById(pid);
		mmap.put("plowlandInfo", plowlandInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存耕地
	 */
	@RequiresPermissions("village:plowlandInfo:edit")
	@Log(title = "耕地", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PlowlandInfo plowlandInfo)
	{		
		return toAjax(plowlandInfoService.updatePlowlandInfo(plowlandInfo));
	}
	
	/**
	 * 删除耕地
	 */
	@RequiresPermissions("village:plowlandInfo:remove")
	@Log(title = "耕地", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(plowlandInfoService.deletePlowlandInfoByIds(ids));
	}
	
}
