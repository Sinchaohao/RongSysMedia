package com.sixin.web.controller.iot;

import com.sixin.broad.service.IAreaService;
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
import com.sixin.iot.domain.Sandbox;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.ISandboxService;
import com.sixin.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/iot/sandbox")
public class SandboxController extends BaseController {

    private String prefix = "iot/sandbox";

    @Autowired
    private ISandboxService ISandboxService;


    @RequiresPermissions("iot:sandboxinfo:view")
    @GetMapping()
    public String Sandbox() {
        return prefix + "/sandbox";
    }

    /**
     * Sandbox列表
     *
     * @param Sandbox
     * @return
     */
    @RequiresPermissions("iot:sandboxinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Sandbox sandbox) {
        startPage();
        List<Sandbox> list = ISandboxService.selectSandboxList(sandbox);
        return getDataTable(list);
    }


    @GetMapping("/add")
    public String add(ModelMap mmap, Torrent torrent){
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", ISandboxService.queryList(torrent));

        return prefix+"/add";
    }

    /**
     * 新增 Sandbox信息
     */
    @RequiresPermissions("iot:sandboxinfo:add")
    @Log(title = " 新增Sandbox信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Sandbox sandbox) {
        return toAjax(ISandboxService.insertSandbox(sandbox));
    }

    /**
     * 修改 Sandbox信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap, Torrent torrent, Iotype iotype) {
        Sandbox sandbox = ISandboxService.selectByid(id);
        mmap.put("sandbox", sandbox);
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", ISandboxService.queryList(torrent));
        mmap.put("iotypes", ISandboxService.selectList(iotype));
        return prefix + "/edit";
    }

    /**
     * 导出
     */
    @Log(title = "Sandbox导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:sandboxinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Sandbox sandbox)
    {
        List<Sandbox> list = ISandboxService.selectSandboxList(sandbox);
        ExcelUtil<Sandbox> util = new ExcelUtil<Sandbox>(Sandbox.class);
        return util.exportExcel(list, "沙盘数据");
    }

    /**
     * 选择导出终端运转列表
     */
    @Log(title = "Sandbox", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:sandboxinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult export(@RequestParam("sjids") List<String> sfids)
    {
        List<Sandbox> list = ISandboxService.selectSandboxByids(sfids);
        ExcelUtil<Sandbox> util = new ExcelUtil<Sandbox>(Sandbox.class);
        return util.exportExcel(list, "Sandbox");
    }

    /**
     * 修改保存 Sandbox信息
     */
    @RequiresPermissions("iot:sandboxinfo:edit")

    @Log(title = " Sandbox信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Sandbox sandbox) {
        return toAjax(ISandboxService.updateSandbox(sandbox));
    }

    /**
     * 删除 Sandbox信息
     */
    @PostMapping("/remove")
    @Log(title = "删除 Sandbox信息", businessType = BusinessType.DELETE)
    @RequiresPermissions("iot:sandboxinfo:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(ISandboxService.deleteSandboxByids(ids));
    }

    /**
     * 修改设备状态
     */
    @Log(title = "Sandbox信息", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:sandbox:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Sandbox sandbox)
    {
        return toAjax(ISandboxService.changeStatus(sandbox));
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Sandbox> util = new ExcelUtil<Sandbox>(Sandbox.class);
        List<Sandbox> SandboxList = util.importExcel(file.getInputStream());
        String message = importSandbox(SandboxList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入用户数据
     *
     * @param SandboxList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importSandbox(List<Sandbox> SandboxList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(SandboxList) || SandboxList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得Sandboxlist，再通过遍历Sandboxlist去将每一行数据插入数据库中*/
        for (Sandbox sandbox : SandboxList) {
            try {
                ISandboxService.insertSandbox(sandbox);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + sandbox.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = sandbox.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
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
        List<Torrent> list = ISandboxService.queryList(torrent);
        return getDataTable(list);
    }

}