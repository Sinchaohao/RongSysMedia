package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Memorial;
import com.sixin.village.domain.Worklog;
import com.sixin.village.service.IMemorialService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 备忘录 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-15
 */
@Controller
@RequestMapping("/village/memorial")
public class MemorialController extends BaseController
{
    private String prefix = "village/memorial";
	
	@Autowired
	private IMemorialService memorialService;
	@Autowired
	private ISysUserService sysUserService;
	@RequiresPermissions("village:memorial:view")
	@GetMapping()
	public String memorial()
	{
	    return prefix + "/memorial";
	}
	
	/**
	 * 查询备忘录列表
	 */
	@RequiresPermissions("village:memorial:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Memorial memorial)
	{
		SysUser currentUser = ShiroUtils.getSysUser();
		int userid = Integer.parseInt(String.valueOf(currentUser.getUserId()));
		memorial.setUid(userid);
		startPage();
        List<Memorial> list = memorialService.selectMemorialList(memorial);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出备忘录列表
	 */
	@RequiresPermissions("village:memorial:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Memorial memorial)
    {
    	List<Memorial> list = memorialService.selectMemorialList(memorial);
        ExcelUtil<Memorial> util = new ExcelUtil<Memorial>(Memorial.class);
        return util.exportExcel(list, "memorial");
    }

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/17 21:06
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:memorial:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Memorial> list = memorialService.selectMemorialByIds(rows);
		ExcelUtil<Memorial> util = new ExcelUtil<Memorial>(Memorial.class);
		return util.exportExcel(list, "Memorial");
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Memorial> util = new ExcelUtil<Memorial>(Memorial.class);
		return util.importTemplateExcel("备忘录");
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
		ExcelUtil<Memorial> util = new ExcelUtil<Memorial>(Memorial.class);
		List<Memorial> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<Memorial> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Memorial user : userList) {
			try {
				memorialService.insertMemorial(user);
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
	 * 新增备忘录
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
		aid = String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
		return prefix + "/add";
	}
	
	/**
	 * 新增保存备忘录
	 */
	@RequiresPermissions("village:memorial:add")
	@Log(title = "备忘录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Memorial memorial)
	{		
		return toAjax(memorialService.insertMemorial(memorial));
	}

	/**
	 * 修改备忘录
	 */
	@GetMapping("/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, ModelMap mmap)
	{
		Memorial memorial = memorialService.selectMemorialById(mid);
		mmap.put("memorial", memorial);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存备忘录
	 */
	@RequiresPermissions("village:memorial:edit")
	@Log(title = "备忘录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Memorial memorial)
	{		
		return toAjax(memorialService.updateMemorial(memorial));
	}
	
	/**
	 * 删除备忘录
	 */
	@RequiresPermissions("village:memorial:remove")
	@Log(title = "备忘录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memorialService.deleteMemorialByIds(ids));
	}
	
}
