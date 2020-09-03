package com.sixin.web.controller.iot;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.iot.domain.Save;
import com.sixin.iot.domain.Securitype;
import com.sixin.iot.service.SaveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/iot/save")
public class SaveController extends BaseController {

    private String prefix = "iot/save";

    @Autowired
    private SaveService saveService;

    @RequiresPermissions("iot:saveinfo:view")
    @GetMapping()
    public String Save(){
        return prefix+"/save";
    }

    /**
     * Save列表
     * @param save
     * @return
     */
    @RequiresPermissions("iot:saveinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Save save){
        startPage();
        List<Save> list = saveService.selectSaveList(save);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add( ModelMap mmap,Securitype securitype){
        mmap.put("securitype", saveService.selectSecuritypeList(securitype));
        return prefix+"/add";
    }

    /**
     * 新增Save信息
     */
    @RequiresPermissions("iot:saveinfo:add")
    @Log(title = "Save信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Save Save)
    {
        return toAjax(saveService.insertSave(Save));
    }
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Save> util = new ExcelUtil<Save>(Save.class);
        return util.importTemplateExcel("用户数据");
    }


    /**
     * 导出终端运转列表
     */
    @Log(title = "Save", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:saveinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Save save)
    {
        List<Save> list = saveService.selectSaveList(save);
        ExcelUtil<Save> util = new ExcelUtil<Save>(Save.class);
        return util.exportExcel(list, "save");
    }
    /**
     * 修改Save信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap,Securitype securitype)
    {
        Save save = saveService.selectSaveByid(id);
        mmap.put("save", save);
        mmap.put("securitype", saveService.selectSecuritypeList(securitype));
        return prefix + "/edit";
    }
    /**
     * 修改保存Save信息
     */
    @RequiresPermissions("iot:saveinfo:edit")
    @Log(title = "Save信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Save save)
    {
        return toAjax(saveService.updateSave(save));
    }
    /**
     * 删除Save信息
     */
    @RequiresPermissions("iot:saveinfo:remove")
    @Log(title = "删除Save信息", businessType = BusinessType.DELETE)

    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saveService.deleteSaveByids(ids));
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:Saveinfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Save save)
    {
        //SaveService.checkUserAllowed(Save);
        return toAjax(saveService.changeStatus(save));
    }

    @Log(title = "Save", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:saveinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportSaveByIds(@RequestParam("sjids") List<String> sfids)
    {
        List<Save> list = saveService.selectSaveByids(sfids);
        ExcelUtil<Save> util = new ExcelUtil<Save>(Save.class);
        return util.exportExcel(list, "Save");
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Save> util = new ExcelUtil<Save>(Save.class);
        List<Save> userList = util.importExcel(file.getInputStream());
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
    public String importUser(List<Save> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
        for (Save user : userList) {
            try {
                saveService.insertSave(user);
                successNum++;
                successMsg.append("<br/>" + successNum + "能源 " + user.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = user.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }
}