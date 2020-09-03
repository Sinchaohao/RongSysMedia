package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.DateUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Fileinfo;
import com.sixin.village.domain.Files;
import com.sixin.village.service.IFileinfoService;
import com.sixin.village.util.bFileUtil1;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件夹管理 信息操作处理
 * 
 * @author 张鸿权
 * @date 2019-08-22
 */
@Controller
@RequestMapping("/village/fileinfo")
public class FileinfoController extends BaseController
{
    private String prefix = "village/fileinfo";
	
	@Autowired
	private IFileinfoService fileinfoService;
    @Autowired
    private ISysUserService sysUserService;
	@RequiresPermissions("village:fileinfo:view")
	@GetMapping()
	public String fileinfo()
	{
	    return prefix + "/fileinfo";
	}

	/**
	 * 查询文件夹管理列表
	 */
	@RequiresPermissions("village:fileinfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Fileinfo fileinfo)
	{
		SysUser currentUser = ShiroUtils.getSysUser();
		Long userid =  currentUser.getUserId();
		String uid = userid + "";
		fileinfo.setUid(uid);
		startPage();
        List<Fileinfo> list = fileinfoService.selectFileinfoList(fileinfo);
		return getDataTable(list);
	}

	/*@GetMapping("/fileData")
	public String fileData(Fileinfo fileinfo,ModelMap mmap)
	{
//		List<Map<String, Object>> filedata = fileinfoService.selectFileTree(new Fileinfo());
		List<Fileinfo> filedata ;
		filedata = fileinfoService.selectFileinfoList(fileinfo);
		System.out.println("！！！下面输出的是filedata数据：");
		System.out.println(filedata);
		mmap.put("aaa","1");
		mmap.put("filedata",filedata);
		return prefix + "/fileinfo";
	}*/
	@GetMapping("/fileData")
	@ResponseBody
	public ModelAndView fileData(Fileinfo fileinfo,String str)
	{
        List<Fileinfo> fileList = fileinfoService.selectFileinfoList(fileinfo);
        Map<String, Object> fileMap = new HashMap<String, Object>();
        for (Fileinfo file : fileList)
        {
            fileMap.put("id", file.getAid());
            fileMap.put("filename", file.getFilename());
            fileMap.put("furl", file.getFurl());
            fileMap.put("filesize", file.getFilesize());
            fileMap.put("pushdate", file.getPushdate());
            fileMap.put("suffix", file.getSuffix());
        }
		System.out.println("！！！下面输出的是filedata数据：");
		System.out.println(fileMap);
		return new ModelAndView(prefix + "/fileinfo",fileMap);
	}
	/**
	 * 导出文件夹管理列表
	 */
	@RequiresPermissions("village:fileinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Fileinfo fileinfo)
    {
    	List<Fileinfo> list = fileinfoService.selectFileinfoList(fileinfo);
        ExcelUtil<Fileinfo> util = new ExcelUtil<Fileinfo>(Fileinfo.class);
        return util.exportExcel(list, "fileinfo");
    }
	
	/**
	 * 新增文件夹管理
	 */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        //从session中获取当前登陆用户的 username、phone、userid
        SysUser currentUser = ShiroUtils.getSysUser();
        String username =  currentUser.getUserName();
        String phone =  currentUser.getPhonenumber();
        Long userid =  currentUser.getUserId();
		String uid = userid + "";
		double sizesum ;
		sizesum = fileinfoService.selectFilesizeSum(uid);
        String aid;
        //int returnId = new Long(userid).intValue();
        //通过所获取的userid去广播用户表中查询用户所属区域的Aid
        aid = String.valueOf(sysUserService.selectAid(userid));
        //	将aid、fname、uname传至add.html中
        mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
        mmap.put("fname", username);
        mmap.put("fphone", phone);
        mmap.put("uid", userid);
        mmap.put("uname", username);
		mmap.put("sizesum", sizesum);
        return prefix + "/add";
    }
	
	/**
	 * 新增保存文件夹管理
	 */
	@RequiresPermissions("village:fileinfo:add")
	@Log(title = "文件夹管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    @ResponseBody
    /*这里加入Project project是为了获得html页面form返回来的数据*/
    public AjaxResult addSave(Fileinfo fileinfo,@RequestParam(value = "files") MultipartFile file,
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
            //保存文件
            Files g = bFileUtil1.uplodeFile(maxfileid,file,fname,flenth,fsize,year);
            System.out.println(g.toString());//在控制台输出文件信息
            fileinfo.setFurl(g.getAddress());//给project实体的“文件地址”赋值
            return toAjax(fileinfoService.insertFileinfo(fileinfo));//将project实体中的值插入数据表中
        }catch (Exception e){
            //return "上传文件失败";
            System.out.println("失败");
            return toAjax(0);
        }
    }

	/**
	 * 修改文件夹管理
	 */
	@GetMapping("/edit/{fileid}")
	public String edit(@PathVariable("fileid") Integer fileid, ModelMap mmap)
	{
		Fileinfo fileinfo = fileinfoService.selectFileinfoById(fileid);
		mmap.put("fileinfo", fileinfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存文件夹管理
	 */
	@RequiresPermissions("village:fileinfo:edit")
	@Log(title = "文件夹管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Fileinfo fileinfo)
	{		
		return toAjax(fileinfoService.updateFileinfo(fileinfo));
	}
	
	/**
	 * 删除文件夹管理
	 */
	@RequiresPermissions("village:fileinfo:remove")
	@Log(title = "文件夹管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(fileinfoService.deleteFileinfoByIds(ids));
	}

	@PostMapping("/filesizeSum")
	@ResponseBody
	public  double filesizeSum()
	{
		SysUser currentUser = ShiroUtils.getSysUser();
		Long userid =  currentUser.getUserId();
		String uid = userid + "";
		double sizesum ;
		sizesum = fileinfoService.selectFilesizeSum(uid);
		System.out.println("文件总大小："+ sizesum);
		return sizesum;
	}

	@PostMapping("/query/{fileid}")
	@ResponseBody
	public  String query(@PathVariable("fileid")Integer fileid)
	{
		String  frullist = fileinfoService.selectFileurlById(fileid);
		return frullist;
	}
}
