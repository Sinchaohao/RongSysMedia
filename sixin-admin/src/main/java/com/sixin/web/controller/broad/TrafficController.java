package com.sixin.web.controller.broad;

import java.util.List;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
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
import com.sixin.broad.domain.Traffic;
import com.sixin.broad.service.ITrafficService;

/**
 * 流量 信息操作处理
 *
 * @author 张鸿权
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/broad/traffic")
public class TrafficController extends BaseController
{
	private String prefix = "broad/traffic";

	@Autowired
	private ITrafficService trafficService;
	@Autowired
	private ISysUserService sysUserService;
	@RequiresPermissions("broad:traffic:view")
	@GetMapping()
	public String traffic()
	{
		return prefix + "/traffic";
	}

	/**
	 * 查询流量列表
	 */
	@RequiresPermissions("broad:traffic:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Traffic traffic)
	{
		SysUser currentUser = ShiroUtils.getSysUser();//从session中获取当前登陆用户的userid
		Long userid =  currentUser.getUserId();
		int roleid = sysUserService.selectRoleid(userid);//通过所获取的userid去广播用户表中查询用户所属区域的Roleid
		if(roleid != 1){
			String aid;
			aid = String.valueOf(sysUserService.selectAid(userid));//通过所获取的userid去广播用户表中查询用户所属区域的Aid
			traffic.setAid(aid);
		}
		startPage();
		List<Traffic> list = trafficService.selectTrafficList(traffic);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUsetraffic() > list.get(i).getTrafficlimit()) {
				list.get(i).setStatus("1");
			} else {
				list.get(i).setStatus("0");
			}
		}
		return getDataTable(list);
	}


	/**
	 * 导出流量列表
	 */
	@RequiresPermissions("broad:traffic:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Traffic traffic)
	{
		List<Traffic> list = trafficService.selectTrafficList(traffic);
		ExcelUtil<Traffic> util = new ExcelUtil<Traffic>(Traffic.class);
		return util.exportExcel(list, "traffic");
	}

	/**
	 * 新增流量
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存流量
	 */
	@RequiresPermissions("broad:traffic:add")
	@Log(title = "流量", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Traffic traffic)
	{
		return toAjax(trafficService.insertTraffic(traffic));
	}

	/**
	 * 修改流量
	 */
	@GetMapping("/edit/{ttid}")
	public String edit(@PathVariable("ttid") Integer ttid, ModelMap mmap)
	{
		Traffic traffic = trafficService.selectTrafficById(ttid);
		mmap.put("traffic", traffic);
		return prefix + "/edit";
	}

	/**
	 * 修改保存流量
	 */
	@RequiresPermissions("broad:traffic:edit")
	@Log(title = "流量", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Traffic traffic)
	{
		trafficService.updateTrafficSet(traffic);
		return toAjax(trafficService.updateTraffic(traffic));
	}

	/**
	 * 批量设置流量限制
	 */
	@PostMapping( "/rdsSet")
	@ResponseBody
	public AjaxResult rdsSetUrl(String ids, String number)
	{
		return toAjax(trafficService.updateRdsByIds(ids,number));
	}

    /**
     * 批量设置限终端启用
     */
    @PostMapping( "/fmfrequencySet")
    @ResponseBody
    public AjaxResult fmfrequencySet(String ids, String number)
    {
        return toAjax(trafficService.updateFmfrequencyByIds(ids,number));
    }




	/**
	 * 选择区域树
	 * @description 目前村务在调用此接口
	 */
	@GetMapping("/selectAidTree")
	public String selectAidTree()
	{
		return prefix + "/aidTrafficTree";
	}
}
