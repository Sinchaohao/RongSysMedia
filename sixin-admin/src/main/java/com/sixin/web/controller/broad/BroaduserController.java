package com.sixin.web.controller.broad;

import java.util.List;
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
import com.sixin.broad.domain.Broaduser;
import com.sixin.broad.service.IBroaduserService;
import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;

/**
 * 广播用户 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-01-18
 */
@Controller
@RequestMapping("/broad/broaduser")
public class BroaduserController extends BaseController
{
    private String prefix = "broad/broaduser";
	
	@Autowired
	private IBroaduserService broaduserService;
	
	@RequiresPermissions("broad:broaduser:view")
	@GetMapping()
	public String broaduser()
	{
	    return prefix + "/broaduser";
	}
	
	/**
	 * 查询广播用户列表
	 */
	@RequiresPermissions("broad:broaduser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Broaduser broaduser)
	{
		startPage();
        List<Broaduser> list = broaduserService.selectBroaduserList(broaduser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出广播用户列表
	 */
	@RequiresPermissions("broad:broaduser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Broaduser broaduser)
    {
    	List<Broaduser> list = broaduserService.selectBroaduserList(broaduser);
        ExcelUtil<Broaduser> util = new ExcelUtil<Broaduser>(Broaduser.class);
        return util.exportExcel(list, "broaduser");
    }

	/**
	 * 修改广播用户
	 */
	@GetMapping("/edit/{userid}")
	public String edit(@PathVariable("userid") String userid, ModelMap mmap)
	{
		Broaduser broaduser = broaduserService.selectBroaduserById(userid);
		mmap.put("broaduser", broaduser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广播用户
	 */
	@RequiresPermissions("broad:broaduser:edit")
	@Log(title = "广播用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Broaduser broaduser)
	{		
		return toAjax(broaduserService.updateBroaduser(broaduser));
	}
	
	/**
	 * 删除广播用户
	 */
	@RequiresPermissions("broad:broaduser:remove")
	@Log(title = "广播用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(broaduserService.deleteBroaduserByIds(ids));
	}

//	/**
//	 * 新增广播用户
//	 */
//	@Log(title = "广播用户", businessType = BusinessType.INSERT)
//	@PostMapping("/add")
//	@ResponseBody
//	public void add()
//	{
//		broaduserService.insertBroaduser();
//	}
}
