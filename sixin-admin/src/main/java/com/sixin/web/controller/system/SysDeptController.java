package com.sixin.web.controller.system;
/**
 * @function:部门信息
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-01-20 18：09
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sixin.common.annotation.Log;
import com.sixin.common.constant.UserConstants;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.base.Ztree;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysDept;
import com.sixin.system.domain.SysRole;
import com.sixin.system.service.ISysDeptService;

@Controller
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController
{
    private String prefix = "system/dept";

    @Autowired
    private ISysDeptService deptService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/dept";
    }

    @RequiresPermissions("system:dept:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SysDept> list(SysDept dept)
    {
        List<SysDept> deptList = deptService.selectDeptList(dept);
        return deptList;
    }

    /**
     * 新增部门
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "地域管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDept dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return error("新增地域'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        SysDept dept = deptService.selectDeptById(deptId);
        if (StringUtils.isNotNull(dept) && 100L == deptId)
        {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "地域管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDept dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return error("修改地域'" + dept.getDeptName() + "'失败，地域名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return error("修改地域'" + dept.getDeptName() + "'失败，上级地域不能是自己");
        }
        dept.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除
     */
    @Log(title = "地域管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if (deptService.selectDeptCount(deptId) > 0)
        {
            return AjaxResult.warn("存在下级地域,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId))
        {
            return AjaxResult.warn("地域表关链用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }

    /**
     * 校验地域名称
     */
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(SysDept dept)
    {
        return deptService.checkDeptNameUnique(dept);
    }

    /**
     * 选择地域树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/tree";
    }

    /**
     * 加载地域列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = deptService.selectDeptTree(new SysDept());
        return ztrees;
    }

    /**
     * 加载角色地域（数据权限）列表树
     */
    @GetMapping("/roleDeptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData(SysRole role)
    {
        List<Ztree> ztrees = deptService.roleDeptTreeData(role);
        return ztrees;
    }
}
