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
import com.sixin.iot.service.IMeteorService;
import com.sixin.iot.service.IPumpService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/iot/fire")
public class FireController extends BaseController {

    private String prefix = "iot/fire";

    @Autowired
    private IFireService IFireService;
    @Autowired
    private com.sixin.iot.service.IMeteorService IMeteorService;

    @RequiresPermissions("iot:fireinfo:view")
    @GetMapping()
    public String Fire(){
        return prefix+"/fire";
    }

    /**
     * Fire列表
     * @param fire
     * @return
     */
    @RequiresPermissions("iot:fireinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Fire fire){
        startPage();
        List<Fire> list = IFireService.selectFireList(fire);
        return getDataTable(list);
    }

    /**
     * 新增
     */
    @GetMapping("/add")
    public String add(ModelMap mmap, Ioterminal ioterminal)
    {   mmap.put("ioterminal",IFireService.queryList(ioterminal));
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        return prefix+"/add";
    }

    /**
     * 新增保存
     */
    @RequiresPermissions("iot:fireinfo:add")
    @Log(title = "新增Fire信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Fire fire)
    {
        return toAjax(IFireService.insertFire(fire));
    }
    /**
     * 修改Fire信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap,Iotype iotype)
    {
        Fire fire = IFireService.selectByid(id);
        mmap.put("fire", fire);
        mmap.put("iotypes", IMeteorService.selectList(iotype));
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        return prefix + "/edit";
    }
    /**
     * 修改保存Fire信息
     */
    @RequiresPermissions("iot:fireinfo:edit")
    @Log(title = "修改保存Fire信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Fire fire)
    {
        return toAjax(IFireService.updateFire(fire));
    }
    /**
     * 删除Fire信息
     */
    @RequiresPermissions("iot:fireinfo:remove")
    @Log(title = "删除Fire信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //System.out.println("*******"+id);
        return toAjax(IFireService.deleteFireByids(ids));
    }

    /**
     * 导出
     */
    @Log(title = "火灾数据导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:fireinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Fire fire)
    {
        List<Fire> list = IFireService.selectFireList(fire);
        ExcelUtil<Fire> util = new ExcelUtil<Fire>(Fire.class);
        return util.exportExcel(list, "火灾监测数据");
    }


    /** @author qwerty
     * @description 导出√中的数据
     *
     * @param sfids
     * @return
     */
    @Log(title = "Fire", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:fireinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportFireByIds(@RequestParam("sjids") List<String> sfids) {
        List<Fire> list = IFireService.selectFireListByids(sfids);
        ExcelUtil<Fire> util = new ExcelUtil<Fire>(Fire.class);
        return util.exportExcel(list, "Fire");
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
        List<Torrent> list = IFireService.queryList(torrent);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:fireinfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Fire fire)
    {
        return toAjax(IFireService.changeStatus(fire));
    }
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Fire> util = new ExcelUtil<Fire>(Fire.class);
        List<Fire> fireList = util.importExcel(file.getInputStream());
        String message = importFire(fireList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入用户数据
     *
     * @param fireList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importFire(List<Fire> fireList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(fireList) || fireList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得firelist，再通过遍历firelist去将每一行数据插入数据库中*/
        for (Fire fire : fireList) {
            try {
                IFireService.insertFire(fire);
                successNum++;
                successMsg.append("<br/>" + successNum + "火灾 " + fire.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = fire.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }

}