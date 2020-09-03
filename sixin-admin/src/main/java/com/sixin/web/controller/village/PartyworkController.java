package com.sixin.web.controller.village;

import java.util.List;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.village.domain.Partystudy;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.Partywork;
import com.sixin.village.service.IPartyworkService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 党员值班 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
@Controller
@RequestMapping("/village/partywork")
public class PartyworkController extends BaseController
{
    private String prefix = "village/partywork";
	
	@Autowired
	private IPartyworkService partyworkService;
	
	@RequiresPermissions("village:partywork:view")
	@GetMapping()
	public String partywork()
	{
	    return prefix + "/partywork";
	}
	
	/**
	 * 查询党员值班列表
	 */
	@RequiresPermissions("village:partywork:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Partywork partywork)
	{
		startPage();
        List<Partywork> list = partyworkService.selectPartyworkList(partywork);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出党员值班列表
	 */
	@RequiresPermissions("village:partywork:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Partywork partywork)
    {
    	List<Partywork> list = partyworkService.selectPartyworkList(partywork);
        ExcelUtil<Partywork> util = new ExcelUtil<Partywork>(Partywork.class);
        return util.exportExcel(list, "partywork");
    }

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 19:45
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:partynew:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Partywork> list = partyworkService.selectPartyworkByIds(rows);
		ExcelUtil<Partywork> util = new ExcelUtil<>(Partywork.class);
		return util.exportExcel(list, "Partywork");
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Partywork> util = new ExcelUtil<>(Partywork.class);
		return util.importTemplateExcel("党员值班");
	}

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 19:44
	 * @Param [file, updateSupport]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<Partywork> util = new ExcelUtil<>(Partywork.class);
		List<Partywork> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<Partywork> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Partywork user : userList) {
			try {
				partyworkService.insertPartywork(user);
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
	 * 新增党员值班
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存党员值班
	 */
	@RequiresPermissions("village:partywork:add")
	@Log(title = "党员值班", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Partywork partywork)
	{		
		return toAjax(partyworkService.insertPartywork(partywork));
	}

	/**
	 * 修改党员值班
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Partywork partywork = partyworkService.selectPartyworkById(id);
		mmap.put("partywork", partywork);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存党员值班
	 */
	@RequiresPermissions("village:partywork:edit")
	@Log(title = "党员值班", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Partywork partywork)
	{		
		return toAjax(partyworkService.updatePartywork(partywork));
	}
	
	/**
	 * 删除党员值班
	 */
	@RequiresPermissions("village:partywork:remove")
	@Log(title = "党员值班", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partyworkService.deletePartyworkByIds(ids));
	}
	
}
