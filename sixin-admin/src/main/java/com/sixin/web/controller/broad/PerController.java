package com.sixin.web.controller.broad;

import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sixin.broad.domain.Program;
import com.sixin.broad.service.IProgramService;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.sixin.broad.utils.bFileUtil;


import java.io.IOException;
import java.util.List;

/**
 * @author 张超 teavamc
 * @Description: 节目库管理
 * @ClassName PerController
 * @date 2019/2/17 20:37
 **/
@Controller
@RequestMapping(value="/broad/per")
public class PerController extends BaseController {
    private String prefix = "broad/program/per";

    @Autowired
    private IProgramService iProgramService;

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping()
    public String per(ModelMap mmp) {
        String path = System.getProperty("user.home");
        path = path.replace("\\","/");
        System.out.println(path);
        mmp.put("path",path);
        return prefix + "/per";
    }

    /**
     * 查询节目库列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Program program) {
        SysUser currentUser = ShiroUtils.getSysUser();
        Long userid =  currentUser.getUserId();
        int roleid = sysUserService.selectRoleid(userid);
        if(roleid == 1){
            startPage();
            List<Program> list = iProgramService.selectProList(program);
            return getDataTable(list);
        }else{
            program.setUname(currentUser.getUserName());
            startPage();
            List<Program> list = iProgramService.selectProList(program);
            return getDataTable(list);
        }


    }

    /**
     * 返回新增页面
     *
     * @return
     */
    @GetMapping("/add")
    @RequiresPermissions("broad:per:add")
    public String addper(ModelMap mmp) {
        SysUser currentUser = ShiroUtils.getSysUser();
        String username = currentUser.getUserName();
        Long userid = currentUser.getUserId();
        mmp.put("username", username);
        mmp.put("userid", userid);
        return prefix + "/add";
    }

    /**
     * 新增保存节目单
     *
     * @author cx
     */
    @Log(title = "新增节目单", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam(value = "files") List<MultipartFile> files, @RequestParam(value = "uname", required = false) String uname) throws IOException {//大小
        String userid =  ShiroUtils.getSysUser().getUserId().toString();
        for(int i=0;i<files.size();i++){
            String year = DateUtil.getYear();
            String maxfileid = iProgramService.getMaxFileidofYear(year);
            Program g = bFileUtil.uplodeFile(maxfileid, files.get(i), files.get(i).getOriginalFilename(), String.valueOf(files.get(i).getSize()), year, userid);
            iProgramService.insertProgram(g);
        };
        return toAjax(1);
    }

    @PostMapping("/remove")
    @Log(title = "节目单删除",businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult removeProgram(String ids)
    {
        return toAjax(iProgramService.deleteProgram(ids));
    }


    /** @author qwerty
     * @description 导出√中的数据
     *
     * @param sfids
     * @return
     */
    @Log(title = "节目库记录导出", businessType = BusinessType.EXPORT)
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportProgramByIds(@RequestParam("sjids") List<String> sfids) {
        List<Program> list = iProgramService.selectProgramListByfids(sfids);
        ExcelUtil<Program> util = new ExcelUtil<Program>(Program.class);
        return util.exportExcel(list, "Organization");
    }

    @Log(title = "节目库是否公共状态转换", businessType = BusinessType.UPDATE)
    @GetMapping("/setispublic/{fid}")
    @ResponseBody
    public int setIsPublic(@PathVariable("fid") String fid) {
        return iProgramService.setIsPublic(fid);
    }

}