package com.sixin.web.controller.iot;

import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.User;
import com.sixin.iot.service.IEnvironlService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/iot/environl")
public class EnvironlController extends BaseController {

    private String prefix = "iot/environl";

    @Autowired
    private IEnvironlService environlService;

    @RequiresPermissions("iot:environlinfo:view")
    @GetMapping()
    public String Environl(){
        return prefix+"/environl";
    }

    /**
     * Environl列表
     * @param environl
     * @return
     */
    @RequiresPermissions("iot:environlinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Environl environl){
        startPage();
        List<Environl> list = environlService.selectEnvironlList(environl);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    /**
     * 导出终端运转列表
     */
    @Log(title = "Environl", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Environl environl)
    {
        List<Environl> list = environlService.selectEnvironlList(environl);
        ExcelUtil<Environl> util = new ExcelUtil<Environl>(Environl.class);
        return util.exportExcel(list, "environl");
    }

    /** @author qwerty
     * @description 导出√中的数据
     *
     * @param sids
     * @return
     */
    @Log(title = "Environl", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:environlinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportEnvironlByIds(@RequestParam("sjids") List<String> sfids)
    {
        List<Environl> list = environlService.selecEnvironlListByids(sfids);
        ExcelUtil<Environl> util = new ExcelUtil<Environl>(Environl.class);
        return util.exportExcel(list, "Environl");
    }
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Environl> util = new ExcelUtil<Environl>(Environl.class);
        List<Environl> userList = util.importExcel(file.getInputStream());
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
    public String importUser(List<Environl> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
        for (Environl user : userList) {
            try {
                environlService.insertEnvironl(user);
                successNum++;
                successMsg.append("<br/>" + successNum + "环境 " + user.getId() + " 导入成功");
            } catch (Exception e) {
                String msg =user.getId() +" 导入失败";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }
    /**
     * 新增Environl信息
     */
    @RequiresPermissions("iot:environlinfo:add")
    @Log(title = "Environl信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Environl environl)
    {
        return toAjax(environlService.insertEnvironl(environl));
    }
    /**
     * 修改Environl信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long hid, ModelMap mmap, Ioterminal ioterminal, User user)
    {
        Environl environl = environlService.selectByid(hid);
        mmap.put("environl", environl);
        mmap.put("ioterminal", environlService.selectimei(ioterminal));
        mmap.put("user", environlService.selectuser(user));
        return prefix + "/edit";
    }
    /**
     * 设备状态修改
     */
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:environlinfo:edit")
    @PostMapping("/changestatus")
    @ResponseBody
    public AjaxResult changeDelflag(Environl environl)
    {
        return toAjax(environlService.changeDelflag(environl));
    }
    /**
     * 修改保存Environl信息
     */
    @RequiresPermissions("iot:environlinfo:edit")
    @Log(title = "Environl信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Environl environl)
    {
        return toAjax(environlService.updateEnvironl(environl));
    }
    /**
     * 删除Environl信息
     */
    @RequiresPermissions("iot:environlinfo:remove")
    @Log(title = "删除Environl信息", businessType = BusinessType.DELETE)

    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //System.out.println("*******"+hid);
        return toAjax(environlService.deleteEnvironlByids(ids));
    }
    @GetMapping("/selectIotTree")
    public String selectOrganizationTree()
    {
        return prefix + "/listIotTree";
    }

    @PostMapping("/listIot")
    @ResponseBody
    public TableDataInfo listProBroad(Torrent torrent)
    {
        startPage() ;
        List<Torrent> list = environlService.queryList(torrent);
        return getDataTable(list);
    }

}