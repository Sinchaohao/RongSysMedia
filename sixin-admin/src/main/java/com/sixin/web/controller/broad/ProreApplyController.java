package com.sixin.web.controller.broad;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sixin.broad.domain.ProApplyUser;
import com.sixin.broad.domain.Program;
import com.sixin.broad.service.IProgramService;
import com.sixin.broad.utils.bConst;
import com.sixin.broad.utils.bFileUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.DateUtil;
//import com.sixin.common.utils.VideoUtil;
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
import com.sixin.broad.domain.ProreApply;
import com.sixin.broad.service.IProreApplyService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 节目申请 信息操作处理
 * 
 * @author 张超
 * @date 2019-03-02
 */
@Controller
@RequestMapping("/broad/proreApply")
public class ProreApplyController extends BaseController{
    private String prefix = "broad/proreApply";
	
	@Autowired
	private IProreApplyService proreApplyService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IProgramService iProgramService;

	@GetMapping()
	public String proreApply()
	{
	    return prefix + "/proreApply";
	}

	@RequiresPermissions("broad:proreApply:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProApplyUser proapplyuser){
		SysUser currentUser = ShiroUtils.getSysUser();
		Long userid =  currentUser.getUserId();
		int roleid = sysUserService.selectRoleid(userid);
		if(roleid == 1){
			startPage();
			List<ProApplyUser> list = proreApplyService.selectProrApplyUserList(proapplyuser);
			return getDataTable(list);
		}else{
			proapplyuser.setUserid(userid);
			startPage();
			List<ProApplyUser> list = proreApplyService.selectProrApplyUserList(proapplyuser);
			return getDataTable(list);
		}
	}

	/**
	 * 导出节目申请列表
	 */
	@RequiresPermissions("broad:proreApply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProreApply proreApply){
    	List<ProreApply> list = proreApplyService.selectProreApplyList(proreApply);
        ExcelUtil<ProreApply> util = new ExcelUtil<ProreApply>(ProreApply.class);
        return util.exportExcel(list, "proreApply");
    }
	
	/**
	 * 新增节目申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存节目申请
	 */
	@Log(title = "新增节目申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestParam(value = "files") MultipartFile file,@RequestParam(value="requires") String requires,@RequestParam(value="timelimit") String timelimit,
							  @RequestParam(value="isemergency") String isemergency,@RequestParam(value="pname") String panme){
		String userId = ShiroUtils.getSysUser().getUserId().toString();
		String fileurl = bFileUtil.saveDoc(file);
		ProreApply  proreApply = new ProreApply(panme,userId,requires,isemergency,timelimit,fileurl);
		return toAjax(proreApplyService.insertProreApply(proreApply));
	}

	/**
	 * 修改节目申请
	 */
	@GetMapping("/edit/{paid}")
	public String edit(@PathVariable("paid") Integer paid, ModelMap mmap){
		ProreApply proreApply = proreApplyService.selectProreApplyById(paid);
		mmap.put("proreApply", proreApply);
	    return prefix + "/edit";
	}

	/**
	 * 修改节目申请
	 */
	@GetMapping("/reply")
	public String reply(){
		return prefix + "/reply";
	}

	/**
	 * 修改保存节目申请
	 */
	@RequiresPermissions("broad:proreApply:edit")
	@Log(title = "修改节目申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ProreApply proreApply)
	{		
		return toAjax(proreApplyService.updateProreApply(proreApply));
	}
	
	/**
	 * 删除节目申请
	 */
	@RequiresPermissions("broad:proreApply:remove")
	@Log(title = "删除节目申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(proreApplyService.deleteProreApplyByIds(ids));
	}

	@Log(title = "节目撤回", businessType = BusinessType.UPDATE)
	@GetMapping("/recall/{fid}")
	@ResponseBody
	public int recall(@PathVariable("fid") String fid) {
		return proreApplyService.recall(fid);
	}

	/**
	 * 回复节目申请
	 */
	@Log(title = "回复节目申请", businessType = BusinessType.UPDATE)
	@PostMapping("/reply")
	@ResponseBody
	public AjaxResult replyfile(String paid,String replyperson,MultipartFile file,String userid){

		String year = DateUtil.getYear();
		String maxfileid = iProgramService.getMaxFileidofYear(year);
		Program g = bFileUtil.uplodeFile(maxfileid, file, file.getOriginalFilename(), String.valueOf(file.getSize()), year, userid);

		g.setPtype(true);
		g.setIspublic(false);
		iProgramService.insertProgram(g);

		ProreApply  proreApply = new ProreApply(Integer.parseInt(paid),true,replyperson,Integer.valueOf(g.getFid()));
		return toAjax(proreApplyService.updateProreApply(proreApply));
	}

}
