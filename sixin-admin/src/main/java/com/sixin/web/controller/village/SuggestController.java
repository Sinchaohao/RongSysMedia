package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Meeting;
import com.sixin.village.domain.Suggest;
import com.sixin.village.service.ISuggestService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 我的建议 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-15
 */
@Controller
@RequestMapping("/village/suggest")
public class SuggestController extends BaseController
{
    private String prefix = "village/suggest";
	
	@Autowired
	private ISuggestService suggestService;
	@Autowired
	private ISysUserService sysUserService;
	@RequiresPermissions("village:suggest:view")
	@GetMapping()
	public String suggest()
	{
	    return prefix + "/suggest";
	}
	
	/**
	 * 查询我的建议列表
	 */
	@RequiresPermissions("village:suggest:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Suggest suggest)
	{
		SysUser currentUser = ShiroUtils.getSysUser();
		int userid = Integer.parseInt(String.valueOf(currentUser.getUserId()));
		suggest.setUid(userid);
		startPage();
        List<Suggest> list = suggestService.selectSuggestList(suggest);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出我的建议列表
	 */
	@RequiresPermissions("village:suggest:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Suggest suggest)
    {
    	List<Suggest> list = suggestService.selectSuggestList(suggest);
        ExcelUtil<Suggest> util = new ExcelUtil<Suggest>(Suggest.class);
        return util.exportExcel(list, "suggest");
    }

	/**
	 * @Author TanXY
	 * @Description 按需导出数据
	 * @Date 2020/4/18 9:20
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:suggest:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Suggest> list = suggestService.selectSuggestByIds(rows);
		ExcelUtil<Suggest> util = new ExcelUtil<Suggest>(Suggest.class);
		return util.exportExcel(list, "Suggest");
	}

	/**
	 * @Author TanXY
	 * @Description 导入数据
	 * @Date 2020/4/18 9:21
	 * @Param [file, updateSupport]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<Suggest> util = new ExcelUtil<Suggest>(Suggest.class);
		List<Suggest> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<Suggest> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Suggest user : userList) {
			try {
				suggestService.insertSuggest(user);
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
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Meeting> util = new ExcelUtil<Meeting>(Meeting.class);
		return util.importTemplateExcel("村情资讯");
	}
	
	/**
	 * 新增我的建议
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username =  currentUser.getUserName();
		Long userid =  currentUser.getUserId();
		String aid;
		//int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid =String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
		return prefix + "/add";
	}
	
	/**
	 * 新增保存我的建议
	 */
	@RequiresPermissions("village:suggest:add")
	@Log(title = "我的建议", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Suggest suggest)
	{		
		return toAjax(suggestService.insertSuggest(suggest));
	}

	/**
	 * 修改我的建议
	 */
	@GetMapping("/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, ModelMap mmap)
	{
		Suggest suggest = suggestService.selectSuggestById(mid);
		mmap.put("suggest", suggest);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存我的建议
	 */
	@RequiresPermissions("village:suggest:edit")
	@Log(title = "我的建议", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Suggest suggest)
	{		
		return toAjax(suggestService.updateSuggest(suggest));
	}
	
	/**
	 * 删除我的建议
	 */
	@RequiresPermissions("village:suggest:remove")
	@Log(title = "我的建议", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(suggestService.deleteSuggestByIds(ids));
	}
	
}
