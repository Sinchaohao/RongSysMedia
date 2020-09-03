package com.sixin.web.controller.broad;

import java.util.List;

import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.broad.domain.Management;
import com.sixin.broad.service.IManagementService;
//import javax.xml.registry.infomodel.User;

/**
 * 终端管理 信息操作处理
 *
 * @author 张超
 * @date 2019-01-15
 */
@Controller
@RequestMapping("/broad/management")
public class ManagementController extends BaseController
{
	private String prefix = "broad/management";

	@Autowired
	private IManagementService managementService;
	@Autowired
	private ISysUserService sysUserService;
	@RequiresPermissions("broad:management:view")
	@GetMapping()
	public String management()
	{
		return prefix + "/management";
	}

	/**
	 * 查询终端管理列表
	 */
	@RequiresPermissions("broad:management:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Management management)
	{
		SysUser currentUser = ShiroUtils.getSysUser();//从session中获取当前登陆用户的userid
		Long userid =  currentUser.getUserId();
		//int returnId = new Long(userid).intValue();
		int roleid = sysUserService.selectRoleid(userid);//通过所获取的userid去广播用户表中查询用户所属区域的Roleid
		/*判断用户等级，若为超级管理员则可查看全部内容，否则只能查看自己的内容*/
//        if(roleid != 1){
//            aid = sysUserService.selectAid(returnId);//通过所获取的userid去广播用户表中查询用户所属区域的Aid
//            list = managementService.selectManagementList(aid);//通过所获取的Aid去查询用户所属区域对应的数据
//        }else
//            list = managementService.selectManagementList("");
//		return getDataTable(list);
		if(management.getAid() == null && (roleid == 1)) {
			startPage() ;
			List<Management> list = managementService.selectManagementList(management);
			return getDataTable(list);
		}else if(management.getAid() != null){
			startPage() ;
			List<Management> list = managementService.selectManagementList(management);
			return getDataTable(list);
		}else{
			String aid;
			aid = String.valueOf(sysUserService.selectAid(userid));//通过所获取的userid去广播用户表中查询用户所属区域的Aid
			management.setAid(aid);
			startPage() ;
			List<Management> list = managementService.selectManagementList(management);
			return getDataTable(list);
		}
	}


	/**
	 * 导出终端管理列表
	 */
	/*@RequiresPermissions("broad:management:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Management management)
    {
    	List<Management> list = managementService.selectManagementList(management);
        ExcelUtil<Management> util = new ExcelUtil<Management>(Management.class);
        return util.exportExcel(list, "management");
    }*/

	/**
	 * 新增终端管理
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存终端管理
	 */
	@RequiresPermissions("broad:management:add")
	@Log(title = "终端管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Management management)
	{
		return toAjax(managementService.insertManagement(management));
	}

	/**
	 * 修改终端管理
	 */
	@GetMapping("/edit/{tid}")
	public String edit(@PathVariable("tid") String tid, ModelMap mmap)
	{
		Management management = managementService.selectManagementById(tid);
		mmap.put("management", management);
		return prefix + "/edit";
	}

	/**
	 * 修改保存终端管理
	 */
	@RequiresPermissions("broad:management:edit")
	@Log(title = "终端管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Management management)
	{
		return toAjax(managementService.updateManagement(management));
	}

	/**
	 * 删除终端管理
	 */
	@RequiresPermissions("broad:management:remove")
	@Log(title = "终端管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(managementService.deleteManagementByIds(ids));
	}

}
