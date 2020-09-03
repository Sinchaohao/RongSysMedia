package com.sixin.web.controller.broad;


import com.sixin.broad.domain.Sendmessages;
import com.sixin.broad.service.ISendmessagesService;
import com.sixin.broad.utils.SmsSender;
import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信发送记录 信息操作处理
 *
 * @author 张超
 * @date 2019-01-11
 */
@Controller
@RequestMapping("/broad/sendmessages")
public class SendmessagesController extends BaseController
{
	private String prefix = "broad/sendmessages";

	@Autowired
	private ISendmessagesService sendmessagesService;

	@Autowired
	private ISysUserService sysUserService;


	@RequiresPermissions("broad:sendmessages:view")
	@GetMapping()
	public String sendmessages()
	{
		return prefix + "/sendmessages";
	}

	/**
	 * 查询短信发送记录列表
	 */
	@RequiresPermissions("broad:sendmessages:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Sendmessages sendmessages)
	{
		startPage();
		List<Sendmessages> list = sendmessagesService.selectSendmessagesList(sendmessages);
		return getDataTable(list);
	}


	/**
	 * 导出短信发送记录列表
	 */
	@RequiresPermissions("broad:sendmessages:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Sendmessages sendmessages)
	{
		List<Sendmessages> list = sendmessagesService.selectSendmessagesList(sendmessages);
		ExcelUtil<Sendmessages> util = new ExcelUtil<Sendmessages>(Sendmessages.class);
		return util.exportExcel(list, "sendmessages");
	}

	/**
	 * 新增短信发送记录
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存短信发送记录
	 */
	@RequiresPermissions("broad:sendmessages:add")
	@Log(title = "新增短信发送记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Sendmessages sendmessages)
	{
		return toAjax(sendmessagesService.insertSendmessages(sendmessages));
	}

	/**
	 * 发送短信
	 *
	 */
	@PostMapping(value = "/sendsms")
	@ResponseBody
	public Object addProBroad(@RequestParam(value = "content") String[] content,
							  @RequestParam(value = "userids") String[] userids,
							  @RequestParam(value = "names") String[] names,
							  @RequestParam(value = "phones") String[] phones) throws Exception {
		Long senderid = ShiroUtils.getSysUser().getUserId();

		System.out.print("\nsenderid: " + senderid);

		System.out.print("\nphones: ");
		for (String i : phones)
			System.out.print(i + " ");

		System.out.print("\nnames: ");
		for (String i : names)
			System.out.print(i + " ");

		System.out.print("\nuserids: ");
		for (String i : userids)
			System.out.print(i + " ");

		System.out.print("\ncontent: ");
		for (String i : content)
			System.out.print(i + " ");
		System.out.println();

		SmsSender smsSender =new SmsSender();
		smsSender.sendSms(phones,content[0]);


		for (int i = 0; i< phones.length;i++) {
			Sendmessages sendmessages = new Sendmessages();
			sendmessages.setSmobile(phones[i]);
			sendmessages.setTid(names[i]);
			sendmessages.setScontent(content[0]);
			sendmessages.setSenderid(ShiroUtils.getSysUser().getUserName());
			sendmessages.setSendtimes(1);
			sendmessages.setIssend(true);

			sendmessagesService.insertSendmessages(sendmessages);
		}

		Map<String, String> map = new HashMap<String, String>();

		map.put("result", "success");
		return map;
	}



	/**
	 * 修改短信发送记录
	 */
	@GetMapping("/edit/{smid}")
	public String edit(@PathVariable("smid") Integer smid, ModelMap mmap)
	{
		Sendmessages sendmessages = sendmessagesService.selectSendmessagesById(smid);
		mmap.put("sendmessages", sendmessages);
		return prefix + "/edit";
	}

	/**
	 * 修改保存短信发送记录
	 */
	@RequiresPermissions("broad:sendmessages:edit")
	@Log(title = "修改短信发送记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Sendmessages sendmessages)
	{
		return toAjax(sendmessagesService.updateSendmessages(sendmessages));
	}

	/**
	 * 删除短信发送记录
	 */
	@RequiresPermissions("broad:sendmessages:remove")
	@Log(title = "删除短信发送记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(sendmessagesService.deleteSendmessagesByIds(ids));
	}


	/**
	 * 加载用户列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData() {
		List<Map<String, Object>> tree = sendmessagesService.selectDeptUserList();
		return tree;
	}
}
