package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.service.IIotypeService;
import com.sixin.village.domain.Warnset;
import com.sixin.village.service.IWarnsetService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Controller
@RequestMapping("/village/message")
public class MessagePannelController extends BaseController {
    private String prefix ="village/message";

    @Autowired
    private IWarnsetService warnsetService;
   @Autowired
   private IIotypeService iotypeService;

    /**
     * @Author TanXY
     * @Description
     * @Date 2020/4/17 13:16
     * @Param [mmap]
     * @return java.lang.String
     */
    @RequiresPermissions("village:message:view")
    @GetMapping()
    public String message(ModelMap mmap){

        mmap.put("type", iotypeService.selectIotypeList());
        return prefix + "/message";
    }

    /**
     * @Author TanXY
     * @Description 查询报警提示列表
     * @Date 2020/4/17 11:43
     * @Param [warnset]
     * @return com.sixin.common.page.TableDataInfo
     */
    @RequiresPermissions("village:message:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Warnset warnset)
    {
        startPage();
        List<Warnset> list = warnsetService.selectWarnsetList(warnset);
        return getDataTable(list);
    }

    /**
     * @Author TanXY
     * @Description 
     * @Date 2020/4/18 15:14
     * @Param [message]
     * @return com.sixin.common.base.AjaxResult
     */
    @RequiresPermissions("village:message:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Warnset message)
    {
        List<Warnset> list = warnsetService.selectWarnsetList(message);
        ExcelUtil<Warnset> util = new ExcelUtil<>(Warnset.class);
        return util.exportExcel(list, "Warnset");
    }

    /**
     * @Author TanXY
     * @Description 
     * @Date 2020/4/18 15:13
     * @Param [rows]
     * @return com.sixin.common.base.AjaxResult
     */
    @RequiresPermissions("village:message:export")
    @PostMapping("/exportByIds")
    @ResponseBody
    public AjaxResult export(@RequestParam("rows") List<String> rows) {
        List<Warnset> list = warnsetService.selectWarnsetByIds(rows);
        ExcelUtil<Warnset> util = new ExcelUtil<>(Warnset.class);
        return util.exportExcel(list, "Warnset");
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Warnset> util = new ExcelUtil<>(Warnset.class);
        return util.importTemplateExcel("报警信息提示");
    }

    /**
     * @Author TanXY
     * @Description 
     * @Date 2020/4/18 15:47
     * @Param [file, updateSupport]
     * @return com.sixin.common.base.AjaxResult
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Warnset> util = new ExcelUtil<>(Warnset.class);
        List<Warnset> userList = util.importExcel(file.getInputStream());
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
    public String importUser(List<Warnset> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
        for (Warnset user : userList) {
            try {
                warnsetService.insertWarnset(user);
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
     * 新增报警提示
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("type",iotypeService.selectIotypeList());
        return prefix + "/add";
    }

    /**
     * 新增保存报警提示
     */
    @RequiresPermissions("village:message:add")
    @Log(title = "报警提示", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Warnset warnset)
    {
        return toAjax(warnsetService.insertWarnset(warnset));
    }

    /**
     * 修改报警提示
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Warnset warnset = warnsetService.selectWarnsetById(id);
        mmap.put("warnset", warnset);
        mmap.put("type",iotypeService.selectIotypeList());
        return prefix + "/edit";
    }

    /**
     * 修改保存报警提示
     */
    @RequiresPermissions("village:message:edit")
    @Log(title = "报警提示", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Warnset warnset)
    {
        return toAjax(warnsetService.updateWarnset(warnset));
    }

    /**
     * 删除报警提示
     */
    @RequiresPermissions("village:message:remove")
    @Log(title = "报警提示", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(warnsetService.deleteWarnsetByIds(ids));
    }

}
