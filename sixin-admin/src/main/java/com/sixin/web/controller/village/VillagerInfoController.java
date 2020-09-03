package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.DateUtil;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Pump;
import com.sixin.iot.service.IPumpService;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Files;
import com.sixin.village.domain.VillagerInfo;
import com.sixin.village.service.IVillagerInfoService;
import com.sixin.village.util.bConstant1;
import com.sixin.village.util.bFileUtil1;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 村民 信息操作处理
 *
 * @author zx
 * @date 2019-12-21
 */
@Controller
@RequestMapping("/village/villagerInfo")
public class VillagerInfoController extends BaseController {
	private String prefix = "village/villagerInfo";

	@Autowired
	private IVillagerInfoService villagerInfoService;
	@Autowired
	private ISysUserService sysUserService;

	@RequiresPermissions("village:villagerInfo:view")
	@GetMapping()
	public String villagerInfo() {
		return prefix + "/villagerInfo";
	}

	/**
	 * 查询村民列表
	 */
	@RequiresPermissions("village:villagerInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VillagerInfo villagerInfo) {
		startPage();
		List<VillagerInfo> list = villagerInfoService.selectVillagerInfoList(villagerInfo);
		return getDataTable(list);
	}

	/**
	 * 导出村民列表
	 */
	@RequiresPermissions("village:villagerInfo:export")
	@PostMapping("/export1")
	@ResponseBody
	public AjaxResult export(@RequestParam("rows") List<String> rows) {//@RequestParam("rows") List<String> rows  VillagerInfo villagerInfo
		//List<VillagerInfo> list = villagerInfoService.selectVillagerInfoList(villagerInfo);
		List<VillagerInfo> list = villagerInfoService.selectVillagerInfoByIds(rows);
		ExcelUtil<VillagerInfo> util = new ExcelUtil<VillagerInfo>(VillagerInfo.class);
		return util.exportExcel(list, "villagerInfo");
	}


