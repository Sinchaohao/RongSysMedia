package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.DateUtil;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Files;
import com.sixin.village.domain.Villageplan;
import com.sixin.village.domain.Worklog;
import com.sixin.village.service.IVillageplanService;
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
 * 美丽乡村 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-20
 */
@Controller
@RequestMapping("/village/villageplan")
public class VillageplanController extends BaseController
{
    private String prefix = "village/villageplan";
	
	@Autowired
	private IVillageplanService villageplanService;

	@Autowired
	private ISysUserService sysUserService;

	@RequiresPermissions("village:villageplan:view")
	@GetMapping()
	public String villageplan()
	{
	    return prefix + "/villageplan";
	}

	/*乡村规划*/
	@GetMapping("/1")
	@RequiresPermissions("village:villageplan:view1")
	public String villageplan1()
	{
		return prefix + "/villageplan1";
	}

	@PostMapping("/list1")
	@RequiresPermissions("village:villageplan:list1")
	@ResponseBody
	public TableDataInfo list1(Villageplan villageplan)
	{
		villageplan.setVtype("0");
		startPage();
		List<Villageplan> list = villageplanService.selectVillageplanList(villageplan);
		return getDataTable(list);
	}

	@GetMapping("/add1")
	public String add1(ModelMap mmap)
	{
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username =  currentUser.getUserName();
		Long userid =  currentUser.getUserId();
		String aid;
		//int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid = String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
		return prefix + "/add1";
	}

	/*我爱我村*/
	@GetMapping("/2")
	@RequiresPermissions("village:villageplan:view2")
	public String villageplan2()
	{
		return prefix + "/villageplan2";
	}

	@PostMapping("/list2")
	@RequiresPermissions("village:villageplan:list2")
	@ResponseBody
	public TableDataInfo list2(Villageplan villageplan)
	{
		villageplan.setVtype("1");
		startPage();
		List<Villageplan> list = villageplanService.selectVillageplanList(villageplan);
		return getDataTable(list);
	}

