package com.sixin.web.controller.broad;
import com.sixin.broad.domain.Organization;
import com.sixin.broad.domain.Termap;
import com.sixin.broad.service.ITermapService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * 地图管理 服务层
 *
 * @author 张鸿权
 * @date 2019-01-18
 */
@Controller
@RequestMapping("/broad/map")
public class TermapController extends BaseController
{
	@Autowired
	private ITermapService mapService;
	@Autowired
	private ISysUserService sysUserService;
	private String prefix = "broad/map";

	@GetMapping("/list")
	public String list(ModelMap mmap,Organization organization){
		SysUser currentUser = ShiroUtils.getSysUser();
		Long userid =  currentUser.getUserId();
		int roleid = sysUserService.selectRoleid(userid);
		String aid = String.valueOf(sysUserService.selectAid(userid));
		List<Termap> mapinfoList ;
		if(roleid != 1){
			organization.setAid(aid);
			mapinfoList = mapService.selectMap(organization);
		}else{
			mapinfoList = mapService.selectMap(organization);}
		mmap.put("mapinfoList", mapinfoList);
		return prefix+"/termap";
	}
}