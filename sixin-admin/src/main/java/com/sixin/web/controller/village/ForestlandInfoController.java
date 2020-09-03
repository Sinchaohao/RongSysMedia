package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.village.domain.AssetAssessment;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.ForestlandInfo;
import com.sixin.village.service.IForestlandInfoService;
import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 林地 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Controller
@RequestMapping("/village/forestlandInfo")
public class ForestlandInfoController extends BaseController
{
    private String prefix = "village/forestlandInfo";
	
	@Autowired
	private IForestlandInfoService forestlandInfoService;
	
	@RequiresPermissions("village:forestlandInfo:view")
	@GetMapping()
	public String forestlandInfo()
	{
	    return prefix + "/forestlandInfo";
	}
	
	/**
	 * 查询林地列表
	 */
	@RequiresPermissions("village:forestlandInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ForestlandInfo forestlandInfo)
	{
		startPage();
        List<ForestlandInfo> list = forestlandInfoService.selectForestlandInfoList(forestlandInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出林地列表
	 */
	@RequiresPermissions("village:forestlandInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ForestlandInfo forestlandInfo)
    {
    	List<ForestlandInfo> list = forestlandInfoService.selectForestlandInfoList(forestlandInfo);
        ExcelUtil<ForestlandInfo> util = new ExcelUtil<ForestlandInfo>(ForestlandInfo.class);
        return util.exportExcel(list, "forestlandInfo");
    }
	
	/**
	 * 新增林地
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存林地
	 */
	@RequiresPermissions("village:forestlandInfo:add")
	@Log(title = "林地", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ForestlandInfo forestlandInfo)
	{		
		return toAjax(forestlandInfoService.insertForestlandInfo(forestlandInfo));
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<ForestlandInfo> util = new ExcelUtil<>(ForestlandInfo.class);
		return util.importTemplateExcel("林地管理");
	}

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 21:55
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:link:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<ForestlandInfo> list = forestlandInfoService.selectForestlandByIds(rows);
		ExcelUtil<ForestlandInfo> util = new ExcelUtil<>(ForestlandInfo.class);
		return util.exportExcel(list, "ForestlandInfo");
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
		ExcelUtil<ForestlandInfo> util = new ExcelUtil<>(ForestlandInfo.class);
		List<ForestlandInfo> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<ForestlandInfo> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (ForestlandInfo user : userList) {
			try {
				forestlandInfoService.insertForestlandInfo(user);
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
	 * 修改林地
	 */
	@GetMapping("/edit/{fid}")
	public String edit(@PathVariable("fid") Integer fid, ModelMap mmap)
	{
		ForestlandInfo forestlandInfo = forestlandInfoService.selectForestlandInfoById(fid);
		mmap.put("forestlandInfo", forestlandInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存林地
	 */
	@RequiresPermissions("village:forestlandInfo:edit")
	@Log(title = "林地", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ForestlandInfo forestlandInfo)
	{		
		return toAjax(forestlandInfoService.updateForestlandInfo(forestlandInfo));
	}
	
	/**
	 * 删除林地
	 */
	@RequiresPermissions("village:forestlandInfo:remove")
	@Log(title = "林地", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(forestlandInfoService.deleteForestlandInfoByIds(ids));
	}
	
}
