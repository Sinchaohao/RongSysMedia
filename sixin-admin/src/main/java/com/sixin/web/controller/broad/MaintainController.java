package com.sixin.web.controller.broad;

import java.util.List;
import java.util.Map;

import com.sixin.broad.domain.BroadMessage;
import com.sixin.broad.service.IMessageService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.broad.domain.Maintain;
import com.sixin.broad.service.IMaintainService;
import com.sixin.common.page.TableDataInfo;


/**
 * 终端维护记录表 terminal_maintain
 *
 * @author 张鸿权
 * @date 2019-04-21
 */
@Controller
@RequestMapping("/broad/maintain")
public class MaintainController extends BaseController
{
    private String prefix = "broad/maintain";

	@Autowired
	private IMaintainService maintainService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IMessageService messageService;
	@RequiresPermissions("broad:maintain:view")
	@GetMapping()
	public String maintain()
	{
	    return prefix + "/maintain";
	}

	/**
	 * 查询终端维护记录列表
	 */
	@RequiresPermissions("broad:maintain:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Maintain maintain)
	{
		SysUser currentUser = ShiroUtils.getSysUser();//从session中获取当前登陆用户的userid
		Long userid =  currentUser.getUserId();
		int roleid = sysUserService.selectRoleid(userid);//通过所获取的userid去广播用户表中查询用户所属区域的Roleid
		if(roleid != 1){
			String aid;
			aid =String.valueOf(sysUserService.selectAid(userid));//通过所获取的userid去广播用户表中查询用户所属区域的Aid
			maintain.setAid(aid);
		}
		startPage();
        List<Maintain> list = maintainService.selectMaintainList(maintain);
		return getDataTable(list);
	}

	/**
	 * 添加用户信息
	 * @author CX
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username =  currentUser.getUserName();
//		String phone =  currentUser.getPhonenumber();
		Long userid =  currentUser.getUserId();
		String aid;
		//int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid =String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
//		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("fname", username);
//		mmap.put("fphone", phone);
		return prefix + "/add";
	}

	/**
	 * 新增保存终端维护记录
	 */
	@RequiresPermissions("broad:maintain:add")
	@Log(title = "终端维护记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Maintain maintain)
	{
		return toAjax(maintainService.insertMaintain(maintain));
	}

	/**
	 * 导出终端维护记录列表
	 */
	@RequiresPermissions("broad:maintain:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Maintain maintain)
    {
    	List<Maintain> list = maintainService.selectMaintainList(maintain);
        ExcelUtil<Maintain> util = new ExcelUtil<Maintain>(Maintain.class);
        return util.exportExcel(list, "maintain");
    }




	/**
	 * 修改终端维护记录
	 */
	@GetMapping("/edit/{tmid}")
	public String edit(@PathVariable("tmid") Integer tmid, ModelMap mmap)
	{
		Maintain maintain = maintainService.selectMaintainById(tmid);
		mmap.put("maintain", maintain);
	    return prefix + "/edit";
	}

	/**
	 * 修改保存终端维护记录
	 */
	@RequiresPermissions("broad:maintain:edit")
	@Log(title = "终端维护记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Maintain maintain)
	{
		return toAjax(maintainService.updateMaintain(maintain));
	}

	/**
	 * 删除终端维护记录
	 */
	@RequiresPermissions("broad:maintain:remove")
	@Log(title = "终端维护记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(maintainService.deleteMaintainByIds(ids));
	}

	/**
	 * 加载部门列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData() {
		List<Map<String, Object>> tree = messageService.selectMessageList((new BroadMessage()));
		return tree;
	}
	/**
	 * 终端维护记录详细
	 */
	@GetMapping("/detail/{id}")
	@Log(title = "终端维护记录详细")
	public String detail(@PathVariable("id") Integer tmid,ModelMap mmp)
	{
		mmp.put("listById",maintainService.selectMaintainById(tmid));
		return prefix + "/detail";
	}
}
