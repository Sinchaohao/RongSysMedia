package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.DateUtil;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.system.domain.SysDept;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysDeptService;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Files;
import com.sixin.village.domain.Project;
import com.sixin.village.domain.Worklog;
import com.sixin.village.service.IProjectService;
import com.sixin.village.service.IWorklogService;
import com.sixin.village.util.bFileUtil1;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重大项目 信息操作处理
 *
 * @author 张鸿权
 * @date 2019-01-19
 */
@Controller
@RequestMapping("/village/project")
public class ProjectController extends BaseController
{
	private String prefix = "village/project";

	@Autowired
	private IProjectService projectService;
    @Autowired
    private ISysUserService sysUserService;
	@Autowired
	private ISysDeptService deptService;
    @Autowired
    private IWorklogService worklogService;
	@RequiresPermissions("village:project:view")
	@GetMapping()
	public String project()
	{
		return prefix + "/project";
	}

	/**
	 * 查询重大项目列表
	 */
	@RequiresPermissions("village:project:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Project project)
	{   //从session中获取当前登陆用户的 userid
        SysUser currentUser = ShiroUtils.getSysUser();
        Long userid =  currentUser.getUserId();
        int returnId = new Long(userid).intValue();
        //通过所获取的userid去用户表中查询用户所属区域的Roleid
        int roleid = sysUserService.selectRoleid(userid);
		/*普通用户只能看自己工作记录*/
		project.setUid(returnId);
        if(project.getAid() == null && (roleid == 1)) {
		startPage();
		List<Project> list = projectService.selectProjectList(project);
		return getDataTable(list);
        }else if(project.getAid() != null){
            startPage();
            List<Project> list = projectService.selectProjectList(project);
            return getDataTable(list);
        }else{
            String aid;
            //通过所获取的userid去用户表中查询用户所属区域的Aid
            aid = String.valueOf(sysUserService.selectAid(userid));
            project.setAid(aid);
            startPage();
            List<Project> list = projectService.selectProjectList(project);
            return getDataTable(list);
        }
	}


	/**
	 * 导出重大项目列表
	 */
	@RequiresPermissions("village:project:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Project project)
	{
		List<Project> list = projectService.selectProjectList(project);
		ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
		return util.exportExcel(list, "project");
	}

	/**
	 * 新增重大项目
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
        //从session中获取当前登陆用户的 username、phone、userid
        SysUser currentUser = ShiroUtils.getSysUser();
        String username =  currentUser.getUserName();
        String phone =  currentUser.getPhonenumber();
		Long userid =  currentUser.getUserId();
        String aid;
		//int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid = String.valueOf(sysUserService.selectAid(userid));
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("uid", userid);
		mmap.put("fname", username);
		mmap.put("fphone", phone);
		mmap.put("uname", username);
		return prefix + "/add";
	}

	/**
	 * 新增保存重大项目
	 */
	@Log(title = "新增项目", businessType = BusinessType.INSERT)
	@PostMapping(value = "/add")
	@ResponseBody
    /*这里加入Project project是为了获得html页面form返回来的数据*/
	public AjaxResult addSave(/*Project project,@RequestParam(value = "files") MultipartFile file[],*/
							 /* @RequestParam(value = "filesnum", required = false) int filesnum,*/
							Project project,@RequestParam(value = "files") MultipartFile file,
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
			/*int i;
			String fileaddress = "";
			for(i=0;i<filesnum;i++)
				{*/
					String fileaddress = "";
					//保存文件
					Files g = bFileUtil1.uplodeFile(maxfileid,file,fname,flenth,fsize,year);
					/*Files g = bFileUtil1.uplodeFile(maxfileid,file[i],fname,flenth,fsize,year);*/
					System.out.println(g.toString());//在控制台输出文件信息
					fileaddress = fileaddress + g.getAddress() + ";";//通过fileaddress来储存文件地址
				/*}*/
			project.setPropic(fileaddress);//给project实体的“文件地址”赋值

			return toAjax(projectService.insertProject(project));//将project实体中的值插入数据表中
		}catch (Exception e){
			//return "上传文件失败";
			System.out.println("失败");
			return toAjax(0);
		}

	}
	/**
	 * 修改重大项目
	 */
	@GetMapping("/edit/{proid}")
	public String edit(@PathVariable("proid") Integer proid, ModelMap mmap)
	{
		Project project = projectService.selectProjectById(proid);
		mmap.put("project", project);
		return prefix + "/edit";
	}

	/**
	 * 修改保存重大项目
	 */
	@Log(title = "重大项目", businessType = BusinessType.UPDATE)
	@PostMapping(value ="/edit")
	@ResponseBody
	public AjaxResult editSave(Project project)
{
	return toAjax(projectService.updateProject(project));
}

	/**
	 * 删除重大项目
	 */
	@RequiresPermissions("village:project:remove")
	@Log(title = "重大项目", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(projectService.deleteProjectByIds(ids));
	}

	/**
	 * 打开项目详情页
	 */
	@GetMapping("/detail/{proid}")
	public String detail(@PathVariable("proid")Integer proid,ModelMap mmap)
	{
		SysUser currentUser = ShiroUtils.getSysUser();
		Long userid =  currentUser.getUserId();
		int returnId = new Long(userid).intValue();
		System.out.println("userid:"+userid+"returnId:"+returnId);
		mmap.put("returnId",returnId);

        List<HashMap> listMap = worklogService.selectWorkLogByProId(proid);
        int i;String contentlist =  "",unamelist =  "",wtitlelist =  "",wpiclist = "",pushdatelist = "";
        for(i=0; i<listMap.size();i++){
            HashMap map = listMap.get(i);
            unamelist = unamelist + map.get("uname")+",";
            wtitlelist = wtitlelist + map.get("wtitle")+",";
            wpiclist = wpiclist + map.get("wpic")+",";
            contentlist = contentlist + map.get("content")+",";
			pushdatelist = pushdatelist + map.get("pushdate")+",";
        }    System.out.print(unamelist);
		/*查询该项目下的工作记录*/
        mmap.put("listWorklognum",worklogService.selectWorkLogNumByProId(proid));
        mmap.put("unamelist",unamelist);
        mmap.put("wtitlelist",wtitlelist);
        mmap.put("wpiclist",wpiclist);
        mmap.put("contentlist",contentlist);
		mmap.put("pushdatelist",pushdatelist);
		mmap.put("listByid",projectService.selectProjectById(proid));
		return prefix + "/detail";
	}

	/**
	 * 选择部门树
	 */
	@GetMapping("/selectDeptTree/{deptId}")
	public String selectDeptTree(@PathVariable("deptId") String deptId, ModelMap mmap)
	{
		Long did=Long.parseLong(deptId);//返回基本数据类型long
		mmap.put("dept", deptService.selectDeptById2(did));//传到tree页面
		/*return prefix + "/tree";*/
		return prefix + "/listProBroadTree";
	}

	/**
	 * 查询用户列表
	 */
	@PostMapping("/listProBroad")
	@ResponseBody
	public TableDataInfo listProBroad(SysUser user)
	{
		startPage();
		List<SysUser> list = sysUserService.selectUserList(user);
		return getDataTable(list);
	}

	/**
	 * 加载用户选择列表树
	 */
	@GetMapping("/listProBroadTree")
	@ResponseBody
	public List<Map<String, Object>> listProBroadTree()
	{
		List<Map<String, Object>> tree = deptService.selectDeptTree2(new SysDept());
		return tree;
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
		return util.importTemplateExcel("项目信息");
	}

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/17 21:06
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:worklog:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Project> list = projectService.selectProjectByIds(rows);
		ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
		return util.exportExcel(list, "Project");
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
		ExcelUtil<Project> util = new ExcelUtil<>(Project.class);
		List<Project> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<Project> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Project user : userList) {
			try {
				projectService.insertProject(user);
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
	 * 加载部门列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData()
	{
		List<Map<String, Object>> tree = deptService.selectDeptTree2(new SysDept());
		return tree;

	}
}
