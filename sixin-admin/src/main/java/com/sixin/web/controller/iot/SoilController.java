package com.sixin.web.controller.iot;

import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Soil;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.ISoilService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/iot/soil")
public class SoilController extends BaseController {

    private String prefix = "iot/soil";

    @Autowired
    private ISoilService SoilService;

    @RequiresPermissions("iot:soilinfo:view")
    @GetMapping()
    public String Soil(){
        return prefix+"/soil";
    }

    /**
     * Soil列表
     * @param soil
     * @return
     */
    @RequiresPermissions("iot:soilinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Soil soil){
        startPage();
        List<Soil> list = SoilService.selectSoilList(soil);
        return getDataTable(list);
    }
    /**
     * 导出终端运转列表
     */
    @Log(title = "Soil", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:soilinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Soil soil)
    {
        List<Soil> list = SoilService.selectSoilList(soil);
        ExcelUtil<Soil> util = new ExcelUtil<Soil>(Soil.class);
        return util.exportExcel(list, "soil");
    }
    @GetMapping("/add")
    public String add(ModelMap mmap, Torrent torrent)
    {
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", SoilService.queryList(torrent));
        return prefix+"/add";
    }

    /**
     * 新增Soil信息
     */
    @RequiresPermissions("iot:soilinfo:add")
    @Log(title = "Soil信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Soil soil)
    {

        return toAjax(SoilService.insertSoil(soil));
    }
    /**
     * 修改Soil信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap,Torrent torrent,Iotype iotype)
    {
        Soil soil = SoilService.selectByid(id);
        mmap.put("soil", soil);
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", SoilService.queryList(torrent));
        mmap.put("iotypes", SoilService.selectList(iotype));
        return prefix + "/edit";
    }
    /**
     * 修改保存Soil信息
     */
    @RequiresPermissions("iot:soilinfo:edit")
    @Log(title = "Soil信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Soil soil)
    {
        return toAjax(SoilService.updateSoil(soil));
    }
    /**
     * 删除Soil信息
     */
    @RequiresPermissions("iot:soilinfo:remove")
    @Log(title = "删除Soil信息", businessType = BusinessType.DELETE)

    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //System.out.println("*******"+wid);
        return toAjax(SoilService.deleteSoilByids(ids));
    }
    /** @author qwerty
     * @description 导出√中的数据
     *
     * @param sfids
     * @return
     */
    @Log(title = "Soil", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:soilinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportSoilByIds(@RequestParam("sjids") List<String> sfids) {
        List<Soil> list = SoilService.selectSoilByids(sfids);
        ExcelUtil<Soil> util = new ExcelUtil<Soil>(Soil.class);
        return util.exportExcel(list, "Soil");
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:soilinfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeDelflag(Soil soil)
    {
        return toAjax(SoilService.changeStatus(soil));
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
        List<Torrent> list = SoilService.queryList(torrent);
        return getDataTable(list);
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Soil> util = new ExcelUtil<Soil>(Soil.class);
        List<Soil> soilList = util.importExcel(file.getInputStream());
        String message = importSoil(soilList, updateSupport);
        return AjaxResult.success(message);
    }
    /**
     * 导入用户数据
     *
     * @param SoilList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importSoil(List<Soil> soilList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(soilList) || soilList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得Soillist，再通过遍历Soillist去将每一行数据插入数据库中*/
        for (Soil soil : soilList) {
            try {
                SoilService.insertSoil(soil);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + soil.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = soil.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }
}