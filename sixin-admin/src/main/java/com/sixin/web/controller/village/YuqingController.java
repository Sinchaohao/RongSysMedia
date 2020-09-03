package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.village.domain.Company;
import com.sixin.village.domain.Project;
import com.sixin.village.domain.Yuqing;
import com.sixin.village.service.IYuqingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 舆情上报 信息操作处理
 *
 * @author 张鸿权
 * @date 2019-08-18
 */
@Controller
@RequestMapping("/village/yuqing")
public class YuqingController extends BaseController
{
	private String prefix = "village/yuqing";

	@Autowired
	private IYuqingService yuqingService;

	@RequiresPermissions("village:yuqing:view")
	@GetMapping()
	public String yuqing()
	{
		return prefix + "/yuqing";
	}

	/**
	 * 查询舆情上报列表
	 */
	@RequiresPermissions("village:yuqing:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Yuqing yuqing)
	{
		startPage();
		List<Yuqing> list = yuqingService.selectYuqingList(yuqing);
		return getDataTable(list);
	}

	/**
	 * 新增舆情上报
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存舆情上报
	 */
	@RequiresPermissions("village:yuqing:add")
	@Log(title = "舆情上报", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Yuqing yuqing)
	{
		return toAjax(yuqingService.insertYuqing(yuqing));
	}

	/**
	 * 修改舆情上报
	 */
	@GetMapping("/edit/{yqid}")
	public String edit(@PathVariable("yqid") Integer yqid, ModelMap mmap)
	{
		Yuqing yuqing = yuqingService.selectYuqingById(yqid);
		mmap.put("yuqing", yuqing);
		return prefix + "/edit";
	}

	/**
	 * 修改保存舆情上报
	 */
	@RequiresPermissions("village:yuqing:edit")
	@Log(title = "舆情上报", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Yuqing yuqing)
	{
		return toAjax(yuqingService.updateYuqing(yuqing));
	}

	/**
	 * 删除舆情上报
	 */
	@RequiresPermissions("village:yuqing:remove")
	@Log(title = "舆情上报", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(yuqingService.deleteYuqingByIds(ids));
	}

	@PostMapping( "/isuseSet")
	@ResponseBody
	public AjaxResult isuseSet(String yqid, String remark)
	{
		return toAjax(yuqingService.updateIsuseByYqid(yqid,remark));
	}




	/**
	 * 导入数据
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<Yuqing> util = new ExcelUtil<Yuqing>(Yuqing.class);
		List<Yuqing> YuqingList = util.importExcel(file.getInputStream());
		String message = importUser(YuqingList, updateSupport);
		return AjaxResult.success(message);
	}

	/**
	 * 导入用户数据
	 *
	 * @param
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
	 * @return 结果
	 */
	public String importUser(List<Yuqing> YuqingList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(YuqingList) || YuqingList.size() == 0) {
			throw new BusinessException("导入数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Yuqing Yuqing : YuqingList) {
			try {
				yuqingService.insertYuqing(Yuqing);
				successNum++;
				successMsg.append("<br/>" + successNum + "舆情 " + Yuqing.getUname() + " 导入成功");
			} catch (Exception e) {
				String msg = Yuqing.getUname() + " 导入失败：";
				failureMsg.append(msg + e.getMessage());
			}
		}
		return successMsg.toString();
	}

	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Yuqing> util = new ExcelUtil<Yuqing>(Yuqing.class);
		return util.importTemplateExcel("舆情信息导出");
	}

	/**
	 * 导出
	 */
	@Log(title = "舆情信息导出", businessType = BusinessType.EXPORT)
	@RequiresPermissions("village:yuqing:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Yuqing yuqing)
	{
		List<Yuqing> list = yuqingService.selectYuqingList(yuqing);
		ExcelUtil<Yuqing> util = new ExcelUtil<Yuqing>(Yuqing.class);
		return util.exportExcel(list, "舆情信息导出");
	}

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 11:19
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:worklog:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Yuqing> list = yuqingService.selectYuqingByIds(rows);
		ExcelUtil<Yuqing> util = new ExcelUtil<Yuqing>(Yuqing.class);
		return util.exportExcel(list, "舆情信息");
	}


	@Log(title = "企业信息", businessType = BusinessType.UPDATE)
	@RequiresPermissions("village:yuqing:edit")
	@PostMapping("/changeStatus")
	@ResponseBody
	public AjaxResult changeStatus(Yuqing yuqing)
	{
		return toAjax(yuqingService.updateYuqing(yuqing));
	}
}


