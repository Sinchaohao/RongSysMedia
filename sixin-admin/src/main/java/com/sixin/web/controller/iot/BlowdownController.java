package com.sixin.web.controller.iot;

import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Blowdown;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.IBlowdownService;
import com.sixin.iot.service.IMeteorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("/iot/blowdown")
public class BlowdownController extends BaseController {
    private String prefix = "iot/blowdown";

    @Autowired
    private IBlowdownService IBlowdownService;

    @Autowired
    private IMeteorService IMeteorService;
    @RequiresPermissions("iot:blowdowninfo:view")
    @GetMapping()
    public String Blowdown(){
        return prefix+"/blowdown";
    }

    /**
     * Blowdawn列表
     * @param blowdown
     * @return
     */
    @RequiresPermissions("iot:blowdowninfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Blowdown blowdown){
        startPage();
        List<Blowdown> list = IBlowdownService.selectBlowdownList(blowdown);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(ModelMap mmap){

        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        return prefix+"/add";
    }
    /**
     * 导出Blowdown列表
     */

    /**
     * 导出终端运转列表
     */
    @Log(title = "Blowdown", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:blowdowninfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportBlowdownByIds(@RequestParam("sjids") List<String> sfids)
    {
        List<Blowdown> list = IBlowdownService.selectBlowdownByids(sfids);
        ExcelUtil<Blowdown> util = new ExcelUtil<Blowdown>(Blowdown.class);
        return util.exportExcel(list, "Blowdown");
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
        List<Torrent> list = IBlowdownService.queryList(torrent);
        return getDataTable(list);
    }
    /**
     * 新增Blowdawn信息
     */
    @RequiresPermissions("iot:blowdowninfo:add")
    @Log(title = "Blowdawn信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Blowdown blowdown)
    {
        return toAjax(IBlowdownService.insertBlowdown(blowdown));
    }
    /**
     * 修改Blowdawn信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap,Iotype iotype)
    {
        Blowdown blowdown = IBlowdownService.selectByid(id);
        mmap.put("blowdown", blowdown);
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("iotypes", IMeteorService.selectList(iotype));
        return prefix + "/edit";
    }
    /**
     * 修改保存Blowdawn信息
     */
    @RequiresPermissions("iot:blowdowninfo:edit")
    @Log(title = "Blowdawn信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Blowdown blowdown)
    {
        return toAjax(IBlowdownService.updateBlowdown(blowdown));
    }
    /**
     * 删除Blowdawn信息
     */
    @RequiresPermissions("iot:blowdowninfo:remove")
    @Log(title = "删除Blowdawn信息", businessType = BusinessType.DELETE)

    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(IBlowdownService.deleteBlowdownByids(ids));
    }



    @Log(title = "气象管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:blowdown:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Blowdown blowdown)
    {
        return toAjax(IBlowdownService.changeStatus(blowdown));
    }
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Blowdown> util = new ExcelUtil<Blowdown>(Blowdown.class);
        List<Blowdown> blowdownList = util.importExcel(file.getInputStream());
        String message = importBlowdown(blowdownList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导出
     */
    @Log(title = "终端导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:metoerinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Blowdown blowdown)
    {
        List<Blowdown> list = IBlowdownService.selectBlowdownList(blowdown);
        ExcelUtil<Blowdown> util = new ExcelUtil<Blowdown>(Blowdown.class);
        return util.exportExcel(list, "气象监测数据");
    }


    /**
     * 导入用户数据
     *
     * @param BlowdownList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importBlowdown(List<Blowdown> blowdownList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(blowdownList) || blowdownList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得Blowdownlist，再通过遍历Blowdownlist去将每一行数据插入数据库中*/
        for (Blowdown blowdown : blowdownList) {
            try {
                IBlowdownService.insertBlowdown(blowdown);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + blowdown.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = blowdown.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }


}