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
import com.sixin.village.domain.Link;
import com.sixin.village.domain.Project;
import com.sixin.village.service.ILinkService;
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
 * 链接管理 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-18
 */
@Controller
@RequestMapping("/village/link")
public class LinkController extends BaseController
{
    private String prefix = "village/link";
	
	@Autowired
	private ILinkService linkService;
	@Autowired
	private ISysUserService sysUserService;
	@RequiresPermissions("village:link:view")
	@GetMapping()
	public String link()
	{
	    return prefix + "/link";
	}
	
	/**
	 * 查询链接管理列表
	 */
	@RequiresPermissions("village:link:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Link link)
	{
		startPage();
        List<Link> list = linkService.selectLinkList(link);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出链接管理列表
	 */
	@RequiresPermissions("village:link:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Link link)
    {
    	List<Link> list = linkService.selectLinkList(link);
        ExcelUtil<Link> util = new ExcelUtil<Link>(Link.class);
        return util.exportExcel(list, "link");
    }

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 14:28
	 * @Param [rows]
	 * @return com.sixin.common.base.AjaxResult
	 */
	@RequiresPermissions("village:link:export")
	@PostMapping("/exportByIds")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {
		List<Link> list = linkService.selectLinkByIds(rows);
		ExcelUtil<Link> util = new ExcelUtil<>(Link.class);
		return util.exportExcel(list, "Link");
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
		ExcelUtil<Link> util = new ExcelUtil<>(Link.class);
		List<Link> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<Link> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (Link user : userList) {
			try {
				linkService.insertLink(user);
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
	 * 新增链接管理
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
	 * 新增保存链接管理
	 */
	@Log(title = "链接管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	/*这里加入Meeting meeting是为了获得html页面form返回来的数据*/
	public AjaxResult addSave(Link link, @RequestParam(value = "files") MultipartFile file,
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
			//保存图片
			Files g = bFileUtil1.uplodeFile(maxfileid,file,fname,flenth,fsize,year);
			System.out.println(g.toString());//在控制台输出文件信息

			link.setMpic(g.getAddress());//给project实体的“文件地址”赋值

			return toAjax(linkService.insertLink(link));//将project实体中的值插入数据表中
		}catch (Exception e){
			//return "上传文件失败";
			System.out.println("失败");
			return toAjax(0);
		}
	}

	/**
	 * 修改链接管理
	 */
	@GetMapping("/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, ModelMap mmap)
	{
		Link link = linkService.selectLinkById(mid);
		mmap.put("link", link);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存链接管理
	 */
	@RequiresPermissions("village:link:edit")
	@Log(title = "链接管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Link link)
	{		
		return toAjax(linkService.updateLink(link));
	}
	
	/**
	 * 删除链接管理
	 */
	@RequiresPermissions("village:link:remove")
	@Log(title = "链接管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(linkService.deleteLinkByIds(ids));
	}
	
}
