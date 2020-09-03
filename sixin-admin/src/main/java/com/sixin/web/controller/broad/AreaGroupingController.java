package com.sixin.web.controller.broad;

import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.ExcelUtil;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.broad.domain.AreaGrouping;
import com.sixin.broad.domain.BroadMessage;
import com.sixin.broad.service.IAreaGroupingService;
import com.sixin.broad.service.IMessageService;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.UserOperateModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/8/2.
 * @author cx
 * 分组管理 控制层
 */
@Controller
@RequestMapping("/broad/areaGrouping")
public class AreaGroupingController extends BaseController{

    private String prefix = "broad/areaGrouping";

    @Autowired
    private IAreaGroupingService iAreaGroupingService;

    @Autowired
    private ISysUserService iSysUserService;

    @RequiresPermissions("broad:areaGrouping:view")
    @GetMapping()
    public String areaGrouping()
    {
        return prefix + "/areaGrouping";
    }

    @Autowired
    private IMessageService messageService;

    @PostMapping("/list")
    @Log(title = "分组管理列表")
    @RequiresPermissions("broad:areaGrouping:list")
    @ResponseBody
    public TableDataInfo list(AreaGrouping areaGrouping)
    {
        SysUser currentUser = ShiroUtils.getSysUser();  //从session中获取当前登陆用户的userid
        Long userid = currentUser.getUserId();
        //int returnid = new Long(userid).intValue();
        int roleid = iSysUserService.selectRoleid(userid); //通过所获取的userid去广播用户表中查询用户所属区域的Roleid
        if(roleid == 1)
        {
            startPage();
            List<AreaGrouping> list = iAreaGroupingService.listAreaGrouping(areaGrouping);
            return getDataTable(list);
        }else {
            startPage();
            areaGrouping.setUid(userid);
            List<AreaGrouping> list = iAreaGroupingService.listAreaGrouping(areaGrouping);
            return getDataTable(list);
        }
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData() {
        List<Map<String, Object>> tree = messageService.selectMessageList((new BroadMessage()));
        return tree;
    }

    /**
     * @author cx
     * @Description 此删除方式可以批量删除，可以单独删除
     * @param ids
     * 注意 这个地方的参数一定要写ids 和前端绑定的固定格式
     */
    @PostMapping("/remove")
    @Log(title = "分组管理删除",businessType = BusinessType.DELETE)
    @RequiresPermissions("broad:areagrouping:remove")
    @ResponseBody
    public AjaxResult removeAreagrouping(String ids)
    {
        return toAjax(iAreaGroupingService.deleteAreaGroupingByIds(ids));
    }

    @GetMapping("/add")
    public String addareaGrouping()
    {
        return prefix + "/add";
    }

    @Log(title = "新增分组管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AreaGrouping areaGrouping)
    {
        return toAjax(iAreaGroupingService.insertAreaGrouping(areaGrouping));
    }

    /**
     * 修改分组管理记录
     */
    @GetMapping("/edit/{aid}")
    public String edit(@PathVariable("aid") String aid, ModelMap mmap)
    {
        AreaGrouping areaGrouping = iAreaGroupingService.selectAreaGroupingByAid(aid);
        mmap.put("areaGrouping", areaGrouping);
        return prefix + "/edit";
    }

    /**
     * 修改保存分组管理记录
     */
    @Log(title = "分组管理记录保存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AreaGrouping areaGrouping)
    {
        return toAjax(iAreaGroupingService.updateAreaGrouping(areaGrouping));
    }

    /**
     * 加载特殊分组及终端列表树
     */
    @GetMapping("/treeData1")
    @ResponseBody
    public List<Map<String, Object>> treeData1() {
        List<Map<String, Object>> tree = messageService.selectMessageList1((new BroadMessage()));
        return tree;
    }

    /**
     * 导出
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AreaGrouping areaGrouping)
    {
        List<AreaGrouping> list = iAreaGroupingService.listAreaGrouping(areaGrouping);
        ExcelUtil<AreaGrouping> util = new ExcelUtil<AreaGrouping>(AreaGrouping.class);
        return util.exportExcel(list, "分组管理");
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<AreaGrouping> util = new ExcelUtil<AreaGrouping>(AreaGrouping.class);
        return util.importTemplateExcel("分组管理");
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<AreaGrouping> util = new ExcelUtil<AreaGrouping>(AreaGrouping.class);
        List<AreaGrouping> list = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        return toAjax(iAreaGroupingService.batchInsertAreaGrouping(list));
    }
}