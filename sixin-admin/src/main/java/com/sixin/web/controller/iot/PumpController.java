package com.sixin.web.controller.iot;

import com.sixin.broad.service.IAreaService;
import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Pump;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.IPumpService;
import com.sixin.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/iot/pump")
public class PumpController extends BaseController {

    private String prefix = "iot/pump";

    @Autowired
    private IPumpService IPumpService;



    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IAreaService areaService;

    @RequiresPermissions("iot:pumpinfo:view")
    @GetMapping()
    public String Pump(){
        return prefix+"/pump";
    }

    /**
     * Pump列表
     * @param pump
     * @return
     */
    @RequiresPermissions("iot:pumpinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Pump pump){
        startPage();
        List<Pump> list = IPumpService.selectPumpList(pump);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(ModelMap mmap, Torrent torrent){
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", IPumpService.queryList(torrent));
        return prefix+"/add";
    }

    /**
     * 新增Pump信息
     */
    @RequiresPermissions("iot:pumpinfo:add")
    @Log(title = "新增Pump信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Pump pump)
    {
        return toAjax(IPumpService.insertPump(pump));
    }
    /**
     * 修改Pump信息
     */

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap, Torrent torrent,Iotype iotype)
    {
        Pump pump = IPumpService.selectByid(id);
        mmap.put("pump", pump);
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", IPumpService.queryList(torrent));
        mmap.put("iotypes", IPumpService.selectList(iotype));
        return prefix + "/edit";
    }

    /**
     * 导出
     */
    @Log(title = "水泵数据导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:pumpinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Pump pump)
    {
        List<Pump> list = IPumpService.selectPumpList(pump);
        ExcelUtil<Pump> util = new ExcelUtil<Pump>(Pump.class);
        return util.exportExcel(list, "水泵监测数据");
    }

    /** @author qwerty
     * @description 导出数据
     *
     * @param sfids
     * @return
     */
    @Log(title = "Pump", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:pumpinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult export(@RequestParam("sjids") List<String> sfids)
    {
        List<Pump> list = IPumpService.selectPumpByids(sfids);
        ExcelUtil<Pump> util = new ExcelUtil<Pump>(Pump.class);
        return util.exportExcel(list, "Pump");
    }


    /**
     * 修改保存Pump信息
     */
    @RequiresPermissions("iot:pumpinfo:edit")
    @Log(title = "修改保存Pump信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Pump pump)
    {
        return toAjax(IPumpService.updatePump(pump));
    }
    /**
     * 删除Pump信息
     */
    @PostMapping( "/remove")
    @Log(title = "删除Pump信息", businessType = BusinessType.DELETE)
    @RequiresPermissions("iot:pumpinfo:remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(IPumpService.deletePumpByids(ids));
    }


    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:pump:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Pump pump)
    {
        return toAjax(IPumpService.changeStatus(pump));
    }




    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Pump> util = new ExcelUtil<Pump>(Pump.class);
        List<Pump> pumpList = util.importExcel(file.getInputStream());
        String message = importPump(pumpList, updateSupport);
        return AjaxResult.success(message);
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
        List<Torrent> list = IPumpService.queryList(torrent);
        return getDataTable(list);
    }

    /**
     * 导入用户数据
     *
     * @param pumpList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importPump(List<Pump> pumpList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(pumpList) || pumpList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得pumplist，再通过遍历pumplist去将每一行数据插入数据库中*/
        for (Pump pump : pumpList) {
            try {
                IPumpService.insertPump(pump);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + pump.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = pump.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }

}