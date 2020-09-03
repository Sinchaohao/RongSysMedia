package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.DateUtil;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Files;
import com.sixin.village.domain.Meeting;
import com.sixin.village.domain.Memorial;
import com.sixin.village.domain.Worklog;
import com.sixin.village.service.IMeetingService;
import com.sixin.village.util.bFileUtil1;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 会议记录 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-15
 */
@Controller
@RequestMapping("/village/meeting")
public class MeetingController extends BaseController
{
    private String prefix = "village/meeting";
	
	@Autowired
	private IMeetingService meetingService;
	@Autowired
	private ISysUserService sysUserService;
	@RequiresPermissions("village:meeting:view")
	@GetMapping()
	public String meeting()
	{
	    return prefix + "/meeting";
	}
	
	/**
	 * 查询会议记录列表
	 */
	@RequiresPermissions("village:meeting:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Meeting meeting)
	{
		startPage();
        List<Meeting> list = meetingService.selectMeetingList(meeting);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会议记录列表
	 */
	@RequiresPermissions("village:meeting:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Meeting meeting)
    {
    	List<Meeting> list = meetingService.selectMeetingList(meeting);
        ExcelUtil<Meeting> util = new ExcelUtil<Meeting>(Meeting.class);
        return util.exportExcel(list, "meeting");
    }

    /**
     * @Author TanXY
     * @Description 按需导出数据
     * @Date 2020/4/18 9:20
     * @Param [rows]
     * @return com.sixin.common.base.AjaxResult
     */
	@RequiresPermissions("village:meeting:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {//@RequestParam("rows") List<String> rows  VillagerInfo villagerInfo
		//List<VillagerInfo> list = villagerInfoService.selectVillagerInfoList(villagerInfo);
		List<Meeting> list = meetingService.selectMeetingByIds(rows);
		ExcelUtil<Meeting> util = new ExcelUtil<Meeting>(Meeting.class);
		return util.exportExcel(list, "Meeting");
	}

	/**
	 * @Author TanXY
	 * @Description 导入数据
	 * @Date 2020/4/18 9:21
	 * @Param [file, updateSupport]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<Meeting> util = new ExcelUtil<Meeting>(Meeting.class);
		List<Meeting> userList = util.importExcel(file.getInputStream());
		String message = importUser(userList, updateSupport);
		return AjaxResult.success(message);
	}

	/**
	 * 导入用户数据
	 *
	 * @param userList        用户数据列表
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
	 * @return 结果
	 */
	public String importUser(List<Meeting> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Meeting user : userList) {
			try {
				meetingService.insertMeeting(user);
				successNum++;
				successMsg.append("<br/>" + successNum + "导入成功");
			} catch (Exception e) {
				String msg = "导入失败：";
				failureMsg.append(msg + e.getMessage());
			}
		}
		return successMsg.toString();
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Meeting> util = new ExcelUtil<Meeting>(Meeting.class);
		return util.importTemplateExcel("村情资讯");
	}

	/**
	 * 新增会议记录
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username =  currentUser.getUserName();
		Long userid =  currentUser.getUserId();
		String aid;
		//int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid =String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
		return prefix + "/add";
	}
	
	/**
	 * 新增保存会议记录
	 */
	@Log(title = "会议记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	/*这里加入Meeting meeting是为了获得html页面form返回来的数据*/
	public AjaxResult addSave(Meeting meeting,@RequestParam(value = "files") MultipartFile file[],
							  @RequestParam(value = "filesnum", required = false) int filesnum,
							  @RequestParam(value = "filename", required = false) String fname,
							  @RequestParam(value = "flenth" ,required = false)String flenth, //时长
							  @RequestParam(value = "fsize",required = false) String fsize){//大小
		String year = DateUtil.getYear();
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
		System.out.println(dateFormat.format(date));
		String maxfileid = dateFormat.format(date); //获取文件上传时的时间参数字符串作为文件名，防止储存同名文件

		//图片上传调用工具类
		try{
			int i;
			String fileaddress = "";
			for(i=0;i<filesnum;i++)
			{
				//保存文件
				Files g = bFileUtil1.uplodeFile(maxfileid,file[i],fname,flenth,fsize,year);
				System.out.println(g.toString());//在控制台输出文件信息
				fileaddress = fileaddress + g.getAddress() + ";";//通过fileaddress来储存文件地址
			}
			meeting.setMpic(fileaddress);//给project实体的“文件地址”赋值

			return toAjax(meetingService.insertMeeting(meeting));//将project实体中的值插入数据表中
		}catch (Exception e){
			//return "上传文件失败";
			System.out.println("失败");
			return toAjax(0);
		}
	}

	/**
	 * 修改会议记录
	 */
	@GetMapping("/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, ModelMap mmap)
	{
		Meeting meeting = meetingService.selectMeetingById(mid);
		mmap.put("meeting", meeting);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会议记录
	 */
	@RequiresPermissions("village:meeting:edit")
	@Log(title = "会议记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Meeting meeting)
	{		
		return toAjax(meetingService.updateMeeting(meeting));
	}
	
	/**
	 * 删除会议记录
	 */
	@RequiresPermissions("village:meeting:remove")
	@Log(title = "会议记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(meetingService.deleteMeetingByIds(ids));
	}
	
}
