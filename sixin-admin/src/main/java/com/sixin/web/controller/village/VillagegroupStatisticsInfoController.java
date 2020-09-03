package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.village.domain.VillagegroupStatisticsInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.VillagegroupStatisticsInfo;
import com.sixin.village.service.IVillagegroupStatisticsInfoService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 村组统计 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Controller
@RequestMapping("/village/villagegroupStatisticsInfo")
public class VillagegroupStatisticsInfoController extends BaseController
{
    private String prefix = "village/villagegroupStatisticsInfo";
	
	@Autowired
	private IVillagegroupStatisticsInfoService villagegroupStatisticsInfoService;
	
	@RequiresPermissions("village:villagegroupStatisticsInfo:view")
	@GetMapping()
	public String villagegroupStatisticsInfo()
	{
	    return prefix + "/villagegroupStatisticsInfo";
	}
	
	/**
	 * 查询村组统计列表
	 */
	@RequiresPermissions("village:villagegroupStatisticsInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VillagegroupStatisticsInfo villagegroupStatisticsInfo)
	{
		startPage();
        List<VillagegroupStatisticsInfo> list = villagegroupStatisticsInfoService.selectVillagegroupStatisticsInfoList(villagegroupStatisticsInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出村组统计列表
	 */
	@RequiresPermissions("village:villagegroupStatisticsInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VillagegroupStatisticsInfo villagegroupStatisticsInfo)
    {
    	List<VillagegroupStatisticsInfo> list = villagegroupStatisticsInfoService.selectVillagegroupStatisticsInfoList(villagegroupStatisticsInfo);
        ExcelUtil<VillagegroupStatisticsInfo> util = new ExcelUtil<VillagegroupStatisticsInfo>(VillagegroupStatisticsInfo.class);
        return util.exportExcel(list, "villagegroupStatisticsInfo");
    }

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<VillagegroupStatisticsInfo> util = new ExcelUtil<VillagegroupStatisticsInfo>(VillagegroupStatisticsInfo.class);
		return util.importTemplateExcel("村组统计");
	}

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 23:14
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:worklog:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<VillagegroupStatisticsInfo> list = villagegroupStatisticsInfoService.selectVillagegroupStatisticsInfoByIds(rows);
		ExcelUtil<VillagegroupStatisticsInfo> util = new ExcelUtil<VillagegroupStatisticsInfo>(VillagegroupStatisticsInfo.class);
		return util.exportExcel(list, "VillagegroupStatisticsInfo");
	}

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 23:14
	 * @Param [file, updateSupport]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<VillagegroupStatisticsInfo> util = new ExcelUtil<VillagegroupStatisticsInfo>(VillagegroupStatisticsInfo.class);
		List<VillagegroupStatisticsInfo> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<VillagegroupStatisticsInfo> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (VillagegroupStatisticsInfo user : userList) {
			try {
				villagegroupStatisticsInfoService.insertVillagegroupStatisticsInfo(user);
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
	 * 新增村组统计
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存村组统计
	 */
	@RequiresPermissions("village:villagegroupStatisticsInfo:add")
	@Log(title = "村组统计", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VillagegroupStatisticsInfo villagegroupStatisticsInfo)
	{		
		return toAjax(villagegroupStatisticsInfoService.insertVillagegroupStatisticsInfo(villagegroupStatisticsInfo));
	}

	/**
	 * 修改村组统计
	 */
	@GetMapping("/edit/{vgsid}")
	public String edit(@PathVariable("vgsid") Integer vgsid, ModelMap mmap)
	{
		VillagegroupStatisticsInfo villagegroupStatisticsInfo = villagegroupStatisticsInfoService.selectVillagegroupStatisticsInfoById(vgsid);
		mmap.put("villagegroupStatisticsInfo", villagegroupStatisticsInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存村组统计
	 */
	@RequiresPermissions("village:villagegroupStatisticsInfo:edit")
	@Log(title = "村组统计", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VillagegroupStatisticsInfo villagegroupStatisticsInfo)
	{		
		return toAjax(villagegroupStatisticsInfoService.updateVillagegroupStatisticsInfo(villagegroupStatisticsInfo));
	}
	
	/**
	 * 删除村组统计
	 */
	@RequiresPermissions("village:villagegroupStatisticsInfo:remove")
	@Log(title = "村组统计", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(villagegroupStatisticsInfoService.deleteVillagegroupStatisticsInfoByIds(ids));
	}
	
}
