package com.sixin.web.controller.broad;

import com.sixin.broad.domain.*;
import com.sixin.broad.service.*;
import com.sixin.broad.utils.bFileUtil;
import com.sixin.common.annotation.Log;
import com.sixin.common.support.Convert;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
/**
 * 终端信息操作处理
 *
 * @author cx
 * @date 2019-09-21
 */
@Controller
@RequestMapping("/broad/organization")
public class OrganizationController extends BaseController
{
	private String prefix = "broad/organization";

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private IMessageService messageService;

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private IAreaService areaService;

	@Autowired
	IAreaGroupingService iAreaGroupingService;

	@RequiresPermissions("broad:organization:view")
	@GetMapping()
	public String organization()
	{
		return prefix + "/organization";
	}
//查询==========================================================================================================
	@RequiresPermissions("broad:organization:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Organization organization){
		startPage();
		List<Organization> list = organizationService.selectOrganizationList(organization);
		return getDataTable(list);
	}
//删除==========================================================================================================
	@Log(title = "终端信息删除", businessType = BusinessType.DELETE)
	@RequiresPermissions("broad:organization:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		List<Organization> organizations = organizationService.selectOrganizationListByTids(Convert.toStrArray(ids));
		for (Organization organization:organizations) {
			String filePathAndName = organization.getPoscene();
			if(filePathAndName != null){
				bFileUtil.delFile(filePathAndName);
			}
		}
		return toAjax(organizationService.deleteOrganizationByIds(Convert.toStrArray(ids)));
	}
//更新==========================================================================================================
	@GetMapping("/edit/{tid}")
	public String edit(@PathVariable("tid") String[] tid, ModelMap mmap){
		Organization organization = organizationService.selectOrganizationListByTids(tid).get(0);
		mmap.put("organization", organization);
		return prefix + "/edit";
	}
	/**
	 * 编辑保存终端信息
	 */
	@RequiresPermissions("broad:organization:edit")
	@Log(title = "终端信息修改", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Organization organization){
		int test = organizationService.updateOrganization(organization);
		if (test == 0){
			test = test + 1;
		}
		return toAjax(test);
	}
//新增===========================================================================================================
	@GetMapping("/add")
	public String add(){
		return prefix + "/add";
	}

	@Log(title = "新增终端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@RequiresPermissions("broad:organization:add")
	@ResponseBody
	public AjaxResult addSave(Organization organization){
		//获取上传的图片，保存
		MultipartFile pic= organization.getPoscenepic();
		String fileurl = bFileUtil.saveImg(pic );
		organization.setPoscene(fileurl);
		return toAjax(organizationService.insertOrganization(organization));
	}
//导出===========================================================================================================
	@Log(title = "终端管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("broad:organization:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Organization organization){
		List<Organization> list = organizationService.selectOrganizationList(organization);
		ExcelUtil<Organization> util = new ExcelUtil<Organization>(Organization.class);
		return util.exportExcel(list, "终端管理表");
	}

//根据Tids批量导出===========================================================================================================
	@Log(title = "终端管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("broad:organization:export")
	@PostMapping("/exportbysingle")
	@ResponseBody
	public AjaxResult exportOrganizationByTids(@RequestParam("sjids") String[] tids) {
		List<Organization> list = organizationService.selectOrganizationListByTids(tids);
		ExcelUtil<Organization> util = new ExcelUtil<Organization>(Organization.class);
		return util.exportExcel(list, "终端管理表");
	}


	@GetMapping("/listProBroadTree")
	@ResponseBody
	public List<Map<String, Object>> listProBroadTree(){
		List<Map<String, Object>> tree = messageService.selectMessageList(new BroadMessage());
		return tree;
	}

	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(){
		SysUser currentUser = ShiroUtils.getSysUser();//从session中获取当前登陆用户的userid
		Long userid =  currentUser.getUserId();
		//int returnId = new Long(userid).intValue();
		int roleid = sysUserService.selectRoleid(userid);//通过所获取的userid去广播用户表中查询用户所属区域的Roleid
		if(roleid == 1) {
			List<Map<String, Object>> tree = areaService.selectAreaTree(new Area());
			return tree;
		}else {
			long aid;
			aid =Long.valueOf(sysUserService.selectAid(userid));//通过所获取的userid去广播用户表中查询用户所属区域的Aid
			Area update_area = new Area();
			update_area.setAid(aid);
			List<Map<String, Object>> tree = areaService.selectAreaTree(update_area);
			return tree;
		}
	}
	/**
	 * 选择区域树
	 * @description 目前村务在调用此接口
	 */
	@GetMapping("/selectAidTree")
	public String selectAidTree()
	{
		return prefix + "/aidTree";
	}

	@GetMapping("/selectOrganizationTree/{aid}")
	public String selectOrganizationTree(@PathVariable("aid") long aid, ModelMap mmap) {
		mmap.put("organization", areaService.selectAreaById(aid));
		return prefix + "/listProBroadTree";
	}

	@GetMapping("/phoneEdit/{tid}")
	public String phoneEdit(@PathVariable("tid") String tid, ModelMap mmap){
		return prefix + "/phoneEdit";
	}

	@PostMapping("/phoneEdit/{tid}")
	@ResponseBody
	public List<TerminalTels> phoneEditpost(@PathVariable("tid") String tid, ModelMap mmap){
		List<TerminalTels> terminalTels= organizationService.selectTelsByTid(tid);
		mmap.put("terminalTels", terminalTels);
		return terminalTels;
	}

	@PostMapping("/addphoneedit")
	@ResponseBody
	public int addphoneEdit(TerminalTels terminalTels){
		return organizationService.addphoneEdit(terminalTels);
	}

	@GetMapping("/deletephoneedit/{telid}")
	@ResponseBody
	public String deletephoneedit(@PathVariable("telid") String telid){
		if(organizationService.deletephoneedit(telid)==1);
		return "操作成功";
	}

	@PostMapping("/listProBroad")
	@ResponseBody
	public TableDataInfo listProBroad(Organization organization){
		startPage() ;
		List<Organization> list = organizationService.selectProBroadList(organization);
		return getDataTable(list);
	}

	@PostMapping( "/isuseSet")
	@ResponseBody
	public AjaxResult isuseSet(String tid, Boolean isuse){
		return toAjax(organizationService.updateIsuseByTid(tid,isuse));
	}
}

