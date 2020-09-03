package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.village.domain.Partynew;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.Partystudy;
import com.sixin.village.service.IPartystudyService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 党员学习 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
@Controller
@RequestMapping("/village/partystudy")
public class PartystudyController extends BaseController
{
    private String prefix = "village/partystudy";
	
	@Autowired
	private IPartystudyService partystudyService;
	
	@RequiresPermissions("village:partystudy:view")
	@GetMapping()
	public String partystudy()
	{
	    return prefix + "/partystudy";
	}
	
	/**
	 * 查询党员学习列表
	 */
	@RequiresPermissions("village:partystudy:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Partystudy partystudy)
	{
		startPage();
        List<Partystudy> list = partystudyService.selectPartystudyList(partystudy);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出党员学习列表
	 */
	@RequiresPermissions("village:partystudy:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Partystudy partystudy)
    {
    	List<Partystudy> list = partystudyService.selectPartystudyList(partystudy);
        ExcelUtil<Partystudy> util = new ExcelUtil<Partystudy>(Partystudy.class);
        return util.exportExcel(list, "partystudy");
    }

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 16:57
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:partynew:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Partystudy> list = partystudyService.selectPartystudyByIds(rows);
		ExcelUtil<Partystudy> util = new ExcelUtil<>(Partystudy.class);
		return util.exportExcel(list, "Partystudy");
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Partystudy> util = new ExcelUtil<>(Partystudy.class);
		return util.importTemplateExcel("党员学习");
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
		ExcelUtil<Partystudy> util = new ExcelUtil<>(Partystudy.class);
		List<Partystudy> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<Partystudy> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Partystudy user : userList) {
			try {
				partystudyService.insertPartystudy(user);
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
	 * 新增党员学习
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存党员学习
	 */
	@RequiresPermissions("village:partystudy:add")
	@Log(title = "党员学习", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Partystudy partystudy)
	{		
		return toAjax(partystudyService.insertPartystudy(partystudy));
	}

	/**
	 * 修改党员学习
	 */
	@GetMapping("/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, ModelMap mmap)
	{
		Partystudy partystudy = partystudyService.selectPartystudyById(mid);
		mmap.put("partystudy", partystudy);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存党员学习
	 */
	@RequiresPermissions("village:partystudy:edit")
	@Log(title = "党员学习", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Partystudy partystudy)
	{		
		return toAjax(partystudyService.updatePartystudy(partystudy));
	}
	
	/**
	 * 删除党员学习
	 */
	@RequiresPermissions("village:partystudy:remove")
	@Log(title = "党员学习", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partystudyService.deletePartystudyByIds(ids));
	}
	
}
