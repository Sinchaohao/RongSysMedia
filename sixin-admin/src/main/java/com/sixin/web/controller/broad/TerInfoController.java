package com.sixin.web.controller.broad;

import com.sixin.broad.domain.Organization;
import com.sixin.broad.service.IOrganizationService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 终端信息操作处理
 *
 * @author cx
 * @date 2019-09-21
 */
@Controller
@RequestMapping("/broad/terstatusinfo")
public class TerInfoController extends BaseController
{
	private String prefix = "broad/terstatusinfo";

	@Autowired
	private IOrganizationService organizationService;

	@GetMapping()
	public String organization(){
		return prefix + "/list";
	}

	/**
	 * 查询终端信息列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Organization organization){
		startPage();
		List<Organization> list = organizationService.selectOrganizationList(organization);
		return getDataTable(list);
	}

	@GetMapping("/timeedit")
	public String terinfoedit(){
		return prefix + "/timeedit";
	}

	@GetMapping("/rdsedit")
	public String rdsedit(){
		return prefix + "/rdsedit";
	}

	@GetMapping("/frequencyedit")
	public String frequencyedit(){
		return prefix + "/frequencyedit";
	}

	@GetMapping("/phoneedit")
	public String phoneedit(){
		return prefix + "/phoneedit";
	}

	@GetMapping("/phonedelete")
	public String phonedelete(){
		return prefix + "/phonedelete";
	}

	//批量设置终端交互时间
	@PostMapping("/timeedit")
	@ResponseBody
	public AjaxResult terinfoedittime(@RequestParam("time")String time, @RequestParam("tids") List<String> tids){
		return toAjax(organizationService.terinfoedittime(time,tids));
	}

	//批量设置终端rds
	@PostMapping("/rdsedit")
	@ResponseBody
	public AjaxResult terinfoeditrds(@RequestParam("time")String rds,@RequestParam("tids") List<String> tids){
		return toAjax(organizationService.terinfoeditrds(rds,tids));
	}

	//批量设置终端接收频率
	@PostMapping("/frequencyedit")
	@ResponseBody
	public AjaxResult terinfoeditfrequency(@RequestParam("time")String frequency,@RequestParam("tids") List<String> tids){
		return toAjax(organizationService.terinfoeditfrequency(frequency,tids));
	}

	//批量设置终端授权号码
	@PostMapping("/phoneedit")
	@ResponseBody
	public AjaxResult terinfoeditphone(@RequestParam("tel")String phone,@RequestParam("tids") List<String> tids,String telperson){
		List<Organization> organizations=new ArrayList<Organization>();
		for (int i = 0; i < tids.size(); i++) {
			Organization organization = new Organization();
			organization.setTid(tids.get(i));
//			organization.setAtphone(phone);
			organization.setManager(telperson);
			organizations.add(organization);
			System.out.println(organizations);
		}
		return toAjax(organizationService.terinfoeditphone(organizations));
	}

	//批量删除授权号码
	@PostMapping("/phonedelete")
	@ResponseBody
	public AjaxResult terinfoeditphonedelete(@RequestParam("time")String phone,@RequestParam("tids") List<String> tids){
		return toAjax(organizationService.terinfoeditphonedelete(phone,tids));
	}
}

