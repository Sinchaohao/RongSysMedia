package com.sixin.web.controller.iot;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.*;
import com.sixin.iot.service.IFireService;
import com.sixin.iot.service.IFloodService;
import com.sixin.iot.service.IMeteorService;
import com.sixin.village.domain.VillagerInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/iot/flood")
public class FloodController extends BaseController {

    private String prefix = "/iot/flood";

    @Autowired
    private IFloodService floodService;
    @Autowired
    private com.sixin.iot.service.IMeteorService IMeteorService;

    @RequiresPermissions("iot:floodinfo:view")
    @GetMapping()
    public String Flood(){
        return prefix+"/flood";
    }

    /**
     * Flood列表
     * @param flood
     * @return
     */
    @RequiresPermissions("iot:floodinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Flood flood){
        startPage();
        List<Flood> list = floodService.selectFloodList(flood);
        return getDataTable(list);
    }
    /**
     * 新增
     */
    @GetMapping("/add")
    public String add(ModelMap mmap, Ioterminal ioterminal){
        mmap.put("ioterminal",floodService.queryList(ioterminal));
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        return prefix+"/add";
    }

    /**
     * 新增保存
     */
    @RequiresPermissions("iot:floodinfo:add")
    @Log(title = "Flood信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Flood flood)
    {
        return toAjax(floodService.insertFlood(flood));
    }


    /**
     * 修改Flood信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap,Iotype iotype)
    {
        Flood flood = floodService.selectByid(id);
        mmap.put("flood",flood);
        mmap.put("iotypes", IMeteorService.selectList(iotype));
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        //mmap.put("flood1", floodService.selectFloodList2(flood));
        return prefix + "/edit";
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
        List<Torrent> list = floodService.queryList(torrent);
        return getDataTable(list);
    }
    /**
     * 修改保存Flood信息
     */
    @RequiresPermissions("iot:floodinfo:edit")
    @Log(title = "Flood信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Flood flood)
    {
        return toAjax(floodService.updateFlood(flood));
    }
    /**
     * 删除Flood信息
     */
    @RequiresPermissions("iot:floodinfo:remove")
    @Log(title = "删除Flood信息", businessType = BusinessType.DELETE)

    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //System.out.println("*******"+id);
        return toAjax(floodService.deleteFloodByids(ids));
    }

    /**
     * 数据状态修改
     */
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:floodinfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Flood flood)
    {
        return toAjax(floodService.changeStatus(flood));
    }
    /**
     * 导出
     */
    @Log(title = "水灾数据导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:floodinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Flood flood)
    {
        List<Flood> list = floodService.selectFloodList(flood);
        ExcelUtil<Flood> util = new ExcelUtil<Flood>(Flood.class);
        return util.exportExcel(list, "水灾监测数据");
    }

    /** @author qwerty
     * @description 导出√中的数据
     *
     * @param sfids
     * @return
     */
    @Log(title = "Flood", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:floodinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportFloodByIds(@RequestParam("sjids") List<String> sfids)
    {
        List<Flood> list = floodService.selectFloodByids(sfids);
        ExcelUtil<Flood> util = new ExcelUtil<Flood>(Flood.class);
        return util.exportExcel(list, "Flood");
    }
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Flood> util = new ExcelUtil<Flood>(Flood.class);
        List<Flood> floodList = util.importExcel(file.getInputStream());
        String message = importFlood(floodList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入用户数据
     *
     * @param floodList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importFlood(List<Flood> floodList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(floodList) || floodList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得floodlist，再通过遍历floodlist去将每一行数据插入数据库中*/
        for (Flood flood : floodList) {
            try {
                floodService.insertFlood(flood);
                successNum++;
                successMsg.append("<br/>" + successNum + "山洪 " + flood.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = flood.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }
}