package com.sixin.web.controller.iot;
import com.sixin.iot.domain.Torrent;
import com.sixin.system.service.ISysDeptService;
import org.springframework.beans.BeanUtils;
import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Light;
import com.sixin.iot.domain.User;
import com.sixin.iot.service.ILightService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RequestMapping("/iot/light")
public class LightController extends BaseController {

    private String prefix = "iot/light";
    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ILightService lightService;

    @RequiresPermissions("iot:lightinfo:view")
    @GetMapping()
    public String Light(){
        return prefix+"/light";
    }


    @Log(title = "Light", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:lightinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Light light)
    {
        List<Light> list = lightService.selectLightList(light);
        ExcelUtil<Light> util = new ExcelUtil<Light>(Light.class);
        return util.exportExcel(list, "save");
    }

    /**
     * Light列表
     * @param light
     * @return
     */
    //@RequiresPermissions("iot:lightinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Light light){
        startPage();
        List<Light> list = lightService.selectLightList(light);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(ModelMap mmap,Ioterminal ioterminal, User user){

        mmap.put("user",lightService.selectUserList(user));
        return prefix+"/add";
    }

    /**
     * 新增Light信息
     */
    @RequiresPermissions("iot:lightinfo:add")
    @Log(title = "Light信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Light light)
    {
        // Ioterminal ioterminal = lightService.selectIoterminalBycid(cid);
        //  ioterminal.setCtype(light.getCtype());
        // BeanUtils.copyProperties(ioterminal, light);
        // lightService.insertLight1(ioterminal);
        return toAjax(lightService.insertLight(light));
    }
    /**
     * 修改Light信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap,Ioterminal ioterminal, User user)
    {
        Light light = lightService.selectBynid(id);
        mmap.put("light", light);

        mmap.put("user",lightService.selectUserList(user));
        return prefix + "/edit";
    }
    /**
     * 修改保存Light信息
     */
    @RequiresPermissions("iot:lightinfo:edit")
    @Log(title = "Light信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Light light)
    {
        return toAjax(lightService.updateLight(light));
    }
    /**
     * 删除Light信息
     */
    @RequiresPermissions("iot:lightinfo:remove")
    @Log(title = "删除Light信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        System.out.println("*******"+ids);
        return toAjax(lightService.deleteLightByids(ids));
    }

    /**
     * 设备状态修改
     */
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:lightinfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Light status)
    {

        return toAjax(lightService.updateLight(status));
    }


    /**
     * 批量导出
     */
    @Log(title = "Light", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:lightinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportLightByIds(@RequestParam("sjids") List<String> sfids)
    {
        List<Light> list = lightService.selectLightByids(sfids);
        ExcelUtil<Light> util = new ExcelUtil<Light>(Light.class);
        return util.exportExcel(list, "Light");
    }


    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Light> util = new ExcelUtil<Light>(Light.class);
        List<Light> userList = util.importExcel(file.getInputStream());
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
    public String importUser(List<Light> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
        for (Light user : userList) {
            try {
                lightService.insertLight(user);
                successNum++;
                successMsg.append("<br/>" +  "路灯 " + user.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = user.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Light> util = new ExcelUtil<Light>(Light.class);
        return util.importTemplateExcel("用户数据");
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
        List<Ioterminal> list =  lightService.selectIoterminalList(torrent);
        return getDataTable(list);
    }

}