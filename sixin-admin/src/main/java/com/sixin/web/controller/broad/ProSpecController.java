package com.sixin.web.controller.broad;
import com.sixin.broad.domain.ProSpec;
import com.sixin.broad.service.IProSpecService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;
import java.util.List;

/**
 * 特种节目管理
 *
 * @author 周博
 * @date 2019-03-27
 */

@Controller
@RequestMapping("/broad/spec")
public class ProSpecController extends BaseController {
    private String prefix = "broad/spec";

    @Autowired
    private IProSpecService proSpecService;
    @Autowired
    private ISysUserService sysUserService;
    @GetMapping()
    public String spec()
    {
        return prefix + "/spec";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProSpec proSpec)
    {
        SysUser currentUser = ShiroUtils.getSysUser();//从session中获取当前登陆用户的userid
        Long userid =  currentUser.getUserId();
        int roleid = sysUserService.selectRoleid(userid);//通过所获取的userid去广播用户表中查询用户所属区域的Roleid
        if(roleid == 1) {
            startPage();
            List<ProSpec> list = proSpecService.selectProSpecList(proSpec);
            return getDataTable(list);
        }else{
            proSpec.setUserid(userid.toString());
            startPage();
            List<ProSpec> list = proSpecService.selectProSpecList(proSpec);
            return getDataTable(list);
        }
    }



    @RequiresPermissions("broad:proSpec:export")
    @GetMapping("/export")
    @ResponseBody
    public AjaxResult export(ProSpec proSpec) {

        List<ProSpec> list = proSpecService.selectProSpecList(proSpec);
        ExcelUtil<ProSpec> util = new ExcelUtil<ProSpec>(ProSpec.class);
        return util.exportExcel(list, "proSpec");
    }
    @GetMapping("/speclist")
    public String doCham(ModelMap mmap){
        return prefix+"/speclist";
    }
    @PostMapping("/changespec")
    @ResponseBody
    public int changespec(String oldfid,String createdtime,String filename,String flenth,String fname,String fsize,String uname,String urls,String userid){
        ProSpec proSpec = new ProSpec();
        proSpec.setFid(oldfid);
        proSpec.setCreatedtime(createdtime);
        proSpec.setFilename(filename);
        proSpec.setFlenth(flenth);
        proSpec.setFname(fname);
        proSpec.setFsize(fsize);
        proSpec.setUname(uname);
        proSpec.setUrls(urls);
        proSpec.setUserid(userid);
        int value = proSpecService.updateprospec(proSpec);
        return value;
    }
}
