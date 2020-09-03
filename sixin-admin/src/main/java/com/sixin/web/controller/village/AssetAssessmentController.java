package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.village.domain.Company;
import com.sixin.village.domain.Link;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.AssetAssessment;
import com.sixin.village.service.IAssetAssessmentService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资产清查 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Controller
@RequestMapping("/village/assetAssessment")
public class AssetAssessmentController extends BaseController
{
    private String prefix = "village/assetAssessment";
	
	@Autowired
	private IAssetAssessmentService assetAssessmentService;
	
	@RequiresPermissions("village:assetAssessment:view")
	@GetMapping()
	public String assetAssessment()
	{
	    return prefix + "/assetAssessment";
	}
	
	/**
	 * 查询资产清查列表
	 */
	@RequiresPermissions("village:assetAssessment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AssetAssessment assetAssessment)
	{
		startPage();
        List<AssetAssessment> list = assetAssessmentService.selectAssetAssessmentList(assetAssessment);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资产清查列表
	 */
	@RequiresPermissions("village:assetAssessment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AssetAssessment assetAssessment)
    {
    	List<AssetAssessment> list = assetAssessmentService.selectAssetAssessmentList(assetAssessment);
        ExcelUtil<AssetAssessment> util = new ExcelUtil<AssetAssessment>(AssetAssessment.class);
        return util.exportExcel(list, "assetAssessment");
    }



	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<AssetAssessment> util = new ExcelUtil<>(AssetAssessment.class);
		return util.importTemplateExcel("资产清查");
	}
	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 14:28
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:link:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<AssetAssessment> list = assetAssessmentService.selectAssetAssessmentByIds(rows);
		ExcelUtil<AssetAssessment> util = new ExcelUtil<>(AssetAssessment.class);
		return util.exportExcel(list, "AssetAssessment");
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
		ExcelUtil<AssetAssessment> util = new ExcelUtil<>(AssetAssessment.class);
		List<AssetAssessment> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<AssetAssessment> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (AssetAssessment user : userList) {
			try {
				assetAssessmentService.insertAssetAssessment(user);
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
	 * 新增资产清查
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资产清查
	 */
	@RequiresPermissions("village:assetAssessment:add")
	@Log(title = "资产清查", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AssetAssessment assetAssessment)
	{		
		return toAjax(assetAssessmentService.insertAssetAssessment(assetAssessment));
	}

	/**
	 * 修改资产清查
	 */
	@GetMapping("/edit/{aaid}")
	public String edit(@PathVariable("aaid") Integer aaid, ModelMap mmap)
	{
		AssetAssessment assetAssessment = assetAssessmentService.selectAssetAssessmentById(aaid);
		mmap.put("assetAssessment", assetAssessment);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资产清查
	 */
	@RequiresPermissions("village:assetAssessment:edit")
	@Log(title = "资产清查", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AssetAssessment assetAssessment)
	{		
		return toAjax(assetAssessmentService.updateAssetAssessment(assetAssessment));
	}
	
	/**
	 * 删除资产清查
	 */
	@RequiresPermissions("village:assetAssessment:remove")
	@Log(title = "资产清查", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(assetAssessmentService.deleteAssetAssessmentByIds(ids));
	}
	
}