	/**
	 * 导出村民列表
	 */
	@RequiresPermissions("village:villagerInfo:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export1(VillagerInfo villagerInfo) {//@RequestParam("rows") List<String> rows
		List<VillagerInfo> list = villagerInfoService.selectVillagerInfoList(villagerInfo);
		//List<VillagerInfo> list = villagerInfoService.selectVillagerInfoByIds(rows);
		ExcelUtil<VillagerInfo> util = new ExcelUtil<VillagerInfo>(VillagerInfo.class);
		return util.exportExcel(list, "villagerInfo");
	}
	/**
	 * 新增村民
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username = currentUser.getUserName();
		Long userid = currentUser.getUserId();
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
	 * 新增保存村民
	 */
	//@RequiresPermissions("village:villagerInfo:add")
	@Log(title = "村民", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	/*这里支持多文件上传*/
	/*这里加入Project project是为了获得html页面form返回来的数据*/
	@ResponseBody
	public AjaxResult addSave(VillagerInfo villagerInfo) {
		String year = DateUtil.getYear();

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		System.out.println(dateFormat.format(date));
		String maxfileid = dateFormat.format(date); //获取文件上传时的时间参数字符串作为文件名，防止储存同名文件
		//	文件上传调用工具类
		int i;
		String personaddress = "";
		for (i = 0; i < villagerInfo.getPersonphotolist().length; i++) {
			Files g = bFileUtil1.uplodeFilepic(maxfileid, villagerInfo.getPersonphotolist()[i], villagerInfo.getPersonphotolist()[i].getName(), String.valueOf(villagerInfo.getPersonphotolist()[i].getSize()), year);
			personaddress = personaddress + g.getAddress() + ";";//通过fileaddress来储存文件地址
		}
		villagerInfo.setPersonphoto(personaddress);

		String hkaddress = "";
		for (i = 0; i < villagerInfo.getHkphotolist().length; i++) {
			Files g = bFileUtil1.uplodeFilepic(maxfileid, villagerInfo.getHkphotolist()[i], villagerInfo.getHkphotolist()[i].getName(), String.valueOf(villagerInfo.getHkphotolist()[i].getSize()), year);
			hkaddress = hkaddress + g.getAddress() + ";";//通过fileaddress来储存文件地址
		}
		villagerInfo.setHkphoto(hkaddress);

		String idaddress = "";
		for (i = 0; i < villagerInfo.getIdphotolist().length; i++) {
			Files g = bFileUtil1.uplodeFilepic(maxfileid, villagerInfo.getIdphotolist()[i], villagerInfo.getIdphotolist()[i].getName(), String.valueOf(villagerInfo.getIdphotolist()[i].getSize()), year);
			idaddress = idaddress + g.getAddress() + ";";//通过fileaddress来储存文件地址
		}
		villagerInfo.setIdphoto(idaddress);

		String caraddress = "";
		for (i = 0; i < villagerInfo.getCarphotolist().length; i++) {
			Files g = bFileUtil1.uplodeFilepic(maxfileid, villagerInfo.getCarphotolist()[i], villagerInfo.getCarphotolist()[i].getName(), String.valueOf(villagerInfo.getCarphotolist()[i].getSize()), year);
			caraddress = caraddress + g.getAddress() + ";";//通过fileaddress来储存文件地址
		}
		villagerInfo.setCarphoto(caraddress);

		String houseohoto = "";
		for (i = 0; i < villagerInfo.getHousephotolist().length; i++) {
			Files g = bFileUtil1.uplodeFilepic(maxfileid, villagerInfo.getHousephotolist()[i], villagerInfo.getHousephotolist()[i].getName(), String.valueOf(villagerInfo.getHousephotolist()[i].getSize()), year);
			houseohoto = houseohoto + g.getAddress() + ";";//通过fileaddress来储存文件地址
		}
		villagerInfo.setHousephoto(houseohoto);
		villagerInfoService.insertVillagerInfo(villagerInfo);

		return toAjax(1);
	}

	/**
	 * 修改村民
	 */
	@GetMapping("/edit/{vid}")
	public String edit(@PathVariable("vid") Integer vid, ModelMap mmap) {
		VillagerInfo villagerInfo = villagerInfoService.selectVillagerInfoById(vid);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (villagerInfo.getGetlowdate() == null) {

		} else {
			String date1 = formatter.format(villagerInfo.getGetlowdate());
			Date date3 = java.sql.Date.valueOf(date1);
			villagerInfo.setGetlowdate(date3);//将日期set回去
		}//转成string格式yyyy-mm-dd
		if (villagerInfo.getDemobdate() == null) {

		} else {
			String date2 = formatter.format(villagerInfo.getDemobdate());
			Date date4 = java.sql.Date.valueOf(date2);
			villagerInfo.setDemobdate(date4);
		}

		mmap.put("villagerInfo", villagerInfo);
		return prefix + "/edit";
	}

	/**
	 * 修改保存村民
	 */
	@RequiresPermissions("village:villagerInfo:edit")
	@Log(title = "村民", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VillagerInfo villagerInfo) {
		return toAjax(villagerInfoService.updateVillagerInfo(villagerInfo));
	}


	@Log(title = "村民", businessType = BusinessType.UPDATE)
	@RequiresPermissions("village:villagerInfo:edit")
	@PostMapping("/changeStatus")
	@ResponseBody
	public AjaxResult changeStatus(VillagerInfo villagerInfo)
	{
		return toAjax(villagerInfoService.updateVillagerInfo(villagerInfo));
	}
	/**
	 * 删除村民
	 */
	@RequiresPermissions("village:villagerInfo:remove")
	@Log(title = "村民", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(villagerInfoService.deleteVillagerInfoByIds(ids));
	}

	/**
	 * 下载模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<VillagerInfo> util = new ExcelUtil<VillagerInfo>(VillagerInfo.class);
		return util.importTemplateExcel("用户数据");
	}

	/**
	 * 导入数据
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<VillagerInfo> util = new ExcelUtil<VillagerInfo>(VillagerInfo.class);
		List<VillagerInfo> userList = util.importExcel(file.getInputStream());
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
	public String importUser(List<VillagerInfo> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		/*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
		for (VillagerInfo user : userList) {
			try {
				villagerInfoService.insertVillagerInfo(user);
				successNum++;
				successMsg.append("<br/>" + successNum + "用户 " + user.getVname() + " 导入成功");
			} catch (Exception e) {
				String msg = user.getVname() + " 导入失败：";
				failureMsg.append(msg + e.getMessage());
			}
		}
		return successMsg.toString();
	}

	// 大量图片上传

	@ResponseBody
	@PostMapping("/testuploadimg")
	//@RequestMapping(value = "/testuploadimg", method = RequestMethod.POST)
	public String uploadImg(@RequestParam("file") MultipartFile[] file,
							HttpServletRequest request) {
		String year = DateUtil.getYear();

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//		System.out.println(dateFormat.format(date));
		String maxfileid = dateFormat.format(date); //获取文件上传时的时间参数字符串作为文件名，防止储存同名文件
//
//         for(int i=0;i<file.length;i++){
//			 Files g = bFileUtil1.uplodeFilepic(maxfileid, file[i], file[i].getName(), String.valueOf(file[i].getSize()), year);
//		 }
		for(int i=0;i<file.length;i++){
			String contentType = file[i].getContentType();
			String filePath=bConstant1.WEB_ADDRESS + bConstant1.WEB_IMG_ADDRESS + maxfileid + file[i].getOriginalFilename();
			//String filePath="d:/img/";
			String fileName=file[i].getOriginalFilename();
			if (file[i].isEmpty()) {

				return "文件为空！";

			}

			try {

				uploadFile(file[i].getBytes(), filePath, fileName);

			} catch (Exception e) {

				// TODO: handle exception

			}

		}
		return "succes";
	}

	private void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}






	/**
	 * 打开项目详情页
	 */
	@GetMapping("/detail/{vid}")
	public String detail(@PathVariable("vid")Integer vid,ModelMap mmap)
	{
		mmap.put("listByid",villagerInfoService.selectVillagerInfoById(vid));
		return prefix + "/detail";
	}
}
