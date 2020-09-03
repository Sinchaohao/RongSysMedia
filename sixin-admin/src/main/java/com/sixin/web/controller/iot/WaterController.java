package com.sixin.web.controller.iot;

import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.Water;
import com.sixin.iot.service.IWaterService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/iot/water")
public class WaterController extends BaseController {

    private String prefix = "iot/water";

    @Autowired
    private IWaterService WaterService;

    @RequiresPermissions("iot:waterinfo:view")
    @GetMapping()
    public String Water(){
        return prefix+"/water";
    }

    /**
     * Water列表
     * @param water
     * @return
     */
    @RequiresPermissions("iot:waterinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Water water){
        startPage();
        List<Water> list = WaterService.selectWaterList(water);
        return getDataTable(list);
    }
    /**
     * 导出终端运转列表
     */
    @Log(title = "Water", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:waterinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Water water)
    {
        List<Water> list = WaterService.selectWaterList(water);
        ExcelUtil<Water> util = new ExcelUtil<Water>(Water.class);
        return util.exportExcel(list, "water");
    }
    @GetMapping("/add")
    public String add(ModelMap mmap,Torrent torrent)
    {
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", WaterService.queryList(torrent));
        return prefix+"/add";
    }

    /**
     * 新增Water信息
     */
    @RequiresPermissions("iot:waterinfo:add")
    @Log(title = "Water信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Water water)
    {

        return toAjax(WaterService.insertWater(water));
    }
    /**
     * 修改Water信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap, Torrent torrent,Iotype iotype)
    {
        Water water = WaterService.selectByid(id);
        mmap.put("water", water);
        mmap.put("imeis", WaterService.queryList(torrent));
        mmap.put("iotypes", WaterService.selectList(iotype));
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);

        return prefix + "/edit";
    }
    /**
     * 修改保存Water信息
     */
    @RequiresPermissions("iot:waterinfo:edit")
    @Log(title = "Water信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Water water)
    {
        return toAjax(WaterService.updateWater(water));
    }
    /**
     * 删除Water信息
     */
    @RequiresPermissions("iot:waterinfo:remove")
    @Log(title = "删除Water信息", businessType = BusinessType.DELETE)

    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //System.out.println("*******"+wid);
        return toAjax(WaterService.deleteWaterByids(ids));
    }
    /** @author qwerty
     * @description 导出√中的数据
     *
     * @param sfids
     * @return
     */
    @Log(title = "Water", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:waterinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportWaterByIds(@RequestParam("sjids") List<String> sfids) {
        List<Water> list = WaterService.selectWaterByids(sfids);
        ExcelUtil<Water> util = new ExcelUtil<Water>(Water.class);
        return util.exportExcel(list, "Water");
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:waterinfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeDelflag(Water Water)
    {
        return toAjax(WaterService.changeDelflag(Water));
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
        List<Torrent> list = WaterService.queryList(torrent);
        return getDataTable(list);
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Water> util = new ExcelUtil<Water>(Water.class);
        List<Water> waterList = util.importExcel(file.getInputStream());
        String message = importWater(waterList, updateSupport);
        return AjaxResult.success(message);
    }
    /**
     * 导入用户数据
     *
     * @param WaterList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importWater(List<Water> waterList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(waterList) || waterList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得Waterlist，再通过遍历Waterlist去将每一行数据插入数据库中*/
        for (Water water : waterList) {
            try {
                WaterService.insertWater(water);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + water.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = water.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }
}
