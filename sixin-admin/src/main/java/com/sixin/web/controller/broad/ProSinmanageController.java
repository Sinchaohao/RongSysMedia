package com.sixin.web.controller.broad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.sixin.broad.domain.*;
import com.sixin.broad.service.IProChamanageService;
import com.sixin.broad.service.IProListService;
import com.sixin.broad.service.IProgramService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.json.JSONObject;
import com.sixin.common.utils.DateUtil;
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
import com.sixin.broad.service.IProSinmanageService;
import javax.websocket.server.PathParam;

/**
 * 节目播出单 信息操作处理
 *
 * @author 张超
 * @date 2019-03-02
 */
@Controller
@RequestMapping("/broad/proSinmanage")
public class ProSinmanageController extends BaseController
{
	private String prefix = "broad/proSinmanage";

	@Autowired
	private IProSinmanageService proSinmanageService;
	@Autowired
	private IProListService proListService;
	@Autowired
	private IProgramService iProgramService;
	@Autowired
	private IProChamanageService iProChamanageService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IProSinmanageService ProSinmanageService;
	@RequiresPermissions("broad:proSinmanage:view")
	@GetMapping()
	public String proSinmanage()
	{
		return prefix + "/proSinmanage";
	}

	@RequiresPermissions("broad:proSinmanage:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProSinmanage proSinmanage)
	{
		SysUser currentUser = ShiroUtils.getSysUser();
		Long userid =  currentUser.getUserId();
		int roleid = sysUserService.selectRoleid(userid);
		if(roleid == 1) {
			startPage();
			List<ProSinmanage> list = proSinmanageService.selectProSinmanageList(proSinmanage);
			return getDataTable(list);
		}else{
			proSinmanage.setUserid(userid.toString());
			startPage();
			List<ProSinmanage> list = proSinmanageService.selectProSinmanageList(proSinmanage);
			return getDataTable(list);
		}
	}

	@RequiresPermissions("broad:proSinmanage:viewwarning")
	@GetMapping("/wproSinmanage")
	public String proSinmanagewarning()
	{
		return prefix + "/wproSinmanage";
	}

	/**
	 * 查询紧急节目播出单列表
	 */
	@PostMapping("/listwarning")
	@ResponseBody
	public TableDataInfo listwarning()
	{
		SysUser currentUser = ShiroUtils.getSysUser();
		long userid =  currentUser.getUserId();
		int roleid = sysUserService.selectRoleid(userid);
		List<ProSinmanage> list ;
		startPage();
		if(roleid != 1){
			list = proSinmanageService.selectProSinmanageListForWarning(userid);
		}else{
			list = proSinmanageService.selectProSinmanageListForWarning(userid);}
		return getDataTable(list);
	}

	/**
	 * 打开节目单详情页
	 */
	@GetMapping("/detail/{sjid}")
	public String detail(@PathVariable("sjid")String sjid,ModelMap mmap)
	{
		mmap.put("sjid",sjid);
		mmap.put("listBySjid",proListService.selectProListListByPid(sjid));
		return prefix + "/detail";
	}

	/**
	 * 导出节目播出单列表
	 */
	@RequiresPermissions("broad:proSinmanage:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(ProSinmanage proSinmanage)
	{
		List<ProSinmanage> list = proSinmanageService.selectProSinmanageList(proSinmanage);
		ExcelUtil<ProSinmanage> util = new ExcelUtil<ProSinmanage>(ProSinmanage.class);
		return util.exportExcel(list, "proSinmanage");
	}

	/**
	 * 新增节目播出单
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存节目播出单
	 */
	@RequiresPermissions("broad:proSinmanage:add")
	@Log(title = "节目播出单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ProSinmanage proSinmanage)
	{
		return toAjax(proSinmanageService.insertProSinmanage(proSinmanage));
	}


	/*************************************************************************************************
	 * 新增节目播出单
	 */
	@GetMapping("/addtest/{scategory}")
	public String addtest(@PathVariable("scategory") int scategory,ModelMap mmap)
	{
		SysUser user = ShiroUtils.getSysUser();
		mmap.put("user", user);
		mmap.put("scategory", scategory);
		return prefix + "/addtest";
	}

	/*************************************************************************************************
	 * 新增保存节目播出单
	 */
//	@RequiresPermissions("broad:proSinmanage:addtest")
	@Log(title = "新增节目播出单测试", businessType = BusinessType.INSERT)
	@PostMapping("/addtest")
	@ResponseBody
	public AjaxResult addtestSave(ProSinmanage proSinmanage)
	{
		return toAjax(proSinmanageService.insertProSinmanage(proSinmanage));
	}

	/**
	 * 返回节目单选择界面
	 * @param mmap
	 * @return
	 */
	@GetMapping("/getdoFile")
	public String doFile(ModelMap mmap){
		return prefix+"/listFile";
	}

	/**
	 * 返回节目单选择界面
	 * @param mmap
	 * @return
	 */
	@GetMapping("/getdoCham")
	public String doCham(ModelMap mmap){
		return prefix+"/listChamanage";
	}
	/**
	 * 返回时间修改界面
	 * @param mmap
	 * @return
	 */
	@GetMapping("/getTime")
	public String doTime(@PathParam("time") String time, ModelMap mmap){
		System.out.println(">>>"+time);
		mmap.put("oddtime",time);
		return prefix+"/time";
	}
	/**
	 * 返回音频播放界面
	 * @param mmap
	 * @return
	 */
	@GetMapping("/getAudio")
	public String doAudio(@PathParam("url") String url, ModelMap mmap){
		mmap.put("AudioUrl",url);
		return prefix+"/audio";
	}
	/**
	 * 获取节目单数据
	 * @param program
	 * @return
	 */
	@PostMapping("/listFile")
	@ResponseBody
	public TableDataInfo listFile(Program program)
	{
		startPage();
		List<Program> list = iProgramService.selectProList(program);
		return getDataTable(list);
	}

	/**
	 * 返回电台数据
	 * @param proChamanage
	 * @return
	 */
	@PostMapping("/listCham")
	@ResponseBody
	public TableDataInfo listCham(ProChamanage proChamanage){
		startPage();
		return getDataTable(iProChamanageService.selectProChamanageList(proChamanage));
	}

	/**
	 * 修改节目播出单
	 */
	@GetMapping("/edit/{sfid}")
	public String edit(@PathVariable("sfid") Integer sfid, ModelMap mmap)
	{
		ProSinmanage proSinmanage = proSinmanageService.selectProSinmanageById(sfid);
		mmap.put("proSinmanage", proSinmanage);
		return prefix + "/edit";
	}

	/**
	 * 修改保存节目播出单
	 */
	@RequiresPermissions("broad:proSinmanage:edit")
	@Log(title = "节目播出单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ProSinmanage proSinmanage)
	{
		return toAjax(proSinmanageService.updateProSinmanage(proSinmanage));
	}

	/**
	 * 删除节目播出单
	 */
	@RequiresPermissions("broad:proSinmanage:remove")
	@Log(title = "节目播出单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(proSinmanageService.deleteProSinmanageByIds(ids));
	}

	/**
	 * 获取新增播出单详情数据
	 * @param ProDay
	 * @param ProIMEI
	 * @param ProLists
	 * @return
	 */
	@RequestMapping("/addProList")
	@ResponseBody
	public Map<String,Object> addProList(@RequestParam("userId") String userId,
										 @RequestParam("ProDate") String ProDate,
										 @RequestParam("ProDay") String ProDay,
										 @RequestParam("ProIMEI") List ProIMEI,
										 @RequestParam("ProData") JSONObject.JSONArray ProLists){
		ProSinmanage ps = new ProSinmanage();
		ps.setUserid(userId);
		ps.setCreatetime(DateUtil.getTime());
		ps.setScategory("正常播出单");
		ps.setBroaddate(ProDate);//节目播放开始日期
		ps.setBroadtimes(ProDay);
		int programmeID = ProSinmanageService.insertProSinmanage(ps);//返回的新创建的节目单id

		List<String> ptlist = new ArrayList<>();
		for (int i = 0; i < ProIMEI.size(); i++) {
			String data = (String) ProIMEI.get(i);
			data= data.replace("[","");
			data = data.replace("]","");
			data = data.replaceAll("\"","");
			ptlist.add(data);
		}
		List<ProTerminal> ptlists = new ArrayList<>();
		for (int i = 0; i < ptlist.size(); i++) {
			ProTerminal proTerminal = new ProTerminal();
			proTerminal.setProgrammeID(programmeID);
			proTerminal.setTerminalID(ptlist.get(i));
			ptlists.add(proTerminal);
		}
		ProSinmanageService.addProTerminals(ptlists);

		List<String> list = new ArrayList<>();
		for (int i=0;i<ProLists.size();i++){
			String data = ProLists.get(i).toString()
					.replace("{","")
					.replace("}","")
					.replace("[","")
					.replace("]","")
					.replace("\"","");
			list.add(data.substring(data.indexOf(":")+2,data.length()-1));
		}
		List<ProList> lists = new ArrayList<>();
		for(int j=0,i=0;j<list.size()/5;j++){
			ProList proList = new ProList();
			proList.setPid(String.valueOf(programmeID));
			proList.setPtp(list.get(i));
			proList.setFid(list.get(i+1));
			proList.setfN(list.get(i+2));
			proList.setBt(list.get(i+3));
			proList.setBroadtime(list.get(i+4));
			i+=5;
			lists.add(proList);
		}
		ProSinmanageService.addProList(lists);
		Map<String, Object> map = new HashMap<>();
		map.put("code",200);
		return map;
	}

	@Log(title = "节目播出单管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("broad:proSinmanage:export")
	@GetMapping("/export/{sjid}")
	@ResponseBody
	public AjaxResult export(@PathVariable("sjid")String sjid){
		List<ProList> list = proListService.selectProListListByPid(sjid);
		ExcelUtil<ProList> util = new ExcelUtil<ProList>(ProList.class);
		return util.exportExcel(list, "节目播出列表");
	}

	@Log(title = "节目播出单", businessType = BusinessType.EXPORT)
	@RequiresPermissions("broad:proSinmanage:export")
	@PostMapping("/exportbysingle")
	@ResponseBody
	public AjaxResult exportProSinmanageByIds(@RequestParam("sjids") List<String> sfids) {
		List<ProSinmanage> list = proSinmanageService.selectProSinmanageListByids(sfids);
		ExcelUtil<ProSinmanage> util = new ExcelUtil<ProSinmanage>(ProSinmanage.class);
		return util.exportExcel(list, "proSinmanage");
	}
}
