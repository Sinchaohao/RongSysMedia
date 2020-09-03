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
import com.sixin.iot.domain.Meteor;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.IMeteorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/iot/meteor")
public class MeteorController extends BaseController {

    private String prefix = "iot/meteor";
    @Autowired
    private IMeteorService IMeteorService;

    @RequiresPermissions("iot:meteorinfo:view")
    @GetMapping()
    public String Meteor(){
        return prefix+"/meteor";
    }

    /**
     * Meteor列表
     * @param meteor
     * @return
     */
    @RequiresPermissions("iot:meteorinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Meteor meteor){
        startPage();
        List<Meteor> list = IMeteorService.selectMeteorList(meteor);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(ModelMap mmap, Torrent torrent){

        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", IMeteorService.queryList(torrent));
        return prefix+"/add";
    }
    /**
     * 导出Meteor列表
     */

    /**
     * 导出终端运转列表
     */
    @Log(title = "Meteor", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:meteorinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportMeteorByIds(@RequestParam("sjids") List<String> sfids)
    {
        List<Meteor> list = IMeteorService.selectMeteorByids(sfids);
        ExcelUtil<Meteor> util = new ExcelUtil<Meteor>(Meteor.class);
        return util.exportExcel(list, "Meteor");
    }

    /**
     * 新增Meteor信息
     */
    @RequiresPermissions("iot:meteorinfo:add")
    @Log(title = "Meteor信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Meteor meteor)
    {
        return toAjax(IMeteorService.insertMeteor(meteor));
    }
    /**
     * 修改Meteor信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap, Torrent torrent,Iotype iotype)
    {
        Meteor meteor = IMeteorService.selectByid(id);
        mmap.put("meteor", meteor);
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", IMeteorService.queryList(torrent));
        mmap.put("iotypes", IMeteorService.selectList(iotype));
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
        List<Torrent> list = IMeteorService.queryList(torrent);
        return getDataTable(list);
    }

    /**
     * 修改保存Meteor信息
     */
    @RequiresPermissions("iot:meteorinfo:edit")
    @Log(title = "Meteor信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Meteor meteor)
    {
        return toAjax(IMeteorService.updateMeteor(meteor));
    }
    /**
     * 删除Meteor信息
     */
    @RequiresPermissions("iot:meteorinfo:remove")
    @Log(title = "删除Meteor信息", businessType = BusinessType.DELETE)

    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(IMeteorService.deleteMeteorByids(ids));
    }



    @Log(title = "气象管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:meteor:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Meteor meteor)
    {
        return toAjax(IMeteorService.changeStatus(meteor));
    }
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Meteor> util = new ExcelUtil<Meteor>(Meteor.class);
        List<Meteor> meteorList = util.importExcel(file.getInputStream());
        String message = importMeteor(meteorList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导出
     */
    @Log(title = "终端导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:metoerinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Meteor meteor)
    {
        List<Meteor> list = IMeteorService.selectMeteorList(meteor);
        ExcelUtil<Meteor> util = new ExcelUtil<Meteor>(Meteor.class);
        return util.exportExcel(list, "气象监测数据");
    }


    /**
     * 导入用户数据
     *
     * @param MeteorList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importMeteor(List<Meteor> meteorList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(meteorList) || meteorList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得Meteorlist，再通过遍历Meteorlist去将每一行数据插入数据库中*/
        for (Meteor meteor : meteorList) {
            try {
                IMeteorService.insertMeteor(meteor);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + meteor.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = meteor.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }

}