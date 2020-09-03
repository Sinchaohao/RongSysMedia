package com.sixin.web.controller.iot;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.iot.domain.*;
import com.sixin.iot.service.ISecurityService;
import com.sixin.system.service.ISysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.web.base.BaseController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RequestMapping("/iot/security")
public class SecurityController extends BaseController {

    private String prefix = "iot/security";
    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISecurityService securityService;

    @RequiresPermissions("iot:securityinfo:view")
    @GetMapping()
    public String Security(){
        return prefix+"/security";
    }

    /**
     * Security列表
     * @param Security
     * @return
     */
    @RequiresPermissions("iot:securityinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Security security){
        startPage();
        List<Security> list = securityService.selectSecurityList(security);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add( ModelMap mmap,Securitype securitype,User user ){
        mmap.put("securitype", securityService.selectSecuritypeList(securitype));

        mmap.put("user",securityService.selectUserList(user));
        return prefix+"/add";
    }

    /**
     * 新增Security信息
     */
    @RequiresPermissions("iot:securityinfo:add")
    @Log(title = "Security信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSecurity(Security security)
    {
        //Ioterminal ioterminal = securityService.selectIoterminalBysno(sno);
        //security.setDept_id(ioterminal.getDeptid());
        //security.setInstalltime(ioterminal.getStartdate());
        // security.setEndtime(ioterminal.getExdate());
        //BeanUtils.copyProperties(ioterminal, security);
        return toAjax(securityService.insertSecurity(security));
    }



    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Security> util = new ExcelUtil<Security>(Security.class);
        return util.importTemplateExcel("用户数据");
    }
    /**
     * 导出终端运转列表
     */
    @Log(title = "Security", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:securityinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Security security)
    {
        List<Security> list = securityService.selectSecurityList(security);
        ExcelUtil<Security> util = new ExcelUtil<Security>(Security.class);
        return util.exportExcel(list, "security");
    }
    /**
     * 修改Security信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap,Securitype securitype,User user )
    {
        Security security = securityService.selectSecurityByid(id);
        mmap.put("security", security);
        mmap.put("user",securityService.selectUserList(user));

        mmap.put("securitype", securityService.selectSecuritypeList(securitype));
        return prefix + "/edit";
    }
    /**
     * 修改保存Security信息
     */
    @RequiresPermissions("iot:securityinfo:edit")
    @Log(title = "Security信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSecurity(Security security)
    {
        return toAjax(securityService.updateSecurity(security));
    }
    /**
     * 删除Security信息
     */
    @RequiresPermissions("iot:securityinfo:remove")
    @Log(title = "删除Security信息", businessType = BusinessType.DELETE)

    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(securityService.deleteSecurityByids(ids));
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:securityinfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Security security)
    {

        return toAjax(securityService.changeStatus(security));
    }
    /**
     * 批量导出
     */
    @Log(title = "Security", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:securityinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportSecurityByIds(@RequestParam("sjids") List<String> sfids)
    {
        List<Security> list = securityService.selectSecurityByids(sfids);
        ExcelUtil<Security> util = new ExcelUtil<Security>(Security.class);
        return util.exportExcel(list, "Security");
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Security> util = new ExcelUtil<Security>(Security.class);
        List<Security> userList = util.importExcel(file.getInputStream());
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
    public String importUser(List<Security> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
        for (Security user : userList) {
            try {
                securityService.insertSecurity(user);
                successNum++;
                successMsg.append("<br/>" +  "路灯 " + user.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = user.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }


    @GetMapping("/selectIotTree")
    public String selectIotTree()
    {
        return prefix + "/listIotTree";
    }
    @PostMapping("/listIot")
    @ResponseBody
    public TableDataInfo listIotTree(Torrent torrent)
    {
        startPage() ;
        List<Ioterminal> list =  securityService.selectIoterminalList(torrent);
        return getDataTable(list);
    }


}