package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.village.domain.AssetAssessment;
import com.sixin.village.domain.HouseInfo;
import com.sixin.village.domain.ForestlandInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.HouseInfo;
import com.sixin.village.service.IHouseInfoService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 房屋 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Controller
@RequestMapping("/village/houseInfo")
public class HouseInfoController extends BaseController
{
    private String prefix = "village/houseInfo";
	
	@Autowired
	private IHouseInfoService houseInfoService;
	
	@RequiresPermissions("village:houseInfo:view")
	@GetMapping()
	public String houseInfo()
	{
	    return prefix + "/houseInfo";
	}
	
	/**
	 * 查询房屋列表
	 */
	@RequiresPermissions("village:houseInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HouseInfo houseInfo)
	{
		startPage();
        List<HouseInfo> list = houseInfoService.selectHouseInfoList(houseInfo);
		return getDataTable(list);
	}


	
	/**
	 * 导出房屋列表
	 */
	@RequiresPermissions("village:houseInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HouseInfo houseInfo)
    {
    	List<HouseInfo> list = houseInfoService.selectHouseInfoList(houseInfo);
        ExcelUtil<HouseInfo> util = new ExcelUtil<HouseInfo>(HouseInfo.class);
        return util.exportExcel(list, "houseInfo");
    }

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 21:54
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:houseInfo:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<HouseInfo> list = houseInfoService.selectHouseInfoByIds(rows);
		ExcelUtil<HouseInfo> util = new ExcelUtil<>(HouseInfo.class);
		return util.exportExcel(list, "HouseInfo");
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
		ExcelUtil<HouseInfo> util = new ExcelUtil<>(HouseInfo.class);
		List<HouseInfo> userList = util.importExcel(file.getInputStream());
		String message = importUser(userList, updateSupport);
		return AjaxResult.success(message);
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<HouseInfo> util = new ExcelUtil<>(HouseInfo.class);
		return util.importTemplateExcel("房屋管理");
	}

	/**
	 * 导入用户数据
	 *
	 * @param userList        用户数据列表
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
	 * @return 结果
	 */
	public String importUser(List<HouseInfo> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (HouseInfo user : userList) {
			try {
				houseInfoService.insertHouseInfo(user);
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
	 * 新增房屋
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存房屋
	 */
	@RequiresPermissions("village:houseInfo:add")
	@Log(title = "房屋", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HouseInfo houseInfo)
	{		
		return toAjax(houseInfoService.insertHouseInfo(houseInfo));
	}

	/**
	 * 修改房屋
	 */
	@GetMapping("/edit/{hid}")
	public String edit(@PathVariable("hid") Integer hid, ModelMap mmap)
	{
		HouseInfo houseInfo = houseInfoService.selectHouseInfoById(hid);
		mmap.put("houseInfo", houseInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存房屋
	 */
	@RequiresPermissions("village:houseInfo:edit")
	@Log(title = "房屋", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HouseInfo houseInfo)
	{		
		return toAjax(houseInfoService.updateHouseInfo(houseInfo));
	}
	
	/**
	 * 删除房屋
	 */
	@RequiresPermissions("village:houseInfo:remove")
	@Log(title = "房屋", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(houseInfoService.deleteHouseInfoByIds(ids));
	}
	
}
