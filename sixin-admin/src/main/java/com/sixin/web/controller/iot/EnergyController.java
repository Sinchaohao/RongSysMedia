package com.sixin.web.controller.iot;

import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Energy;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.IEnergyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/iot/energy")
public class EnergyController extends BaseController {

    private String prefix = "iot/energy";

    @Autowired
    private IEnergyService energyService;

    @RequiresPermissions("iot:energyinfo:view")
    @GetMapping()
    public String Energy(){
        return prefix+"/energy";
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Energy> util = new ExcelUtil<Energy>(Energy.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 导出终端运转列表
     */

    @Log(title = "Energy", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Energy energy)
    {
        List<Energy> list = energyService.selectEnergyList(energy);
        ExcelUtil<Energy> util = new ExcelUtil<Energy>(Energy.class);
        return util.exportExcel(list, "energy");
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
        List<Torrent> list = energyService.queryList(torrent);
        return getDataTable(list);
    }
    /**
     * Energy列表
     * @param energy
     * @return
     */
    //@RequiresPermissions("iot:energyinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Energy energy){
        startPage();
        List<Energy> list = energyService.selectEnergyList(energy);
        // 反转lists
        Collections.reverse(list);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(ModelMap mmap,Torrent torrent){
        mmap.put("ioterminal", energyService.queryList(torrent));
        return prefix+"/add";
    }

    /**
     * 新增Energy信息
     */
    @RequiresPermissions("iot:energyinfo:add")
    @Log(title = "Energy信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Energy energy)
    {
        return toAjax(energyService.insertEnergy(energy));
    }
    /**
     * 修改Energy信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap,Torrent torrent )
    {
        Energy energy = energyService.selectByid(id);
        mmap.put("energy", energy);
        mmap.put("ioterminal", energyService.queryList(torrent));
        return prefix + "/edit";
    }
    /**
     * 修改保存Energy信息
     */
    @RequiresPermissions("iot:energyinfo:edit")
    @Log(title = "Energy信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Energy energy)
    {
        return toAjax(energyService.updateEnergy(energy));
    }
    /**
     * 删除Energy信息
     */
    @RequiresPermissions("iot:energyinfo:remove")
    @Log(title = "删除Energy信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //System.out.println("*******"+ids);
        return toAjax(energyService.deleteEnergyByids(ids));
    }
    /**
     * 清空Energy信息
     */
    @RequiresPermissions("iot:energyinfo:clean")
    @Log(title = "删除Energy信息", businessType = BusinessType.DELETE)
    @PostMapping( "/clean")
    @ResponseBody
    public AjaxResult clean(){
        return toAjax(energyService.deleteAllEnergy());
    }
    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:energyinfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Energy energy)
    {
        return toAjax(energyService.changeStatus(energy));
    }

    /**
     * 批量导出
     */
    @Log(title = "Energy", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:Energyinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportEnergyByIds(@RequestParam("sjids") List<String> sfids)
    {
        List<Energy> list = new ArrayList<>();
        if(sfids.size()<=0){
            list = energyService.selectEnergyByids(null);
        }else {
            list = energyService.selectEnergyByids(sfids);
        }
        ExcelUtil<Energy> util = new ExcelUtil<Energy>(Energy.class);
        return util.exportExcel(list, "Energy");
    }


    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Energy> util = new ExcelUtil<Energy>(Energy.class);
        List<Energy> userList = util.importExcel(file.getInputStream());
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
    public String importUser(List<Energy> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
        for (Energy user : userList) {
            try {
//                if(isUpdateSupport){
//                    energyService.updateEnergy(user);
//                    successNum++;
//                    successMsg.append("<br/>" + successNum + "能源 " + user.getId() + " 导入成功");
//                }else {
//                    energyService.insertEnergy(user);
//                    successNum++;
//                    successMsg.append("<br/>" + successNum + "能源 " + user.getId() + " 导入成功");
//                }
                energyService.insertEnergy(user);
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