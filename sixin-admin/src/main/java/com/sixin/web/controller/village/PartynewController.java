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
import com.sixin.village.domain.Memorial;
import com.sixin.village.domain.Partynew;
import com.sixin.village.service.IPartynewService;
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
 * 党员新闻 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
@Controller
@RequestMapping("/village/partynew")
public class PartynewController extends BaseController
{
    private String prefix = "village/partynew";
	
	@Autowired
	private IPartynewService partynewService;
	@Autowired
	private ISysUserService sysUserService;
	@RequiresPermissions("village:partynew:view")
	@GetMapping()
	public String partynew()
	{
	    return prefix + "/partynew";
	}
	
	/**
	 * 查询党员新闻列表
	 */
	@RequiresPermissions("village:partynew:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Partynew partynew)
	{
		startPage();
        List<Partynew> list = partynewService.selectPartynewList(partynew);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出党员新闻列表
	 */
	@RequiresPermissions("village:partynew:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Partynew partynew)
    {
    	List<Partynew> list = partynewService.selectPartynewList(partynew);
        ExcelUtil<Partynew> util = new ExcelUtil<Partynew>(Partynew.class);
        return util.exportExcel(list, "partynew");
    }

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 16:33
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:partynew:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Partynew> list = partynewService.selectPartynewByIds(rows);
		ExcelUtil<Partynew> util = new ExcelUtil<>(Partynew.class);
		return util.exportExcel(list, "Partynew");
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<Partynew> util = new ExcelUtil<>(Partynew.class);
		return util.importTemplateExcel("党建新闻");
	}

	/**
	 * @Author TanXY
	 * @Description 导入数据
	 * @Date 2020/4/17 22:21
	 * @Param [file, updateSupport]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<Partynew> util = new ExcelUtil<>(Partynew.class);
		List<Partynew> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<Partynew> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Partynew user : userList) {
			try {
				partynewService.insertPartynew(user);
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
	 * 新增党员新闻
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
	 * 新增保存党员新闻
	 */
	@Log(title = "党员新闻", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	/*这里支持多文件上传*/
	/*这里加入Project project是为了获得html页面form返回来的数据*/
	@ResponseBody
	public AjaxResult addSave(Partynew partynew,@RequestParam(value = "files") MultipartFile file[],
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
			int i;
			String fileaddress = "";
			for(i=0;i<filesnum;i++)
			{
				//保存文件
				Files g = bFileUtil1.uplodeFile(maxfileid,file[i],fname,flenth,fsize,year);
				System.out.println(g.toString());//在控制台输出文件信息
				fileaddress = fileaddress + g.getAddress() + ";";//通过fileaddress来储存文件地址
			}
			partynew.setMpic(fileaddress);//给project实体的“文件地址”赋值
			partynewService.insertPartynew(partynew);
//			partynewService.selectPartynewList(partynew);
//			return toAjax(1);//将project实体中的值插入数据表中
			return toAjax(1);
	}

	/**
	 * 修改党员新闻
	 */
	@GetMapping("/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, ModelMap mmap)
	{
		Partynew partynew = partynewService.selectPartynewById(mid);
		mmap.put("partynew", partynew);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存党员新闻
	 */
	@RequiresPermissions("village:partynew:edit")
	@Log(title = "党员新闻", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Partynew partynew)
	{		
		return toAjax(partynewService.updatePartynew(partynew));
	}
	
	/**
	 * 删除党员新闻
	 */
	@RequiresPermissions("village:partynew:remove")
	@Log(title = "党员新闻", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partynewService.deletePartynewByIds(ids));
	}

	/**
	 * 打开项目详情页
	 */
	@GetMapping("/detail/{mid}")
	public String detail(@PathVariable("mid")Integer mid,ModelMap mmap)
	{
		mmap.put("listByid",partynewService.selectPartynewById(mid));
		return prefix + "/detail";
	}
}
