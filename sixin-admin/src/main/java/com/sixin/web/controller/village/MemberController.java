package com.sixin.web.controller.village;

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
import com.sixin.village.domain.Member;
import com.sixin.village.service.IMemberService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;

/**
 * 村民基本 信息操作处理
 * 
 * @author 张超
 * @date 2019-01-15
 */
@Controller
@RequestMapping("/village/member")
public class MemberController extends BaseController
{
    private String prefix = "village/member";
	
	@Autowired
	private IMemberService memberService;
	
	@RequiresPermissions("village:member:view")
	@GetMapping()
	public String member()
	{
	    return prefix + "/member";
	}
	
	/**
	 * 查询村民基本列表
	 */
	@RequiresPermissions("village:member:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Member member)
	{
		startPage();
        List<Member> list = memberService.selectMemberList(member);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出村民基本列表
	 */
	@RequiresPermissions("village:member:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Member member)
    {
    	List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        return util.exportExcel(list, "member");
    }
	
	/**
	 * 新增村民基本
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存村民基本
	 */
	@RequiresPermissions("village:member:add")
	@Log(title = "村民基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Member member)
	{		
		return toAjax(memberService.insertMember(member));
	}

	/**
	 * 修改村民基本
	 */
	@GetMapping("/edit/{pid}")
	public String edit(@PathVariable("pid") Integer pid, ModelMap mmap)
	{
		Member member = memberService.selectMemberById(pid);
		mmap.put("member", member);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存村民基本
	 */
	@RequiresPermissions("village:member:edit")
	@Log(title = "村民基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Member member)
	{		
		return toAjax(memberService.updateMember(member));
	}
	
	/**
	 * 删除村民基本
	 */
	@RequiresPermissions("village:member:remove")
	@Log(title = "村民基本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberService.deleteMemberByIds(ids));
	}
	
}