	@GetMapping("/add2")
	public String add2(ModelMap mmap)
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
		return prefix + "/add2";
	}

	/*文化活动*/
	@GetMapping("/3")
	@RequiresPermissions("village:villageplan:view3")
	public String villageplan3()
	{
		return prefix + "/villageplan3";
	}

	@PostMapping("/list3")
	@RequiresPermissions("village:villageplan:list3")
	@ResponseBody
	public TableDataInfo list3(Villageplan villageplan)
	{
		villageplan.setVtype("2");
		startPage();
		List<Villageplan> list = villageplanService.selectVillageplanList(villageplan);
		return getDataTable(list);
	}

	@GetMapping("/add3")
	public String add3(ModelMap mmap)
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
		return prefix + "/add3";
	}

	/*农家乐*/
	@GetMapping("/4")
	@RequiresPermissions("village:villageplan:view4")
	public String villageplan4()
	{
		return prefix + "/villageplan4";
	}

	@PostMapping("/list4")
	@RequiresPermissions("village:villageplan:list4")
	@ResponseBody
	public TableDataInfo list4(Villageplan villageplan)
	{
		villageplan.setVtype("3");
		startPage();
		List<Villageplan> list = villageplanService.selectVillageplanList(villageplan);
		return getDataTable(list);
	}

	@GetMapping("/add4")
	public String add4(ModelMap mmap)
	{
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username =  currentUser.getUserName();
		Long userid =  currentUser.getUserId();
		String aid;
		//int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid = String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
		return prefix + "/add4";
	}
	/*民宿管理*/
	@GetMapping("/5")
	@RequiresPermissions("village:villageplan:view5")
	public String villageplan5()
	{
		return prefix + "/villageplan5";
	}

	@PostMapping("/list5")
	@RequiresPermissions("village:villageplan:list5")
	@ResponseBody
	public TableDataInfo list5(Villageplan villageplan)
	{
		villageplan.setVtype("4");
		startPage();
		List<Villageplan> list = villageplanService.selectVillageplanList(villageplan);
		return getDataTable(list);
	}

	@GetMapping("/add5")
	public String add5(ModelMap mmap)
	{
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username =  currentUser.getUserName();
		Long userid =  currentUser.getUserId();
		String aid;
		//int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid = String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
		return prefix + "/add5";
	}
	/*宣传*/
	@GetMapping("/6")
	@RequiresPermissions("village:villageplan:view6")
	public String villageplan6()
	{
		return prefix + "/villageplan6";
	}

	@PostMapping("/list6")
	@RequiresPermissions("village:villageplan:list6")
	@ResponseBody
	public TableDataInfo list6(Villageplan villageplan)
	{
		villageplan.setVtype("5");
		startPage();
		List<Villageplan> list = villageplanService.selectVillageplanList(villageplan);
		return getDataTable(list);
	}

	@GetMapping("/add6")
	public String add6(ModelMap mmap)
	{
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username =  currentUser.getUserName();
		Long userid =  currentUser.getUserId();
		String aid;
		//int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid = String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
		return prefix + "/add6";
	}
	/*咨询*/
	@GetMapping("/7")
	@RequiresPermissions("village:villageplan:view7")
	public String villageplan7()
	{
		return prefix + "/villageplan7";
	}

	@PostMapping("/list7")
	@RequiresPermissions("village:villageplan:list7")
	@ResponseBody
	public TableDataInfo list7(Villageplan villageplan)
	{
		villageplan.setVtype("6");
		startPage();
		List<Villageplan> list = villageplanService.selectVillageplanList(villageplan);
		return getDataTable(list);
	}

	@GetMapping("/add7")
	public String add7(ModelMap mmap)
	{
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username =  currentUser.getUserName();
		Long userid =  currentUser.getUserId();
		String aid;
		//int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid = String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
		return prefix + "/add7";
	}
	/**
	 * 查询美丽乡村列表
	 */

	@PostMapping("/list")
	@RequiresPermissions("village:villageplan:list")
	@ResponseBody
	public TableDataInfo list(Villageplan villageplan)
	{
		startPage();
        List<Villageplan> list = villageplanService.selectVillageplanList(villageplan);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出美丽乡村列表
	 */
	@RequiresPermissions("village:villageplan:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Villageplan villageplan)
    {
    	List<Villageplan> list = villageplanService.selectVillageplanList(villageplan);
        ExcelUtil<Villageplan> util = new ExcelUtil<Villageplan>(Villageplan.class);
        return util.exportExcel(list, "villageplan");
    }

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Villageplan> util = new ExcelUtil<>(Villageplan.class);
		return util.importTemplateExcel("美丽乡村");
	}

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 16:03
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:villageplan:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Villageplan> list = villageplanService.selectBeaCountryListByids(rows);
		ExcelUtil<Villageplan> util = new ExcelUtil<>(Villageplan.class);
		return util.exportExcel(list, "Villageplan");
	}

	/**
	 * @Author TanXY
	 * @Description 导入数据
	 * @Date 2020/4/17 21:54
	 * @Param [file, updateSupport]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<Villageplan> util = new ExcelUtil<Villageplan>(Villageplan.class);
		List<Villageplan> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<Villageplan> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Villageplan user : userList) {
			try {
				villageplanService.insertVillageplan(user);
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
	 * 新增美丽乡村
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
		aid = String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
		return prefix + "/add";
	}
	
	/**
	 * 新增保存美丽乡村
	 */
	@Log(title = "美丽乡村", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
    /*这里支持多文件上传*/
    /*这里加入Project project是为了获得html页面form返回来的数据*/
    public AjaxResult addSave(Villageplan villageplan,@RequestParam(value = "files") MultipartFile file[],
                              @RequestParam(value = "filesnum", required = false) int filesnum,
                              @RequestParam(value = "filename", required = false) String fname,
                              @RequestParam(value = "flenth" ,required = false)String flenth, //时长
                              @RequestParam(value = "fsize",required = false) String fsize){//大小
        String year = DateUtil.getYear();

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        System.out.println(dateFormat.format(date));
        String maxfileid = dateFormat.format(date); //获取文件上传时的时间参数字符串作为文件名，防止储存同名文件

        //文件上传调用工具类
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
            villageplan.setMpic(fileaddress);//给project实体的“文件地址”赋值

            return toAjax(villageplanService.insertVillageplan(villageplan));//将project实体中的值插入数据表中
        }catch (Exception e){
            //return "上传文件失败";
            System.out.println("失败");
            return toAjax(0);
        }
    }


	/**
	 * 修改美丽乡村
	 */
	@GetMapping("/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, ModelMap mmap)
	{
		Villageplan villageplan = villageplanService.selectVillageplanById(mid);
		mmap.put("villageplan", villageplan);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存美丽乡村
	 */
	@RequiresPermissions("village:villageplan:edit")
	@Log(title = "美丽乡村", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Villageplan villageplan)
	{		
		return toAjax(villageplanService.updateVillageplan(villageplan));
	}
	
	/**
	 * 删除美丽乡村
	 */
	@RequiresPermissions("village:villageplan:remove")
	@Log(title = "美丽乡村", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(villageplanService.deleteVillageplanByIds(ids));
	}

	/**
	 * 打开项目详情页
	 */
	@GetMapping("/detail/{mid}")
	public String detail(@PathVariable("mid")Integer mid,ModelMap mmap)
	{
		mmap.put("listByid",villageplanService.selectVillageplanById(mid));
		return prefix + "/detail";
	}
}
